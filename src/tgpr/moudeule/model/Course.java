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

    public String prettyString() {
        return "" + id +
                "  " + code +
                "  " + ((capacity < 10) ? " " : "") + capacity +
                "  " + description;
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

    public boolean save() {

        Course c = getCourseByID(this.id);
        int count = 0;
        try {
            PreparedStatement stmt;
            if (c == null) {
                stmt = db.prepareStatement("INSERT INTO `courses` (`id`, `code`, `description`, `capacity`, `teacher`) VALUES (?, ?, ?, ?, ?)");
                stmt.setInt(1, id);
                stmt.setString(2, code);
                stmt.setString(3, description);
                stmt.setInt(4, capacity);
                stmt.setObject(5, teacher);
            } else {
                stmt = db.prepareStatement("UPDATE courses SET `code`=?, `description`=?, `capacity`=?, `teacher`=? WHERE `id`=?");
                stmt.setString(1, code);
                stmt.setString(2, description);
                stmt.setInt(3, capacity);
                stmt.setObject(4, teacher);
                stmt.setInt(5, id);
            }
            count = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            return count == 1;
    }

    public boolean delete() {
        return false;
    }

    public static List<Course> getCoursesFromTeacher(User teacher) {
        var list = new ArrayList<Course>();
        try {
            var stmt = Model.db.prepareStatement("SELECT * FROM `courses` WHERE `teacher` = ? ORDER BY `id` ASC");
            stmt.setString(1, teacher.getPseudo());
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

    public static Course getCourseByID(int id) {
        try {
            var stmt = Model.db.prepareStatement("SELECT * FROM `courses` WHERE `id` = ?;");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                Course course = new Course();
                mapper(rs, course);
                return course;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Course getCourseByID(String courseID) {
        int id = 0;
        if (isInteger(courseID)) {
            return getCourseByID(Integer.parseInt(courseID));
        } else
            return null;
    }

    /**    Depreciated   **/
//    public static boolean isValidCourseID(String courseID, User teacher) {
//        try {
//            int id = 0;
//            if (isInteger(courseID))
//                id = Integer.parseInt(courseID);
//            else
//                return false;
//            // we check at the same if the ID is valid and if the course belongs to the teacher
//            var stmt = Model.db.prepareStatement("SELECT * FROM `courses` WHERE `teacher` = ? AND `id` = ?;");
//            stmt.setString(1, teacher.getPseudo());
//            stmt.setInt(2, id);
//            var rs = stmt.executeQuery();
//            return (rs.next());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public List<String> validate() {

        List<String> errors = new ArrayList<>();

        var err = isValidID(id);
        if (err != null) errors.add(err);
        err = isValidCode(code);
        if (err != null) errors.add(err);
        err = isValidDescription(description);
        if (err != null) errors.add(err);
        err = isValidCapacity(capacity);
        if (err != null) errors.add(err);
        return errors;
    }

    public static String isValidID(int id) {
        if (id > 9999) return "L'ID ne peut pas faire plus de 4 chiffres";
        if (id < 1000) return "L'ID doit faire quatres chiffres " + id;
        if (getCourseByID(id) != null) return "Cet ID existe déja";
        return null;
    }

    public static String isValidCode(String code) {
        if (code.length() != 4) return "Le code doit faire 4 carratères";

        if (!code.matches("[a-zA-Z0-9]{4}"))
            return "Seul les carractères alphanumériques sont autorisés";
        return null;
    }

    public static String isValidDescription(String description) {
        if (description.length() < 5)
            return "La description n'est pas assez précise";
        if (description.length() > 60)
            return "La description est trop longue (60 carractère max)";
        return null;
    }

    public static String isValidCapacity(int capacity) {
        if (capacity < 4) return "La capacité minimal est de 4";
        if (capacity > 28) return "La capacité maximal est de 28";
        return null;
    }

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch( Exception e ) {
            return false;
        }
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

    public List<User> getRegistratedStudents(Course course) {
        List<User> studentsList = new ArrayList<>();

        try {
            //Etudiants inscrits à ce cours
            var stmt = db.prepareStatement("SELECT student FROM registrations WHERE course =? AND active = 1");
            stmt.setInt(1, course.getId());
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var student = new User();
                User.mapper(rs, student);
                studentsList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsList;
    }

    public List<User> getPendingRegistrations(Course course) {
        List<User> studentsList = new ArrayList<>();

        try {
            //Etudiants en liste d'attente pour s'inscrire à ce cours
            var stmt = db.prepareStatement("");
            //
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var student = new User();
                User.mapper(rs, student);
                studentsList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsList;
    }

}
