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
                var m = courses.get(res.getNumber() - 1);
                switch (res.getAction()) {
                    case 'I':
                        student.addToWaitingList(m);
                        courses = student.getAvailableCourses();
                        break;
                    case 'D':
                        student.deleteRegistration(m);
                        courses = student.getAvailableCourses();
                        break;
                    case 'V':
                        System.out.println("Description du cours");
                        // new StudentCourseDescription(m).run();
                        break;
                }
            } while (res.getAction() != 'Q');
        } catch (View.ActionInterruptedException e) {

        }

    }
}
