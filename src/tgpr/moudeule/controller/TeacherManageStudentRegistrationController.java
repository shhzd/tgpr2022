package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherMainMenuView;
import tgpr.moudeule.view.TeacherManageStudentRegistrationView;
import tgpr.moudeule.view.View;

public class TeacherManageStudentRegistrationController extends Controller {

    private int page = 1;
    private Course course = new Course();
    /** should be included in Controller() **/
    static final int lgPage = 14;

    public TeacherManageStudentRegistrationController(Course course) {
        this.course = course;
    }

    @Override
    public void run() {}

    public void run(Course course) {
        var view = new TeacherManageStudentRegistrationView();
        try {
            String res;
            do {
                view.displayHeaderWithCourse(course.getCode());
                User user = MoudeuleApp.getLoggedUser();

                /** for testing purposes **/
//                User user = User.getByPseudo("p");

                var students = User.getByCourse(course);
                int nbPages = (int)Math.ceil(students.size() / (lgPage + 0.0));

                view.displaySubMenuWithPage(page, nbPages);
                view.displayMenu(students, course, page, nbPages, lgPage);
                res = view.askForString().toUpperCase(); // lowercase entries are converted to uppercase



                if (res.equals("R")) {
                    /** to uncomment when ready **/
//                    new TeacherEditCourseController(course).run();
                    System.out.println("Goes back to TeacherEditCourseController(" + course.getCode() + ").run()");
                }

                if (res.matches("[1-9]|[0][1-9]|[1][0-2]")) {

                    User student = students.get((int)Integer.parseInt(res) - 1);
                    if (student == null)
                        throw new View.ActionInterruptedException();
                    else {
//                        student.changeStatus(course);
                    }
                }

                if (res.equals("S") && (page) != nbPages && nbPages > 1) {
                    this.page++;
                }
                if (res.equals("P") && page > 1) {
                    this.page--;
                }
            } while (!res.equals("Q"));
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("logged out");
        }
        MoudeuleApp.logout();
    }
}