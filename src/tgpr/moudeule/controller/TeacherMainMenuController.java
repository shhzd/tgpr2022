package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherMainMenuView;
import tgpr.moudeule.view.View;

import java.util.Locale;
import java.util.regex.Pattern;

public class TeacherMainMenuController extends Controller {

    private int page = 0;

    public void run() {
        TeacherMainMenuView view = new TeacherMainMenuView();
        try {
            String res;
            do {
                view.displayHeader();

//                User user = MoudeuleApp.getLoggedUser();
                User user = User.getByPseudo("p");

                var courses = Course.getCoursesFromTeacher(user);
                int lgPage = 12;
                int nbPages = (int)(courses.size() / (lgPage + 0.0)) + 1;

                view.displayMenu(courses, page, nbPages, lgPage);
                res = view.askForString().toUpperCase(); // les entrées en miniscule sont converties en majuscule
                if (res.length() > 1) {
                    /** we should check that the teacher is allowed to see the course **/
                    Course course = Course.getCourseByID(res);
                    if (course != null) {
                        /** to uncomment when UC are ready  **/
                        System.out.println("start new view.TeacherEditCourseController(course).run() >> " + course.prettyPrint());
//                        new view.TeacherEditCourseController(res).run();
                    } else {
                        System.out.println("il ne se passe rien");
                    }
                }
                if (res.equals("S") && (page + 1) != nbPages && nbPages > 1) {
                    this.page++;
                    this.run();
                }
                if (res.equals("P") && page > 0) {
                    this.page--;
                    this.run();
                }
                if (res.equals("A")) {
                    /** to uncomment when UC are ready  **/
                    System.out.println("start new TeacherAddCourse().run();");
//                    new TeacherAddCourse().run();
                }
            } while (!res.equals("O"));
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("logged out");
        }

        MoudeuleApp.logout();
    }
}