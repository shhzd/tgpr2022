package tgpr.moudeule.controller;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.view.StudentEditCourseView;
import tgpr.moudeule.view.TeacherEditCourseView;
import tgpr.moudeule.view.View;

public class StudentEditCourseController extends Controller {

    private StudentEditCourseView view = new StudentEditCourseView();
    private Course course;

    public StudentEditCourseController(Course course) {
        this.course = course;
    }

    public void run() {
        try {

            View.Action res;


        } catch (View.ActionInterruptedException e) {

        }
    }

}
