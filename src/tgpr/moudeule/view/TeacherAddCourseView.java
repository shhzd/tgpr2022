package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;

public class TeacherAddCourseView extends View{

    public void displayHeader() {super.displayHeader("Ajouter un nouveau cours");}

    public void displayMenu() {
        println("[ESC] Retour\n");
    }

    public int askID(int actual) {
        return askInteger("entrez une ID " + "(" +
                ((actual != 0) ?  actual : "4 chiffres") + ") : ", actual);
    }

    public String askCode(String actual) {
        return askString("entrez un code " + "(" +
                ((actual != null) ? actual : "4 carractères") + ") : ", actual);
    }

    public String askDescription(String actual) {
        return askString("entrez une description " + "(" +
                ((actual != null) ? actual : "max 60 carractères") + ") : ", actual);
    }

    public int askCapacity(int actual) {
        return askInteger("entrez une capacité " + "(" +
                ((actual != 0) ? actual : "max 28") + ") : ", actual);
    }

    public View.Action askForAction() {
        return doAskForAction(-1, "\n" +
                "[O/N] Confirmer la création", "[oOnN]");
    }

}
