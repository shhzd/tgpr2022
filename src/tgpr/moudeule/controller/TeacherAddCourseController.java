package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherAddCourseView;
import tgpr.moudeule.view.TeacherMainMenuView;
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

            /** for testing purposes **/
//        User user = MoudeuleApp.getLoggedUser();
            User user = User.getByPseudo("p");

            do {
                view.displayHeader();

                int id = view.askID(course.getId());
                String code = view.askCode(course.getCode());
                String description = view.askDescription(course.getDescription());
                int capacity = view.askCapacity(course.getCapacity());

                course.setId(course.getId());
                course.setTeacher(user.getPseudo());
                course.setCode(code);
                course.setDescription(description);
                course.setCapacity(capacity);
                errors = course.validate();

            } while (errors.size() > 0);

            res = view.askForAction();
            switch (res.getAction()) {
                case 'V':
                    course.save();
                    new TeacherMainMenuController().run();
                    break;
                case 'A':
                    new StartMenuController();
                    break;
            }
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("création abandonnée");
        }

    }



}