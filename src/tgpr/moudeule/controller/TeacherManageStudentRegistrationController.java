package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherManageStudentRegistrationView;
import tgpr.moudeule.view.View;

public class TeacherManageStudentRegistrationController extends Controller {

    private int page = 1;
    private Course course = new Course();

    public TeacherManageStudentRegistrationController(Course course) {
        this.course = course;
    }

    public void run() {
        var view = new TeacherManageStudentRegistrationView();
        try {
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
                view.displayNavigationMenuWithEsc(page, nbPages);

                res = view.askForString().toUpperCase(); // lowercase entries are converted to uppercase

                if (res.equals("I")) {
                    new TeacherAddStudentController(course).run();
                }
                try {
                    if (res.matches("[1-9]|[0][1-9]|[1][0-5]")) {
                        User student = students.get(Integer.parseInt(res) - 1);
                        if (student == null)
                            throw new View.ActionInterruptedException();
                        else {
                            View.Action subRes;
                            String status = student.getStatus(course);
                            /** the String status is not yet used, changes have to be made to the
                             * database before being able to distinguish student which have been
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
                                             * that recursively deleted all the test of student for that course
                                             * when quiz are implemented
                                             */
                                            student.cancelWaitingList(course);
                                        }
                                        break;
                                }
                            } else if (subRes.getAction() == '2' && status.equals("en attente"))
                                student.cancelWaitingList(course);
                        }
                    }
                } catch (View.ActionInterruptedException e) {
                }
                if (res.equals("S") && (page) != nbPages && nbPages > 1) {
                    this.page++;
                }
                if (res.equals("P") && page > 1) {
                    this.page--;
                }
            } while (!res.equals("Q") && MoudeuleApp.isLogged());
            MoudeuleApp.logout();
        } catch (View.ActionInterruptedException e) {
        }
        view.close();
    }
}