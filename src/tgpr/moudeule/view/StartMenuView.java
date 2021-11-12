package tgpr.moudeule.view;

public class StartMenuView extends View {

    public void displayHeaderWithLogo() {
        displayHeaderWithLogo("Start Menu");
    }

    public void displayMenu() {
        println("[1] S'identifier");
        println("[2] Enregistrer un nouveau compte");
        println("\n[Q] Quitter");
    }

    public Action askForAction() {
        return doAskForAction(-1, "", "[qQ]|[1]|[2]");
    }
}