package tgpr.moudeule.view;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Course;

import java.util.List;

public class StudentEditCourseView extends View {

    public void displayHeader() {
        super.displayHeader("Liste des cours inscrits");
    }

    public void displayCourse(int i, Course course) {
        printf("[%2d] %s %s %s %s\n", i,course.getId(), course.getCode(), course.getDescription());
    }

    public void displayCourses(List<Course> courses) {
        println("ID  Code Intitulé");
        println("");
        int i = 1;
        for (var course : courses) {
            displayCourse(i, course);
            ++i;
        }
    }

    public View.Action askForAction(int size) {
        return doAskForAction(size, "\n[ID] Sélectionner un cours, [T] Voir les tests, [V] Désactiver un cours, [R] Retour, [Q] Quitter",
                "[0-9]+[tT][0-9]+|[vV][0-9]+[rRqQ]");
    }

}