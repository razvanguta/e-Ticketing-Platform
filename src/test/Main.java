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
import service.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Service menu = Service.Menu(readFromCsvFile("audit.csv"),readFromCsvFile("concerts.csv"),readFromCsvFile("movies.csv"),readFromCsvFile("tennisGames.csv"),readFromCsvFile("footballGames.csv"),readFromCsvFile("tours.csv"));
    }
    public static List<String[]> readFromCsvFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String[]> list = new ArrayList<>();
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] array = (String[]) line.split(",");
                list.add(array);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

