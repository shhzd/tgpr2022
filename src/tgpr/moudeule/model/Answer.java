package tgpr.moudeule.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answer extends Model {
    private int testId;
    private int optionId;

    public Answer() {
    }

    public Answer(int testId, int optionId) {
        this.testId = testId;
        this.optionId = optionId;
    }

    public int gettestId() {
        return testId;
    }

    public void settestId(int testId) {
        this.testId = testId;
    }

    public int getoptionId() {
        return optionId;
    }

    public void setoptionId(int optionId) {
        this.optionId = optionId;
    }

    @Override
    public String toString() {
        return "Réponses du test " + testId + " est " + optionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return testId == answer.testId && optionId == answer.optionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, optionId);
    }

    public static void mapper(ResultSet rs, Answer answer) throws SQLException {
        answer.testId = rs.getInt("testId");
        answer.optionId = rs.getInt("optionId");
    }

    public static List<Answer> getAllAnswerBytestId(int id) {
        var list = new ArrayList<Answer>();
        try {
            var stmt = db.prepareStatement("SELECT * FROM answers WHERE test = ?");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                Answer answer = new Answer();
                mapper(rs, answer);
                list.add(answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
