package tgpr.moudeule.controller;


import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherAddStudentView;
import tgpr.moudeule.view.View;

import java.util.ArrayList;
import java.util.List;

public class TeacherAddStudentController extends Controller {
    private int page = 1;
    private Course course = new Course();
    private boolean keepLooping = true;
    /** should be included in Controller() **/
    static final int NUMBER_DISPLAY_LINE = 14;

    public TeacherAddStudentController(Course course) {
        this.course = course;
    }

    /** SHOULD BE DONE IN THE MODEL **/
    public List<User> newStudent(Course course) {
        var students = User.getAllStudents();
        List<User> newStudents = new ArrayList<>();
        for(User s:students){
            if(s.getStatus(course) == ""){
                newStudents.add(s);
            }
        }return newStudents;
    }

    public void run() {
        var view = new TeacherAddStudentView();
        try {
            String res;
            do {
                view.displayHeaderWithCourse(course.getCode());
//                User user = MoudeuleApp.getLoggedUser();

                var students = User.getByCourse(course);

                int nbPages = (int)Math.ceil(students.size() / (NUMBER_DISPLAY_LINE + 0.0));

                view.displaySubHeaderWithPage(page, nbPages);
                var newStudent = newStudent(course);
                view.displayNewStudents(newStudent);
                res = view.askForString().toUpperCase(); // lowercase entries are converted to uppercase
                try {
                    if (res.matches("[1-9]|[0][1-9]|[1][0-2]")) {
                        User student = students.get((int)Integer.parseInt(res) - 1);
                        if (student == null)
                            throw new View.ActionInterruptedException();
                        else {
                            View.Action subRes;
                            String status = student.getStatus(course);
                            /** the String status is not yes used, changes have to be made to the
                             * database before.
                             */
                            view.displaySubMenu(student, status);
                            subRes = view.askForAction();
                            if (subRes.getAction() == '1') {
                                switch (status) {
                                    case "en attente" :
                                        student.activateCourse(course);
                                        break;
                                    case "actif" :
                                        view.showWarning();
                                        if (view.askForConfirmation().getAction() == 'O') {
                                            /** using cancelWaitingList for now, but needs a function
                                             * that recursivly deleted all the test of student for that course
                                             * when quiz are implemented
                                             */
                                            student.activateCourse(course);
                                        }
                                        break;
                                }
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
