package tgpr.moudeule.view;


import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;

import java.util.List;

public class TeacherAddStudentView extends View {

    public void displayHeaderWithCourse(String courseID) {
        displayHeader("Gestion des inscription - cours " + courseID);
    }

    public void displaySubHeaderWithPage(int page, int nbPages){
        println("== Liste des étudiants - page " + page + " de " + nbPages + " ==\n");
    }

    public void displayNewStudents(List<User> sUsers) {
        int i = 1;
        for(User s:sUsers){
            println("[" + i  + "]" + s.getFullname());
            i++;

        }
        println("\n[n] Selectionner un étudiant");
        println("[R] Retour - [Q] Quitter ");
    }
    public String askForString() {
        return askString("", "", false);
    }

    private String prettyString(User student, int i, Course course) {
        return "[" + ((i < 9) ? " " : "") +
                (i+1) +  "] " + student.getFullname();
    }

    public void displaySubMenu(User Student, String status) {
        switch (status) {
            case "1":
                print("Voulez vous : [1] Inscrire un étudiant " + Student.getFullname());
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
