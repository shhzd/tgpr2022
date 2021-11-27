package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherAddStudentView;
import tgpr.moudeule.view.View;

public class TeacherAddStudentController extends Controller {
    private int page = 1;
    private Course course = new Course();

    public TeacherAddStudentController(Course course) {
        this.course = course;
    }

    public void run() {
        var view = new TeacherAddStudentView();
        try {
            String res;
            do {
                res = "";
                var students = User.getAllNotRegisteredStudents(course);
                int nbPages = (int)Math.ceil(students.size() / (NUMBER_DISPLAY_LINE + 0.0));

                view.displayHeaderWithCourse(course.getCode());
                view.displaySubHeaderWithPage(page, nbPages);
                if (students.size() > 0) {
                    for (int i = 0; (i + ((page - 1) * NUMBER_DISPLAY_LINE)) < students.size() && i < (NUMBER_DISPLAY_LINE + 1); i++) {
                        User student = students.get(i + ((page - 1) * NUMBER_DISPLAY_LINE));
                        view.displayStudent(student.getFullname(), i);
                    }
                    view.displayMenu();
                } else {
                    view.displayEmptyListMenu();
                }
                view.displayNavigationMenuWithEsc(page, nbPages);

                res = view.askForString().toUpperCase(); // lowercase entries are converted to uppercase
                try {
                    if (Controller.isParsable(res) && Integer.parseInt(res) < students.size()) {
                        int index = Integer.parseInt(res) - 1 + ((page - 1) * NUMBER_DISPLAY_LINE);
                        if (index < students.size() && index >= 0) {
                        User student = students.get(index);
                        View.Action subRes;
                        view.displaySubMenu(student);
                        subRes = view.askForAction();
                        if (subRes.getAction() == '1') {
                            student.registerStudent(course);
                        }
                        }
                    }
                } catch (View.ActionInterruptedException e) {
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
}
