package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;

import java.util.List;

public class TeacherMainMenuView extends View {
    public void displayHeader() {
        displayHeaderWithPseudo("Main Menu");
    }

    public void displaySubHeader(int page, int nbPages) {
        println("== Liste des cours - page " + page +
                ((nbPages > 1) ? " de " + nbPages : "") +
                " ==\n");
    }

    public void displayMenu(List<Course> courses, int page, int nbPages, int lgPage) {

        if (courses.size() != 0) {
            println(" ID   CODE Cap  Description");
            for (int i = 0; (i + ((page - 1) * lgPage)) < courses.size() && i < (lgPage + 1); i++) {
                Course course = courses.get(i + ((page - 1) * lgPage));
                println(course.prettyString());
            }
        } else {
            println("Vous n'avez pas encore de cours.");
        }
    }

    public void displayNavigationMenu(int page, int nbPages) {
        println("\n[ID] Selectionner un cours - [0]  Ajouter un nouveau cours");
        println("[Q] Quitter Moudeule" +
                ((page != nbPages) ? "- [S] page suivante " : "") +
                ((page > 1) ? "- [P] page précédente " : "")
        );
    }
    public String askForString() {
        return askString("", "", false);
    }

}