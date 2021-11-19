package tgpr.moudeule.view;

import tgpr.moudeule.model.Option;
import tgpr.moudeule.model.Question;
import tgpr.moudeule.model.Test;

import java.util.*;

public class StudentActiveTestView extends View {

    void displayHeader(Test test) {
        super.displayHeader("test " + test);
    }

    void displayQuestion(int order) {
        println("== QUESTION " + order + " ==");
    }

    void displayStatement(Question question) {
        println(question.getTitle());
    }

    void shuffleOptions(List<Option> options) {

    }

    void displayPropositions(List<Option> options) {
        Random r = new Random();
        Collections.shuffle(options);
    }

}
