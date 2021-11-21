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
                    case '1':
                        new LoginController().run();
                        break;
                    case '2':
                        new SignupController().run();
                        break;
                }
            } while (res.getAction() != 'Q');
        } catch (View.ActionInterruptedException e) {
        }
        view.pausedWarning("A bientôt.");
        view.close();
    }
}

