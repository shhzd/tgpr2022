package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;

import java.util.List;

public class TeacherMainMenuView extends View {
    public void displayHeader() {
        displayHeaderWithPseudo("Main Menu");
    }

    public void displayMenu(List<Course> courses, int page, int nbPages, int lgPage, User user) {
        println("== Liste des cours ==\n");

        println(" ID   CODE Cap  Description");

        for (int i = 1 ; (i + (page * lgPage)) <= courses.size() && i < (lgPage + 1) ; i++) {
            Course course = courses.get((i - 1) + (page * lgPage));
            if (course.getTeacher().equals(user)) {
                println(course.prettyString());
            }
        }
        println("   0  Ajouter un nouveau cours");
        println("\n[ID] Selectionner un cours " +
                (((page + 1) != nbPages) ? "- [S] page suivante " :
                (page > 0) ? "- [P] page précédente " : "") +
                "\n[Q] Quitter Moudeule"); //-
    }

    public String askForString() {
        return askString("", "", false);
    }

}