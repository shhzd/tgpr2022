package tgpr.moudeule.controller;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.view.TeacherQuizzesListView;
import tgpr.moudeule.view.View;

import java.util.List;

public class TeacherQuizzesListController extends Controller {

    private final Course course;
    private final TeacherQuizzesListView view;

    public TeacherQuizzesListController(int courseID) {
        this.course = Course.getCourseByID(courseID);
        this.view = new TeacherQuizzesListView(course);
    }

    /*public int quizzesAmount(List<Quiz> quizzes){
        int i = 0;
        for(Quiz q : quizzes){
            ++i;
        }
        return i;
    }*/

    public int quizId(List<Quiz> quizzes){
        int max = 0;
        for(Quiz q : quizzes){
            max = q.getId();
        }
        return max;
    }

    @Override
    public void run() {

        List<Quiz> quizzes;
        quizzes = Quiz.getAllQuizzesBycourseId(course.getId());
        int maxNumber = quizId(quizzes);

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
                        break;
                }
            }
            while (res.getAction() != 'R');
        } catch (View.ActionInterruptedException e) {
            //just leave the loop
        }
    }
}
