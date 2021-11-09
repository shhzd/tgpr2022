package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherAddCourseView;
import tgpr.moudeule.view.View;
//import java.security.IdentityScope;

import java.util.List;

public class TeacherAddCourseController extends Controller {

    private TeacherAddCourseView view = new TeacherAddCourseView();

    private int page = 0;

    public void run() {

        try {
            List<String> errors;
            View.Action res;
            Course course = new Course();

            User user = MoudeuleApp.getLoggedUser();
            /** ONLY for testing purposes
            User user = User.getByPseudo("p");
             **/

            do {
                view.displayHeader();

                view.displayMenu();

                int id = view.askID(course.getId());
                keepAsking(id);
                String code = view.askCode(course.getCode());
                keepAsking(code);
                String description = view.askDescription(course.getDescription());
                keepAsking(description);
                int capacity = view.askCapacity(course.getCapacity());
                keepAsking(capacity);

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

    private void keepAsking(int i) {
        if (i == 0) {
            throw new View.ActionInterruptedException();
        }
    }

    private void keepAsking(String s) {
        if (s.equals("0")) {
            throw new View.ActionInterruptedException();
        }
    }
}