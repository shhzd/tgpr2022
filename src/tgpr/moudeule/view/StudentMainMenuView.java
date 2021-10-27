package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import java.util.List;

public class StudentMainMenuView extends View{
    public void displayMenu(){
        println("[D] Liste de course disponible");
        println("[I] List de course inscrit");
    }
    public Action askForAction() {
        return doAskForAction(-1, "", "[dD]|[qQ]");
    }
}

