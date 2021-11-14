package tgpr.moudeule.controller;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.view.TeacherAddQuizView;
import tgpr.moudeule.view.TeacherQuizzesListView;

public class TeacherAddQuizController extends Controller{

    private final Course course;
    private final TeacherAddQuizView view;

    public TeacherAddQuizController(Course course){
        this.course = course;
        this.view = new TeacherAddQuizView(course);
    }



    @Override
    public void run() {

    }
}
