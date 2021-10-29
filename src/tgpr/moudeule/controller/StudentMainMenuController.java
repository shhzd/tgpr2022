package tgpr.moudeule.controller;

import tgpr.moudeule.view.StudentMainMenuView;
import tgpr.moudeule.view.View;


public class StudentMainMenuController extends Controller {
    public void run() {
        StudentMainMenuView view = new StudentMainMenuView();
        try {
            View.Action res;
            do {
                view.displayMenu();
                res = view.askForAction();
                switch (res.getAction()){
                    case 'D':
                        // uncoment when UC is ready
                        System.out.println("Liste of course disponibles");
                     //   new StudentAvailableCoursesList();
                        break;
                    case 'I':
                        // uncoment when UC is ready
                        System.out.println("Liste de course Inscrit");
                        //   new StudentEditCourses();
                }
            } while (res.getAction() != 'Q');
        }catch (View.ActionInterruptedException e) {
            // just leave
        }
        view.pausedWarning("A bientôt.");
        view.close();
    }
}
