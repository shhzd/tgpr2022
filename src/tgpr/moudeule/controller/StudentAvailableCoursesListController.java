package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.view.StudentAvailableCoursesListView;
import tgpr.moudeule.view.View;

public class StudentAvailableCoursesListController extends Controller {

    private int page = 0;

    @Override
    public void run() {
        var student = MoudeuleApp.getLoggedUser();
        var courses = student.getAvailableCourses();
        var view = new StudentAvailableCoursesListView();
        try {
            String res;
            do {
                view.displayHeader();
                int lgPage = 12;
                int nbPages = (int)(courses.size() / (lgPage + 0.0)) + 1;
                view.displayCourses(courses, page, nbPages, lgPage);
                view.displayOption(courses, page, nbPages, lgPage);

                res = view.askForString();

                if(res.length() > 1) {
                    Course course = Course.getCourseByID(res);
                } else if (res.equals("S") && (page + 1) != nbPages && nbPages > 1) {
                    this.page++;
                } else if (res.equals("P") && page > 0) {
                    this.page--;
                } else if (res.equals("R")) {
                    new StartMenuController().run();
                    /**to uncomment
                    new StudentMainMenuController.run();
                     **/
                }
            } while (!res.equals("Q"));
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("Déconnecté.e");
        }

        MoudeuleApp.logout();
    }
}
