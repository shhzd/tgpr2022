package tgpr.moudeule.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Option extends Model {
    private int id;
    private String title;
    private int correct;
    private int questionId;

    public Option() {
    }

    public Option(int id, String title, int correct, int questionId) {
        this.id = id;
        this.title = title;
        this.correct = correct;
        this.questionId = questionId;
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

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Option : " +
                "id : " + id +
                ", title : " + title + '\'' +
                ", correct :  " + correct +
                ", questionId : " + questionId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return id == option.id && correct == option.correct && questionId == option.questionId && Objects.equals(title, option.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, correct, questionId);
    }

    public static void mapper(ResultSet rs, Option option) throws SQLException {
        option.id = rs.getInt("id");
        option.title = rs.getString("title");
        option.correct = rs.getInt("correct");
        option.questionId = rs.getInt("questionId");
    }

    public static List<Option> getOptionsByQuestion(int id) {
        var list = new ArrayList<Option>();
        try {
            var stmt = db.prepareStatement("SELECT * FROM options WHERE question = ?");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                Option option = new Option();
                mapper(rs, option);
                list.add(option);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
