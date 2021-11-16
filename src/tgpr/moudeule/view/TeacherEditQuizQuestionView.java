package tgpr.moudeule.view;

import tgpr.moudeule.model.Option;
import tgpr.moudeule.model.Question;

import java.util.List;

public class TeacherEditQuizQuestionView extends View {

    public void displayHeader(Question question){
        super.displayHeader("Modifier une question - Cours " + question.getCourseCodeByQuestionId());
    }

    public void displayTitle(Question question) {
        println("=== " + question.getTitle() + " ===");
    }

    public void displayType(Question question) {
        println("Type " + question.getType());
    }

    public void displayOption(Question question, int page, int nbPages, int lgPage) {
        println("\n[1] Modifier le titre : " + question.getTitle());
        println("[2] Modifier le type : " + question.getType() + "\n");
        List<Option> options = Option.getOptionsByQuestion(question.getId());
        if(options.size() > 0) {
            int index = 3;
            int j = 0;
            for(int k = (page - 1) * lgPage; k < options.size(); ++k) {
                if(k < options.size() && j < lgPage) {
                    println("[" + index + "] Proposition " + (char)('A' + j) + "     " + (options.get(j).getCorrect() == 1 ? " Vrai" : " Faux"));
                    ++index;
                    ++j;
                }
            }
        }
        print("\n[0] Ajouter une proposition");

        println("\n[R] Retour - [Q] Quitter" + ((page != nbPages) ? " - [S] Page suivante " : "") +
                ((page > 1) ? " - [P] Page précédente " : ""));
    }


}
