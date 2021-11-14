package tgpr.moudeule.controller;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.view.TeacherQuizzesListView;
import tgpr.moudeule.view.View;

import java.util.List;

public class TeacherQuizzesListController extends Controller {

    private final Course course;
    private final TeacherQuizzesListView view;

    public TeacherQuizzesListController(Course course) {
        this.course = course;
        this.view = new TeacherQuizzesListView(course);
    }

    public int quizzesAmount(List<Quiz> quizzes){
        int i = 0;
        for(Quiz q : quizzes){
            ++i;
        }
        return i;
    }

    @Override
    public void run() {
        View.Action res;
        List<Quiz> quizzes  = Quiz.getAllQuizzesBycourseId(course.getId());
        view.displayHeader();
        int maxNumber = quizzesAmount(quizzes);
        view.displayQuizzesList(quizzes);
        res = view.askForAction(maxNumber);
        switch (res.getAction()){
            case 'A' :
                new TeacherAddQuizController().run(); //add view and controller;
                break;
        }
    }
}
