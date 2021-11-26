package tgpr.moudeule.controller;

import tgpr.moudeule.model.Question;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.view.TeacherAddQuizView;
import tgpr.moudeule.view.View;

public class TeacherAddQuestionController extends Controller{

    private final Quiz quiz;
    private final TeacherAddQuizView view;

    public TeacherAddQuestionController(Quiz quiz, TeacherAddQuizView view){
        this.quiz = quiz;
        this.view = view;
    }

    @Override
    public void run() {

        /** Needs a try catch for breaking **/
        Question quest = new Question();
        quest.setquizId(quiz.getId());
        String qtit = view.askQuestionText(quest.getTitle());
        quest.setTitle(qtit);
        view.askQuestionType();
        String res;
        res = view.askForString();
        if (res.matches("1")){
            quest.setType("QCM");
        }
        if (res.matches("2")){
            quest.setType("QRM");
        }
        quest.save();

        view.askAddOption();
        res = view.askForString().toUpperCase();
        if (res.equals("O")){
            new TeacherAddOptionController(quest, view).run();
        }
        if (res.equals("N")){
            view.askAddNewQuestion();
            String subres;
            subres = view.askForString().toUpperCase();
            while (subres.equals("O")){
                new TeacherAddQuestionController(quiz, view).run();
            }
//            if (subres.equals("N")){
//            }
        }
        throw new View.ActionInterruptedException();
    }
}
