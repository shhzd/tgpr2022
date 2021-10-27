package tgpr.moudeule.view;

import tgpr.moudeule.model.Test;
import tgpr.moudeule.model.VariableForTesting;

import java.time.LocalDate;
import java.util.List;

public class StudentTestsListView extends View {
    //public String afficheTestList(){}
    public void displayHeader(){displayHeader("Liste des tests");}

    public void displayOneTest(int n,Test t){
        String status = "";
        if(t.getStart() != null )
            status = "(test entamé)";
        if(VariableForTesting.getCurrentDate().compareTo())
    }
    public void displayTestsList(List<Test> test){

    }

}
