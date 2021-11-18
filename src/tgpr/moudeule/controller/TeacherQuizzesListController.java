package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
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

    public int quizzesAmount(List<Quiz> quizzes){
        int i = 0;
        for(Quiz q : quizzes){
            ++i;
        }
        return i;
    }

    /*public int quizId(List<Quiz> quizzes){
        int max = 0;
        for(Quiz q : quizzes){
            max = q.getId();
        }
        return max;
    }*/

    @Override
    public void run() {

        List<Quiz> quizzes;
        quizzes = Quiz.getQuizzesBycourseId(course.getId());
        //int maxNumber = quizId(quizzes);

        try {
            String res;
            do {
                view.displayHeader();
                view.displayQuizzesList(quizzes);
                res = view.askForString().toUpperCase();
                if (res.matches("[1-9]|[0][1-9]|[1][0-2]")){
                    int p = Integer.parseInt(res);
                    Quiz q = quizzes.get(p-1);
                    new TeacherEditQuizController(q.getId()).run();
                }
                if (res.matches("0")){
                    view.pausedWarning("Cette opération n'est pas encore disponible");
                }
                /*if (res.equals("R")){
                    new TeacherEditCourseController(course.getId()).run();
                }*/ //obsolete
                /*res = view.askForAction(maxNumber);
                switch (res.getAction()) {
                    case '0':
                        //new TeacherAddQuizController(course).run(); //add view and controller;
                        view.pausedWarning("Cette opération n'est pas encore disponible");
                        break;
                    case 'S':
                        int q;
                        q = res.getNumber();
                        new TeacherEditQuizController(q).run();
                        break;
                }*/
            } while (!res.equals("Q"));
            MoudeuleApp.logout();
            new StartMenuController().run();

        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("logged out");
        }
    }
}
