package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.model.Test;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.StudentTestListView;
import tgpr.moudeule.view.View;

import java.time.LocalDate;
import java.util.List;

public class StudentTestListController extends Controller {

    private int page = 1;
    private Course course = new Course();
    /** should be included in Controller() as GLOBAL **/
    static final int NUMBER_DISPLAY_LINE = 14;
    /** should be included in Controller() as GLOBAL **/
    static final LocalDate TODAY = LocalDate.now();

    public StudentTestListController(Course course) {
        this.course = course;
    }

    public static LocalDate getToday() {return TODAY;}

    public void run() {
        var view = new StudentTestListView();
        String res = "";
        try {
            do {
                User user = MoudeuleApp.getLoggedUser();
                var quizzes = Quiz.getQuizzesBycourseId(course.getId());
                int nbPages = (int) Math.ceil(quizzes.size() / (NUMBER_DISPLAY_LINE + 0.0));

                view.displayHeaderWithCourseAndPage(course.getCode(), page, nbPages);
                view.displayDate(TODAY);
                displayQuizzes(page, quizzes, user, view);
                view.displayNavigationMenu(page, nbPages);

                res = view.askForString().toUpperCase(); // lowercase entries are converted to uppercase

                /** If the student hasn't started yet AND the test is opened, the controller receives
                 * a null test, otherwise it receives the test the student has started
                 * The next controller will know what to do ...
                 */
                if (res.matches("[1-9]|[0][1-9]|[1][0-4]")) {
                    Test test = Test.getByQuizAndStudent(quizzes.get((int) Integer.parseInt(res) - 1), user);
                    if (test == null) {
                        /** Yet to implement **/
                        view.pausedWarning("Cette opération n'est pas encore possible");
                    } else {
                        /** Yet to implement **/
                        view.pausedWarning("Cette opération n'est pas encore possible");
                    }
                }
                if (res.equals("S") && (page) != nbPages && nbPages > 1) {
                    this.page++;
                }
                if (res.equals("P") && page > 1) {
                    this.page--;
                }
            } while (!res.equals("Q") && MoudeuleApp.isLogged());
            MoudeuleApp.logout();
        } catch (View.ActionInterruptedException e) {
        }
        view.close();
    }

    private static String getStatus(Quiz quiz, Test test) {
        String status = "";
        if (test == null)
            if (quiz.getFinish().compareTo(StudentTestListController.getToday()) <= 0)
                status = "Trop tard ! vous ne pouvez plus participer";
            else {
                status = (quiz.getStart().compareTo(StudentTestListController.getToday()) <= 0) ?
                        "Commencer le test, termine le " + quiz.getFinish() :
                        "Le test commence le " + quiz.getStart();
            }
        else
            status = test.getStatus(StudentTestListController.getToday());
        return status;
    }

    private static void displayQuizzes(int page, List<Quiz> quizzes, User user, StudentTestListView view) {

        for (int i = 0; (i + ((page - 1) * NUMBER_DISPLAY_LINE)) < quizzes.size() && i < (NUMBER_DISPLAY_LINE + 1); i++) {
            Quiz quiz = quizzes.get(i + ((page - 1) * NUMBER_DISPLAY_LINE));
            Test test = Test.getByQuizAndStudent(quiz, user);
            view.displayTest(quiz.getTitle(), i, getStatus(quiz, test));
        }
        if (quizzes.size() > 0)
            view.displaySelectMenu();


    }
}