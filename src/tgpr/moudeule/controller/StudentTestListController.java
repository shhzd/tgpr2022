package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.model.Test;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.StudentTestListView;
import tgpr.moudeule.view.View;

import java.time.LocalDate;

public class StudentTestListController extends Controller {

    private int page = 1;
    private Course course = new Course();
    private boolean keepLooping = true;
    /** should be included in Controller() as GLOBAL **/
    static final int NUMBER_DISPLAY_LINE = 14;
    /** should be included in Controller() as GLOBAL **/
    static final LocalDate TODAY = LocalDate.now();

    public StudentTestListController(Course course) {
        this.course = course;
    }

    public static LocalDate getToday() {return TODAY;}

    private void leave(String res) {
        switch (res) {
            case "R":
                new StudentEditCourseController().run();
                break;
        }
    }

    private void leave(Test test) {
        /** to uncomment when ready **/
//                new NewStudentTestxxx(test).run();
    }

    public void run() {
        var view = new StudentTestListView();
        String res = "";
        try {
            do {
                User user = MoudeuleApp.getLoggedUser();
                var tests = Test.getByCourseAndStudent(course, user);
                var quizzes = Quiz.getQuizzesBycourseId(course.getId());
                int nbPages = (int)Math.ceil(quizzes.size() / (NUMBER_DISPLAY_LINE + 0.0));

                view.displayHeaderWithCourseAndPage(course.getCode(), page, nbPages);
                view.displayDate(TODAY);
                view.displayMenu(quizzes, user, course, page, nbPages, NUMBER_DISPLAY_LINE);

                res = view.askForString().toUpperCase(); // lowercase entries are converted to uppercase

                System.out.println("res : " + res);

                if (res.equals("R")) {
                    System.out.println("should print R " + res);
                    keepLooping = false;
                    leave(res);
                }
                int i = 1;

                /** If the student hasn't started yet AND the test is opened, the controller receives
                 * a null test, otherwise it receives the test the student has started
                 * The controller will know what to do ...
                 */
                if (res.matches("[1-9]|[0][1-9]|[1][0-4]")) {
                    Test test = Test.getByQuizAndStudent(quizzes.get((int)Integer.parseInt(res) - 1), user);
                    if (test == null) {
                        /** to uncomment when ready **/
                        view.pausedWarning("Ce opération n'est pas encore possible");
//                        keepLooping = false;
//                        leave(test);
                    } else {
                        /** to uncomment when ready **/
                        view.pausedWarning("Ce opération n'est pas encore possible");
//                        keepLooping = false;
//                        leave(test);
                    }
                }
                if (res.equals("S") && (page) != nbPages && nbPages > 1) {
                    this.page++;
                }
                if (res.equals("P") && page > 1) {
                    this.page--;
                }
            } while (!res.equals("Q") && keepLooping);
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("logged out");
        }
        if (keepLooping) {
            MoudeuleApp.logout();
        }
        view.close();
    }

}