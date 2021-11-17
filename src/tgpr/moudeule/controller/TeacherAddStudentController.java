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
                switch (res.getAction()){
                    case '1':
                        // uncoment when UC is ready
                        System.out.println("Liste of course disponibles");
                        new StudentAvailableCoursesListController().run();
                        break;
                    case '2':
                        // uncoment when UC is ready
                        System.out.println("Liste de course Inscrit");
                        new StudentEditCourseController().run();
                }
            } while (res.getAction() != 'Q');
        }catch (View.ActionInterruptedException e) {
            // just leave
        }
        view.pausedWarning("A bientôt.");
        view.close();
    }}
