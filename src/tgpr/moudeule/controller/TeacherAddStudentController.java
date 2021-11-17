package tgpr.moudeule.controller;


import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.StudentMainMenuView;

import tgpr.moudeule.view.TeacherAddStudentView;
import tgpr.moudeule.view.TeacherManageStudentRegistrationView;
import tgpr.moudeule.view.View;

public class TeacherAddStudentController extends Controller {
    private int page = 1;
    private Course course = new Course();
    private boolean keepLooping = true;
    /** should be included in Controller() **/
    static final int NUMBER_DISPLAY_LINE = 14;

    public TeacherAddStudentController(Course course) {
        this.course = course;
    }

    private void leave(String res) {
        switch (res) {
            case "R":
                new TeacherEditCourseController(course.getId()).run();
                break;
        }
    }

    public void run() {
        var view = new TeacherManageStudentRegistrationView();
        try {
            String res;
            do {
                view.displayHeaderWithCourse(course.getCode());
                User user = MoudeuleApp.getLoggedUser();

                var students = User.getByCourse(course);
                int nbPages = (int)Math.ceil(students.size() / (NUMBER_DISPLAY_LINE + 0.0));

                view.displaySubHeaderWithPage(page, nbPages);
                view.displayCourseCapacity(course.currentActiveStudents(), course.getCapacity());
                view.displayMenu(students, course, page, nbPages, NUMBER_DISPLAY_LINE);
                res = view.askForString().toUpperCase(); // lowercase entries are converted to uppercase

                if (res.equals("R")) {
                    keepLooping = false;
                    leave(res);
                }
                if (res.matches("[1-9]|[0][1-9]|[1][0-2]")) {
                    User student = students.get((int)Integer.parseInt(res) - 1);
                    if (student == null)
                        throw new View.ActionInterruptedException();
                    else {
                        View.Action subRes;
                        String status = student.getStatus(course);
                        /** the String status is not yes used, changes have to be made to the
                         * database before.
                         */
                        view.displaySubMenu(student, status);
                        subRes = view.askForAction();
                        if (subRes.getAction() == '1') {
                            switch (status) {
                                case "en attente" :
                                    student.activateCourse(course);
                                    break;
                                case "actif" :
                                    view.showWarning();
                                    if (view.askForConfirmation().getAction() == 'O') {
                                        /** using cancelWaitingList for now, but needs a function
                                         * that recursivly deleted all the test of student for that course
                                         * when quiz are implemented
                                         */
                                        student.cancelWaitingList(course);
                                    }
                                    break;
                            }
                        }
                    }
                }
                if (res.equals("S") && (page) != nbPages && nbPages > 1) {
                    this.page++;
                }
                if (res.equals("P") && page > 1) {
                    this.page--;
                }
            } while (!res.equals("Q") && keepLooping);
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("logged out");
        }
        if (keepLooping) {
            MoudeuleApp.logout();
        }
        view.close();
    }
}
