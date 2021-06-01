package test;

import clients.ClientPreferences;
import clients.Clients;
import config.DatabaseConfiguration;
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
import service.ServiceDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Service menu = Service.Menu();
        ServiceDB menu2=ServiceDB.Menu();
    }
}

