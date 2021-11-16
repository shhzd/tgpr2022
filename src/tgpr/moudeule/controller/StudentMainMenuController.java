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
                    case 'D':
                        new StudentAvailableCoursesListController().run();
                        break;
                    case 'I':
                        // uncoment when UC is ready
                        System.out.println("Liste de course Inscrit");
                            new StudentEditCourseController().run();
                }
            } while (res.getAction() != 'Q');
        }catch (View.ActionInterruptedException e) {
            // just leave
        }
        MoudeuleApp.logout();
    }
}
