package tgpr.moudeule.controller;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.view.TeacherEditCourseView;
import tgpr.moudeule.view.View;

public class TeacherEditCourseController extends Controller {
    private TeacherEditCourseView view = new TeacherEditCourseView();
    private Course course;

    public TeacherEditCourseController(Course course) {
        this.course = course;
    }

    public void run() {
        try {

            View.Action res;


        } catch (View.ActionInterruptedException e) {

        }
    }
}
