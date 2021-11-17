package tgpr.moudeule.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Quiz extends Model {
    private int id;
    private String title;
    private LocalDate start;
    private LocalDate finish;
    private int courseId;

    public Quiz() {
    }

    public Quiz(int id, String title, LocalDate start, LocalDate finish, int courseId) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.finish = finish;
        this.courseId = courseId;
    }

    public static void getQuizById(int quizId) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }

    public int getcourseId() {
        return courseId;
    }

    public void setcourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return id == quiz.id && courseId == quiz.courseId && Objects.equals(title, quiz.title) && Objects.equals(start, quiz.start) && Objects.equals(finish, quiz.finish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, start, finish, courseId);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", start=" + start +
                ", finish=" + finish +
                ", courseId=" + courseId +
                '}';
    }

    public static void mapper(ResultSet rs, Quiz quiz) throws SQLException {
        quiz.id = rs.getInt("id");
        quiz.title = rs.getString("title");
        quiz.start = rs.getObject("start", LocalDate.class);
        quiz.finish = rs.getObject("finish", LocalDate.class);
        quiz.courseId = rs.getInt("course");
    }

    public static List<Quiz> getQuizzesBycourseId(int id) {
        var list = new ArrayList<Quiz>();
        try {
            var stmt = db.prepareStatement("SELECT * FROM quizzes WHERE course IN (SELECT id FROM courses WHERE id = ?)");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var quizz = new Quiz();
                mapper(rs, quizz);
                list.add(quizz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Quiz getById(int id) {
        Quiz quiz = null;
        try {
            var stmt = db.prepareStatement("SELECT * FROM quizzes WHERE id = ?");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                quiz = new Quiz();
                mapper(rs, quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quiz;
    }

    public boolean save() {
        Quiz q = getById(id);
        int count = 0;
        try {
            PreparedStatement stmt;
            if (q == null) {
                stmt = db.prepareStatement("insert into quizzes (title, start, finish, course) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, title);
                stmt.setObject(2, start);
                stmt.setObject(3, finish);
                stmt.setInt(4, courseId);
            } else {
                stmt = db.prepareStatement("update quizzes set title=?, start=?, finish=?, course=? where id=?");
                stmt.setString(1, title);
                stmt.setObject(2, start);
                stmt.setObject(3, finish);
                stmt.setInt(4, courseId);
                stmt.setInt(5, id);
            }
            count = stmt.executeUpdate();
            if (q == null)
                id = this.getLastInsertId(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count == 1;
    }

    public String getCourseCode() {
        String result = "";
        try {
            var stmt = db.prepareStatement("SELECT code FROM courses WHERE id IN (SELECT course FROM quizzes WHERE id = ?)");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean newStartDateIsBeforeCurrentFinisedDate(LocalDate newStartDate) {
        return newStartDate.compareTo(this.finish) <= 0;
    }

    public boolean newStartDateIsAfterCurrentDate(LocalDate newStartDate) {
        return newStartDate.compareTo(VariableForTesting.getCurrentDate()) >= 0;
    }

    public boolean isValidNewStartDate(LocalDate newStartDate) {
        return newStartDateIsBeforeCurrentFinisedDate(newStartDate) && newStartDateIsAfterCurrentDate(newStartDate);
    }

    public boolean newFinishedDateisAfterCurrentStartDate(LocalDate newFinishedDate) {
        return newFinishedDate.compareTo(this.start) >= 0;
    }

    public boolean newFinisedDateIsAfterCurrentDate(LocalDate newDate) {
        return newDate.compareTo(VariableForTesting.getCurrentDate()) >= 0;
    }

    public boolean isValidNewFinishedDate(LocalDate newFinishedDate) {
        return newFinishedDateisAfterCurrentStartDate(newFinishedDate) && newFinisedDateIsAfterCurrentDate(newFinishedDate);
    }


    public static Quiz getByQuestionId(int id) {
        Quiz quiz = null;
        try {
            var stmt = db.prepareStatement("SELECT quizzes.* FROM quizzes JOIN questions ON quizzes.id = questions.quiz WHERE questions.id = ?");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                quiz = new Quiz();
                mapper(rs, quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quiz;
    }
}