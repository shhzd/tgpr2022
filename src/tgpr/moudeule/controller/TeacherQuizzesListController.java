package tgpr.moudeule.controller;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.view.TeacherQuizzesListView;

import java.util.List;

public class TeacherQuizzesListController extends Controller {

    private final Course course;
    private final TeacherQuizzesListView view;

    public TeacherQuizzesListController(Course course) {
        this.course = course;
        this.view = new TeacherQuizzesListView(course);
    }

    @Override
    public void run() {
        List<Quiz> quizzes  = Quiz.getAllQuizzesBycourseId(course.getId());
    }
}
