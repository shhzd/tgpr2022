package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import java.util.List;

public class StudentMainMenuView extends View{
    public void displayMenu(){
        println("[D] Liste de cours disponibles");
        println("[I] Liste de cours inscrits");
    }
    public void displayHeader(){
        super.displayHeader("Ecran d'accueil");
    }
    public Action askForAction() {
        return doAskForAction(-1, "", "[dD]|[Ii]");
    }
}

