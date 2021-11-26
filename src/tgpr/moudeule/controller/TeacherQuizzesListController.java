package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.view.TeacherQuizzesListView;
import tgpr.moudeule.view.View;

import java.util.List;

public class TeacherQuizzesListController extends Controller {

    private final Course course;

    public TeacherQuizzesListController(int courseID) {
        this.course = Course.getCourseByID(courseID);
    }


    @Override
    public void run() {
        var view = new TeacherQuizzesListView();
        try {
            String res;
            do {
                List<Quiz> quizzes = Quiz.getQuizzesBycourseId(course.getId());
                view.displayHeader(course);
                view.displayQuizzesList(quizzes);
                res = view.askForString().toUpperCase();
                if (Controller.isParsable(res) && Integer.parseInt(res) < quizzes.size() + 1){
                    int p = Integer.parseInt(res);
                    if(p != 0) {
                        Quiz q = quizzes.get(p-1);
                        new TeacherEditQuizController(q.getId()).run();
                    } else{
                        new TeacherAddQuizController(course).run();
                    }
                }

            } while (!res.equals("Q") && MoudeuleApp.isLogged());
            MoudeuleApp.logout();

        } catch (View.ActionInterruptedException e) {
        }
        view.close();
    }
}
