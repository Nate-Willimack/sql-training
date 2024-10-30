import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Query {
    public static void main(String[] args) {
        String userName = "coms363";
        String password = "password";
        String dbServer = "jdbc:mysql://localhost/project1";

        try (Connection conn = DriverManager.getConnection(dbServer, userName, password);
             Statement stmt = conn.createStatement()) {

            String query1 = "SELECT c.number, c.name, AVG(CAST(r.grade AS DECIMAL(5, 2))) AS avg_grade " +
                    "FROM courses c " +
                    "JOIN register r ON c.number = r.course_number " +
                    "GROUP BY c.number, c.name";
            ResultSet rs1 = stmt.executeQuery(query1);
            while (rs1.next()) {
                System.out.println("Course: " + rs1.getString("number") + ", Name: " + rs1.getString("name") + ", Avg Grade: " + rs1.getFloat("avg_grade"));
            }

            String query2 = "SELECT COUNT(*) AS count FROM students s " +
                    "JOIN major m ON s.snum = m.snum " +
                    "JOIN administer a ON m.degree_name = a.degree_name AND m.degree_level = a.degree_level " +
                    "WHERE s.gender = 'F' AND a.dept_code = 'LAS'";
            ResultSet rs2 = stmt.executeQuery(query2);
            if (rs2.next()) {
                System.out.println("Female students in LAS degrees: " + rs2.getInt("count"));
            }

            String query3 = "SELECT d.name, d.level FROM degrees d " +
                    "JOIN major m ON d.name = m.degree_name AND d.level = m.degree_level " +
                    "JOIN students s ON m.snum = s.snum " +
                    "GROUP BY d.name, d.level " +
                    "HAVING SUM(CASE WHEN s.gender = 'M' THEN 1 ELSE 0 END) > " +
                    "SUM(CASE WHEN s.gender = 'F' THEN 1 ELSE 0 END)";
            ResultSet rs3 = stmt.executeQuery(query3);
            while (rs3.next()) {
                System.out.println("Degree: " + rs3.getString("name") + ", Level: " + rs3.getString("level"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
