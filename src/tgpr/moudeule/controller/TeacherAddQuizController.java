package tgpr.moudeule.controller;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Option;
import tgpr.moudeule.model.Question;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.view.TeacherAddQuizView;
import tgpr.moudeule.view.View;

import java.time.LocalDate;


public class TeacherAddQuizController extends Controller{

    private final Course course;

    public TeacherAddQuizController(Course course){
        this.course = course;
    }

    @Override
    public void run() {
        var view = new TeacherAddQuizView();
        Quiz quiz;
        quiz = new Quiz();
        try{
            String res = "0";

            do{
                view.displayHeader(course);
                //res = view.askForString().toUpperCase();

                String title = view.askTitle(quiz.getTitle());
                quiz.setTitle(title);

                LocalDate startDate = view.askStartDate(quiz.getStart());
                while(!quiz.newStartDateIsAfterCurrentDate(startDate)){
                    view.displayStartDateError(quiz,startDate);
                    startDate = view.askStartDate(quiz.getStart());
                }
                quiz.setStart(startDate);

                LocalDate finishDate = view.askFinishDate(quiz.getFinish());
                while(!quiz.newFinishedDateisAfterCurrentStartDate(finishDate)){
                    view.displayFinishDateError(quiz,finishDate);
                    finishDate = view.askFinishDate(quiz.getFinish());
                }
                quiz.setFinish(finishDate);
                quiz.setcourseId(course.getId());
                quiz.save();

                Question quest = new Question();
                quest.setquizId(quiz.getId());
                String qtit = view.askQuestionText(quest.getTitle());
                quest.setTitle(qtit);
                res = view.askForString();
                view.askQuestionType();
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
                    Option opt = new Option();
                    opt.setQuestionId(quest.getId());
                    String optext = view.enterOptionText(opt.getTitle());
                    opt.setTitle(optext);
                    view.enterOptionValue();
                    res = view.askForString().toUpperCase();
                    if(res.matches("1")){
                        opt.setCorrect(1);
                    }
                    if(res.matches("0")){
                        opt.setCorrect(0);
                    }
                    opt.save();
                }
                if (res.equals("N")){
                    view.addNewQuestion();
                }
            } while (!res.equals("Q"));

        } catch (View.ActionInterruptedException e){
            view.pausedWarning("logged out");
        }


    }
}
