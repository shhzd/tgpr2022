package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.view.StudentAvailableCoursesListView;
import tgpr.moudeule.view.View;

public class StudentAvailableCoursesListController extends Controller {

    private int page = 1;

    private final static int NUMBER_DISPLAY_LINE = 12;

    @Override
    public void run() {
        var student = MoudeuleApp.getLoggedUser();
        var view = new StudentAvailableCoursesListView();
        try {
            String res;
            do {
                var courses = student.getAvailableCourses();
                int nbPages = (int)Math.ceil(courses.size() / ((double)NUMBER_DISPLAY_LINE)) ;
                view.displayHeaderWithPseudoAndPageNumber("Liste des cours disponibles", page, nbPages);
                view.displayCourses(courses, page, NUMBER_DISPLAY_LINE);
                view.displayOption(courses, page, nbPages, NUMBER_DISPLAY_LINE);

                res = view.askForString().toUpperCase();

                if(res.length() == 4) {
                    Course course = Course.getCourseByID(res);
                    if(course != null && courses.contains(course)) {
                        new StudentCourseDescriptionInscriptionController(course).run();
                    }
                    /**To refactor avoiding code repetition**/
                } else if (res.equals("S") && page < nbPages && nbPages > 1) {
                    ++page;
                } else if (res.equals("P") && page > 1) {
                    --page;
                } else if (res.equals("R")) {
                    new StudentMainMenuController().run();
                }
            } while (!res.equals("Q"));
        } catch (View.ActionInterruptedException e) {
        }
        MoudeuleApp.logout();
        new StartMenuController().run();
    }
}
