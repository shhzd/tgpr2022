package tgpr.moudeule.view;

public class TeacherAddCourseView extends View{

    public void displayHeader() {super.displayHeader("Ajouter un noueau cours");}

    public int askID(int actual) {
        return askInteger("entrez une ID " +
                ((actual != 0) ? "(" + actual + ") " : "") +
                ": ", actual);
    }

    public String askCode(String actual) {
        return askString("entréz un code " +
                ((actual != null) ? "(" + actual + ") ": "") +
                ": ", actual);
    }

    public String askDescription(String actual) {
        return askString("entréz une description " +
                ((actual != null) ? "(" + actual + ") " : "") +
                ": ", actual);
    }

    public int askCapacity(int actual) {
        return askInteger("entréz une capacité " +
                ((actual != 0) ? "(" + actual + ") " : "") +
                ": ", actual);
    }

    public View.Action askForAction() {
        return doAskForAction(-1, "\n[V] Valider, [A] Annuler", "[vVaA]");
    }

}
