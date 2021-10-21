package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
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
                view.
            } while (res.getAction() != 'Q');
        } catch (View.ActionInterruptedException e) {

        }
    }
}
