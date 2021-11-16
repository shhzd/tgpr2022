package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;

import java.util.ArrayList;
import java.util.List;

public class TeacherEditCourseView extends View {

    public void displayHeader() {
        displayHeaderWithPseudo("Modifier un cours");
    }

    public void displayregistratedStudents(List<User> students) {
        if (students.size() > 0) {
            for (var student : students) {
                print(student.fullname + ", ");
            }
            println("");
        } else {
            println("Aucun inscrit");
        }
    }

    public void displayWaitingList(List<User> students) {
        if (students.size() > 0) {
            for (var student : students) {
                print(student.fullname + ", ");
            }
            System.out.println("");
        } else {
            println("Personne dans la liste d'attente");
        }
    }

    public void displayCourseInformation(Course course) {
        println("");
        println("ID: " + course.getId());
        println(("Code: " + course.getCode()));
        println("Description: " + course.getDescription());
        println("Enseignant: " + course.getTeacher().getFullname());
        println("Capacité totale: " + course.getCapacity());
        println("");

        println("Inscrits: ");
        displayregistratedStudents(course.getRegistratedStudents(course));
        println("");

        println("En attente: ");
        displayWaitingList(course.getInWaitingListStudents(course));
        println("");
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
        public void askCode() {
        print("Entrez le nouveau code (4 caractères): ");
    }
        public void badCode() {
        println("Entrez un code valide (4 caractères): ");
    }
        public void displayEditCodeConfirmation() {
        println("[O/N] Confirmer le nouveau code");
    }


    public void displayEditDescription() {
        println("[3] Modifier la description");
    }
        public void askDescription() {
        print("Entrez la nouvelle description: ");
    }
        public void displayEditDescriptionConfirmation() {
        println("[O/N] Confirmer la nouvelle description");
    }

    public void displayEditCapacity() {
        println("[4] Modifier la capacité");
    }
        public void askCapacity() {
        print("Entrez la nouvelle capacité (max ?): ");
    }
        public void badCapacity() {
        println("Entrez une capacité valide");
    }
        public void displayEditCapacityConfirmation() {
        println("[O/N] Confirmer la nouvelle capacité");
    }

    public void displayManageRegistrations() {
        println("[5] Gérer les inscriptions");
    }

    public void displayManageQuiz() {
        println("[6] Gérer les quiz");
    }

    public void displayFooter() {
        print("\n[R] Retour - [Q] Quitter\n");
    }

    public String askForString() {
        return askString("", "", false);
    }

}