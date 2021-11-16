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


}
