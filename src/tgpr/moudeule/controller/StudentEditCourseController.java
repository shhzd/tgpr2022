package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.view.StudentEditCourseView;
import tgpr.moudeule.view.View;

public class StudentEditCourseController extends Controller {
    private String res;
    private void leavePossibility(String res) {
        switch (res) {
            case "R":
                new StudentMainMenuController().run();
                break;
            case "Q":
                MoudeuleApp.logout();
                new StartMenuController().run();
                break;
        }
    }

    @Override
    public void run() {
        var student = MoudeuleApp.getLoggedUser();
        var courses = student.getRegistratedCourses();
        var view = new StudentEditCourseView();
        try {
            do {
                view.displayHeader();
                view.displayCourses(courses, student);
                //could eventually redirect to StudentAvailableCoursesList if the student is not registrated anywhere (to be discuted)

                view.displayIDSelection();
                res = view.askForString().toUpperCase();
                leavePossibility(res);

                if(res.length() == 4) {
                    Course course = Course.getCourseByID(res);
                    if (course != null && courses.contains(course)) {
                        view.displayChoices();
                        res = view.askForString().toUpperCase();
                        if (res.equals("1")) {
                            new StudentTestListController(course).run();
                        }
                        if (res.equals("2")) {
                            view.displayConfirmation(course);
                            res = view.askForString().toUpperCase();
                            if (res.equals("O")) {
                                student.deactivateCourse(course);
                                courses = student.getRegistratedCourses();
                            }
                        }
                    } else {
                        view.badID();
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