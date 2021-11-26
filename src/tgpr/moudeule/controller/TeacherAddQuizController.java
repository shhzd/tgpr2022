package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.view.TeacherAddQuizView;
import tgpr.moudeule.view.View;

import java.time.LocalDate;


public class TeacherAddQuizController extends Controller{
    private int page = 1;

    private final Course course;

    public TeacherAddQuizController(Course course){
        this.course = course;
    }


    @Override
    public void run() {
        var view = new TeacherAddQuizView();
        try{
            Quiz quiz = new Quiz();
            String res = "";

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
                throw new View.ActionInterruptedException();

            } while (!res.equals("Q") && MoudeuleApp.isLogged());
        } catch (View.ActionInterruptedException e){
        }
        view.close();
    }

}
