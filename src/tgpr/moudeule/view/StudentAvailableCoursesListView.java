package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;

import java.util.List;

public class StudentAvailableCoursesListView extends View {

    public void displayHeader() {
        super.displayHeader("Liste des cours disponibles");
    }

    public void displayOneCourse(int i, Course course) {
        printf("[%2d] %s %s\n", i, course.getCode(), course.getDescription(), course.getTeacher());
    }

    public void displayCourses(List<Course> courses) {
        int i = 1;
        for (var course : courses) {
            displayOneCourse(i, course);
            ++i;
        }
    }

    public View.Action askForAction(int size) {
        return doAskForAction(size, "\n[I] S'inscrire, [D] Désinscrition, [V] Détails, [Q] Quitter",
                "[vV][0-9]+|[fF][0-9]+|[uU][0-9]+|[lL]");
    }

}
