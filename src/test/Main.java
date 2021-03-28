package test;

import clients.ClientPreferences;
import clients.Clients;
import events.Events;
import events.concerts.Concerts;
import events.culutral.ShowCategory;
import events.culutral.movies.Movies;
import events.sports.CompetitionType;
import events.sports.football.FootballGames;
import events.sports.tennis.TennisGames;
import events.tours.Tours;
import locations.Locations;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class Main {
    public static Boolean cmp(String a, String b){
        return a.length()>b.length();
    }
    public static void main(String[] args)
    {
       // demonstrating the project features

        //Locations class is used to set a place where an event will take place.
        //It can be initialised by specifying the attributes or by using the addLocation method, where you can select the attributes by reading them
        Locations location1 = new Locations("Romania","Bucharest","Streed Academiei","12345");
        Locations location2 = new Locations();
        location2.addLocation();
        System.out.println(location1+" "+location2);

        //Clients class is used to see the list of participants for an event
        //You can add the preferences of a client by adding them into a set
        Clients client = new Clients("123421","Ion Popescu",23,"ion.popescu@gmail.com","0123456621");
        client.addClientPreferences(ClientPreferences.TRAVELING);
        System.out.println(client.aboutClientPreferences());
        client.addClientPreferences(ClientPreferences.SPORTS);
        System.out.println(client.aboutClientPreferences());
        client.addClientPreferences(ClientPreferences.SPORTS);
        System.out.println(client.aboutClientPreferences());

        //Abstract Class Events is use to establish the principal attributes
        //You can add an Event, add an Client or update the personal information about a client

        //Abstract Class Sports is a subclass of Events and is use to establish the principal attributes of a sporting event
        //It overrides the add Event method and has an abstract method that is used by its subclass to give a prediction about a winner

        //TennisGames class is a subclass of Sports and is use to have all the details about a tennis game
        // It overrides the add Event method and has a specific formula to determine the possible winner of a competition
        TennisGames tg = new TennisGames();
        tg.addEvent();
        System.out.println(tg.winnerPrediction());
        tg.addClient(); // illustrate the addClient method from Events class
        tg.modifyClientInformation("12345"); // modify the Information of a client with a given personID


        //FootballGames class is a subclass of Sports and is use to have all the details about a football game
        //It overrides the add Event method, has a specific formula to determine the possible winner or a competition and has a method that tells you about the importance of a match by the number of games that two teams had
        FootballGames fg = new FootballGames();
        fg.addEvent();
        System.out.println(fg.winnerPrediction());
        System.out.println(fg.gameDescription());

        //Abstract class Cultural is a subclass of Events that is use to establish the principal attributes of a show event
        //It overrides the add Event method and has an abstract method that is used by the subclasses to review a show\

        //Movies class is a subclass of Cultural and has the purpose to have all the characteristics of a movie
        //It overrides the add Event method, add Client method and has the possibility to review a movie by giving a number of stars
        Movies m = new Movies();
        m.addEvent();
        m.addClient(); // if the client is under the permission age for the movie, will throw an exception
        m.reviewShow();
        m.reviewShow();
        m.reviewShow();
        System.out.println(m.overallScore()); // the average of the scores

        // Tours class is a subclass of Events and implements the details of a vacation/excursion
        //It overrides the add Event method and gives the possibility to modify the number of days, that will automatically recalculate the price
        Tours t = new Tours();
        t.addEvent();
        t.renewNumberOfDays();
        System.out.println(t);

        //Concerts class is a subclass of Events and implements the details of a musical concert
        //It overrides the add Event method and has a method that calculate the places distribution by the number of tickets
        Concerts c = new Concerts();
        c.addEvent();
        c.capacityDistribution();
        System.out.println(c);

    }
}
