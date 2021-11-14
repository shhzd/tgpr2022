package tgpr.moudeule.view;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;

import java.util.List;

public class StudentAvailableCoursesListView extends View {

    public void displayHeader() {
        super.displayHeader("Liste des cours disponibles");
    }

    public void displayOneCourse(int i, Course course) {
        String status = course.getStatus(MoudeuleApp.getLoggedUser());
        printf("%s %s %s %s\n", course.getId(), course.getCode(), course.getDescription(), status);
    }

    public void displayCourses(List<Course> courses, int page, int nbPages, int lgPage) {
        println("ID   Code Intitulé");
        println("");
        int i = 0;
        for(Course course : courses) {
            if (i + (page * lgPage) <= courses.size() && i < (lgPage + 1)) {
                displayOneCourse(i, course);
                ++i;
            }
        }
    }

    public void displayOption(List<Course> courses, int page, int nbPages, int lgPage) {
        println("\n[ID] Selectionner un cours" +
                "\n[R] Retour - [Q] Quitter" + (((page + 1) != nbPages) ? " - [S] page suivante " :
                (page > 0) ? " - [P] page précédente " : "")); //-
    }

//    public View.Action askForAction() {
//        return doAskForAction(-1, "\n[ID] Sélectionner un cours - [P] Page précédente - [S] Page suivante \n[R] Retour - [Q] Quitter",
//                "[0-9][0-9][0-9][0-9]+|[pP]+|[sS]+|[rR]+|[qQ]");
//    }

    public String askForString() {
        return askString("", "", false);
    }

}
