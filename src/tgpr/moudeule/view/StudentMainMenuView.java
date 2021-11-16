package tgpr.moudeule.view;



public class StudentMainMenuView extends View{
    public void displayMenu(){
        println("[1] Liste de cours disponibles");
        println("[2] Liste de cours inscrits");
        println("\n[Q] Quitter Moudeule");

    }
    public void displayHeader(){
        super.displayHeaderWithPseudo("Menu Principal");
    }
    public Action askForAction() {
        return doAskForAction(-1, "", "[qQ]|[1]|[2]");
    }
}

