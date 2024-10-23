import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DropTables {
    public static void main(String[] args) {
        String userName = "test";
        String password = "password";
        String dbServer = "jdbc:mysql://localhost/project1_del_data";

        try (Connection conn = DriverManager.getConnection(dbServer, userName, password);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS=0");

            String dropRegister = "DROP TABLE IF EXISTS register";
            stmt.executeUpdate(dropRegister);

            String dropMajor = "DROP TABLE IF EXISTS major";
            stmt.executeUpdate(dropMajor);

            String dropMinor = "DROP TABLE IF EXISTS minor";
            stmt.executeUpdate(dropMinor);

            String dropOffers = "DROP TABLE IF EXISTS offers";
            stmt.executeUpdate(dropOffers);

            String dropAdminister = "DROP TABLE IF EXISTS administer";
            stmt.executeUpdate(dropAdminister);

            String dropStudents = "DROP TABLE IF EXISTS students";
            stmt.executeUpdate(dropStudents);

            String dropDegrees = "DROP TABLE IF EXISTS degrees";
            stmt.executeUpdate(dropDegrees);

            String dropCourses = "DROP TABLE IF EXISTS courses";
            stmt.executeUpdate(dropCourses);

            String dropDepartments = "DROP TABLE IF EXISTS departments";
            stmt.executeUpdate(dropDepartments);

            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS=1");

            System.out.println("All tables dropped successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
