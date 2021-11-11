package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.view.StudentEditCourseView;
import tgpr.moudeule.view.View;

public class StudentEditCourseController extends Controller {
    @Override
    public void run() {
        var student = MoudeuleApp.getLoggedUser();
        var courses = student.getRegistratedCourses();
        var view = new StudentEditCourseView();
        try {
            View.Action res;
            do {
                view.displayHeader();
                view.displayCourses(courses);
                res = view.askForAction(courses.size());
                var m = courses.get(res.getNumber() - 1);
                switch (res.getAction()) {

                    //case sélectionner un cours

                    case 'T':
                        //student.getTestsList(m);
                        break;
                    case 'D':
                        student.deactivateCourse(m);

                        //confirmation ?

                        break;
                    case 'R':
                        //new StudentAvailableCoursesListController().run();
                        break;
                }
            } while (res.getAction() != 'Q');
        } catch (View.ActionInterruptedException e) {

        }

    }
}