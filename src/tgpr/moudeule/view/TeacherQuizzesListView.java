package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;

import java.util.List;

public class TeacherQuizzesListView extends View{
    private final Course course;

    public TeacherQuizzesListView(Course course){
        this.course = course;
    }

    public void displayHeader(){
        super.displayHeader("Liste des quiz pour le cours de " + course.getDescription());
    }

    public void displayQuizzesList (List<Quiz> quizzes){
        println("N°" + " Nom du Quiz " + " Date de début " + " Date de fin ");
        if (quizzes.size() > 1) {
            int i = 1;
            for (Quiz q : quizzes) {
                println(q.getId() +"     " +q.getTitle()+"      "+ q.getStart() +"    "+ q.getFinish());
                i++;
            }
        }
        print("[ID] Sélectionnez un quiz  ");
        println("[R] Retour");
    }

    public View.Action askForAction(int size){
        return doAskForAction(size,"\n[A] Add quiz, [S][ID] Select quiz, [R] Return",
                "[aA]+|[sS][0-9]+|[rR]");
    }
}
