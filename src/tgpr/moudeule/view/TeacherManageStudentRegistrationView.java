package tgpr.moudeule.view;

import tgpr.moudeule.model.Course;
import tgpr.moudeule.model.User;

import java.util.List;

public class TeacherManageStudentRegistrationView extends View {
    public void displayHeaderWithCourse(String courseID) {
        displayHeader("Gestion des inscription - cours " + courseID);
    }

    public void displaySubMenuWithPage (int page, int nbPages){
        println("== Liste des étudiants - page " + page + " de " + nbPages + " ==\n");
    }

    public void displayMenu(List<User> students, Course course, int page, int nbPages, int lgPage) {

        println(" ID   CODE Cap  Description");

        for (int i = 0 ; (i + ((page - 1) * lgPage)) < students.size() && i < (lgPage + 1) ; i++) {
            User student = students.get(i + ((page - 1) * lgPage));
            println(prettyString(student, i, course));
        }
        println("   0  Ajouter un nouveau cours");
        println("\n[ID] Selectionner un cours " +
                ((page != nbPages) ? "- [S] page suivante " : "") +
                ((page > 1) ? "- [P] page précédente " : "") +
                "\n[Q] Quitter Moudeule"); //-
    }

    public String askForString() {
        return askString("", "", false);
    }

    private String prettyString(User student, int i, Course course) {

        return "[" + (i-1) +  "]" + student.getFullname() +
            "(" + student.getStatus(course) + ")";
    }

}