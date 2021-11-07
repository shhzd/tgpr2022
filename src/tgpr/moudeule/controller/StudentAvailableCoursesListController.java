package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.view.StudentAvailableCoursesListView;
import tgpr.moudeule.view.View;

public class StudentAvailableCoursesListController extends Controller {
    @Override
    public void run() {
        var student = MoudeuleApp.getLoggedUser();
        var courses = student.getAvailableCourses();
        StudentAvailableCoursesListView view = new StudentAvailableCoursesListView();
        try {
            View.Action res;
            do {
                view.displayHeader();
                view.displayCourses(courses);
                res = view.askForAction(courses.size());
                switch (res.getAction()) {
                    case 'I':

                        break;
                    case 'D':
                        break;
                    case 'V':
                        break;
                    case 'Q':
                        break;
                }
            } while (res.getAction() != 'Q');
        } catch (View.ActionInterruptedException e) {

        }
    }
}
