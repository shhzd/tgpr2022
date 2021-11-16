package tgpr.moudeule.view;

import tgpr.moudeule.model.Option;
import tgpr.moudeule.model.Question;

import java.util.List;

public class TeacherEditQuizQuestionView extends View {

    public void displayHeaderWithPseudoAndPageNumber(Question question, int page, int lgPage) {
        super.displayHeaderWithPseudoAndPageNumber("Modifier une question - cours "   + question.getCourseCodeByQuestionId(), page, lgPage);
    }

    public void displayTitle(Question question) {
        println("=== " + question.getTitle() + " ===");
    }

    public void displayType(Question question) {
        println("\nType " + question.getType().toUpperCase());
    }

    public void displayOption(Question question, int page, int lgPage) {
        println("\n[1] Modifier l'énoncé : " + question.getTitle());
        println("[2] Modifier le type : " + question.getType().toUpperCase() + "\n");
        List<Option> options = Option.getOptionsByQuestion(question.getId());
        if(options.size() > 0) {
            int index = (page - 1) * lgPage + 3;
            int j = 0;
            for(int k = (page - 1) * lgPage; k < options.size(); ++k) {
                if(k < options.size() && j < lgPage) {
                    printf("%s %s\n","[" + index + "] " + options.get(k).getTitle(), (options.get(k).getCorrect() == 1 ? " Vrai" : " Faux"));
                    ++index;
                    ++j;
                }
            }
        }
        print("\n[0] Ajouter une proposition");
    }

    public void displaySubOptions() {
        println("\n[0] Pour quitter le menu de modifiction");
        println("[1] Modifier le texte de la proposition");
        println("[2] Modifier la réponse à la proposition");
        println("[3] Supprimer la proposition");
    }

    public void displayConfirmation() {
        println("\n[O/N] Confirmer la modification");
    }

    public void displayBottomOptions(int page, int nbPages){
        println("\n[R] Retour - [Q] Quitter" + ((page != nbPages) ? " - [S] Page suivante " : "") +
                ((page > 1) ? " - [P] Page précédente " : ""));
    }

    public String askForString() {
        return askString("", "", false);
    }


    public String askForTitle(String actual) {
        return askString("Titre (" + ((actual != null) ? actual : "") + "): ", actual);
    }

    public String askForType(String actual) {
        return askString("Type (" + ((actual != null) ? actual : "") + "): ", actual);
    }

    public String askForOptionTitle() {
        return askString("Proposition : ", "");
    }

    public String askForOptionTitle(String actual) {
        return askString("Proposition (" + ((actual != null) ? actual : "") + "): ", actual);
    }

    public int askForOptionCorrect() {
        return askInteger("Correct (0 pour Faux - 1 pour Vrai) : ", 0);
    }

    public int askForOptionCorrect(int actual) {
        return askInteger("Correct (0 pour Faux - 1 pour Vrai) : (" +  actual  + ") ", actual);
    }

    public void showTypeError() {
        showError("Le type n'est pas valide. Veuillez choisir entre \"QCM\" et \"QRM\".");
    }

    public void showTypeErrorManyCorrectAnswers() {
        showError("Impossible de transformer une question QRM à QCM lorsqu'il y a plusieurs réponses correctes. Veuillez choisir une réponse unique avant.");
    }

    public void showTypeErrorNoCorrectAnswer() {
        showError("Impossible de changer la réponse, un QRM doit contenir au moins une bonne réponse.");
    }
}
