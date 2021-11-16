package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Test;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.StudentTestListView;
import tgpr.moudeule.view.TeacherManageStudentRegistrationView;
import tgpr.moudeule.view.View;

public class StudentTestListController extends Controller {

    private int page = 1;
    private Course course = new Course();
    private boolean keepLooping = true;
    /** should be included in Controller() **/
    static final int NUMBER_DISPLAY_LINE = 14;

    public StudentTestListController(Course course) {
        this.course = course;
    }

    private void leave(String res) {
        switch (res) {
            case "R":
                new TeacherEditCourseController(course.getId()).run();
                break;
        }
    }

    public void run() {
        var view = new StudentTestListView();
        try {
            do {
                view.displayHeaderWithCourse(course.getCode());
                User user = MoudeuleApp.getLoggedUser();

                var tests = Test.getByCourseAndStudent(course, student);
                int nbPages = (int)Math.ceil(test.size() / (NUMBER_DISPLAY_LINE + 0.0));

                view.displayMenu(tests, course, page, nbPages, NUMBER_DISPLAY_LINE);
                View.Action res;
                res = view.askForAction(); // lowercase entries are converted to uppercase


                if (res.getAction() == 'R') {
                    keepLooping = false;
                    leave(res);
                }
                int i = 1;
                if (res.matches("[1-9]|[0][1-9]|[1][0-4]")) {
                    Test test = tests.get((int)Integer.parseInt(res) - 1);
                    if (test == null)
                        throw new View.ActionInterruptedException();
                    else {
                        keepLooping = false;
                        leave(res.getAction());
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