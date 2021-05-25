package service;

import CRUD.ConcertsRepository;
import CRUD.FootballGamesRepository;
import CRUD.LocationsRepository;
import CRUD.ToursRepository;
import config.DatabaseConfiguration;
import events.concerts.Concerts;
import events.culutral.movies.Movies;
import events.sports.football.FootballGames;
import events.tours.Tours;
import locations.Locations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceDB {
    private static ServiceDB single_instance = null;

    private ServiceDB() {}

    public static ServiceDB Menu() {
        if (single_instance == null)
            single_instance = new ServiceDB();

        Connection databaseConfiguration = DatabaseConfiguration.getDatabaseConnection();

        List<Locations> locations = new ArrayList<Locations>();
        List<Concerts> concerts = new ArrayList<Concerts>();
        List<Tours> tours = new ArrayList<Tours>();
        List<FootballGames> fg = new ArrayList<FootballGames>();
        LocationsRepository locationsRepository = LocationsRepository.getInstance();
        ConcertsRepository concertsRepository = ConcertsRepository.getInstance();
        ToursRepository toursRepository = ToursRepository.getInstance();
        FootballGamesRepository footballGamesRepository = FootballGamesRepository.getInstance();
        while(true) {
            System.out.println("------------------------------");
            System.out.println("Options to interact with database!");
            System.out.println("Pick an option!");
            System.out.println("0: STOP\n");
            System.out.println("1: Create a location\n2: Create a football game\n3: Create a tour\n4: Create a concert\n");
            System.out.println("5: Read a location\n6: Read a football game\n7: Read a tour\n8: Read a concert\n");
            System.out.println("9: Update a location\n10: Update a football game\n11: Update a tour\n12: Update a concert\n");
            System.out.println("13: Delete a location\n14: Delete a football game\n15: Delete a tour\n16: Delete a concert\n");
            Scanner scanner = new Scanner(System.in);
            Integer option = scanner.nextInt();
            if(option==0){
                DatabaseConfiguration.closeDatabaseConnection();
                break;
            }
            else if(option==1){
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("######################");

                System.out.println("Introduce the country: ");
                String country = scanner2.nextLine();

                System.out.println("Introduce the city: ");
                String city = scanner2.nextLine();

                System.out.println("Introduce the address: ");
                String address = scanner2.nextLine();

                System.out.println("Introduce the ZIP: ");
                String ZIP = scanner2.nextLine();
                try {
                    locationsRepository.insert(country, city, address, ZIP, databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            else if(option==5){
                try {
                locations=locationsRepository.read(databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
                for(int i=0;i<locations.size();i++){
                    System.out.println(locations.get(i)+" ");
                }
            }
            else if(option==9){
                try {
                    locations=locationsRepository.read(databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
                System.out.println("Pick a location to update");
                Scanner scanner3= new Scanner(System.in);
                for(int i=0;i<locations.size();i++){
                    String sqlLoc = "SELECT location_id FROM locations";
                    try {
                        Statement statementLoc = databaseConfiguration.createStatement();
                        ResultSet result = statementLoc.executeQuery(sqlLoc);
                        while (result.next()) {
                            Integer location_id = result.getInt(1);
                            System.out.println(location_id + ":" + locations.get(i) + " ");
                            i++;
                        }
                        break;
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                Integer op = scanner3.nextInt();

                Scanner scanner2 = new Scanner(System.in);

                System.out.println("!!!UPDATE!!!");
                System.out.println("Introduce the country: ");
                String country = scanner2.nextLine();

                System.out.println("Introduce the city: ");
                String city = scanner2.nextLine();

                System.out.println("Introduce the address: ");
                String address = scanner2.nextLine();

                System.out.println("Introduce the ZIP: ");
                String ZIP = scanner2.nextLine();
                try{
                    locationsRepository.update(op,country,city,address,ZIP,databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            else if(option==13){
                try {
                    locations=locationsRepository.read(databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
                System.out.println("Pick a location to delete");
                Scanner scanner3= new Scanner(System.in);
                for(int i=0;i<locations.size();i++){
                    String sqlLoc = "SELECT location_id FROM locations";
                    try {
                        Statement statementLoc = databaseConfiguration.createStatement();
                        ResultSet result = statementLoc.executeQuery(sqlLoc);
                        while (result.next()) {
                            Integer location_id = result.getInt(1);
                            System.out.println(location_id + ":" + locations.get(i) + " ");
                            i++;
                        }
                        break;
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                Integer op = scanner3.nextInt();
                try{
                    locationsRepository.delete(op,databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else if(option==4){
                Concerts c = new Concerts();
                c.addEvent();
                try {
                    locations=locationsRepository.read(databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
                Scanner scanner3= new Scanner(System.in);
                System.out.println("Pick a location for the concert");
                for(int i=0;i<locations.size();i++){
                    String sqlLoc = "SELECT location_id FROM locations";
                    try {
                        Statement statementLoc = databaseConfiguration.createStatement();
                        ResultSet result = statementLoc.executeQuery(sqlLoc);
                        while (result.next()) {
                            Integer location_id = result.getInt(1);
                            System.out.println(location_id + ":" + locations.get(i) + " ");
                            i++;
                        }
                        break;
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                Integer op = scanner3.nextInt();
                try {
                    concertsRepository.insert(c.getName(),c.getNumberTickets(),c.getPrice(),op,c.getArtistName(),c.getNumberOfAlbums(), databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else if(option==8) {
                try {
                    concerts = concertsRepository.read(databaseConfiguration);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < concerts.size(); i++) {
                    System.out.println(concerts.get(i) + " ");
                }
            } else if(option==12){
                try {
                    concerts = concertsRepository.read(databaseConfiguration);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Pick a concert to update");
                Scanner scanner3= new Scanner(System.in);
                for(int i=0;i<concerts.size();i++){
                    String sqlLoc = "SELECT concert_id FROM concerts";
                    try {
                        Statement statementLoc = databaseConfiguration.createStatement();
                        ResultSet result = statementLoc.executeQuery(sqlLoc);
                        while (result.next()) {
                            Integer concert_id = result.getInt(1);
                            System.out.println(concert_id + ":" + concerts.get(i) + " ");
                            i++;
                        }
                        break;
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                Integer op = scanner3.nextInt();
                Concerts c = new Concerts();
                c.addEvent();
                try {
                    concertsRepository.update(op,c.getName(),c.getNumberTickets(),c.getPrice(),c.getArtistName(),c.getNumberOfAlbums(), databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else if(option==16){
                try {
                    concerts=concertsRepository.read(databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
                System.out.println("Pick a concert to delete");
                Scanner scanner3= new Scanner(System.in);
                for(int i=0;i<concerts.size();i++){
                    String sqlLoc = "SELECT concert_id FROM concerts";
                    try {
                        Statement statementLoc = databaseConfiguration.createStatement();
                        ResultSet result = statementLoc.executeQuery(sqlLoc);
                        while (result.next()) {
                            Integer concert_id = result.getInt(1);
                            System.out.println(concert_id + ":" + concerts.get(i) + " ");
                            i++;
                        }
                        break;
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                Integer op = scanner3.nextInt();
                try{
                    concertsRepository.delete(op,databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else if(option==3){
                Tours c = new Tours();
                c.addEvent();
                try {
                    locations=locationsRepository.read(databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
                Scanner scanner3= new Scanner(System.in);
                System.out.println("Pick a location for the tour");
                for(int i=0;i<locations.size();i++){
                    String sqlLoc = "SELECT location_id FROM locations";
                    try {
                        Statement statementLoc = databaseConfiguration.createStatement();
                        ResultSet result = statementLoc.executeQuery(sqlLoc);
                        while (result.next()) {
                            Integer location_id = result.getInt(1);
                            System.out.println(location_id + ":" + locations.get(i) + " ");
                            i++;
                        }
                        break;
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                Integer op = scanner3.nextInt();
                try {
                    toursRepository.insert(c.getName(),c.getNumberTickets(),c.getPrice(),op,c.getTourDate(),c.getNumberOfDays(), databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else if(option==7) {
                try {
                    tours = toursRepository.read(databaseConfiguration);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < tours.size(); i++) {
                    System.out.println(tours.get(i) + " ");
                }
            } else if(option==11){
                try {
                    tours = toursRepository.read(databaseConfiguration);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Pick a tour to update");
                Scanner scanner3= new Scanner(System.in);
                for(int i=0;i<tours.size();i++){
                    String sqlLoc = "SELECT tour_id FROM tours";
                    try {
                        Statement statementLoc = databaseConfiguration.createStatement();
                        ResultSet result = statementLoc.executeQuery(sqlLoc);
                        while (result.next()) {
                            Integer tour_id = result.getInt(1);
                            System.out.println(tour_id + ":" + tours.get(i) + " ");
                            i++;
                        }
                        break;
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                Integer op = scanner3.nextInt();
                Tours c = new Tours();
                c.addEvent();
                try {
                    toursRepository.update(op,c.getName(),c.getNumberTickets(),c.getPrice(),c.getTourDate(),c.getNumberOfDays(), databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else if(option==15){
                try {
                    tours=toursRepository.read(databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
                System.out.println("Pick a tour to delete");
                Scanner scanner3= new Scanner(System.in);
                for(int i=0;i<tours.size();i++){
                    String sqlLoc = "SELECT tour_id FROM tours";
                    try {
                        Statement statementLoc = databaseConfiguration.createStatement();
                        ResultSet result = statementLoc.executeQuery(sqlLoc);
                        while (result.next()) {
                            Integer tour_id = result.getInt(1);
                            System.out.println(tour_id + ":" + tours.get(i) + " ");
                            i++;
                        }
                        break;
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                Integer op = scanner3.nextInt();
                try{
                    toursRepository.delete(op,databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }else if(option==2){
                FootballGames c = new FootballGames();
                c.addEvent();
                try {
                    locations=locationsRepository.read(databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
                Scanner scanner3= new Scanner(System.in);
                System.out.println("Pick a location for the football game");
                for(int i=0;i<locations.size();i++){
                    String sqlLoc = "SELECT location_id FROM locations";
                    try {
                        Statement statementLoc = databaseConfiguration.createStatement();
                        ResultSet result = statementLoc.executeQuery(sqlLoc);
                        while (result.next()) {
                            Integer location_id = result.getInt(1);
                            System.out.println(location_id + ":" + locations.get(i) + " ");
                            i++;
                        }
                        break;
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                Integer op = scanner3.nextInt();
                try {
                    footballGamesRepository.insert(c.getName(),c.getNumberTickets(),c.getPrice(),op,c.getWinsForFirst(),c.getWinsForSecond(),c.getCompetitionType(),c.getHomeTeam(),c.getAwayTeam(),c.getCompetitionName(),c.getEqualGames(), databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else if(option==6) {
                try {
                    fg = footballGamesRepository.read(databaseConfiguration);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < fg.size(); i++) {
                    System.out.println(fg.get(i) + " ");
                }
            } else if(option==10){
                try {
                    fg = footballGamesRepository.read(databaseConfiguration);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Pick a football game to update");
                Scanner scanner3= new Scanner(System.in);
                for(int i=0;i<fg.size();i++){
                    String sqlLoc = "SELECT football_game_id FROM football_games";
                    try {
                        Statement statementLoc = databaseConfiguration.createStatement();
                        ResultSet result = statementLoc.executeQuery(sqlLoc);
                        while (result.next()) {
                            Integer fg_id = result.getInt(1);
                            System.out.println(fg_id + ":" + fg.get(i) + " ");
                            i++;
                        }
                        break;
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                Integer op = scanner3.nextInt();
                FootballGames c = new FootballGames();
                c.addEvent();
                try {
                    footballGamesRepository.update(op,c.getName(),c.getNumberTickets(),c.getPrice(),c.getWinsForFirst(),c.getWinsForSecond(),c.getCompetitionType(),c.getHomeTeam(),c.getAwayTeam(),c.getCompetitionName(),c.getEqualGames(), databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else if(option==14){
                try {
                    fg=footballGamesRepository.read(databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
                System.out.println("Pick a football game to delete");
                Scanner scanner3= new Scanner(System.in);
                for(int i=0;i<fg.size();i++){
                    String sqlLoc = "SELECT football_game_id FROM football_games";
                    try {
                        Statement statementLoc = databaseConfiguration.createStatement();
                        ResultSet result = statementLoc.executeQuery(sqlLoc);
                        while (result.next()) {
                            Integer fg_id = result.getInt(1);
                            System.out.println(fg_id + ":" + fg.get(i) + " ");
                            i++;
                        }
                        break;
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                Integer op = scanner3.nextInt();
                try{
                    footballGamesRepository.delete(op,databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }


        }

        return  single_instance;
    }

}
