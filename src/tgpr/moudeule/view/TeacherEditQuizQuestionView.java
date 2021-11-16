package tgpr.moudeule.view;

import tgpr.moudeule.model.Question;

public class TeacherEditQuizQuestionView extends View {

    public void displayHeader(Question question){
        super.displayHeader("Modifier une question - Cours " + question.getCourseCodeByQuestionId());
    }
}
