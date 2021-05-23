package CRUD;

import events.concerts.Concerts;
import events.sports.CompetitionType;
import events.sports.football.FootballGames;
import locations.Locations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FootballGamesRepository {
    private static FootballGamesRepository single_instance = null;
    private FootballGamesRepository(){
    }
    public static FootballGamesRepository getInstance()
    {
        if (single_instance == null)
            single_instance = new FootballGamesRepository();

        return single_instance;
    }

    public void insert(String name, Integer numberTickets, Double price, Integer locationID, Integer winsForFirst, Integer winsForSecond, CompetitionType competitionType, String homeTeam, String awayTeam, String competitionName, Integer equalGames, Connection connection) throws SQLException {
        String sqlLoc = "SELECT * FROM locations WHERE location_id="+locationID;
        Statement statementLoc = connection.createStatement();
        ResultSet result = statementLoc.executeQuery(sqlLoc);
        while(result.next()) {
            Integer location_id = result.getInt(1);
            String sql = "INSERT INTO football_games (football_game_name, football_game_number_tickets, football_game_price_ticket, location_id,wins_for_first,wins_for_second,competition_type,home_team_name,away_team_name,competition_name,equal_games)";
            sql = sql + " VALUES('" + name + "', '" + numberTickets + "', '" + price +"', '" + location_id+"', '" +winsForFirst+"', '" +winsForSecond+"', '" +competitionType.toString()+"', '" +homeTeam+"', '" +awayTeam+"', '" +competitionName+"', '" +equalGames+"')";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
        }

    }


    public List<FootballGames> read(Connection connection) throws SQLException {
        List<FootballGames> footballGames = new ArrayList<>();

        String sql = "SELECT * FROM football_games";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            int concert_id = result.getInt(1);
            String name = result.getString(2);
            Integer numberTickets = result.getInt(3);
            Double price = result.getDouble(4);
            Integer location_id = result.getInt(5);
            Integer winsForFirst = result.getInt(6);
            Integer winsForSecond = result.getInt(7);
            String competitionType = result.getString(8);
            String homeTeam = result.getString(9);
            String awayTeam = result.getString(10);
            String competitionName = result.getString(11);
            CompetitionType ct;
            if(competitionName.equals("INTERNATIONAL")){
                ct=CompetitionType.INTERNATIONAL;
            }
            else{
                ct=CompetitionType.NATIONAL;
            }
            Integer equalGames = result.getInt(12);
            String sqlLoc = "SELECT * FROM locations WHERE location_id="+location_id;
            Statement statementLoc = connection.createStatement();
            ResultSet result2 = statementLoc.executeQuery(sqlLoc);
            while(result2.next()) {
               footballGames.add(new FootballGames(name, numberTickets, price, new Locations(result2.getString(2), result2.getString(3), result2.getString(4), result2.getString(5)), winsForFirst, winsForSecond, ct,homeTeam,awayTeam,competitionName,equalGames));
            }
        }
        return footballGames;
    }

    public void update(int id,String name, Integer numberTickets, Double price, Integer winsForFirst, Integer winsForSecond, CompetitionType competitionType,String homeTeam,String awayTeam,String competitionName, Integer equalGames, Connection connection) throws SQLException {
        String sql = "UPDATE football_games SET football_game_name='" + name + "', football_game_number_tickets='" + numberTickets + "', football_game_price_ticket='" + price + "', wins_for_first='" + winsForFirst + "', wins_for_second='" + winsForSecond+ "', competition_type='" + competitionType.toString()+ "', home_team_name='" + homeTeam + "', away_team_name='" +awayTeam+ "', competition_name='" +competitionName+ "', equal_games='" +equalGames+ "' WHERE football_game_id=" + id;

        Statement statement = connection.createStatement();

        int rows = statement.executeUpdate(sql);

        if (rows > 0) {
            System.out.println("Information updated");
        }
    }

    public void delete(int id, Connection connection) throws  SQLException {
        System.out.println(id);
        String sql = "DELETE FROM football_games WHERE football_game_id=" + id;
        Statement statement = connection.createStatement();

        int rows = statement.executeUpdate(sql);
    }
}
