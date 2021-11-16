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

        List<Quiz> quizzes  = Quiz.getAllQuizzesBycourseId(course.getId());
        int maxNumber = quizzesAmount(quizzes);
        try {
            View.Action res;
            do {
                view.displayHeader();
                view.displayQuizzesList(quizzes);
                res = view.askForAction(maxNumber);
                switch (res.getAction()) {
                    case 'A':
                        new TeacherAddQuizController(course).run(); //add view and controller;
                        break;
                    case 'S':
                        int q;
                        q = res.getNumber();
                        new TeacherEditQuizController(q);
                    case 'R':
                        new TeacherEditCourseController(course.getId());
                }
            }
            while (res.getAction() != 'L');
        } catch (View.ActionInterruptedException e) {
            //just leave the loop
        }
    }
}
