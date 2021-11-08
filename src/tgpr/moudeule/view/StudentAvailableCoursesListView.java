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
        printf("[%2d] %s %s %s %s\n", i,course.getId(), course.getCode(), course.getDescription(), status);
    }

    public void displayCourses(List<Course> courses) {
        println("Ordre ID  Code Intitulé");
        println("");
        int i = 1;
        for (var course : courses) {
            displayOneCourse(i, course);
            ++i;
        }
    }

    public View.Action askForAction(int size) {
        return doAskForAction(size, "\n[I] S'inscrire, [D] Désinscrition, [V] Détails, [Q] Quitter",
                "[vV][0-9]+|[fF][0-9]+|[uU][0-9]+|[lL]");
    }

}
