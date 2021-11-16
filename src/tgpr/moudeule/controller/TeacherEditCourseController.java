package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherEditCourseView;
import tgpr.moudeule.view.View;

public class TeacherEditCourseController extends Controller {
    private String res;
    private boolean keepLooping = true;
    private Course course = new Course();

    public TeacherEditCourseController(Course course) {
        this.course = course;
    }

    private void leave(String res) {
        switch (res) {
            case "R":
                new TeacherMainMenuController().run();
                break;
            case "5":
                new TeacherManageStudentRegistrationController(course).run();
                break;
            case "6":
                /**
                 * To uncomment when UC ready
                 */
                new TeacherManageStudentRegistrationController(course).run();
                //new TeacherQuizzesList().run();
                break;
        }
    }

    @Override
    public void run() {
        var teacher = MoudeuleApp.getLoggedUser();
        var view = new TeacherEditCourseView();
        try {
            do {
                view.displayHeader();
                view.displayCourseInformation(course);
                System.out.println("");
                view.displayDeleteCourse();
                view.displayEditCode();
                view.displayEditDescription();
                view.displayEditCapacity();
                view.displayManageRegistrations();
                view.displayManageQuiz();
                System.out.println("");
                view.displayFooter();
                System.out.println("");
                res = view.askForString().toUpperCase();
                switch (res) {
                    case "R":
                        leave(res);
                    case "1":
                        view.displayDeleteCourseConfirmation(course);
                        res = view.askForString().toUpperCase();
                        switch (res) {
                            case "O":
                                teacher.removeCourseFromRegistrations(course);
                                teacher.deleteCourse(course);
                                keepLooping = false;
                                leave(res);
                                break;
                        }
                        break;
                    case "2":
                        view.askCode();
                        res = view.askForString().toUpperCase();
                        while (res.length() < 4) {
                            view.badCode();
                            res = view.askForString().toUpperCase();
                        }
                        String newCode = res;
                        view.displayEditCodeConfirmation();
                        res = view.askForString().toUpperCase();
                        switch (res) {
                            case "O":
                                course.setCode(newCode);
                                course.save();
                                break;
                        }
                        break;
                    case "3":
                        view.askDescription();
                        res = view.askForString();
                        String newDescription = res;
                        view.displayEditDescriptionConfirmation();
                        res = view.askForString().toUpperCase();
                        switch (res) {
                            case "O":
                                course.setDescription(newDescription);
                                course.save();
                                break;
                        }
                        break;
                    case "4":
                        view.askCapacity();
                        res = view.askForString().toUpperCase();
                        int newCapacity = Integer.parseInt(res);
                        view.displayEditCapacityConfirmation();
                        res = view.askForString().toUpperCase();
                        switch (res) {
                            case "O":
                                course.setCapacity(newCapacity);
                                course.save();
                        }
                        break;
                    case "5":
                        keepLooping = false;
                        leave(res);
                        break;
                    case "6":
                        keepLooping = false;
                        leave(res);
                        break;
                }
            } while (!res.equals("Q") && keepLooping);
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("logged out");
        }
        if (keepLooping)
            MoudeuleApp.logout();
        view.close();
    }
}