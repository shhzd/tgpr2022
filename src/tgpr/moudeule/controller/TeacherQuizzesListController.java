package tgpr.moudeule.controller;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.view.TeacherQuizzesListView;

import java.util.List;

public class TeacherQuizzesListController extends Controller {

    public Course course;
    int courseId = course.getId();

    @Override
    public void run() {
        List<Quiz> quizzes  = Quiz.getAllQuizzesBycourseId(courseId);
        TeacherQuizzesListView view = new TeacherQuizzesListView();
    }
}
