package tgpr.moudeule.view;

import tgpr.moudeule.model.Question;

public class TeacherEditQuizQuestionView extends View {

    public void displayHeader(Question question){
        super.displayHeader("Modifier une question - Cours " + question.getCourseCodeByQuestionId());
    }

    public void displayTitle(Question question) {
        println("=== " + question.getTitle() + " ===");
    }

    public void displayType(Question question) {
        println("Type " + question.getType());
    }

    public void displayOption(Question question, int page, int nbPage, int lgPage) {
        println("\n[1] Modifier le titre : " + question.getTitle());
        println("\n[2] Modifier le type : " + question.getType() + "\n");

    }
}
