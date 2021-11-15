package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;

public class TeacherEditQuizView extends View {

    public void displayHeader(Course course) {
        super.displayHeader("Modifier un quiz - cours " + course.getId());
    }

}
