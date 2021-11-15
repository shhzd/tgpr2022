package tgpr.moudeule.view;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;

import java.util.List;

public class StudentAvailableCoursesListView extends View {


    public void displayHeaderWithPseudoAndPageNumber(String header, int page, int lgPage) {
       super.displayHeaderWithPseudoAndPageNumber(header, page, lgPage);
    }

    public void displayOneCourse(int i, Course course) {
        String status = course.getStatus(MoudeuleApp.getLoggedUser());
        printf("%s %s %s %s\n", course.getId(), course.getCode(), course.getDescription(), status);
    }

    public void displayCourses(List<Course> courses, int page, int lgPage) {
        println("ID   Code Intitulé");
        println("");
        if (courses.size() > 0) {
            int j = 0;
            for(int i = (page - 1) * lgPage; i < courses.size(); ++i) {
                if (i < courses.size() && j < lgPage) {
                    displayOneCourse(i, courses.get(i));
                    ++j;
                }
            }
        } else {
            println("Il n'y a actuellement pas de cours disponibles.");
        }
    }

    public void displayOption(List<Course> courses, int page, int nbPages, int lgPage) {
        println("\n[ID] Selectionner un cours" +
                "\n[R] Retour - [Q] Quitter" + (((page) != nbPages) ? " - [S] Page suivante " : "") +
                ((page > 1) ? " - [P] Page précédente " : "")); //-
    }

    public String askForString() {
        return askString("", "", false);
    }

}
