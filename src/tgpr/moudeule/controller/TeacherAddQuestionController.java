package tgpr.moudeule.controller;

import tgpr.moudeule.model.Question;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.view.TeacherAddQuizView;

public class TeacherAddQuestionController extends Controller {

    private final Quiz quiz;
    private final TeacherAddQuizView view;

    public TeacherAddQuestionController(Quiz quiz, TeacherAddQuizView view) {
        this.quiz = quiz;
        this.view = view;
    }

    @Override
    public void run() {

        /** Needs a try catch for breaking **/
        Question quest = new Question();
        quest.setquizId(quiz.getId());
        String qtit = view.askQuestionText();
        while (qtit == null || qtit.trim().length() == 0) {
            view.showInvalidTitle();
            qtit = view.askQuestionText();
        }
        quest.setTitle(qtit);

        String res;
        res = view.askForString();
        while (!(res.equals("1") || res.equals("2"))) {
            view.askQuestionType();
            res = view.askForString();
        }
        if (res.matches("1")) {
            quest.setType("QCM");
        } else if (res.matches("2")) {
            quest.setType("QRM");
        }
        quest.save();

        int numberOfOptions = quest.getNumberOfOptions();
        while (numberOfOptions < 2) {
            new TeacherAddOptionController(quest, view).run();
            numberOfOptions = quest.getNumberOfOptions();
        }
        int trueAnswers = Question.getNumberTrueAnswers(quest);
        while(trueAnswers < 1) {
            view.showInvalidAmountOfRightAnswers();
            new TeacherAddOptionController(quest, view).run();
            trueAnswers = Question.getNumberTrueAnswers(quest);

        }
        view.askAddOption();
        res = view.askForString().toUpperCase();
        if (res.equals("O")) {
            new TeacherAddOptionController(quest, view).run();
        }
        if (res.equals("N")) {
            view.askAddNewQuestion();
            String subres;
            subres = view.askForString().toUpperCase();
            if (subres.equals("O")) {
                new TeacherAddQuestionController(quiz, view).run();
            }
        }
    }
}
