package tgpr.moudeule.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public String getTitle() {
        String title = "";
        try {
            var stmt = db.prepareStatement(
                    "SELECT quizzes.title FROM quizzes WHERE id = ?;");
            stmt.setInt(1,quizId);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                title = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }

    public String getStatus(LocalDate today) {
        String status = "";
        Quiz quiz = Quiz.getById(this.quizId);
        if (quiz != null)
            if (today.compareTo(quiz.getFinish()) > 0)
                status = "Test cloturé, voir le résultat";
            else
                status = "Test en cours jusqu'au " + quiz.getFinish() +", éditer les réponses";
        return status;
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

    public static List<Test> getByCourseAndStudent(Course course, User student) {
        List<Test> tests = new ArrayList<>();

        try {
            var stmt = db.prepareStatement(
                    "SELECT * FROM tests WHERE tests.quiz IN"+
                        "(SELECT quizzes.id FROM quizzes WHERE quizzes.course = ?)" +
                        "AND tests.student = ?;"
            );
            stmt.setInt(1, course.getId());
            stmt.setString(2, student.getPseudo());
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var test = new Test();
                Test.mapper(rs, test);
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public static Test getByQuizAndStudent(Quiz quiz, User student) {
        Test test = null;
        try {
            var stmt = db.prepareStatement(
                    "SELECT * FROM tests WHERE tests.quiz = ?;"
            );
            stmt.setInt(1, quiz.getId());
            var rs = stmt.executeQuery();
            if (rs.next()) {
                var t = new Test();
                Test.mapper(rs, t);
                test = t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }
}
