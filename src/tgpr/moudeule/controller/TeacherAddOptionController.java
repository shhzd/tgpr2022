package tgpr.moudeule.controller;

import tgpr.moudeule.model.Option;
import tgpr.moudeule.model.Question;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.view.TeacherAddQuizView;
import tgpr.moudeule.view.View;

public class TeacherAddOptionController extends Controller{

    private final Question question;
    private final TeacherAddQuizView view;

    public TeacherAddOptionController(Question question, TeacherAddQuizView view) {
        this.question = question;
        this.view = view;
    }

    @Override
    public void run() {

        /** Needs a try catch for breaking **/
        String res;
        Option opt = new Option();
        opt.setQuestionId(question.getId());
        String optext = view.enterOptionText(opt.getTitle());
        opt.setTitle(optext);
        view.enterOptionValue();
        res = view.askForString().toUpperCase();
        if(res.matches("1")){
            opt.setCorrect(1);
        }
        if(res.matches("2")){
            opt.setCorrect(0);
        }
        opt.save();

        view.askAddOption();
        res = view.askForString().toUpperCase();
        if (res.equals("O")){
            new TeacherAddOptionController(question, view).run();
        }
        if (res.equals("N")){
            view.askAddNewQuestion();
            res = view.askForString().toUpperCase();
            while (res.equals("O")){
                new TeacherAddQuestionController(Quiz.getById(question.getquizId()), view).run();
            }
//            if (res.equals("N")){
//                throw new View.ActionInterruptedException();
//            }
        }
        throw new View.ActionInterruptedException();

    }
}
