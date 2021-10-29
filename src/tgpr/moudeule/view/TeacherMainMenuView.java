package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;

import java.util.List;

public class TeacherMainMenuView extends View {
    public void displayHeader() {
        displayHeader("Main Menu");
    }

    public void displayMenu(List<Course> courses, int page, int nbPages, int lgPage) {
        println("== Liste des cours ==\n");

        println(" ID   CODE Cap  Description");

        for (int i = 1 ; (i + (page * lgPage)) <= courses.size() && i < (lgPage + 1) ; i++)
            println(courses.get((i - 1)+ (page * lgPage)).prettyString());

        println("\n[ID] Selectionner un cours - " +
                "[A] ajouter un nouveau cours " +
                (((page + 1) != nbPages) ? "- [S]: page suivante " :
                (page > 0) ? "- [P]: page précédente " : "") +
                "\n[O] Logout"); //-
    }

    public String askForString() {
        return askString("", "", false);
    }

}