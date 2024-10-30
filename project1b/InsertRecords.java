import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertRecords {
    public static void main(String[] args) {
        String userName = "coms363";
        String password = "password";
        String dbServer = "jdbc:mysql://localhost/project1";

        try (Connection conn = DriverManager.getConnection(dbServer, userName, password);
             Statement stmt = conn.createStatement()) {

            String insertStudent1 = "INSERT INTO students (snum, ssn, name, gender, dob, c_phone, p_phone, c_addr, p_addr) " +
                    "VALUES (1, '123456789', 'John Doe', 'M', '2000-01-01', '123-456-7890', '098-765-4321', '123 Campus Rd', '456 Parent St')";
            stmt.executeUpdate(insertStudent1);

            String insertDegree1 = "INSERT INTO degrees (name, level) VALUES ('Computer Science', 'Bachelor')";
            stmt.executeUpdate(insertDegree1);

            String insertDepartment1 = "INSERT INTO departments (name, code, phone, college) VALUES ('Computer Science', 'CSCI', '123-456-7890', 'LAS')";
            stmt.executeUpdate(insertDepartment1);

            String insertCourse1 = "INSERT INTO courses (number, name, description, credit_hours, level) VALUES (101, 'Intro to CS', 'Introduction to programming', 3, 'Undergrad')";
            stmt.executeUpdate(insertCourse1);

            String insertRegister1 = "INSERT INTO register (snum, course_number, regtime, grade) VALUES (1, 101, NOW(), 'A')";
            stmt.executeUpdate(insertRegister1);

            String insertMajor1 = "INSERT INTO major (snum, degree_name, degree_level) VALUES (1, 'Computer Science', 'Bachelor')";
            stmt.executeUpdate(insertMajor1);

            String insertMinor1 = "INSERT INTO minor (snum, degree_name, degree_level) VALUES (1, 'Mathematics', 'Bachelor')";
            stmt.executeUpdate(insertMinor1);

            String insertOffers1 = "INSERT INTO offers (dept_code, course_number) VALUES ('CSCI', 101)";
            stmt.executeUpdate(insertOffers1);

            String insertAdminister1 = "INSERT INTO administer (dept_code, degree_name, degree_level) VALUES ('CSCI', 'Computer Science', 'Bachelor')";
            stmt.executeUpdate(insertAdminister1);

            System.out.println("Records inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
