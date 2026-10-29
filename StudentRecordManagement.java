
import java.sql.*;
import java.util.Scanner;

public class StudentRecordManagement {

    static final String URL =
        "jdbc:mysql://localhost:3306/student_db";
    static final String USER = "root";
    static final String PASSWORD =
        System.getenv("STUDENT_DB_PASSWORD");

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        if (PASSWORD == null || PASSWORD.isBlank()) {
            System.out.println(
                "Error: Set the STUDENT_DB_PASSWORD environment variable."
            );
            return;
        }

        try (Connection con =
                 DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.println("Database connected successfully!");

            while (true) {
                System.out.println("\n--- Student Record Management ---");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Search Student");
                System.out.println("4. Update Student");
                System.out.println("5. Delete Student");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");

                String input = sc.nextLine();

                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number from 1 to 6.");
                    continue;
                }

                try {
                    switch (choice) {
                        case 1 -> addStudent(con);
                        case 2 -> viewStudents(con);
                        case 3 -> searchStudent(con);
                        case 4 -> updateStudent(con);
                        case 5 -> deleteStudent(con);
                        case 6 -> {
                            System.out.println("Program closed.");
                            return;
                        }
                        default ->
                            System.out.println("Invalid choice!");
                    }
                } catch (SQLException e) {
                    System.out.println("Database error: " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("ID and year must be valid numbers.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    static void addStudent(Connection con) throws SQLException {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter department: ");
        String dept = sc.nextLine();

        System.out.print("Enter year: ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        String sql =
            "INSERT INTO students (id, name, department, year, phone) " +
            "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, dept);
            ps.setInt(4, year);
            ps.setString(5, phone);

            ps.executeUpdate();
            System.out.println("Student added successfully!");
        }
    }

    static void viewStudents(Connection con) throws SQLException {
        String sql = "SELECT * FROM students";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            boolean found = false;

            while (rs.next()) {
                found = true;
                displayStudent(rs);
            }

            if (!found) {
                System.out.println("No student records found.");
            }
        }
    }

    static void searchStudent(Connection con) throws SQLException {
        System.out.print("Enter student ID: ");
        int id = Integer.parseInt(sc.nextLine());

        String sql = "SELECT * FROM students WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    displayStudent(rs);
                } else {
                    System.out.println("Student not found.");
                }
            }
        }
    }

    static void updateStudent(Connection con) throws SQLException {
        System.out.print("Enter student ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        System.out.print("Enter new department: ");
        String dept = sc.nextLine();

        System.out.print("Enter new year: ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.print("Enter new phone: ");
        String phone = sc.nextLine();

        String sql =
            "UPDATE students SET name = ?, department = ?, " +
            "year = ?, phone = ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setInt(3, year);
            ps.setString(4, phone);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found.");
            }
        }
    }

    static void deleteStudent(Connection con) throws SQLException {
        System.out.print("Enter student ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        String sql = "DELETE FROM students WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found.");
            }
        }
    }

    static void displayStudent(ResultSet rs) throws SQLException {
        System.out.println(
            "ID: " + rs.getInt("id") +
            " | Name: " + rs.getString("name") +
            " | Department: " + rs.getString("department") +
            " | Year: " + rs.getInt("year") +
            " | Phone: " + rs.getString("phone")
        );
    }
}
