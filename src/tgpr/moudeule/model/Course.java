package tgpr.moudeule.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course extends Model {
    private int id;
    private String code;
    private String description;
    private int capacity;
    private String teacher;

    public Course() {
    }

    public Course(int id, String code, String description, int capacity, String teacher) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.capacity = capacity;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Cours : " +
                "id : " + id +
                ", code : " + code + '\'' +
                ", description : " + description + '\'' +
                ", capacity : " + capacity +
                ", teacher : " + teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && capacity == course.capacity && Objects.equals(code, course.code) && Objects.equals(description, course.description) && Objects.equals(teacher, course.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, description, capacity, teacher);
    }

    public static void mapper(ResultSet rs, Course course) throws SQLException {
        course.id = rs.getInt("id");
        course.code = rs.getString("code");
        course.description = rs.getString("description");
        course.capacity = rs.getInt("capacity");
        course.teacher = rs.getString("teacher");
    }

    public static List<Course> getAllCourses() {
        var list = new ArrayList<Course>();
        try {
            var stmt = Model.db.prepareStatement("SELECT * FROM courses");
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var course = new Course();
                mapper(rs, course);
                list.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public User getTeacher() {
        User teacher = null;
        try {
            var stmt = db.prepareStatement("SELECT * FROM Users WHERE pseudo IN (SELECT teacher FROM courses WHERE id = ?)");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                teacher = new User();
                User.mapper(rs, teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    public int getLeftPlaces() {
        int result = 0;
        try {
            var stmt = db.prepareStatement("SELECT COUNT(*) \n" +
                    "FROM courses c JOIN registrations r ON c.id = r.course\n" +
                    "WHERE c.id = ? AND r.active = 1\n" +
                    "GROUP BY c.id;"
            );
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            result = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.getCapacity() - result;
    }


    public boolean isInWaitingList(User student) {
        if(student.role != Role.STUDENT) {
            int result = 0;
            try {
                PreparedStatement stmt = Model.db.prepareStatement("SELECT COUNT(*) FROM registrations WHERE course = ? AND student = ? AND active = 0");
                stmt.setString(1, code);
                stmt.setString(2, student.getPseudo());
                var rs = stmt.executeQuery();
                result = rs.getInt(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result == 1;
        }
        return false;
    }

}
