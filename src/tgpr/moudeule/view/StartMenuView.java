package tgpr.moudeule.view;

public class StartMenuView extends View {

    public void displayHeaderWithLogo() {
        displayHeaderWithLogo("Start Menu");
    }

    public void displayMenu() {
        println("[C] Se Connecter");
        println("[E] S'enregistrer");
        println("\n[Q] Quitter");
    }

    public Action askForAction() {
        return doAskForAction(-1, "", "[qQ]|[cC]|[eE]");
    }
}