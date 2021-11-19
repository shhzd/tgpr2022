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
        return askString("Pseudo (3 caractères min): ", actual);
    }

    public String askPassword(String actual) {
        String stars = "min 3 caractères";
        if (actual != null)
            stars = String.join("", Collections.nCopies(actual.length(), "*"));
        return askString("Mot de passe (" + stars + "): ", actual, true);
    }

    public String askPasswordConfirm(String actual) {
        String stars = "le même mot de passe";
        if (actual != null)
            stars = String.join("", Collections.nCopies(actual.length(), "*"));
        return askString("Confirmer le mot de passe (" + stars + "): ", actual, true);
    }

    public String askFullname(String actual) {
        return askString("Nom complet: ", actual);

    }

    public LocalDate askBirthDate(LocalDate actual) {
        return askDate("Date de naissance (JJ/MM/AAAA): ", actual);
    }

    public void showEnteredData(String pseudo, String fullname, LocalDate birthdate) {
        println("\nVous avez entré");
        println("pseudo: " + pseudo);
        println("fullname: " + fullname);
        println("date de naissance: " + birthdate);
    }

    public View.Action askForConfirmation() {
        return doAskForAction(-1, "\n[O/N] Confirmer les données saisies]", "[oOnN]");
    }

}
