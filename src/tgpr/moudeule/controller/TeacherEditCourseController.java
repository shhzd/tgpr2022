package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.view.TeacherEditCourseView;
import tgpr.moudeule.view.View;

    public class TeacherEditCourseController extends Controller {

        private String res;
        private int courseID;

        public TeacherEditCourseController(int courseID) {
            this.courseID = courseID;
        }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
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
                view.displayEditID();
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
                    case "1":
                        try {
                            view.displayDeleteCourseConfirmation(course);
                            res = view.askForString().toUpperCase();
                            switch (res) {
                                case "O":
                                    teacher.removeCourseFromRegistrations(course);
                                    teacher.deleteCourse(course);
                                    throw new View.ActionInterruptedException();
                            }
                        } catch (View.ActionInterruptedException e) {
                            throw new View.ActionInterruptedException();
                        }
                        break;
                    case "2":
                        try {
                            view.askID();
                            Integer resInt = view.askForInt();
                            while (resInt == null || Course.isValidID(resInt) != null) {
                                view.badID();
                                view.askID();
                                resInt = view.askForInt();
                            }
                            int newID = resInt.intValue();
                            view.displayEditIDConfirmation();
                            res = view.askForString().toUpperCase();
                            switch (res) {
                                case "O":
                                    course.setId(newID);
                                    course.save();
                                    break;
                            }
                        } catch (View.ActionInterruptedException e) {
                        }
                        break;
                    case "3":
                        try {
                            view.askCode();
                            res = view.askForString().toUpperCase();
                            while (Course.isValidCode(res) != null) {
                                view.badCode();
                                view.askCode();
                                res = view.askForString().toUpperCase();
                            }
                            String newCode = res;
                            view.displayEditCodeConfirmation();
                            res = view.askForString().toUpperCase();
                            switch (res) {
                                case "O":
                                    view.warning("Fonction pas encore implémentée");
                                    course.setCode(newCode);
                                    break;
                            }
                        } catch (View.ActionInterruptedException e) {
                        }
                        break;
                    case "4":
                        try {
                            view.askDescription();
                            res = view.askForString();
                            while (Course.isValidDescription(res) != null) {
                                view.badDescription();
                                view.askDescription();
                                res = view.askForString();
                            }
                            String newDescription = res;
                            view.displayEditDescriptionConfirmation();
                            res = view.askForString().toUpperCase();
                            switch (res) {
                                case "O":
                                    course.setDescription(newDescription);
                                    course.save();
                                    break;
                            }
                        } catch (View.ActionInterruptedException e) {
                        }
                        break;
                    case "5":
                        try {
                            view.askCapacity();
                            Integer resCap = view.askForInt();
                            while (resCap == null || Course.isValidCapacity(resCap) != null) {
                                view.badCapacity();
                                view.askCapacity();
                                resCap = view.askForInt();
                            }
                            int newCapacity = resCap.intValue();
                            view.displayEditCapacityConfirmation();
                            res = view.askForString().toUpperCase();
                            switch (res) {
                                case "O":
                                    course.setCapacity(newCapacity);
                                    course.save();
                                    break;
                            }
                        } catch (View.ActionInterruptedException e) {
                        }
                        break;
                    case "6":
                        new TeacherManageStudentRegistrationController(course).run();
                        break;
                    case "7":
                        new TeacherQuizzesListController(course.getId()).run();
                        break;
                }
            } while (!res.equals("Q") && MoudeuleApp.isLogged());
            MoudeuleApp.logout();
        } catch (View.ActionInterruptedException e) {
        }
    view.close();
    }
}
