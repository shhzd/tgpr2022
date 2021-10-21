package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;

import java.util.List;

public class TeacherMainMenuView extends View {
    public void displayHeader() {
        displayHeader("Main Menu");
    }

    public void displayMenuStudent() {
        println("[L] Liste des cours déjà inscrit");
        println("[D] Liste des cours disponibles a l'inscription");
        println("\n[O] Logout");

    }

    public void displayMenuTeacher(List<Course> courses, int page) {
        println("== Liste des cours ==\n");

        println("n    ID   CODE Cap  Description");
        int pages = (int)(courses.size() / 9.0);

        for (int i = 1 ; (i + (page * 9)) < courses.size() && i < 10 ; i++)
            println(i + "   " + courses.get((i - 1)+ (page * 9)).prettyPrint());

        println("\n[n] détails du cours " +
                ((pages != page) ? "- [s]: page suivante " :
                (page > 0) ? "- [p]: page précédente " : "") +
                "- [O] Logout"); //-
    }

    public Action askForAction() {
        return doAskForAction(-1, "", "[oO]|[lL]|[dD]");
    }

    public Action askForActionStudent() {
        return doAskForAction(-1, "", "[oO]|[lL]|[dD]");
    }

    public Action askForActionTeacher() {
        return doAskForAction(-1, "", "[oO]|[0-9]|[sS]|[pP]");
    }

}