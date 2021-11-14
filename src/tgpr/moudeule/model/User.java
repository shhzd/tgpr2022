package tgpr.moudeule.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class User extends Model {
    private String pseudo;
    public String password;
    public String fullname;
    public LocalDate birthdate;
    public Role role;

    public User() {
    }

    public User(String pseudo, String password, Role role) {
        this.pseudo = pseudo;
        this.password = password;
        this.role = role;
    }

    public User(String pseudo, String password, String fullname, LocalDate birthdate, Role role) {
        this.pseudo = pseudo;
        this.password = password;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.role = role;

    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "user{" +
                "pseudo='" + pseudo + '\'' +
                ", fullname='" + fullname + '\'' +
                ", birthdate=" + birthdate +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // si les deux références sont identiques, il s'agit du même objet et ils sont donc égaux
        if (this == o) return true;
        // faux si l'objet o est nul ou si l'objet courant et l'objet o n'ont pas le même type (la même classe)
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        // si les objets ont le même pseudo, ils sont égaux
        return pseudo.equals(user.pseudo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pseudo);
    }

    public static void mapper(ResultSet rs, User user) throws SQLException {
        user.pseudo = rs.getString("pseudo");
        user.password = rs.getString("password");
        user.fullname = rs.getString("fullname");
        user.birthdate = rs.getObject("birthdate", LocalDate.class);
        user.role = Role.getRole(rs.getInt("role")); // ici ça renvoie un int mais à partir du int
        //il doit créer le rôle correspondant
        // 1 pour TEACHER et 2 pour STUDENT
    }

    public static List<User> getAll() {
        var list = new ArrayList<User>();
        try {
            var stmt = db.prepareStatement("select * from users order by pseudo");
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var user = new User();
                mapper(rs, user);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static User getByPseudo(String pseudo) {
        User user = null;
        try {
            var stmt = db.prepareStatement("select * from users where pseudo=?");
            stmt.setString(1, pseudo);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                mapper(rs, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<User> getByCourse(Course course) {
        var list = new ArrayList<User>();
        try {
            var stmt = db.prepareStatement(
                    "SELECT * FROM `users` WHERE users.pseudo IN " +
                        "(SELECT registrations.student FROM registrations " +
                        "WHERE registrations.course = ?) order by pseudo");
            stmt.setInt(1, course.getId());
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var user = new User();
                mapper(rs, user);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getStatus(Course course) {

        String res = "";
        try {
            var stmt = db.prepareStatement(
                "SELECT `active` FROM `registrations` " +
                    "WHERE `registrations`.`student` = ? AND `registrations`.`course` = ?");
            stmt.setString(1, pseudo);
            stmt.setInt(2, course.getId());
            var rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == 0)
                    res = "inactif";
                if (rs.getInt(1) == 1)
                    res = "actif";

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean changeStatus(Course course, int status) {
        int count = 0;
        try {
            PreparedStatement stmt;
            stmt = db.prepareStatement(
                "UPDATE registrations SET registrations.active = ? " +
                    "WHERE registrations.student = ? AND course = ?;");
            stmt.setInt(1, status);
            stmt.setString(2, pseudo);
            stmt.setInt(3, course.getId());
            count = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count == 1;
    }

    public boolean save() {
        User m = getByPseudo(pseudo);
        int count = 0;
        try {
            PreparedStatement stmt;
            if (m == null) {
                stmt = db.prepareStatement("insert into users (pseudo, password, fullname, birthdate, role) values (?,?,?,?,?)");
                stmt.setString(1, pseudo);
                stmt.setString(2, password);
                stmt.setString(3, fullname);
                stmt.setObject(4, birthdate);
                stmt.setInt(5, role.getRoleId());
            } else {
                stmt = db.prepareStatement("update users set password=?, fullname=?, birthdate=?, role=? where pseudo=?");
                stmt.setString(1, password);
                stmt.setString(2, fullname);
                stmt.setObject(5, birthdate);
                stmt.setInt(4, role.getRoleId());
                stmt.setString(5, pseudo);
            }
            count = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count == 1;
    }

    public boolean delete() {
        int count = 0;
        try {
            PreparedStatement stmt = db.prepareStatement("delete from users where pseudo=?");
            stmt.setString(1, pseudo);
            count = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count == 1;
    }

    public static String isValidBirthdate(LocalDate birthdate) {
        if (birthdate == null)
            return null;
        if (birthdate.compareTo(LocalDate.now()) > 0)
            return "impossible d'être né dans le future";
        if (Period.between(birthdate, LocalDate.now()).getYears() < 18)
            return "avoir minimum 18 ans accomplis";
        return null;
    }

    public static String isValidPseudo(String pseudo) {
        if (pseudo == null || !Pattern.matches("[a-zA-Z0-9]{3,}", pseudo))
            return "pseudo invalide";
        return null;
    }

    public static String isValidAvailablePseudo(String pseudo) {
        var error = isValidPseudo(pseudo);
        if (error != null)
            return error;
        if (getByPseudo(pseudo) != null)
            return "pseudo déjà utilisé";
        return null;
    }

    public static String isValidPassword(String password) {
        if (password == null || !Pattern.matches("[a-zA-Z0-9]{3,}", password))
            return "mot de passe invalide";
        return null;
    }

    public static String isValidPasswordConfirm(String passwordConfirm, String password) {
        if (!passwordConfirm.equals(password))
            return "entrez le même mot de passe";
        return null;
    }

    public List<String> validate(String passwordConfrim) {
        var errors = new ArrayList<String>();

        var err = isValidPseudo(pseudo);
        if (err != null) errors.add(err);
        err = isValidPassword(password);
        if (err != null) errors.add(err);
        err = isValidPasswordConfirm(passwordConfrim, password);
        if (err != null) errors.add(err);
        err = isValidBirthdate(birthdate);
        if (err != null) errors.add(err);

        return errors;
    }
}