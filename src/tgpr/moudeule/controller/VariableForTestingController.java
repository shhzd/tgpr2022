package tgpr.moudeule.controller;

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
                }
            } while (res.getAction() != 'Q');
        } catch (View.ActionInterruptedException e) {
        }
        view.close();
    }
}
