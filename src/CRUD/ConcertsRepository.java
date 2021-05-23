package CRUD;

import events.concerts.Concerts;
import locations.Locations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConcertsRepository {
    private static ConcertsRepository single_instance = null;
    private ConcertsRepository(){
    }
    public static ConcertsRepository getInstance()
    {
        if (single_instance == null)
            single_instance = new ConcertsRepository();

        return single_instance;
    }

    public void insert(String name, Integer numberTickets, Double price, Integer locationID,String artistName, Integer numberOfAlbums, Connection connection) throws SQLException {
        String sqlLoc = "SELECT * FROM locations WHERE location_id="+locationID;
        Statement statementLoc = connection.createStatement();
        ResultSet result = statementLoc.executeQuery(sqlLoc);
        while(result.next()) {
            Integer location_id = result.getInt(1);
            String sql = "INSERT INTO concerts (concert_name, concert_number_tickets, concert_price_ticket, location_id,artist_name,number_albums)";
            sql = sql + " VALUES('" + name + "', '" + numberTickets + "', '" + price +"', '" + location_id+"', '" +artistName+"', '" +numberOfAlbums+"')";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
        }

    }


    public List<Concerts> read(Connection connection) throws SQLException {
        List<Concerts> concerts = new ArrayList<>();

        String sql = "SELECT * FROM concerts";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            int concert_id = result.getInt(1);
            String name = result.getString(2);
            Integer numberTickets = result.getInt(3);
            Double price = result.getDouble(4);
            Integer location_id = result.getInt(5);
            String artistName = result.getString(6);
            Integer numberOfAlbums = result.getInt(7);
            String sqlLoc = "SELECT * FROM locations WHERE location_id="+location_id;
            Statement statementLoc = connection.createStatement();
            ResultSet result2 = statementLoc.executeQuery(sqlLoc);
            while(result2.next()) {
                concerts.add(new Concerts(name, numberTickets, price, new Locations(result2.getString(2), result2.getString(3), result2.getString(4), result2.getString(5)), artistName, numberOfAlbums));
            }
        }
        return concerts;
    }

    public void update(int id, String name, Integer numberTickets, Double price ,String artistName, Integer numberOfAlbums, Connection connection) throws SQLException {
        String sql = "UPDATE concerts SET concert_name='" + name + "', concert_number_tickets='" + numberTickets + "', concert_price_ticket='" + price + "', artist_name='" + artistName + "', number_albums='" + numberOfAlbums + "' WHERE concert_id=" + id;

        //System.out.println(sql);
        Statement statement = connection.createStatement();

        int rows = statement.executeUpdate(sql);

        if (rows > 0) {
            System.out.println("Information updated");
        }
    }

    public void delete(int id, Connection connection) throws  SQLException {
        String sql = "DELETE FROM concerts WHERE concert_id=" + id;
        Statement statement = connection.createStatement();

        int rows = statement.executeUpdate(sql);
    }
}
