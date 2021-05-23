package CRUD;

import locations.Locations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationsRepository {
    private static LocationsRepository single_instance = null;
    private LocationsRepository(){
    }
    public static LocationsRepository getInstance()
    {
        if (single_instance == null)
            single_instance = new LocationsRepository();

        return single_instance;
    }

    public void insert(String country, String city, String address, String ZIP, Connection connection) throws SQLException {
        String sql = "INSERT INTO locations (country, city, address, ZIP)";
        sql = sql + " VALUES('" + country + "', '" + city + "', '" + address +"', '" + ZIP+"')";
        Statement statement = connection.createStatement();

        int rows = statement.executeUpdate(sql);
    }


    public List<Locations> read(Connection connection) throws SQLException {
        List<Locations> locations = new ArrayList<>();

        String sql = "SELECT * FROM locations";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            int location_id = result.getInt(1);
            String country = result.getString(2);
            String city = result.getString(3);
            String address = result.getString(4);
            String ZIP = result.getString(5);

            locations.add(new Locations(country, city, address, ZIP));

        }

        return locations;
    }

    public void update(int id, String country,  String city, String address, String ZIP, Connection connection) throws SQLException {
        String sql = "UPDATE locations SET country='" + country + "', city='" + city + "', address='" + address + "', ZIP='" + ZIP + "' WHERE location_id=" + id;

        //System.out.println(sql);
        Statement statement = connection.createStatement();

        int rows = statement.executeUpdate(sql);

        if (rows > 0) {
            System.out.println("Information updated");
        }
    }

    public void delete(int id, Connection connection) throws  SQLException {
        String sql = "DELETE FROM locations WHERE location_id=" + id;
        Statement statement = connection.createStatement();

        int rows = statement.executeUpdate(sql);
    }
}
