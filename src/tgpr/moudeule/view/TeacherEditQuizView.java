package tgpr.moudeule.view;

import tgpr.moudeule.model.Question;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.model.VariableForTesting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TeacherEditQuizView extends View {

    public void displayHeader(Quiz quiz) {
        super.displayHeader("Modifier un quiz - cours " + quiz.getCourseCode());
    }

    public void displayTitle(Quiz quiz) {
        println("== " + quiz.getTitle() + " ==");
    }

    public void displayOption(Quiz quiz, int page, int nbPages, int lgPage) {
        println("[ESC] Pour abandonner la modification");
        println("\n[1] Modifier le titre : " + quiz.getTitle());
        println("Current date " + VariableForTesting.getCurrentDate());
        println("[2] Modifier la date de début : " + quiz.getStart());
        println("[3] Modifier la date de fin : " + quiz.getFinish());
        println("\n");
        List<Question> questions = Question.getQuestionsByQuiz(quiz.getId());
        if(questions.size() > 0) {
            int index = 4 + ((page - 1) * lgPage);
            int k = 0;
            for(int j = (page - 1) * lgPage; j < questions.size(); ++j) {
                if(j < questions.size() && k < lgPage) {
                    println("[" + index + "] la question " + (index - 3) + " : " + questions.get(j).getTitle());
                    ++index;
                    ++k;
                }
            }
        }
        println("\n[R] Retour - [Q] Quitter" + ((page != nbPages) ? " - [S] Page suivante " : "") +
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

    public LocalDate askForFinishDate(LocalDate actual) {
        return askDate("Date de fin (" +
                (actual == null ? "jj/mm/aaaa" : DateTimeFormatter.ofPattern("dd/MM/yyyy").format(actual)) + "): ", actual);
    }
}
