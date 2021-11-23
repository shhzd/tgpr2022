package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;

import java.util.List;

public class TeacherMainMenuView extends View {
    public void displayHeader() {
        displayHeaderWithPseudo("Main Menu");
    }

    public void displaySubHeader(int p, int n) {
        println("== Liste des cours" + pageNbr(p, n) + " ==\n");
    }

    public void displayMenu(List<Course> courses, int page, int lgPage) {

        if (courses.size() != 0) {
            println(" ID   CODE Cap  Description");
            for (int i = 0; (i + ((page - 1) * lgPage)) < courses.size() && i < (lgPage + 1); i++) {
                Course course = courses.get(i + ((page - 1) * lgPage));
                println(course.prettyString());
            }
        } else {
            println("Vous n'avez pas encore de cours.");
        }
        println("\n[ID] Selectionner un cours - [0] Ajouter un nouveau cours");
    }

    public String askForString() {
        return askString("", "", false);
    }
}