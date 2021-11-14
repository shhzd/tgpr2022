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

    public void displayQuizzesList(List<Quiz> quizzes){
        println("N°    " + "    Nom du Quiz    " + " Date de début " + " Date de fin ");
        int i = 1;
        for(Quiz q : quizzes){
            println("["+i+"] " + q.getId() + q.getTitle() + q.getStart() + q.getFinish() + q.getcourseId());
        }
    }

    public View.Action askForAction(int maxNumber){
        return doAskForAction(maxNumber,"\n[A] Add [R] Return, [Q] Quit", "[aA]|[rR]|[qQ]");
    }
}
