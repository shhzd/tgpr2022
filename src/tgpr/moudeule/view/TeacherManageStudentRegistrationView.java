package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;

import java.util.List;

public class TeacherManageStudentRegistrationView extends View {
    public void displayHeaderWithCourse(String courseID) {
        displayHeader("Gestion des inscription - cours " + courseID);
    }

    public void displaySubHeaderWithPage(int page, int nbPages){
        println("== Liste des étudiants - page " + page + " de " + nbPages + " ==\n");
    }

    public void displayCourseCapacity(int activeStudent, int courseCapacity) {
        println("Capacité annoncée du cours : " + courseCapacity + ", actuellement : " + activeStudent + " inscriptions confirmées");
        if (activeStudent > courseCapacity)
            this.warning("Capacité dépassée de : " + (activeStudent - courseCapacity) + " étudiant" +
                ((((activeStudent - courseCapacity) > 1)) ? "s." : "."));
        println("");
    }

    public void displayMenu(List<User> students, Course course, int page, int nbPages, int lgPage) {

        for (int i = 0 ; (i + ((page - 1) * lgPage)) < students.size() && i < (lgPage + 1) ; i++) {
            User student = students.get(i + ((page - 1) * lgPage));
            println(prettyString(student, i, course));
        }
        println("\n[n] Selectionner un étudiant - [I] Inscrire d'office un étudiant ");
        println("[R] Retour - [Q] Quitter " +
            ((page > 1) ? "- [P] page précédente " : "") +
            ((page != nbPages) ? "- [S] page suivante " : ""));
    }

    public String askForString() {
        return askString("", "", false);
    }

    private String prettyString(User student, int i, Course course) {
        return "[" + ((i < 9) ? " " : "") +
            (i+1) +  "] " + student.getFullname() +
            " - (" + student.getStatus(course) + ")";
    }

    public void displaySubMenu(User Student, String status) {
        switch (status) {
            case "en attente":
                print("Voulez vous : [1] Activer " + Student.getFullname());
                break;
            case "actif":
                print("Voulez vous : [1] Supprimer " + Student.getFullname());
                break;
        }
    }

    public void showWarning() {
        this.warning("Attention, cette opération est irréversible !");
    }

    public View.Action askForAction() {
        return doAskForAction(-1,
                "", "[1]");
    }

    public View.Action askForConfirmation() {
        return doAskForAction(-1,
        "[O/N] Confirmer la suppression", "[oOnN]");
    }
}