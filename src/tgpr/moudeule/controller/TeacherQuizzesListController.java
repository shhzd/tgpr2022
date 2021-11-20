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

        List<Quiz> quizzes;
        quizzes = Quiz.getQuizzesBycourseId(course.getId());
        var view = new TeacherQuizzesListView();
        if(MoudeuleApp.isLogged()) {
            try {
                String res;
                do {
                    view.displayHeader(course);
                    view.displayQuizzesList(quizzes);
                    res = view.askForString().toUpperCase();
                    if (res.matches("[1-9]|[0][1-9]|[1][0-2]")){
                        int p = Integer.parseInt(res);
                        Quiz q = quizzes.get(p-1);
                        new TeacherEditQuizController(q.getId()).run();
                    }
                    if (res.matches("0")){
                        new TeacherAddQuizController(course).run();
                    }
                } while (!res.equals("Q"));
                MoudeuleApp.logout();

            } catch (View.ActionInterruptedException e) {
            }
        }
        view.close();
    }
}
