package tgpr.moudeule.controller;


import tgpr.moudeule.view.StudentMainMenuView;

import tgpr.moudeule.view.TeacherAddStudentView;
import tgpr.moudeule.view.View;

public class TeacherAddStudentController extends Controller {
    public void run() {
        var view = new TeacherAddStudentView();
        try {
            View.Action res;
            do {
                view.displayHeader();
                view.displayMenu();
                res = view.askForAction();

            } while (res.getAction() != 'Q');
        }catch (View.ActionInterruptedException e) {
            // just leave
        }
        view.pausedWarning("A bientôt.");
        view.close();
    }}
