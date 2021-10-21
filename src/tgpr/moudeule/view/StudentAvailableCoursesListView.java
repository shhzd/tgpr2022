package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;

import java.util.List;

public class StudentAvailableCoursesListView extends View {

    public void displayHeader() {
        super.displayHeader("List des cours disponibles");
    }

    public void displayOneCourse(int i, Course course) {
        int left = course.getLeftPlaces(); // has to change
        printf("[%2d] %s %s\n", i,  course.getId(), course.getCode(), course.getDescription(), left, course.getTeacher());
    }

    public void displayCourses(List<Course> courses) {
        int i = 1;
        for (var course : courses) {
            displayOneCourse(i, course);
            ++i;
        }
    }
}
