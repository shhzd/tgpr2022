package tgpr.moudeule.controller;

import tgpr.moudeule.view.StartMenuView;
import tgpr.moudeule.view.View;

public class StartMenuController extends Controller {
    public void run() {
        StartMenuView view = new StartMenuView();
        try {
            View.Action res;
            do {
                view.displayHeaderWithLogo();
                view.displayMenu();
                res = view.askForAction();
                switch (res.getAction()) {
                    case 'C':
                        /** to uncomment when UC are ready  **/
                        System.out.println("start new LoginController().run()");
//                        new LoginController().run();
                        /** uncomment to test TeacherMainMenuController()  **/
//                        new TeacherMainMenuController().run();
                        break;
                    case 'E':
                        new SignupController().run();
                        break;
                }
            } while (res.getAction() != 'Q');
        } catch (View.ActionInterruptedException e) {
            // just leave
        }
        view.pausedWarning("A bientôt.");
        view.close();
    }
}

