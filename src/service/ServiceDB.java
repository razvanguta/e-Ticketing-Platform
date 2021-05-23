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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceDB {
    private static ServiceDB single_instance = null;

    private ServiceDB() {}

    public static ServiceDB Menu() {
        if (single_instance == null)
            single_instance = new ServiceDB();
        System.out.println("------------------------------");
        System.out.println("Options to interact with database!");
        System.out.println("Pick an option!");
        System.out.println("0: STOP\n");
        System.out.println("1: Create a location\n2: Create a football game\n3: Create a tour\n4: Create a concert\n");
        System.out.println("5: Read a location\n6: Read a football game\n7: Read a tour\n8: Read a concert\n");
        System.out.println("9: Update a location\n10: Update a football game\n11: Update a tour\n12: Update a concert\n");
        System.out.println("13: Delete a location\n14: Delete a football game\n15: Delete a tour\n16: Delete a concert\n");
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
                    System.out.println(i+":"+locations.get(i)+" ");
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
                    locationsRepository.update(op+1,country,city,address,ZIP,databaseConfiguration);
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
                    System.out.println(i+":"+locations.get(i)+" ");
                }
                Integer op = scanner3.nextInt();
                try{
                    locationsRepository.delete(op+1,databaseConfiguration);
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
                    System.out.println(i+":"+locations.get(i)+" ");
                }
                Integer op = scanner3.nextInt();
                try {
                    concertsRepository.insert(c.getName(),c.getNumberTickets(),c.getPrice(),op+1,c.getArtistName(),c.getNumberOfAlbums(), databaseConfiguration);
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
                    System.out.println(i+":"+concerts.get(i)+" ");
                }
                Integer op = scanner3.nextInt();
                Concerts c = new Concerts();
                c.addEvent();
                try {
                    concertsRepository.update(op+1,c.getName(),c.getNumberTickets(),c.getPrice(),c.getArtistName(),c.getNumberOfAlbums(), databaseConfiguration);
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
                    System.out.println(i+":"+concerts.get(i)+" ");
                }
                Integer op = scanner3.nextInt();
                try{
                    concertsRepository.delete(op+1,databaseConfiguration);
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
                System.out.println("Pick a location for the concert");
                for(int i=0;i<locations.size();i++){
                    System.out.println(i+":"+locations.get(i)+" ");
                }
                Integer op = scanner3.nextInt();
                try {
                    toursRepository.insert(c.getName(),c.getNumberTickets(),c.getPrice(),op+1,c.getTourDate(),c.getNumberOfDays(), databaseConfiguration);
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
                    System.out.println(i+":"+tours.get(i)+" ");
                }
                Integer op = scanner3.nextInt();
                Tours c = new Tours();
                c.addEvent();
                try {
                    toursRepository.update(op+1,c.getName(),c.getNumberTickets(),c.getPrice(),c.getTourDate(),c.getNumberOfDays(), databaseConfiguration);
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
                    System.out.println(i+":"+tours.get(i)+" ");
                }
                Integer op = scanner3.nextInt();
                try{
                    toursRepository.delete(op+1,databaseConfiguration);
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
                    System.out.println(i+":"+locations.get(i)+" ");
                }
                Integer op = scanner3.nextInt();
                try {
                    footballGamesRepository.insert(c.getName(),c.getNumberTickets(),c.getPrice(),op+1,c.getWinsForFirst(),c.getWinsForSecond(),c.getCompetitionType(),c.getHomeTeam(),c.getAwayTeam(),c.getCompetitionName(),c.getEqualGames(), databaseConfiguration);
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
                    System.out.println(i+":"+fg.get(i)+" ");
                }
                Integer op = scanner3.nextInt();
                FootballGames c = new FootballGames();
                c.addEvent();
                try {
                    footballGamesRepository.update(op+1,c.getName(),c.getNumberTickets(),c.getPrice(),c.getWinsForFirst(),c.getWinsForSecond(),c.getCompetitionType(),c.getHomeTeam(),c.getAwayTeam(),c.getCompetitionName(),c.getEqualGames(), databaseConfiguration);
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
                    System.out.println(i+":"+fg.get(i)+" ");
                }
                Integer op = scanner3.nextInt();
                try{
                    footballGamesRepository.delete(op+1,databaseConfiguration);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }


        }

        return  single_instance;
    }

}
