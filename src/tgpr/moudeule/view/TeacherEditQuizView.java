package tgpr.moudeule.view;

import tgpr.moudeule.model.Question;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.model.VariableForTesting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TeacherEditQuizView extends View {

    public void displayHeaderWithPseudoAndPageNumber(Quiz quiz, int page, int lgPage) {
        super.displayHeaderWithPseudoAndPageNumber("Modifier un quiz - cours "   + quiz.getCourseCode(), page, lgPage);
        println("Date du jour : " + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(VariableForTesting.getCurrentDate()) + "\n");
    }

    public void displayTitle(Quiz quiz) {
        println("== " + quiz.getTitle() + " ==");
    }

    public void displayOption(Quiz quiz, int page, int nbPages, int lgPage) {
        println("\n[0] Supprimer le quiz");
        println("[1] Modifier le titre : " + quiz.getTitle());
        println("[2] Modifier la date de début : " + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(quiz.getStart()));
        println("[3] Modifier la date de fin : " + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(quiz.getFinish()) + "\n");
        List<Question> questions = Question.getQuestionsByQuiz(quiz.getId());
        if(questions.size() > 0) {
            int index = 4 + ((page - 1) * lgPage);
            int k = 0;
            for(int j = (page - 1) * lgPage; j < questions.size(); ++j) {
                if(j < questions.size() && k < lgPage) {
                    println("[" + index + "] Question " + (index - 3) + " : " + questions.get(j).getTitle());
                    ++index;
                    ++k;
                }
            }
        }
        println("\n[ESC] Retour - [Q] Quitter" + ((page != nbPages) ? " - [S] Page suivante " : "") +
                ((page > 1) ? " - [P] Page précédente " : ""));
    }

    public String askForString() {
        return askString("", "", false);
    }

    public String askForTitle(String actual) {
        return askString("Titre (" + ((actual != null) ? actual : "") + "): ", actual);
    }

    public LocalDate askForStartDate(LocalDate actual) {
        return askDate("Date de début (" +
                (actual == null ? "jj/mm/aaaa" : DateTimeFormatter.ofPattern("dd/MM/yyyy").format(actual)) + "): ", actual);
    }


    public void displayStartDateError(Quiz quiz, LocalDate newDate) {
        if(!quiz.newStartDateIsAfterCurrentDate(newDate)) {
            showError("La date de début ne peut être dans le passé !");
        } else if(!quiz.newStartDateIsBeforeCurrentFinisedDate(newDate)) {
            showError("La date de début ne peut pas être après la date de fin !");
        }
    }

    public void displayFinisedDateError(Quiz quiz, LocalDate newDate) {
        if(!quiz.newFinisedDateIsAfterCurrentDate(newDate)) {
            showError("La date de fin ne peut être dans le passé !");
        } else if(!quiz.newFinishedDateisAfterCurrentStartDate(newDate)) {
            showError("La date de fin ne peut pas être avant la date de début !");
        }
    }

    public LocalDate askForFinishDate(LocalDate actual) {
        return askDate("Date de fin (" +
                (actual == null ? "jj/mm/aaaa" : DateTimeFormatter.ofPattern("dd/MM/yyyy").format(actual)) + "): ", actual);
    }

}
