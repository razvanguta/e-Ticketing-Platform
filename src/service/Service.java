package service;

import readWrite.*;
import events.Events;
import events.concerts.Concerts;
import events.culutral.ShowCategory;
import events.culutral.movies.Movies;
import events.sports.CompetitionType;
import events.sports.football.FootballGames;
import events.sports.tennis.TennisGames;
import events.tours.Tours;
import locations.Locations;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
public class Service {
    private static Service single_instance = null;

    private Service() {}

    public static Service Menu()
    {
        if (single_instance == null)
            single_instance = new Service();
        ReadWrite<String> readWrite = new ReadWrite();

        //Read the classes into the Arrays to work with them
        //Read
        List<String> audit = new ArrayList<String>();
        for(String[] sm: readWrite.readFromCsvFile("audit.csv")){
            String a1 = sm[0];
            String a2 = sm[1];
            audit.add(sm[0]+","+sm[1]);
        }
        //Read
        List<Concerts> concerts = new ArrayList<Concerts>();
        for (String[] sm : readWrite.readFromCsvFile("concerts.csv")) {
            String name=sm[0];
            Integer numberTickets=Integer.parseInt(sm[1]);
            Double price=Double.parseDouble(sm[2]);
            String country = sm[3];
            String city=sm[4];
            String address = sm[5];
            String ZIP =sm[6];
            String artistName = sm[7];
            Integer numberOfAlbums = Integer.parseInt(sm[8]);
            concerts.add(new Concerts(name,numberTickets,price,new Locations(country,city,address,ZIP),artistName,numberOfAlbums));
        }
        //Read
        List<Movies> movies = new ArrayList<Movies>();
        for (String[] sm : readWrite.readFromCsvFile("movies.csv")) {
            String name=sm[0];
            Integer numberTickets=Integer.parseInt(sm[1]);
            Double price=Double.parseDouble(sm[2]);
            String country = sm[3];
            String city=sm[4];
            String address = sm[5];
            String ZIP =sm[6];
            String sC=sm[7];
            ShowCategory showCategory;
            if(sC.equals("DRAMA"))
                showCategory= ShowCategory.DRAMA;
            else if(sC.equals("THRILLER"))
                showCategory=ShowCategory.THRILLER;
            else if(sC.equals("COMEDY"))
                showCategory=ShowCategory.COMEDY;
            else if(sC.equals("ROMANTIC"))
                showCategory=ShowCategory.ROMANTIC;
            else if(sC.equals("HORROR"))
                showCategory=ShowCategory.HORROR;
            else if(sC.equals("SF"))
                showCategory=ShowCategory.SF;
            else
                throw new RuntimeException("Incorrect show category");
            Integer showLength = Integer.parseInt(sm[8]);
            String movieDirectorName=sm[9];
            Integer numberOfActors = Integer.parseInt(sm[10]);
            ArrayList<String> mainActorsName = new ArrayList<String>();
            for(int i=11;i<11+numberOfActors;i++)
                mainActorsName.add(sm[i]);
            Integer minimumAge = Integer.parseInt(sm[11+numberOfActors]);
            movies.add(new Movies(name,numberTickets,price,new Locations(country,city,address,ZIP),showCategory,showLength,movieDirectorName,mainActorsName,minimumAge));
        }
        //Read
        List<TennisGames> tennisGames = new ArrayList<TennisGames>();
        for (String[] sm : readWrite.readFromCsvFile("tennisGames.csv")) {
            String name=sm[0];
            Integer numberTickets=Integer.parseInt(sm[1]);
            Double price=Double.parseDouble(sm[2]);
            String country = sm[3];
            String city=sm[4];
            String address = sm[5];
            String ZIP =sm[6];
            Integer winsForFirst = Integer.parseInt(sm[7]);
            Integer winsForSecond = Integer.parseInt(sm[8]);
            String cT = sm[9];
            CompetitionType competitionType;
            if(cT.equals("NATIONAL"))
                competitionType = CompetitionType.NATIONAL;
            else if(cT.equals("INTERNATIONAL"))
                competitionType = CompetitionType.INTERNATIONAL;
            else
                throw new RuntimeException("No correct Competition Type");
            String firstPlayerName = sm[10];
            Integer firstPlayerRank = Integer.parseInt(sm[11]);
            String secondPlayerName = sm[12];
            Integer secondPlayerRank = Integer.parseInt(sm[13]);

            tennisGames.add(new TennisGames(name,numberTickets,price,new Locations(country,city,address,ZIP),winsForFirst,winsForSecond,competitionType,firstPlayerName,firstPlayerRank,secondPlayerName,secondPlayerRank));
        }
        //Read
        List<FootballGames> footballGames = new ArrayList<FootballGames>();
        for (String[] sm : readWrite.readFromCsvFile("footballGames.csv")) {
            String name=sm[0];
            Integer numberTickets=Integer.parseInt(sm[1]);
            Double price=Double.parseDouble(sm[2]);
            String country = sm[3];
            String city=sm[4];
            String address = sm[5];
            String ZIP =sm[6];
            Integer winsForFirst = Integer.parseInt(sm[7]);
            Integer winsForSecond = Integer.parseInt(sm[8]);
            String cT = sm[9];
            CompetitionType competitionType;
            if(cT.equals("NATIONAL"))
                competitionType = CompetitionType.NATIONAL;
            else if(cT.equals("INTERNATIONAL"))
                competitionType = CompetitionType.INTERNATIONAL;
            else
                throw new RuntimeException("No correct Competition Type");
            String homeTeam = sm[10];
            String awayTeam = sm[11];
            String competitionName = sm[12];
            Integer equalGames = Integer.parseInt(sm[13]);
            footballGames.add(new FootballGames(name,numberTickets,price,new Locations(country,city,address,ZIP),winsForFirst,winsForSecond,competitionType,homeTeam,awayTeam,competitionName,equalGames));
        }
        //Read
        List<Tours> tours = new ArrayList<Tours>();
        for (String[] sm : readWrite.readFromCsvFile("tours.csv")) {
            String name=sm[0];
            Integer numberTickets=Integer.parseInt(sm[1]);
            Double price=Double.parseDouble(sm[2]);
            String country = sm[3];
            String city=sm[4];
            String address = sm[5];
            String ZIP =sm[6];
            String tourDate = sm[7];
            Integer numberOfDays = Integer.parseInt(sm[8]);
            tours.add(new Tours(name,numberTickets,price,new Locations(country,city,address,ZIP),tourDate,numberOfDays));
        }
        System.out.println("Welcome to the main menu!");
        System.out.println("Pick an action!");
        System.out.println("0: STOP\n1: Add an event\n2: Add a client to an event\n3: Modify client personal data\n4: Capacity distribution for the concerts\n5: Review a movie\n6: Prediction for all sporting events\n7: Football games description\n8: Renew number of days for a tour\n9: Delete an event");

        while(true) {
            Scanner scanner = new Scanner(System.in);
            Integer option = scanner.nextInt();
            if(option==0){
                break;
            }
            else if (option == 1) {
                audit.add("Add an event"+","+Timestamp.from(Instant.now()).toString());
                readWrite.writeToAudit(audit);
                System.out.println("What kind of event is this?");
                System.out.println("1: Concert\n2: Movie\n3: Tennis Game\n4: Football Game\n5: Tour");
                Integer op = scanner.nextInt();
                if (op == 1) {
                    Concerts newInstance = new Concerts();
                    newInstance.addEvent();
                    concerts.add(newInstance);
                } else if (op == 2) {
                    Movies newInstance = new Movies();
                    newInstance.addEvent();
                    movies.add(newInstance);
                } else if (op == 3) {
                    TennisGames newInstance = new TennisGames();
                    newInstance.addEvent();
                    tennisGames.add(newInstance);
                } else if (op == 4) {
                    FootballGames newInstance = new FootballGames();
                    newInstance.addEvent();
                    footballGames.add(newInstance);
                } else if (op == 5) {
                    Tours newInstance = new Tours();
                    newInstance.addEvent();
                    tours.add(newInstance);
                } else {
                    System.out.println("No correct action provided!");
                }
                System.out.println("The active events are");
                System.out.println(concerts);
                System.out.println(movies);
                System.out.println(tennisGames);
                System.out.println(footballGames);
                System.out.println(tours);
            } else if (option == 2) {
                audit.add("Add a client to an event"+","+Timestamp.from(Instant.now()).toString());
                readWrite.writeToAudit(audit);
                System.out.println("Pick a ticket to an event");
                System.out.println("1: Concert\n2: Movie\n3: Tennis Game\n4: Football Game\n5: Tour");
                Integer op = scanner.nextInt();
                if (op == 1) {
                    if (concerts.size() == 0) {
                        System.out.println("There is no active concert!");
                    } else {
                        System.out.println("Pick a concert");
                        for (int i = 0; i < concerts.size(); i++) {
                            System.out.println(i + ": " + concerts.get(i));
                        }
                        Integer pick = scanner.nextInt();
                        if (pick >= concerts.size()) {
                            System.out.println("No correct action provided!");
                        } else {
                            concerts.get(pick).addClient();
                        }
                    }
                } else if (op == 2) {
                    if (movies.size() == 0) {
                        System.out.println("There is no active movie!");
                    } else {
                        System.out.println("Pick a movie");
                        for (int i = 0; i < movies.size(); i++) {
                            System.out.println(i + ": " + movies.get(i));
                        }
                        Integer pick = scanner.nextInt();
                        if (pick >= movies.size()) {
                            System.out.println("No correct action provided!");
                        } else {
                            movies.get(pick).addClient();
                        }
                    }
                } else if (op == 3) {
                    if (tennisGames.size() == 0) {
                        System.out.println("There is no active tennis game!");
                    } else {
                        System.out.println("Pick a tennis game");
                        for (int i = 0; i < tennisGames.size(); i++) {
                            System.out.println(i + ": " + tennisGames.get(i));
                        }
                        Integer pick = scanner.nextInt();
                        if (pick >= tennisGames.size()) {
                            System.out.println("No correct action provided!");
                        } else {
                            tennisGames.get(pick).addClient();
                        }
                    }
                } else if (op == 4) {
                    if (footballGames.size() == 0) {
                        System.out.println("There is no active football game!");
                    } else {
                        System.out.println("Pick a football game");
                        for (int i = 0; i < footballGames.size(); i++) {
                            System.out.println(i + ": " + footballGames.get(i));
                        }
                        Integer pick = scanner.nextInt();
                        if (pick >= footballGames.size()) {
                            System.out.println("No correct action provided!");
                        } else {
                            footballGames.get(pick).addClient();
                        }
                    }
                } else if (op == 5) {
                    if (tours.size() == 0) {
                        System.out.println("There is no active tour!");
                    } else {
                        System.out.println("Pick a tour");
                        for (int i = 0; i < tours.size(); i++) {
                            System.out.println(i + ": " + tours.get(i));
                        }
                        Integer pick = scanner.nextInt();
                        if (pick >= tours.size()) {
                            System.out.println("No correct action provided!");
                        } else {
                            tours.get(pick).addClient();
                        }
                    }
                } else {
                    System.out.println("No correct action provided!");
                }
            }
            else if(option==3){
                audit.add("Modify client personal data"+","+Timestamp.from(Instant.now()).toString());
                readWrite.writeToAudit(audit);
                System.out.println("Provide a valid ID");
                String ID = scanner.next();
                for(Concerts c: concerts){
                    c.modifyClientInformation(ID);
                }
                for(Movies c: movies){
                    c.modifyClientInformation(ID);
                }
                for(TennisGames c: tennisGames){
                    c.modifyClientInformation(ID);
                }
                for(FootballGames c: footballGames){
                    c.modifyClientInformation(ID);
                }
                for(Tours c: tours){
                    c.modifyClientInformation(ID);
                }
            }
            else if(option==4){
                audit.add("Capacity distribution for a concert"+","+Timestamp.from(Instant.now()).toString());
                readWrite.writeToAudit(audit);
                if(concerts.size()==0){
                    System.out.println("There is no active concert!");
                }
                for(Concerts c: concerts){
                    System.out.println("The capacity distribution for "+c.getName()+" is:");
                    c.capacityDistribution();
                }
            }
            else if(option==5){
                audit.add("Review a movie"+","+Timestamp.from(Instant.now()).toString());
                readWrite.writeToAudit(audit);
                if (movies.size() == 0) {
                    System.out.println("There is no active movie!");
                } else {
                    System.out.println("Pick a movie");
                    for (int i = 0; i < movies.size(); i++) {
                        System.out.println(i + ": " + movies.get(i));
                    }
                    Integer pick = scanner.nextInt();
                    if (pick >= movies.size()) {
                        System.out.println("No correct action provided!");
                    } else {
                        movies.get(pick).reviewShow();
                       System.out.println("The movie score is "+movies.get(pick).overallScore());
                    }
                }
            }
            else if(option==6){
                audit.add("Prediction for all sporting events"+","+Timestamp.from(Instant.now()).toString());
                readWrite.writeToAudit(audit);
                for(TennisGames tg: tennisGames){
                    System.out.println(tg.winnerPrediction());
                }
                for(FootballGames fg: footballGames){
                    System.out.println(fg.winnerPrediction());
                }
            }
            else if(option==7) {
                audit.add("Football game description"+","+Timestamp.from(Instant.now()).toString());
                readWrite.writeToAudit(audit);
                if (footballGames.size() == 0)
                    System.out.println("There is no active tennis game!");
                for (FootballGames fg : footballGames) {
                    System.out.println(fg.gameDescription());
                }
            }
            else if(option==8){
                audit.add("Renew number of days for a tour"+","+Timestamp.from(Instant.now()).toString());
                readWrite.writeToAudit(audit);
                if (tours.size() == 0) {
                    System.out.println("There is no active tour!");
                } else {
                    System.out.println("Pick a tour");
                    for (int i = 0; i < tours.size(); i++) {
                        System.out.println(i + ": " + tours.get(i));
                    }
                    Integer pick = scanner.nextInt();
                    if (pick >= tours.size()) {
                        System.out.println("No correct action provided!");
                    } else {
                        tours.get(pick).renewNumberOfDays();
                    }
                }
            }
            else if(option==9){
                audit.add("Remove an event"+","+Timestamp.from(Instant.now()).toString());
                readWrite.writeToAudit(audit);
                Integer i=0;
                for(Concerts c:concerts){
                    System.out.println(i+" "+c.getName());
                    i++;
                }
                for(Movies c:movies){
                    System.out.println(i+" "+c.getName());
                    i++;
                }
                for(TennisGames c:tennisGames){
                    System.out.println(i+" "+c.getName());
                    i++;
                }
                for(FootballGames c:footballGames){
                    System.out.println(i+" "+c.getName());
                    i++;
                }
                for(Tours c:tours){
                    System.out.println(i+" "+c.getName());
                    i++;
                }
                System.out.println("Pick an event to remove");
                Integer pick = scanner.nextInt();
                i=0;
                int j=0;
                for(Concerts c:concerts){
                    if(pick==i){
                        concerts.remove(j);
                        i++;
                        break;
                    }
                    i++;
                    j++;
                }
                j=0;
                for(Movies c:movies){
                    if(pick==i){
                        movies.remove(j);
                        i++;
                        break;
                    }
                    i++;
                    j++;
                }
                j=0;
                for(TennisGames c:tennisGames){
                    if(pick==i){
                        tennisGames.remove(j);
                        i++;
                        break;
                    }
                    i++;
                    j++;
                }
                j=0;
                for(FootballGames c:footballGames){
                    if(pick==i){
                        footballGames.remove(j);
                        i++;
                        break;
                    }
                    i++;
                    j++;
                }
                j=0;
                for(Tours c:tours){
                    if(pick==i){
                        tours.remove(j);
                        i++;
                        break;
                    }
                    i++;
                    j++;
                }
            }
            else{
                System.out.println("No correct action provided!");
            }
            //Write the classes back into the csv file after every operation
            List<String[]> writeConcerts = new ArrayList<>();
            for(Concerts c:concerts){
                String name=c.getName();
                String numberTickets= c.getNumberTickets().toString();
                String price=c.getPrice().toString();
                String country = c.getLocation().getCountry();
                String city=c.getLocation().getCity();
                String address = c.getLocation().getAddress();
                String ZIP =c.getLocation().getZIP();
                String artistName = c.getArtistName();
                String numberOfAlbums = c.getNumberOfAlbums().toString();
                String coma=name+","+numberTickets+","+price+","+country+","+city+","+address+","+ZIP+","+artistName+","+numberOfAlbums;
                writeConcerts.add(coma.split(","));
            }

            List<String[]> writeMovies = new ArrayList<>();
            for(Movies m:movies){
                String name=m.getName();
                String numberTickets=m.getNumberTickets().toString();
                String price=m.getPrice().toString();
                String country = m.getLocation().getCountry();
                String city=m.getLocation().getCity();
                String address = m.getLocation().getAddress();
                String ZIP =m.getLocation().getZIP();
                ShowCategory showCategory = m.getShowCategory();
                String showLength = m.getShowLength().toString();
                String movieDirectorName=m.getMovieDirectorName();
                Integer number = m.getMainActorsName().size();
                String numberOfActors = number.toString();
                String coma=name+","+numberTickets+","+price+","+country+","+city+","+address+","+ZIP+","+showCategory+","+showLength+","+movieDirectorName+","+numberOfActors+",";
                for(String s: m.getMainActorsName())
                    coma+=s+",";
                String minimumAge = m.getMinimumAge().toString();
                coma+=minimumAge;
                writeMovies.add(coma.split(","));
            }

            List<String[]> writeTennisGames = new ArrayList<>();
            for(TennisGames tg:tennisGames){
                String name=tg.getName();
                String numberTickets=tg.getNumberTickets().toString();
                String price=tg.getPrice().toString();
                String country = tg.getLocation().getCountry();
                String city=tg.getLocation().getCity();
                String address = tg.getLocation().getAddress();
                String ZIP =tg.getLocation().getZIP();
                String winsForFirst = tg.getWinsForFirst().toString();
                String winsForSecond = tg.getWinsForSecond().toString();
                String competitionType = tg.getCompetitionType().toString();
                String firstPlayerName = tg.getFirstPlayerName();
                String firstPlayerRank = tg.getFirstPlayerRank().toString();
                String secondPlayerName = tg.getSecondPlayerName();
                String secondPlayerRank = tg.getSecondPlayerRank().toString();
                String coma=name+","+numberTickets+","+price+","+country+","+city+","+address+","+ZIP+","+winsForFirst+","+winsForSecond+","+competitionType+","+firstPlayerName+","+firstPlayerRank+","+secondPlayerName+","+secondPlayerRank;
                writeTennisGames.add(coma.split(","));
            }

            List<String[]> writeFootballGames = new ArrayList<>();
            for(FootballGames fg:footballGames){
                String name=fg.getName();
                String numberTickets=fg.getNumberTickets().toString();
                String price=fg.getPrice().toString();
                String country = fg.getLocation().getCountry();
                String city=fg.getLocation().getCity();
                String address = fg.getLocation().getAddress();
                String ZIP =fg.getLocation().getZIP();
                String winsForFirst = fg.getWinsForFirst().toString();
                String winsForSecond = fg.getWinsForSecond().toString();
                String competitionType = fg.getCompetitionType().toString();
                String homeTeam = fg.getHomeTeam();
                String awayTeam = fg.getAwayTeam();
                String competitionName = fg.getCompetitionName();
                String equalGames = fg.getEqualGames().toString();
                String coma=name+","+numberTickets+","+price+","+country+","+city+","+address+","+ZIP+","+winsForFirst+","+winsForSecond+","+competitionType+","+homeTeam+","+awayTeam+","+competitionName+","+equalGames;
                writeFootballGames.add(coma.split(","));
            }

            List<String[]> writeTours = new ArrayList<>();
            for(Tours c:tours){
                String name=c.getName();
                String numberTickets= c.getNumberTickets().toString();
                String price=c.getPrice().toString();
                String country = c.getLocation().getCountry();
                String city=c.getLocation().getCity();
                String address = c.getLocation().getAddress();
                String ZIP =c.getLocation().getZIP();
                String tourDate = c.getTourDate();
                String numberOfDays = c.getNumberOfDays().toString();
                String coma=name+","+numberTickets+","+price+","+country+","+city+","+address+","+ZIP+","+tourDate+","+numberOfDays;
                writeTours.add(coma.split(","));
            }

            readWrite.writeToCsvFile(writeConcerts, "concerts.csv");
            readWrite.writeToCsvFile(writeMovies, "movies.csv");
            readWrite.writeToCsvFile(writeTennisGames, "tennisGames.csv");
            readWrite.writeToCsvFile(writeFootballGames, "footballGames.csv");
            readWrite.writeToCsvFile(writeTours, "tours.csv");


        }
        return single_instance;
    }

}
