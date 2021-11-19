package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherAddCourseView;
import tgpr.moudeule.view.View;
//import java.security.IdentityScope;

import java.util.List;
import java.util.Locale;

public class TeacherAddCourseController extends Controller {

    public void run() {

        var view = new TeacherAddCourseView();

        try {
            List<String> errors;
            View.Action res;
            Course course = new Course();
            User user = MoudeuleApp.getLoggedUser();
            do {
                view.displayHeader();
                view.displayMenu();

                int id = 0;
                do {
                    id = view.askID(id);
                    String error = course.isValidID(id);
                    if (error != null)
                        view.showError(error);
                } while (course.isValidID(id) != null);

                String code = null;
                do {
                    code = view.askCode(code).toUpperCase();
                    String error = course.isValidCode(code);
                    if (error != null)
                        view.showError(error);
                } while (course.isValidCode(code) != null);

                String description = null;
                do {
                    description = view.askDescription(description);
                    String error = course.isValidDescription(description);
                    if (error != null)
                        view.showError(error);
                } while (course.isValidDescription(description) != null);

                int capacity = 0;
                do {
                    capacity = view.askCapacity(capacity);
                    String error = course.isValidCapacity(capacity);
                    if (error != null)
                        view.showError(error);
                } while (course.isValidCapacity(capacity) != null);

                course.setId(id);
                course.setTeacher(user.getPseudo());
                course.setCode(code);
                course.setDescription(description);
                course.setCapacity(capacity);
                errors = course.validate();

                if (errors.size() > 0)
                    view.showErrors(errors);
            } while (errors.size() > 0);

            res = view.askForAction();
            switch (res.getAction()) {
                case 'O':
                    course.save();
                    break;
            }
            view.close();
            new TeacherMainMenuController().run();
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("création abandonnée");
        }
    }
}