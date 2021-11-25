package tgpr.moudeule.view;

import tgpr.moudeule.model.User;

public class TeacherAddStudentView extends View {

    public void displayHeaderWithCourse(String courseID) {
        displayHeader("Gestion des inscriptions - cours " + courseID);
    }

    public void displaySubHeaderWithPage(int p, int n){
        println("== Liste des étudiants" + pageNbr(p, n) + " ==\n");
    }

    public void displayStudent(String fullname, int i) {
        println( "[" + ((i < 9) ? " " : "") + (i+1) +  "] " + fullname);
    }

    public void displayEmptyListMenu() {
        println("Aucun disponible");
    }

    public void displayMenu() {
        println("\n[n] Selectionner un étudiant");
    }

    public String askForString() {
        return askString("", "", false);
    }

    public void displaySubMenu(User Student) {
        print("Voulez vous : [1] Inscrire " + Student.getFullname());
    }

    public View.Action askForAction() {
        return doAskForAction(-1, "", "[1]");
    }
}
