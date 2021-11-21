package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherMainMenuView;
import tgpr.moudeule.view.View;

public class TeacherMainMenuController extends Controller {

    private int page = 1;
//    private boolean keepLooping = true;
//
//    private void leave(String res, Course course) {
//        switch (res) {
//            case "E":
//                new TeacherEditCourseController(course.getId()).run();
//                break;
//        }
//    }
//
//    private void leave(String res) {
//        switch (res) {
//            case "0":
//                new TeacherAddCourseController().run();
//                break;
//        }
//    }

    public void run() {
        var view = new TeacherMainMenuView();
        try {
            String res;
            do {
                view.displayHeader();
                User user = MoudeuleApp.getLoggedUser();

                var courses = Course.getCoursesFromTeacher(user);
                int lgPage = 14;
                int nbPages = (int)Math.ceil(courses.size() / (lgPage + 0.0));

                view.displaySubHeader(page, nbPages);
                view.displayMenu(courses, page, nbPages, lgPage);
                view.displayNavigationMenu(page, nbPages);

                res = view.askForString().toUpperCase(); // lowercase entries are converted to uppercase
                if (res.length() > 1) {
                    Course course = Course.getCourseByID(res);
                    if (course != null && courses.contains(course)) {
                        new TeacherEditCourseController(course.getId()).run();
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
            } while (!res.equals("Q") && MoudeuleApp.isLogged());
            MoudeuleApp.logout();
        } catch (View.ActionInterruptedException e) {
        }
        view.close();
    }

}