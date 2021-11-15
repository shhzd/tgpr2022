package tgpr.moudeule.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Registration extends Model {
    private int courseId;
    private String student;
    private short active;

    public Registration() {
    }

    public Registration(int courseId, String student, short active) {
        this.courseId = courseId;
        this.student = student;
        this.active = active;
    }

    public int getcourseId() {
        return courseId;
    }

    public void setcourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public short isActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "courseId=" + courseId +
                ", student='" + student + '\'' +
                ", active=" + active +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, student, active);
    }

    public static void mapper(ResultSet rs, Registration registration) throws SQLException {
        registration.courseId = rs.getInt("courseId");
        registration.student = rs.getString("student");
        registration.active = rs.getShort("active");
    }


}
