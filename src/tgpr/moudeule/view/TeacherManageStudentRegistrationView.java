package tgpr.moudeule.view;

public class TeacherManageStudentRegistrationView extends View {
    public void displayHeaderWithCourse(String courseID) {
        displayHeader("Gestion des inscriptions - cours " + courseID);
    }

    public void displaySubHeaderWithPage(int p, int n){
        println("== Liste des étudiants" + pageNbr(p, n) + " ==\n");
    }

    public void displayCourseCapacity(int activeStudent, int courseCapacity) {
        println("Capacité annoncée du cours : " + courseCapacity + ", actuellement : " + activeStudent + " inscriptions confirmées");
        if (activeStudent > courseCapacity)
            this.warning("Capacité dépassée de : " + (activeStudent - courseCapacity) + " étudiant" +
                ((((activeStudent - courseCapacity) > 1)) ? "s." : "."));
        println("");
    }

    public void displayStudent(String fullname, int i, String status ) {
        println( "[" + ((i < 9) ? " " : "") + (i+1) +  "] " + fullname + " - (" + status + ")");
    }

    public void displayEmptyListMenu() {
        println("Aucun étudiant inscrit \n\n[I] Inscrire d'office un étudiant ");
    }

    public void displayMenu() {
        println("\n[n] Selectionner un étudiant - [I] Inscrire d'office un étudiant ");
    }

    public void displaySubMenu(String fullname, String status) {
        switch (status) {
            case "en attente":
                print("Voulez vous : [1] Activer - [2] Refuser : " + fullname);
                break;
            case "actif":
                print("Voulez vous : [1] Supprimer " + fullname);
                break;
        }
    }

    public void showWarning() {
        this.warning("Attention, cette opération est irréversible !");
    }

    public String askForString() {
        return askString("", "", false);
    }

    public View.Action askForAction() {
        return doAskForAction(-1, "", "[1-2]");
    }

    public View.Action askForConfirmation() {
        return doAskForAction(-1, "[O/N] Confirmer la suppression", "[oOnN]");
    }
}