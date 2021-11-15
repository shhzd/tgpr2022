package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Question;
import tgpr.moudeule.model.Quiz;

import java.util.List;

public class TeacherEditQuizView extends View {

    public void displayHeader(Course course) {
        super.displayHeader("Modifier un quiz - cours " + course.getId());
    }

    public void displayTitle(Quiz quiz) {
        println("== " + quiz.getTitle() + " ==");
    }

    public void displayOption(Quiz quiz, int page, int nbPages, int lgPage) {
        println("[ESC] Pour abandonner la modification");
        println("\n[1] Modifier le titre : " + quiz.getTitle());
        println("[2] Modifier la date de début : " + quiz.getClass());
        println("[3] Modifier la date de fin : " + quiz.getFinish());
        List<Question> questions = Question.getQuestionsByQuiz(quiz.getId());
        if(questions.size() > 0) {
            int index = 4 + (page * lgPage);
            for(int j = (page - 1) * lgPage; j < questions.size(); ++j) {
                if(j < questions.size() && j < lgPage) {
                    println("[" + index + "] la question " + (index - 3) * (page * lgPage) + " : " + questions.get(j).getTitle());
                }
            }
        }
        println("\n[R] Retour - [Q] Quitter - " + (((page) != nbPages) ? " - [S] Page suivante " : "") +
                ((page > 1) ? " - [P] Page précédente " : ""));
    }
}
