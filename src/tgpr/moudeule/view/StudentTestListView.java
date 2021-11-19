package tgpr.moudeule.view;

import java.time.LocalDate;

public class StudentTestListView extends View {
    public void displayHeaderWithCourseAndPage(String courseID, int page, int nbPages) {
        displayHeader("Liste des tests - cours " + courseID + " - page " + page +
            ((nbPages > 1) ? " de " + nbPages : "")
        );
    }

    public void displayDate (LocalDate d) {
        println("Aujourd'hui : " + d + "\n");
    }

    public void displayTest(String title, int i, String status) {
        println( "[" + ((i < 9) ? " " : "") + (i+1) +  "] " + title + " - " + status);
    }

    public void displaySelectMenu() {
        println("\n[n] Selectionner un test");
    }

    public void displayNavigationMenu(int page, int nbPages) {
        println("[ESC] Retour - [Q] Quitter " +
                ((page != nbPages && nbPages > 0) ? "- [S] page suivante " : "") +
                ((page > 1) ? "- [P] page précédente " : "")
        );
    }

    public String askForString() {
        return askString("", "", false);
    }

    public Action askForAction() {
        return doAskForAction(-1,
                "", "[1]");
    }
}