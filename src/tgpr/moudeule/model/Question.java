package tgpr.moudeule.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static Question getById(int id) {
        Question question = null;
        try {
            var stmt = db.prepareStatement("SELECT * FROM questions WHERE id = ?");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                question = new Question();
                mapper(rs, question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
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

    public boolean save() {
        Question q = getById(id);
        int count = 0;
        try {
            PreparedStatement stmt;
            if (q == null) {
                stmt = db.prepareStatement("insert into questions (title, type, quiz) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, title);
                stmt.setString(2, type);
                stmt.setInt(3, quizId);
            } else {
                stmt = db.prepareStatement("update questions set title=?, type=?, quiz=? where id=?");
                stmt.setString(1, title);
                stmt.setString(2, type);
                stmt.setInt(3, quizId);
                stmt.setInt(4, id);
            }
            count = stmt.executeUpdate();
            if (q == null)
                id = this.getLastInsertId(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count == 1;
    }

    public static boolean isValidType(String type) {
        return type.equalsIgnoreCase("QCM") || type.equalsIgnoreCase("QRM");
    }

    public static int getNumberTrueAnswers(Question question) {
        int result = 0;
        try {
            var stmt = db.prepareStatement("SELECT Count(*) count FROM options WHERE question = ? AND correct = 1");
            stmt.setInt(1, question.getId());
            var rs = stmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Question> getAllQuestionsOfQuiz(int idQuiz) {
        var list = new ArrayList<Question>();
        try {
            var stmt = db.prepareStatement("SELECT * FROM questions WHERE quiz IN (SELECT id FROM quizzes WHERE id = ?)");
            stmt.setInt(1, idQuiz);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var question = new Question();
                mapper(rs, question);
                list.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
