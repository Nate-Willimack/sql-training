import java.sql.*;
import com.github.javafaker.Faker;

public class Index {
    public static void main(String[] args) {
        String userName = "coms363";
        String password = "password";
        String dbServer = "jdbc:mysql://localhost/project1";

        try (Connection conn = DriverManager.getConnection(dbServer, userName, password);
             Statement stmt = conn.createStatement()) {

            Faker faker = new Faker();

            for (int i = 0; i < 5000; i++) {
                String name = faker.name().fullName();
                String gender = faker.demographic().sex().substring(0, 1); // "M" or "F"
                String dob = faker.date().birthday(18, 30).toString();
                String cPhone = faker.phoneNumber().cellPhone();
                String pPhone = faker.phoneNumber().phoneNumber();
                String cAddr = faker.address().fullAddress();
                String pAddr = faker.address().fullAddress();
                
                String insertStudent = String.format(
                        "INSERT INTO students (snum, ssn, name, gender, dob, c_phone, p_phone, c_addr, p_addr) " +
                        "VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                        i + 2, faker.idNumber().ssnValid(), name, gender, dob, cPhone, pPhone, cAddr, pAddr);
                stmt.executeUpdate(insertStudent);
            }

            long startTime = System.currentTimeMillis();
            String query = "SELECT d.name, d.level FROM degrees d " +
                           "JOIN major m ON d.name = m.degree_name AND d.level = m.degree_level " +
                           "JOIN students s ON m.snum = s.snum " +
                           "GROUP BY d.name, d.level " +
                           "HAVING SUM(CASE WHEN s.gender = 'M' THEN 1 ELSE 0 END) > " +
                           "SUM(CASE WHEN s.gender = 'F' THEN 1 ELSE 0 END)";
            stmt.executeQuery(query);
            long executionTime = System.currentTimeMillis() - startTime;
            System.out.println("Query execution time (before index): " + executionTime + " ms");

            String createIndex = "CREATE INDEX idx_gender ON students(gender)";
            stmt.executeUpdate(createIndex);

            startTime = System.currentTimeMillis();
            stmt.executeQuery(query);
            executionTime = System.currentTimeMillis() - startTime;
            System.out.println("Query execution time (after index): " + executionTime + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
