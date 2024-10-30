import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTables {
    public static void main(String[] args) {
        String userName = "coms363";
        String password = "password";
        String dbServer = "jdbc:mysql://localhost/project1";

        try (Connection conn = DriverManager.getConnection(dbServer, userName, password);
             Statement stmt = conn.createStatement()) {

            // Create students table
            String createStudents = "CREATE TABLE IF NOT EXISTS students (" +
                    "snum INT PRIMARY KEY," +
                    "ssn CHAR(9) UNIQUE," +
                    "name VARCHAR(100)," +
                    "gender CHAR(1) CHECK (gender IN ('M', 'F'))," +
                    "dob DATE," +
                    "c_phone VARCHAR(15)," +
                    "p_phone VARCHAR(15)," +
                    "c_addr VARCHAR(100)," +
                    "p_addr VARCHAR(100))";
            stmt.executeUpdate(createStudents);

            // Create degrees table
            String createDegrees = "CREATE TABLE IF NOT EXISTS degrees (" +
                    "name VARCHAR(100)," +
                    "level VARCHAR(10)," +
                    "PRIMARY KEY (name, level))";
            stmt.executeUpdate(createDegrees);

            // Create departments table
            String createDepartments = "CREATE TABLE IF NOT EXISTS departments (" +
                    "name VARCHAR(100)," +
                    "code CHAR(4) PRIMARY KEY," +
                    "phone VARCHAR(15)," +
                    "college VARCHAR(50))";
            stmt.executeUpdate(createDepartments);

            // Create courses table
            String createCourses = "CREATE TABLE IF NOT EXISTS courses (" +
                    "number INT PRIMARY KEY," +
                    "name VARCHAR(100)," +
                    "description TEXT," +
                    "credit_hours INT," +
                    "level VARCHAR(10))";
            stmt.executeUpdate(createCourses);

            // Create register table
            String createRegister = "CREATE TABLE IF NOT EXISTS register (" +
                    "snum INT," +
                    "course_number INT," +
                    "regtime TIMESTAMP," +
                    "grade CHAR(2)," +
                    "PRIMARY KEY (snum, course_number)," +
                    "FOREIGN KEY (snum) REFERENCES students(snum)," +
                    "FOREIGN KEY (course_number) REFERENCES courses(number))";
            stmt.executeUpdate(createRegister);

            // Create major table
            String createMajor = "CREATE TABLE IF NOT EXISTS major (" +
                    "snum INT," +
                    "degree_name VARCHAR(100)," +
                    "degree_level VARCHAR(10)," +
                    "PRIMARY KEY (snum, degree_name, degree_level)," +
                    "FOREIGN KEY (snum) REFERENCES students(snum)," +
                    "FOREIGN KEY (degree_name, degree_level) REFERENCES degrees(name, level))";
            stmt.executeUpdate(createMajor);

            // Create minor table
            String createMinor = "CREATE TABLE IF NOT EXISTS minor (" +
                    "snum INT," +
                    "degree_name VARCHAR(100)," +
                    "degree_level VARCHAR(10)," +
                    "PRIMARY KEY (snum, degree_name, degree_level)," +
                    "FOREIGN KEY (snum) REFERENCES students(snum)," +
                    "FOREIGN KEY (degree_name, degree_level) REFERENCES degrees(name, level))";
            stmt.executeUpdate(createMinor);

            // Create offers table
            String createOffers = "CREATE TABLE IF NOT EXISTS offers (" +
                    "dept_code CHAR(4)," +
                    "course_number INT," +
                    "PRIMARY KEY (dept_code, course_number)," +
                    "FOREIGN KEY (dept_code) REFERENCES departments(code)," +
                    "FOREIGN KEY (course_number) REFERENCES courses(number))";
            stmt.executeUpdate(createOffers);

            // Create administer table
            String createAdminister = "CREATE TABLE IF NOT EXISTS administer (" +
                    "dept_code CHAR(4)," +
                    "degree_name VARCHAR(100)," +
                    "degree_level VARCHAR(10)," +
                    "PRIMARY KEY (dept_code, degree_name, degree_level)," +
                    "FOREIGN KEY (dept_code) REFERENCES departments(code)," +
                    "FOREIGN KEY (degree_name, degree_level) REFERENCES degrees(name, level))";
            stmt.executeUpdate(createAdminister);

            System.out.println("All tables created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
