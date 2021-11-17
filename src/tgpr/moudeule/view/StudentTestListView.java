package tgpr.moudeule.view;

import tgpr.moudeule.controller.StudentTestListController;
import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;
import tgpr.moudeule.model.Test;
import tgpr.moudeule.model.User;

import java.time.LocalDate;
import java.util.List;

public class StudentTestListView extends View {
    public void displayHeaderWithCourseAndPage(String courseID, int page, int nbPages) {
        displayHeader("Liste des tests - cours " + courseID + " - page " + page + " de " + nbPages);
    }

    public void displayDate (LocalDate d) {
        println("Aujourd'hui : " + d + "\n");
    }

    /** It is all made in the view for now, but should be refactored in the controller
     */
    public void displayMenu(List<Quiz> quizzes, User student, Course course, int page, int nbPages, int lgPage) {
        for (int i = 0 ; (i + ((page - 1) * lgPage)) < quizzes.size() && i < (lgPage + 1) ; i++) {
            Quiz quiz = quizzes.get(i + ((page - 1) * lgPage));
            Test test = Test.getByQuizAndStudent(quiz, student);
            println(prettyString(quiz, test, i, course));
        }
        println("\n[n] Selectionner un test");
        println("[R] Retour - [Q] Quitter " +
            ((page > 1) ? "- [P] page précédente " : "") +
            ((page != nbPages) ? "- [S] page suivante " : ""));
    }

    public String askForString() {
        return askString("", "", false);
    }

    private String prettyString(Quiz quiz, Test test, int i, Course course) {
        return "[" + ((i < 9) ? " " : "") +
            (i+1) +  "] " + quiz.getTitle() +
            " - " + status(quiz, test);
    }

    private String status (Quiz quiz, Test test) {
        String status = "";
        if (test == null)
            if (quiz.getFinish().compareTo(StudentTestListController.getToday()) <= 0)
                status = "Ce test est clôturé, vous ne pouvez plus participer";
            else {
                status = (quiz.getStart().compareTo(StudentTestListController.getToday()) <= 0) ?
                        "Commencer le test, termine le " + quiz.getFinish() :
                        "Le test commence le " + quiz.getStart();
            }
        else
            status = test.getStatus(StudentTestListController.getToday());
            return status;
    }

    public Action askForAction() {
        return doAskForAction(-1,
                "", "[1]");
    }

    public Action askForConfirmation() {
        return doAskForAction(-1,
        "[O/N] Confirmer la suppression", "[oOnN]");
    }
}