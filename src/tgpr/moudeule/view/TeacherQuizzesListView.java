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
        for(Quiz q : quizzes){
            println(q.getId() + q.getTitle() + q.getStart() + q.getFinish());
        }
    }

    public View.Action askForAction(int size){
        return doAskForAction(size,"\n[A] Add quiz, [S] Select, [R] Return, [L] Leave",
                "[aA]+|[sS][0-9]+|[rR]+|[lL]");
    }
}
