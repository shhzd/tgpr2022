package tgpr.moudeule.controller;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.view.TeacherAddQuizView;
import java.time.LocalDate;


public class TeacherAddQuizController extends Controller{

    private final Course course;
    private final TeacherAddQuizView view;

    public TeacherAddQuizController(Course course){
        this.course = course;
        this.view = new TeacherAddQuizView(course);
    }

    @Override
    public void run() {
        Quiz quiz;
        quiz = new Quiz();

            String title = view.askTitle(quiz.getTitle());
            LocalDate startDate = view.askStartDate(quiz.getStart());
            LocalDate finishDate = view.askFinishDate(quiz.getFinish());

            quiz.setTitle(title);
            quiz.setStart(startDate);
            quiz.setFinish(finishDate);

    }
}
