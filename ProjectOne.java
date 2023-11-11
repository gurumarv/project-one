import java.sql.*;
import java.util.Scanner;

public class ProjectOne {
    public static void main(String[] args) {
        createTable();
        int count = populateTable();
        System.out.println("You have entered " + count + " details. Thank you");

    }
    public static void createTable() {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_one", "root", "enterYourPassword");
            Statement statement = connection.createStatement();){
            String create = "CREATE TABLE IF NOT EXISTS users(name Text, email Text, age Integer, location Text, destination Text)";
            statement.execute(create);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static int populateTable() {
        int count = 1;
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_one", "root", "enterYourPassword");
            Statement statement = connection.createStatement(); Scanner scanner = new Scanner(System.in)) {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO users(name, email, age, location, destination) VALUES(?, ?, ?, ?, ?)");
            while(count <= 10) {
                System.out.println("\nPlease enter your name: ");
                String name = scanner.nextLine();
                System.out.println("Email: ");
                String email = scanner.nextLine();
                System.out.println("Age: ");
                int age = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter your location: ");
                String location = scanner.nextLine();
                System.out.println("Finally your destination: ");
                String destination = scanner.nextLine();

                insertStatement.setString(1, name);
                insertStatement.setString(2, email);
                insertStatement.setInt(3, age);
                insertStatement.setString(4, location);
                insertStatement.setString(5, destination);
                insertStatement.execute();
                count++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
}

