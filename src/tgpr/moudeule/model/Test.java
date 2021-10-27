package tgpr.moudeule.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Test extends Model {
    private int id;
    private String student;
    private int quizId;
    private LocalDate start;

    public Test() {
    }

    public Test(int id, String student, int quizId, LocalDate start) {
        this.id = id;
        this.student = student;
        this.quizId = quizId;
        this.start = start;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public int getquizId() {
        return quizId;
    }

    public void setquizId(int quizId) {
        this.quizId = quizId;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", student='" + student + '\'' +
                ", quizId=" + quizId +
                ", start=" + start +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id && quizId == test.quizId && Objects.equals(student, test.student) && Objects.equals(start, test.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, quizId, start);
    }

    public static void mapper(ResultSet rs, Test test) throws SQLException {
        test.id = rs.getInt("id");
        test.student = rs.getString("student");
        test.quizId = rs.getInt("quiz");
        test.start = rs.getObject("start", LocalDate.class);
    }
    public LocalDate getQuizEndDate(){
        LocalDate d ;
        try {
            var stmt = Model.db.prepareStatement("Select finish from Quizzes join Tests on quizzes.id = ?");
            stmt.setInt(1,quizId);
            var rs = stmt.executeQuery();
            if(rs.next()){
                d = rs.getObject("finish",LocalDate.class);
                //Prend le res de le la requete qui est stocker dans rs et le transforme en objet et , je recupere la colonne finish et je transforme la donée dans la col finish en localdate
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
