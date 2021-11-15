package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;

public class StudentCourseDescriptionInscriptionView extends View {

    public void displayHeader() {
        super.displayHeader("Détail d'un cours");
    }

    public void displayCourseId(Course course) {
        println("=== Cours " + course.getId() + " ===");
    }

    public void displayInfoTeacher(Course course) {
        println("Enseignant : " + course.getTeacher().getFullname());
    }

    public void displayCourseDescription(Course course) {
        println(course.getDescription());
    }

    public void displayLeftPlaces(Course course) {
        println("Il reste " + course.getLeftPlaces() + " places");
    }

    public void displayStatus(Course course, User student) {
        String status = "Statut : ";
        StringBuilder sb = new StringBuilder();
        if(course.isInWaitingList(student)) {
            sb.append("est dans la liste d'attente");
        } else {
            sb.append("non inscrit");
        }
        println(status);
    }

    public View.Action askForAction(Course course, User student) {
        return doAskForAction(0, "\n[1] " + (course.isInWaitingList(student) ? "Annuler l'inscription" : "S'inscrire au cours") +
                        "\n[R] Retour - [Q] Quitter",
                "[1-1]+|[rR]+|[qQ]");
    }

    public void displayCourse(Course course, User student) {
        displayCourseId(course);
        println("");
        displayInfoTeacher(course);
        displayCourseDescription(course);
        println("");
        displayLeftPlaces(course);
        println("");
        displayStatus(course, student);
    }
}
