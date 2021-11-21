package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
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

                new TeacherAddQuestionController(quiz, view).run();

            } while (!res.equals("Q") && MoudeuleApp.isLogged());
            MoudeuleApp.logout();
        } catch (View.ActionInterruptedException e){
            view.pausedWarning("logged out");
        }
        view.close();
    }
}
