package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherManageStudentRegistrationView;
import tgpr.moudeule.view.View;

public class TeacherManageStudentRegistrationController extends Controller {

    private int page = 1;
    private Course course = new Course();
    private boolean keepLooping = true;
    /** should be included in Controller() as global constant **/
    static final int NUMBER_DISPLAY_LINE = 14;

    public TeacherManageStudentRegistrationController(Course course) {
        this.course = course;
    }

    public void run() {
        var view = new TeacherManageStudentRegistrationView();
        if (MoudeuleApp.isLogged())  {
            try {
//                User user = MoudeuleApp.getLoggedUser();
                String res;
                do {
                    view.displayHeaderWithCourse(course.getCode());

                    var students = User.getByCourse(course);
                    int nbPages = (int) Math.ceil(students.size() / (NUMBER_DISPLAY_LINE + 0.0));

                    view.displaySubHeaderWithPage(page, nbPages);
                    view.displayCourseCapacity(course.currentActiveStudents(), course.getCapacity());
                    if (students.size() > 0) {
                        for (int i = 0; (i + ((page - 1) * NUMBER_DISPLAY_LINE)) < students.size() && i < (NUMBER_DISPLAY_LINE + 1); i++) {
                            User student = students.get(i + ((page - 1) * NUMBER_DISPLAY_LINE));
                            view.displayStudent(student.getFullname(), i, student.getStatus(course));
                        }
                        view.displayMenu();
                    } else {
                        view.displayEmptyListMenu();
                    }
                    view.displayNavigationMenu(page, nbPages);

                    res = view.askForString().toUpperCase(); // lowercase entries are converted to uppercase

                    if (res.equals("I")) {
                        view.pausedWarning("Cette opération n'est pas encore possible");
//                        new TeacherAddStudentController(course).run();
                    }

                    if (res.matches("[1-9]|[0][1-9]|[1][0-2]")) {
                        User student = students.get((int) Integer.parseInt(res) - 1);
                        if (student == null)
                            throw new View.ActionInterruptedException();
                        else {
                            View.Action subRes;
                            String status = student.getStatus(course);
                            /** the String status is not yet used, changes have to be made to the
                             * database before beeing able to distinguih student which have been
                             * deactivated and deleted student
                             */
                            view.displaySubMenu(student.getFullname(), status);
                            subRes = view.askForAction();
                            if (subRes.getAction() == '1') {
                                switch (status) {
                                    case "en attente":
                                        student.activateCourse(course);
                                        break;
                                    case "actif":
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
                view.pausedWarning("logged out");
                MoudeuleApp.logout();
            } catch (View.ActionInterruptedException e) {
            }
        }
        view.close();
    }
}

