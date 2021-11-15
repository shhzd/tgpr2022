package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.view.StudentCourseDescriptionInscriptionView;
import tgpr.moudeule.view.View;

public class StudentCourseDescriptionInscriptionController extends Controller {
    private Course course;

    public StudentCourseDescriptionInscriptionController(Course course) {
        this.course = course;
    }

    @Override
    public void run() {
        var student = MoudeuleApp.getLoggedUser();
        var view = new StudentCourseDescriptionInscriptionView();
        try {
            View.Action res;
            do {
                view.displayHeader();
                view.displayCourse(this.course, student);
                res = view.as
            } while (res.getAction() != 'Q');
        } catch (View.ActionInterruptedException e) {

        }
    }
}
