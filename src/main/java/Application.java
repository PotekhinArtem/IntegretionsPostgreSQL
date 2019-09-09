import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "admin";
        final String url = "jdbc:postgresql://localhost:5432/demo";

        final Connection connection = DriverManager.getConnection(url, user, password);


        try (PreparedStatement statement = connection.prepareStatement("SELECT a.aircraft_code, a.model, s.seat_no, s.fare_conditions, a.range\n" +
                "FROM seats s JOIN aircrafts a ON s.aircraft_code = a.aircraft_code WHERE a.model LIKE '_%' ORDER BY a.range desc ")) {

            System.out.println("aircraft_code\t\t model\t\t\3 seat_no\t fare_conditions\t range\t");
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String byCode =  resultSet.getString("aircraft_code");
                String byName =  resultSet.getString("model");
                String byseat =  resultSet.getString("seat_no");
                String byfare =  resultSet.getString("fare_conditions");
                String byIndex =  resultSet.getString("range");
                System.out.print("\t" +byCode +"\t\t");
                System.out.print(byName +"\t\t\t");
                System.out.print(byseat +"\t\t\f");
                System.out.print(byfare +"\t\t\f");
                System.out.println(byIndex + "\f");
            }
        } finally {
            connection.close();
        }
    }
}