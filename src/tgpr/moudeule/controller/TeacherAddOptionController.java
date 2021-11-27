package tgpr.moudeule.controller;

import tgpr.moudeule.model.Option;
import tgpr.moudeule.model.Question;
import tgpr.moudeule.view.TeacherAddQuizView;

import java.util.List;

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
            while(optext == null || optext.trim().length() < 1) {
                view.showErrorStringNull();
                optext = view.enterOptionText(opt.getTitle());
            }
            List<String> existingOptions = Option.getAllOptionTitlesOfQuestions(question.getId());
            while (existingOptions.contains(optext)) {
                view.showErrorOptionAlreadyExists();
                optext = view.enterOptionText(opt.getTitle());
            }
            opt.setTitle(optext);
            view.enterOptionValue();
            res = view.askForString().toUpperCase();
            while(!(res.equals("1") || res.equals("2"))) {
                view.enterOptionValue();
                res = view.askForString().toUpperCase();
            }
            if (res.matches("1")) {
                if(question.getType().equalsIgnoreCase("QCM")) {
                    List<Option> options = Option.getOptionsByQuestion(question.getId());
                    for (Option o : options) {
                        o.setCorrect(0);
                        o.save();
                    }
                }
                opt.setCorrect(1);
            } else {
                opt.setCorrect(0);
            }
            opt.save();

    }
}
