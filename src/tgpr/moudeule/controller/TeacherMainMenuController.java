package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.TeacherMainMenuView;
import tgpr.moudeule.view.View;

import java.util.regex.Pattern;

public class TeacherMainMenuController extends Controller {

    private int page = 0;

    public void run() {
        TeacherMainMenuView view = new TeacherMainMenuView();
        try {
            View.Action res;
            do {
                view.displayHeader();

                User user = MoudeuleApp.getLoggedUser();


                if (user.role.getRoleId() == 2) {
                    view.displayMenuStudent();
                    res = view.askForActionStudent();
                    switch (res.getAction()) {
                        case 'L':
//                        new MemberListController().run();
                            break;
                        case 'D':
//                        new MemberListController().run();
                            break;
                    }
                }
                else {
                    var courses= Course.getCoursesFromTeacher(user);

                    view.displayMenuTeacher(courses, page);
                    res = view.askForActionTeacher();
                    if ( Pattern.matches("[0-9]", "" + res.getAction())) {
                        System.out.println(courses.get(Integer.parseInt("" + res.getAction()) - 1));
//                        new view.Teacher   Controller().run();
                    }
                    switch (res.getAction()) {
                        case 'S':
                            this.page++;
                            this.run();
                            break;
                        case 'P':
                            this.page--;
                            this.run();
                            break;
                    }
                }
            } while (res.getAction() != 'O');
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("logged out");
        }

        MoudeuleApp.logout();
    }
}