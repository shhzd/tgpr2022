package tgpr.moudeule.view;



public class TeacherAddStudentView extends View {
    public void displayHeader(){
        super.displayHeader("Gestion de Inscription");
    }




    public void displayMenu(){
        println("[\n n]  Sélectionner un étudiant");
        println("[R] Retour");
        println("[Q] Quitter");
        println("[P] Page précédente");
        println("[S] Page Suivante");

    }
    public Action askForAction() {
        return doAskForAction(-1, "", "[qQ]|[rR]|[pP]|[sS]");
    }


}
