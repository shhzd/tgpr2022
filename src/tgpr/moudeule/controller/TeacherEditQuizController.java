package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Question;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.view.TeacherEditQuizView;
import tgpr.moudeule.view.View;

import java.time.LocalDate;

public class TeacherEditQuizController extends Controller {

    private int quizId;

    public TeacherEditQuizController(int quizId) {
        this.quizId = quizId;
    }

    private int page = 1;

    private final static int NUMBER_DISPLAY_LINE = 5;

    @Override
    public void run() {
        var questions = Question.getQuestionsByQuiz(this.quizId);
        var view = new TeacherEditQuizView();
        var quiz = Quiz.getById(quizId);
        try {
            String res;
            do {
                int nbPages = (int) Math.ceil(questions.size() / ((double) NUMBER_DISPLAY_LINE));
                view.displayHeader(quiz);
                view.displayTitle(quiz);
                view.displayOption(quiz, page, nbPages, NUMBER_DISPLAY_LINE);

                res = view.askForString().toUpperCase();

                if (res.equals("1")) {
                    String title = view.askForTitle(quiz.getTitle());
                    quiz.setTitle(title);
                    quiz.save();
                } else if (res.equals("2")) {
                    LocalDate startDate = view.askForStartDate(quiz.getStart());
                    while (!quiz.isValidStartDate(startDate)) {
                        startDate = view.askForStartDate(quiz.getStart());
                    }
                    quiz.setStart(startDate);
                    quiz.save();
                } else if (res.equals("3")) {
                    LocalDate endDate = view.askForFinishDate(quiz.getFinish());
                    while (!quiz.isValidFinishedDate(endDate)) {
                        endDate = view.askForFinishDate(quiz.getFinish());
                    }
                    quiz.setFinish(endDate);
                    quiz.save();
                } else if (res.equals("S") && page < nbPages && nbPages > 1) {
                    ++page;
                } else if (res.equals("P") && page > 1) {
                    --page;
                } else if (res.equals("R")) {
                    new TeacherMainMenuController().run();
                    /**to uncomment when is done
                     new TeacherQuizzesList().run();
                     **/
                } else {
                    /** to uncomment when is done
                     if(getQuestionById() is not null)
                     new TeacherEditQuestion().run();
                     **/
                }
            } while (!res.equals("Q"));
        } catch (View.ActionInterruptedException e) {
        }
        MoudeuleApp.logout();
        new StartMenuController().run();
    }
}
