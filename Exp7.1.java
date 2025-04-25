import java.sql.*;

public class EmployeeJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/company"; 
        String user = "sameer03"; 
        String password = "9955"; 
        
        String query = "SELECT * FROM Employee";
        
        try {
            // Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish Connection
            Connection conn = DriverManager.getConnection(url, user, password);
            
            // Create Statement
            Statement stmt = conn.createStatement();
            
            // Execute Query
            ResultSet rs = stmt.executeQuery(query);
            
            // Display Results
            System.out.println("Employee Records:");
            System.out.println("----------------------------");
            while (rs.next()) {
                int id = rs.getInt("EmID"); // Ensure column names match the table
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
            }
            
            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();
        }
    }
}
