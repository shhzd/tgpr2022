package tgpr.moudeule.view;

import tgpr.moudeule.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class SignupView extends View{

    public void displayHeader() {
        super.displayHeader("S'enregistrer");
    }

    public void displayMenu() {
        println("[ESC] Retour\n");
    }

    public String askPseudo(String actual) {
        return askString("Pseudo (" + ((actual != null) ? actual : "") + "): ", actual);
    }

    public String askPassword(String actual) {
        String stars = "min 3 caractères";
        if (actual != null)
            stars = String.join("", Collections.nCopies(actual.length(), "*"));
        return askString("Mot de passe (" + stars + "): ", actual, true);
    }

    public String askFullname(String actual) {
        return askString("Nom complet (" + ((actual != null) ? actual : "") + "): ", actual);

    }

    public LocalDate askBirthDate(LocalDate actual) {
        return askDate("Date de naissance (" +
                (actual == null ? "jj/mm/aaaa" : DateTimeFormatter.ofPattern("dd/MM/yyyy").format(actual)) + "): ", actual);
    }

    public View.Action askForAction() {
        return doAskForAction(-1, "\n[V] Valider, [A] Annuler", "[vVaA]");
    }

}
