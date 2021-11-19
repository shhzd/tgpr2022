package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TeacherAddQuizView extends View{

    public void displayHeader(Course course){
        super.displayHeader(" Ajouter un quiz - cours " + course.getCode());
    }

    public String askTitle(String actual){
        return askString("Ajoutez un titre : ",actual);
    }

    public LocalDate askStartDate(LocalDate actual){
        return askDate("Ajoutez la date de début ("+
                (actual == null ? "jj/mm/aaaa" : DateTimeFormatter.ofPattern("dd/MM/yyyy").format(actual)) + ") : ", actual);
    }

    public void displayStartDateError(Quiz quiz, LocalDate newDate) {
        if(!quiz.newStartDateIsAfterCurrentDate(newDate)) {
            showError("La date de début ne peut être dans le passé !");
        }
    }

    public LocalDate askFinishDate(LocalDate actual){
        return askDate("Ajoutez la date de fin : " +
                (actual == null ? "jj/mm/aaaa" : DateTimeFormatter.ofPattern("dd/MM/yyyy").format(actual)) + "): ", actual);
    }

    public void displayFinishDateError(Quiz quiz, LocalDate newDate) {
        if(!quiz.newFinishedDateisAfterCurrentStartDate(newDate)) {
            showError("La date de fin ne peut pas être avant la date de début !");
        }
    }

    public String askQuestionText(String actual){
        return askString("Ajoutez l'énoncé de la question : ", actual);
    }

    public void askQuestionType(){
        print("Est-ce une [1] QCM ou une [2] QRM ?");
    }

    public void askAddOption(){
        println("[O/N] Voulez-vous ajouter une proposition ? ");
    }

    public String enterOptionText(String actual){
        return askString("Entrez la proposition : ", actual);
    }

    public void enterOptionValue(){
        print("Cette proposition est-elle [1] vraie ou [2] fausse ? ");
    }

    public void askAddNewQuestion(){
        println("[O/N] Voulez vous ajouter une question ? ");
    }

    public String askForString() {
        return askString("", "", false);
    }
}
