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
        println("Statut : " + course.getStatus(student));
    }


}
