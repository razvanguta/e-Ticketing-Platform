package CRUD;

import events.concerts.Concerts;
import events.tours.Tours;
import locations.Locations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ToursRepository {
    private static ToursRepository single_instance = null;
    private ToursRepository(){
    }
    public static ToursRepository getInstance()
    {
        if (single_instance == null)
            single_instance = new ToursRepository();

        return single_instance;
    }

    public void insert(String name, Integer numberTickets, Double price, Integer locationID,String tourDate, Integer numberOfDays, Connection connection) throws SQLException {
        String sqlLoc = "SELECT * FROM locations WHERE location_id="+locationID;
        Statement statementLoc = connection.createStatement();
        ResultSet result = statementLoc.executeQuery(sqlLoc);
        while(result.next()) {
            Integer location_id = result.getInt(1);
            String sql = "INSERT INTO tours (tour_name, tour_number_tickets, tour_price_ticket, location_id,tour_date,tour_number_days)";
            sql = sql + " VALUES('" + name + "', '" + numberTickets + "', '" + price +"', '" + location_id+"', '" +tourDate+"', '" +numberOfDays+"')";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
        }

    }


    public List<Tours> read(Connection connection) throws SQLException {
        List<Tours> tours = new ArrayList<>();

        String sql = "SELECT * FROM tours";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            int tour_id = result.getInt(1);
            String name = result.getString(2);
            Integer numberTickets = result.getInt(3);
            Double price = result.getDouble(4);
            Integer location_id = result.getInt(5);
            String tourDate = result.getString(6);
            Integer numberOfDays = result.getInt(7);
            String sqlLoc = "SELECT * FROM locations WHERE location_id="+location_id;
            Statement statementLoc = connection.createStatement();
            ResultSet result2 = statementLoc.executeQuery(sqlLoc);
            while(result2.next()) {
                tours.add(new Tours(name, numberTickets, price, new Locations(result2.getString(2), result2.getString(3), result2.getString(4), result2.getString(5)), tourDate, numberOfDays));
            }
        }
        return tours;
    }

    public void update(int id, String name, Integer numberTickets, Double price ,String tourDate, Integer numberOfDays, Connection connection) throws SQLException {
        String sql = "UPDATE tours SET tour_name='" + name + "', tour_number_tickets='" + numberTickets + "', tour_price_ticket='" + price + "', tour_date='" + tourDate + "', tour_number_days='" + numberOfDays + "' WHERE tour_id=" + id;

        //System.out.println(sql);
        Statement statement = connection.createStatement();

        int rows = statement.executeUpdate(sql);

        if (rows > 0) {
            System.out.println("Information updated");
        }
    }

    public void delete(int id, Connection connection) throws  SQLException {
        String sql = "DELETE FROM tours WHERE tour_id=" + id;
        Statement statement = connection.createStatement();

        int rows = statement.executeUpdate(sql);
    }
}
