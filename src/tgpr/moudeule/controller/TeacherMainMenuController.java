package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherMainMenuView;
import tgpr.moudeule.view.View;

public class TeacherMainMenuController extends Controller {

    private int page = 1;

    public void run() {
        TeacherMainMenuView view = new TeacherMainMenuView();
        try {
            String res;
            do {
                view.displayHeader();
                User user = MoudeuleApp.getLoggedUser();

                /** for testing purposes **/
//                User user = User.getByPseudo("p");

                var courses = Course.getCoursesFromTeacher(user);
                int lgPage = 14;
                int nbPages = (int)(courses.size() / (lgPage + 0.0));

                view.displayMenu(courses, page, nbPages, lgPage, user);
                res = view.askForString().toUpperCase(); // lowercase entries are converted to uppercase
                if (res.length() > 1) {
                    Course course = Course.getCourseByID(res);
                    if (course != null) {
                        /** to uncomment when UC are ready  **/
                        //new TeacherEditCourseController(course.getId()).run();
                    } else {
                        /** for testing purposes **/
                        System.out.println("il ne se passe rien");
                    }
                }
                if (res.equals("S") && (page) != nbPages && nbPages > 1) {
                    this.page++;
                }
                if (res.equals("P") && page > 1) {
                    this.page--;
                }
                if (res.equals("0")) {
                    new TeacherAddCourseController().run();
                }
            } while (!res.equals("Q"));
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("logged out");
        }
        MoudeuleApp.logout();
    }
}