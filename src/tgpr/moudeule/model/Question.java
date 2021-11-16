package tgpr.moudeule.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question extends Model {
    private int id;
    private String title;
    private String type;
    private int quizId;

    public Question() {
    }

    public Question(int id, String title, String type, int quizId) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.quizId = quizId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getquizId() {
        return quizId;
    }

    public void setquizId(int quizId) {
        this.quizId = quizId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", quizId=" + quizId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id && quizId == question.quizId && Objects.equals(title, question.title) && Objects.equals(type, question.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, quizId);
    }

    public static void mapper(ResultSet rs, Question question) throws SQLException {
        question.id = rs.getInt("id");
        question.title = rs.getString("title");
        question.type = rs.getString("type");
        question.quizId = rs.getInt("quiz");
    }
    
    public static List<Question> getQuestionsByQuiz(int id) {
        var list = new ArrayList<Question>();
        try {
            var stmt = db.prepareStatement("SELECT * FROM questions WHERE quiz = ?");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                mapper(rs, question);
                list.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getCourseCodeByQuestionId() {
        String res = "";
        try {
            var stmt = db.prepareStatement("SELECT code FROM courses WHERE id IN (SELECT course FROM quizzes WHERE id IN (SELECT quiz FROM questions WHERE id = ?))");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                res = rs.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
