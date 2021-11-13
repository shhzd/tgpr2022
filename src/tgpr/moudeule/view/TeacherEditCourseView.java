package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;

public class TeacherEditCourseView extends View {

    public void displayHeader() {
        displayHeaderWithPseudo("Modifier un cours");
    }

    public void displayCourseInformation(Course course) {
        println("ID: " + course.getId());
        println("Description: " + course.getDescription());
        println("Enseignant: " + course.getTeacher());
        println("Capacité totale: " + course.getCapacity());
        println("Inscrits: " + course.getRegistratedStudents());
        println("En attente: " + course.getPendingRegistrations());
    }

    public void displayDeleteCourse() {
        println("\n[1] Supprimer ce cours");
    }
    public void displayDeleteCourseConfirmation(Course course) {
        println("[O/N] Confirmer la suppression du cours " + course.getId());
    }

    public void displayEditCode() {
        println("[2] Modifier le code");
    }
    public void displayEditCodeConfirmation() {
        println("[O/N] Confirmer le nouveau code");
    }
    public void badCode() {
        println("Entrez un code de cours valide");
    }

    public void displayEditDescription() {
        println("[3] Modifier la description");
    }
    public void displayEditDescriptionConfirmation() {
        println("[O/N] Confirmer la nouvelle description");
    }

    public void displayEditCapacity() {
        println("[4] Modifier la capacité");
    }
    public void displayEditCapacityConfirmation() {
        println("[O/N] Confirmer la nouvelle capacité");
    }
    public void badCapacity() {
        println("Entrez une capacité de cours valide");
    }

    public void displayManageRegistrations() {
        println("[5] Gérer les inscriptions");
    }

    public void displayManageQuizzes() {
        println("[6] Gérer les inscriptions");
    }

    public void displayFooter() {
        print("\n[R] Retour - [Q] Quitter");
    }

    public String askForString() {
        return askString("", "", false);
    }

}
