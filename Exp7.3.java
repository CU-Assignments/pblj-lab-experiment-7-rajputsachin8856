// Student.java (Model)
class Student {
    private int studentID;
    private String name;
    private String department;
    private double marks;

    public Student(int studentID, String name, String department, double marks) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }
    
    public int getStudentID() { return studentID; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getMarks() { return marks; }
}

// StudentController.java (Controller)
import java.sql.*;
import java.util.*;

class StudentController {
    private static final String URL = "jdbc:mysql://localhost:3306/StudentDB";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";
    
    public static void addStudent(Student student) {
        String query = "INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, student.getStudentID());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDepartment());
            pstmt.setDouble(4, student.getMarks());
            pstmt.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void viewStudents() {
        String query = "SELECT * FROM Student";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\nStudentID | Name | Department | Marks");
            while (rs.next()) {
                System.out.println(rs.getInt("StudentID") + " | " + rs.getString("Name") + " | " + rs.getString("Department") + " | " + rs.getDouble("Marks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateStudentMarks(int id, double newMarks) {
        String query = "UPDATE Student SET Marks = ? WHERE StudentID = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setDouble(1, newMarks);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("Marks updated successfully.");
            else System.out.println("Student not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteStudent(int id) {
        String query = "DELETE FROM Student WHERE StudentID = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("Student deleted successfully.");
            else System.out.println("Student not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// StudentManagement.java (View & Main Menu)
public class StudentManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student Marks");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = scanner.nextDouble();
                    Student student = new Student(id, name, dept, marks);
                    StudentController.addStudent(student);
                    break;
                case 2:
                    StudentController.viewStudents();
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    int updateID = scanner.nextInt();
                    System.out.print("Enter New Marks: ");
                    double newMarks = scanner.nextDouble();
                    StudentController.updateStudentMarks(updateID, newMarks);
                    break;
                case 4:
                    System.out.print("Enter Student ID to Delete: ");
                    int deleteID = scanner.nextInt();
                    StudentController.deleteStudent(deleteID);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
