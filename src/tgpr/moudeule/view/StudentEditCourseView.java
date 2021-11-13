package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;

import java.util.List;

public class StudentEditCourseView extends View {

    public void displayHeader() {
        displayHeaderWithPseudo("Liste des cours inscrits");
    }

    public void displayCourse(int i, Course course) {
        printf("[%2d] %s %s %s\n", i,course.getId(), course.getCode(), course.getDescription());
    }

    public void displayCourses(List<Course> courses) {
        println("ID  Code Intitulé\n");
        int i = 1;
        if (courses.size() > 0) {
            for (var course : courses) {
                displayCourse(i, course);
                ++i;
            }
        } else {
            println("Vous n'êtes inscrit à aucun cours");
        }
        displayFooter();
    }

    public void displayIDSelection() {
        println("\n[ID] Sélectionner un cours");
    }

    public void displayChoices() {
        println("[1] Voir les tests - " + "[2] Désactiver ce cours");
    }

    public void displayConfirmation(Course course) {
        println("[O/N] Confirmer la désactivation du cours " + course.getId());
    }

    public void displayFooter() {
        print("[R] Retour - [Q] Quitter");
    }

    public String askForString() {
        return askString("", "", false);
    }

    public void badID() {
        println("Entrez un ID de cours valide");
    }

}