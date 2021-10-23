package tgpr.moudeule.view;

import tgpr.moudeule.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class SignupView extends View{

    public void displayHeader() {
        super.displayHeader("S'enregistrer");
    }

    public String askPseudo(String actual) {
        return askString("Pseudo (" + actual + "): ", actual);
    }

    public String askPassword(String actual) {
        String stars = null;
        if (actual != null)
            stars = String.join("", Collections.nCopies(actual.length(), "*"));
        return askString("Mot de passe (" + stars + "): ", actual, true);
    }

    public String askFullname(String actual) {
        return askString("Nom complet (" + actual + "): ", actual);
    }

    public LocalDate askBirthDate(LocalDate actual) {
        return askDate("Date de naissance (" +
                (actual == null ? "null" : DateTimeFormatter.ofPattern("dd/MM/yyyy").format(actual)) + "): ", actual);
    }

    public View.Action askForAction() {
        return doAskForAction(-1, "\n[V] Valider, [A] Annuler", "[vVaA]");
    }

}
