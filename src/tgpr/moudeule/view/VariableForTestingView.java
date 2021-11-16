package tgpr.moudeule.view;

import tgpr.moudeule.model.VariableForTesting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VariableForTestingView extends View {

    public void displayHeader() {
        super.displayHeader("Ecran d'entrée de variables");
    }

    public void displayCurrentDate() {
        println("La date actuelle est " +  DateTimeFormatter.ofPattern("dd/MM/yyyy").format(VariableForTesting.getCurrentDate()) + "\n");
    }

    public Action askForAction() {
        return doAskForAction(-1, "[1] Entrer une nouvelle date\n[2] Utiliser Moudeule\n[Q] Quitter", "[qQ]|[1]|[2]");
    }

    public LocalDate askForDate(LocalDate actual) {
        return askDate("Date actuelle (" +
                (actual == null ? "jj/mm/aaaa" : DateTimeFormatter.ofPattern("dd/MM/yyyy").format(actual)) + "): ", actual);
    }
}
