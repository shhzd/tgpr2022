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
                view.displayCurrentDate();

                setTitle(view, quiz);
                setStartDate(view, quiz);
                setFinishedDate(view, quiz);
                quiz.setcourseId(course.getId());
                quiz.save();
                new TeacherAddQuestionController(quiz, view).run();
                throw new View.ActionInterruptedException();

            } while (!res.equals("Q") && MoudeuleApp.isLogged());
        } catch (View.ActionInterruptedException e){
        }
        view.close();
    }

    public void setTitle(TeacherAddQuizView view, Quiz quiz) {
        String title = view.askTitle(quiz.getTitle());
        while(title == null || title.trim().length() == 0) {
            view.showInvalidTitle();
            title = view.askTitle(quiz.getTitle());
        }
        quiz.setTitle(title);
    }

    public void setStartDate(TeacherAddQuizView view, Quiz quiz) {
        LocalDate startDate = view.askStartDate(quiz.getStart());
        while(startDate == null || !quiz.newStartDateIsAfterCurrentDate(startDate)){
            view.showInvalidDate();
            startDate = view.askStartDate(quiz.getStart());
        }
        quiz.setStart(startDate);
    }

    private void setFinishedDate(TeacherAddQuizView view, Quiz quiz) {
        LocalDate finishDate = view.askFinishDate(quiz.getFinish());
        while(finishDate == null || !quiz.newFinishedDateisAfterCurrentStartDate(finishDate)){
            view.showInvalidDate();
            finishDate = view.askFinishDate(quiz.getFinish());
        }
        quiz.setFinish(finishDate);
    }
}
