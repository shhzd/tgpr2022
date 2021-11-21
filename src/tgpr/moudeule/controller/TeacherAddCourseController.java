package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherAddCourseView;
import tgpr.moudeule.view.View;

import java.util.ArrayList;
import java.util.List;

public class TeacherAddCourseController extends Controller {

    public void run() {

        var view = new TeacherAddCourseView();
        int id = 0, capacity = 0;
        String code = null, description = null;

        try {
            List<String> errors = new ArrayList<>();
            View.Action res;
            Course course = new Course();
            User user = MoudeuleApp.getLoggedUser();
            do {
                view.displayHeader();
                view.displayMenu();

                do {
                    id = view.askID(id);
                    String error = course.isValidID(id);
                    if (error != null)
                        view.warning(error);
                } while (course.isValidID(id) != null);

                do {
                    code = view.askCode(code).toUpperCase();
                    String error = course.isValidCode(code);
                    if (error != null)
                        view.warning(error);
                } while (course.isValidCode(code) != null);

                do {
                    description = view.askDescription(description);
                    String error = course.isValidDescription(description);
                    if (error != null)
                        view.warning(error);
                } while (course.isValidDescription(description) != null);

                do {
                    capacity = view.askCapacity(capacity);
                    String error = course.isValidCapacity(capacity);
                    if (error != null)
                        view.warning(error);
                } while (course.isValidCapacity(capacity) != null);

                course.setId(id);
                course.setTeacher(user.getPseudo());
                course.setCode(code);
                course.setDescription(description);
                course.setCapacity(capacity);
                errors = course.validate();

                if (errors.size() > 0)
                    view.showErrors(errors);
                res = view.askForAction();
                switch (res.getAction()) {
                    case 'O':
                        course.save();
                        System.out.println(errors);
                        break;
                    case 'N':
                        errors.add("No");
                }
                System.out.println("deux " + errors);

            } while (errors.size() > 0);
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("création abandonnée");
        }
        view.close();
    }
}