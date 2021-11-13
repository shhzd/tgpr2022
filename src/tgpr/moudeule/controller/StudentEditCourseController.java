package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.view.StudentEditCourseView;
import tgpr.moudeule.view.View;

public class StudentEditCourseController extends Controller {
    @Override
    public void run() {
        var student = MoudeuleApp.getLoggedUser();
        var courses = student.getRegistratedCourses();
        var view = new StudentEditCourseView();
        try {
            String res;
            do {
                view.displayHeader();
                view.displayCourses(courses);
                System.out.println("");
                //could eventually redirect to StudentAvailableCoursesList if the student is not registrated anywhere (to be discuted)

                view.displayIDSelection();
                res = view.askForString().toUpperCase();
                if (res.equals("R")) {
                    new StudentMainMenuController().run();
                }
                if (res.equals("Q")) {
                    MoudeuleApp.logout();
                    new StartMenuController().run();
                }
                while (res.length() != 4) {
                    view.pausedWarning("Entrez un ID de cours valide");
                    res = view.askForString().toUpperCase();
                    if (res.equals("R")) {
                        new StudentMainMenuController().run();
                    }
                    if (res.equals("Q")) {
                        MoudeuleApp.logout();
                        new StartMenuController().run();
                    }
                }
                var course = Course.getCourseByID(res);

                view.displayChoices();
                res = view.askForString().toUpperCase();
                if (res.equals("1")) {
                    view.pausedWarning("new StudentTestsList().run()");
                    /**
                     * to uncomment when UC ready
                     */
                    //new StudentTestsList().run();
                }
                if (res.equals("2")) {
                    view.displayConfirmation(course);
                    res = view.askForString().toUpperCase();
                    if (res.equals("O")) {
                        //student.deactivateCourse(course);
                    }
                }

                if (res.equals("R")) {
                    new StudentMainMenuController().run();
                }
            } while (!res.equals("Q"));
            MoudeuleApp.logout();
            new StartMenuController().run();
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("logged out");
        }
    }
}