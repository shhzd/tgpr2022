package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.view.StudentMainMenuView;
import tgpr.moudeule.view.View;

public class StudentMainMenuController extends Controller {
    public void run() {
        var view = new StudentMainMenuView();
        try {
            View.Action res;
            do {
                view.displayHeader();
                view.displayMenu();
                res = view.askForAction();
                switch (res.getAction()){
                    case '1':
                        new StudentAvailableCoursesListController().run();
                        break;
                    case '2':
                        new StudentEditCourseController().run();
                        break;
                }
            } while (res.getAction() != 'Q' && MoudeuleApp.isLogged());
            MoudeuleApp.logout();
        }catch (View.ActionInterruptedException e) {
        }
        view.close();
    }
}
