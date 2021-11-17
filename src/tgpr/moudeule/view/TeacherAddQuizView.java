package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import java.time.LocalDate;


public class TeacherAddQuizView extends View{
    private final Course course;

    public TeacherAddQuizView(Course course){
        this.course = course;
    }

    public void displayHeader(){
        super.displayHeader(" Créez un nouveau quiz ");
    }

    public String askTitle(String actual){
        return askString("Veuillez entrer un titre pour le quiz : ",actual);
    }

    public LocalDate askStartDate(LocalDate actual){
        return askDate("Veuillez entrer une date de début pour le quiz : ", actual);
    }

    public LocalDate askFinishDate(LocalDate actual){
        return askDate("Veuillez entrer une date de fin pour le quiz : ", actual);
    }
}
