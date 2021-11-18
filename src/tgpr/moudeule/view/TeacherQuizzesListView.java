package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.Quiz;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TeacherQuizzesListView extends View{
    private final Course course;

    public TeacherQuizzesListView(Course course){
        this.course = course;
    }

    public void displayHeader(){
        super.displayHeader("Gestion des quiz - " + course.getDescription() + " " + course.getCode());
    }

    public void displayQuizzesList (List<Quiz> quizzes){
        println("                Date de début " + " Date de fin ");
        if (quizzes.size() > 1) {
            int i = 1;
            for (Quiz q : quizzes) {
                println(i +"     " +q.getTitle()+"      "+ DateTimeFormatter.ofPattern("dd/MM/yyyy").format(q.getStart())
                        +"    "+ DateTimeFormatter.ofPattern("dd/MM/yyyy").format(q.getFinish()));
                i++;
            }
        }
        println("\n[0] Créer un nouveau quiz \n[n] Sélectionner un quiz " +
                "\n[Esc] Retour - [Q] Quitter");
    }

    /*public View.Action askForAction(int size){
        return doAskForAction(size,"\n[0] Créer un nouveau quiz \n[n] Sélectionner un quiz " +
                        "\n[R] Retour - [Q] Quitter \n[D] Changer la date courante",
                "[0]+|[1-9]+|[rR]+|[qQ]+|[dD]");
    }*/

    public String askForString() {
        return askString("", "", false);
    }

}
