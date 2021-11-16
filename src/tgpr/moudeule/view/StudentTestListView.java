package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Test;
import tgpr.moudeule.model.User;

import java.util.List;

public class StudentTestListView extends View {
    public void displayHeaderWithCourse(String courseID) {
        displayHeader("Liste des tests - cours " + courseID);
    }

    public void displayMenu(List<Test> tests, Course course, int page, int nbPages, int lgPage) {

        for (int i = 0 ; (i + ((page - 1) * lgPage)) < tests.size() && i < (lgPage + 1) ; i++) {
            Test test = tests.get(i + ((page - 1) * lgPage));
            println(prettyString(test, i, course));
        }
        println("\n[n] Selectionner un test");
        println("[R] Retour - [Q] Quitter " +
            ((page > 1) ? "- [P] page précédente " : "") +
            ((page != nbPages) ? "- [S] page suivante " : ""));
    }

    public String askForString() {
        return askString("", "", false);
    }

    private String prettyString(Test test, int i, Course course) {
        return "[" + ((i < 9) ? " " : "") +
            (i+1) +  "] " + test.getTitle() +
            " - (" + test.getStatus(user) + ")";
    }

    public Action askForAction() {
        return doAskForAction(-1,
                "", "[1]");
    }

    public Action askForConfirmation() {
        return doAskForAction(-1,
        "[O/N] Confirmer la suppression", "[oOnN]");
    }
}