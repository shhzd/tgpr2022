package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;

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
}
