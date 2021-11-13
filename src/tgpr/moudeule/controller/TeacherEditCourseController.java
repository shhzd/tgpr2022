package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.view.TeacherEditCourseView;
import tgpr.moudeule.view.View;

public class TeacherEditCourseController extends Controller {
    private String res;
    private void quitPossibility(String res) {
        if (res.equals("R")) {
            new TeacherMainMenuController().run();
        }
        if (res.equals("Q")) {
            MoudeuleApp.logout();
            new StartMenuController().run();
        }
    }

    private int courseID;
    public TeacherEditCourseController(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public void run() {
        var teacher = MoudeuleApp.getLoggedUser();
        var course = Course.getCourseByID(courseID);
        var view = new TeacherEditCourseView();

        try {
            do {
                view.displayHeader();
                view.displayCourseInformation(course);
                System.out.println("");

                view.displayDeleteCourse();
                view.displayEditCode();
                view.displayEditDescription();
                view.displayEditCapacity();
                view.displayManageRegistrations();
                view.displayManageQuizzes();
                System.out.println("");
                res = view.askForString().toUpperCase();
                quitPossibility(res);

                

            } while (!res.equals("Q"));
            MoudeuleApp.logout();
            new StartMenuController().run();
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("logged out");
        }
    }
}
