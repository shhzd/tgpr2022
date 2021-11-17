package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;

import java.util.List;

public class StudentEditCourseView extends View {

    public void displayHeader() {
        displayHeaderWithPseudo("Liste des cours inscrits");
    }

    public void displayCourse(int i, Course course) {
        printf("%s %s %s", course.getId(), course.getCode(), course.getDescription());
    }

    public void displayCourses(List<Course> courses, User student) {
        println("ID       Code      Intitulé\n");
        int i = 1;
        if (courses.size() > 0 ) {
            for (var course : courses) {
                if (course.isActive(course, student)) {
                    displayCourse(i, course);
                    ++i;
                    println("");
                }
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
        println("");
        println("[R] Retour - [Q] Quitter");
    }

    public String askForString() {
        return askString("", "", false);
    }

    public void badID() {
        this.showError("Entrez un ID de cours valide");
    }

}