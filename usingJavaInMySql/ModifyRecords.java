import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ModifyRecords {
    public static void main(String[] args) {
        String userName = "coms363";
        String password = "password";
        String dbServer = "jdbc:mysql://localhost/project1";

        try (Connection conn = DriverManager.getConnection(dbServer, userName, password);
             Statement stmt = conn.createStatement()) {

            String updateName = "UPDATE students SET name = 'Scott' WHERE ssn = '144673371'";
            stmt.executeUpdate(updateName);

            String updateMajor = "UPDATE major SET degree_name = 'Computer Science', degree_level = 'Master' " +
                                 "WHERE snum = (SELECT snum FROM students WHERE ssn = '144673371')";
            stmt.executeUpdate(updateMajor);

            String deleteSummer2024 = "DELETE FROM register WHERE regtime LIKE '2024-06-%'";
            stmt.executeUpdate(deleteSummer2024);

            String deleteDuplicateCourses = "DELETE c1 FROM courses c1 " +
                    "JOIN courses c2 ON c1.level = c2.level AND c1.number > c2.number " +
                    "AND c1.number != c2.number";
            stmt.executeUpdate(deleteDuplicateCourses);

            System.out.println("Records modified successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
