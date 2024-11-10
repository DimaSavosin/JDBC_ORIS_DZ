import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "vimer_401";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ORIS_PRACTICA";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);


        Scanner scanner = new Scanner(System.in);


        String sqlInsertUser = "INSERT INTO driver(first_name, last_name, age) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);

        int totalInserted = 0;

        for (int i = 1; i <= 6; i++) {
            System.out.println("Введите данные для водителя " + i + ":");
            String firstName = scanner.nextLine();
            String lastName = scanner.nextLine();
            int age = scanner.nextInt();
            scanner.nextLine();
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            int affectedRows = preparedStatement.executeUpdate();
            totalInserted += affectedRows;
        }

        System.out.println("Было добавлено " + totalInserted + " строк");
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from driver");

        while (result.next()) {
            System.out.println(result.getInt("id") + " " + result.getString("first_name"));
        }
        System.out.println("Водители, которым больше 25 лет:");
        String sql = "select * from driver where age>?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
        preparedStatement1.setInt(1,25);
        ResultSet resultSet = preparedStatement1.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + " " + resultSet.getString("first_name") + " " + resultSet.getInt("age"));
        }


    }

}

