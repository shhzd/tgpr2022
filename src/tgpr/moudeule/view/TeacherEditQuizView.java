package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;

public class TeacherEditQuizView extends View {

    public void displayHeader(Course course) {
        super.displayHeader("Modifier un quiz - cours " + course.getId());
    }

    public void displayTitle(Quiz quiz) {
        println("== " + quiz.getTitle() + " ==");
    }

}
