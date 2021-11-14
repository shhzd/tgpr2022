package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.view.TeacherEditCourseView;
import tgpr.moudeule.view.View;

public class TeacherEditCourseController extends Controller {
    private String res;
    private void leavePossibility(String res) {
        switch (res) {
            case "R":
                new TeacherMainMenuController().run();
                break;
            case "Q":
                MoudeuleApp.logout();
                new StartMenuController().run();
                break;
        }
    }

    private int courseID;
    public TeacherEditCourseController(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public void run() {
        var teacher = MoudeuleApp.getLoggedUser();
        var course = Course.getCourseByID(courseID);
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
                leavePossibility(res);

                switch (res) {
                    case "1":
                        view.displayDeleteCourseConfirmation(course);
                        res = view.askForString().toUpperCase();
                        leavePossibility(res);
                        switch (res) {
                            case "O":
                                course.delete();
                                break;
                        }
                        break;
                    case "2":
                            view.askCode();
                            res = view.askForString().toUpperCase();
                            leavePossibility(res);
                            while (res.length() < 4 && !res.equals("R") && !res.equals("Q")) {
                                view.badCode();
                                res = view.askForString().toUpperCase();
                                leavePossibility(res);
                            }
                            String newCode = res;
                            view.displayEditCodeConfirmation();
                            res = view.askForString().toUpperCase();
                            leavePossibility(res);
                            switch (res) {
                                case "O":
                                    course.setCode(newCode);
                                    course.save();
                                    break;
                            }
                        break;
                    case "3":
                        view.askDescription();
                        res = view.askForString().toUpperCase();
                        leavePossibility(res);
                        String newDescription = res;
                        view.displayEditDescriptionConfirmation();
                        res = view.askForString().toUpperCase();
                        leavePossibility(res);
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
                        leavePossibility(res);
                        int newCapacity = Integer.parseInt(res);
                        view.displayEditCapacityConfirmation();
                        res = view.askForString().toUpperCase();
                        leavePossibility(res);
                        switch (res) {
                            case "O":
                                course.setCapacity(newCapacity);
                                course.save();
                        }
                        break;
                    case "5":
                        /**
                         * to uncomment when UC ready
                         */
                        //new TeacherManageStudentRegistration().run();
                        break;
                    case "6":
                        /**
                         * to uncomment when UC ready
                         */
                        //new TeacherQuizzesList().run();
                        break;
                }
            } while (!res.equals("Q"));
            MoudeuleApp.logout();
            new StartMenuController().run();
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("logged out");
        }
    }
}
