import java.io.*;
import java.sql.*;

public class Application {
    File file = new File("newFile.txt");

    public static void main(String[] args) throws SQLException, IOException {
        final String user = "postgres";
        final String password = "admin";
        final String url = "jdbc:postgresql://localhost:5432/demo";
        final Connection connection = DriverManager.getConnection(url, user, password);
        Application appl = new Application();
        PrintWriter pw = new PrintWriter(appl.file);

        try (PreparedStatement statement = connection.prepareStatement("SELECT a.aircraft_code, a.model, s.seat_no, s.fare_conditions, a.range\n" +
                "FROM seats s JOIN aircrafts a ON s.aircraft_code = a.aircraft_code WHERE a.model LIKE '_%' ORDER BY a.range desc ")) {
            String tabl = "aircraft_code\t\t model\t\t\3 seat_no\t fare_conditions\t range\t";
            pw.println(tabl);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String byCode =  resultSet.getString("aircraft_code");
                String byName =  resultSet.getString("model");
                String byseat =  resultSet.getString("seat_no");
                String byfare =  resultSet.getString("fare_conditions");
                Integer byIndex =  resultSet.getInt(5);
                pw.printf( "|\t" + byCode + "\t|\t\3" + byName +"\t|\t\3" + byseat +"\t|\t\3" + byfare +"\t|\t" + byIndex + "|\n");
            }
        } finally {
            pw.close();
            connection.close();
        }
    }
}