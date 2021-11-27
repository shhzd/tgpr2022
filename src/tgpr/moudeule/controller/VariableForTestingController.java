package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.User;
import tgpr.moudeule.model.VariableForTesting;
import tgpr.moudeule.view.VariableForTestingView;
import tgpr.moudeule.view.View;

import java.time.LocalDate;

public class VariableForTestingController extends Controller {
    @Override
    public void run() {
        var view = new VariableForTestingView();
        try {
            View.Action res;
            do {
                view.displayHeader();
                view.displayCurrentDate();
                res = view.askForAction();
                switch (res.getAction()) {
                    case '1':
                        LocalDate newDate = view.askForDate(VariableForTesting.getCurrentDate());
                        VariableForTesting.setCurrentDate(newDate);
                        break;
                    case '2':
                        new StartMenuController().run();
                        break;
                    case '3':
                        User bob = User.getByPseudo("bob");
                        MoudeuleApp.setLoggedUser(bob);
                        new StudentMainMenuController().run();
                        break;
                    case '4':
                        User p = User.getByPseudo("p");
                        MoudeuleApp.setLoggedUser(p);
                        new TeacherMainMenuController().run();
                        break;
                    case '5':
                        User ben = User.getByPseudo("ben");
                        MoudeuleApp.setLoggedUser(ben);
                        new TeacherMainMenuController().run();
                        break;
                }
            } while (res.getAction() != 'Q');
        } catch (View.ActionInterruptedException e) {
        }
        view.close();
    }
}
