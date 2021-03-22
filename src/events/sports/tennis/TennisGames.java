package events.sports.tennis;

import events.sports.CompetitionType;
import events.sports.Sports;
import locations.Locations;

import java.util.Objects;
import java.util.Scanner;

public class TennisGames extends Sports {
    private String firstPlayerName;
    private Integer firstPlayerRank;
    private String secondPlayerName;
    private Integer secondPlayerRank;

    public  TennisGames(){

    }

    public TennisGames(String name, Integer numberTickets, Double price, Locations location, Integer winsForFirst, Integer winsForSecond, CompetitionType competitionType, String firstPlayerName, Integer firstPlayerRank, String secondPlayerName, Integer secondPlayerRank) {
        super(name, numberTickets, price, location, winsForFirst, winsForSecond, competitionType);
        this.firstPlayerName = firstPlayerName;
        this.firstPlayerRank = firstPlayerRank;
        this.secondPlayerName = secondPlayerName;
        this.secondPlayerRank = secondPlayerRank;
    }
    //10/10 override


    @Override
    public void addEvent() {
        super.addEvent();
        Scanner scanner = new Scanner(System.in);

        System.out.println("First player name: ");
        String firstPlayerName = scanner.nextLine();

        System.out.println("Second player name: ");
        String secondPlayerName = scanner.nextLine();

        System.out.println("First player rank: ");
        Integer firstPlayerRank = scanner.nextInt();

        System.out.println("Second player rank: ");
        Integer secondPlayerRank = scanner.nextInt();

        this.firstPlayerRank=firstPlayerRank;
        this.secondPlayerName=secondPlayerName;
        this.firstPlayerName=firstPlayerName;
        this.secondPlayerRank=secondPlayerRank;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public void setFirstPlayerName(String firstPlayerName) {
        this.firstPlayerName = firstPlayerName;
    }

    public Integer getFirstPlayerRank() {
        return firstPlayerRank;
    }

    public void setFirstPlayerRank(Integer firstPlayerRank) {
        this.firstPlayerRank = firstPlayerRank;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public void setSecondPlayerName(String secondPlayerName) {
        this.secondPlayerName = secondPlayerName;
    }

    public Integer getSecondPlayerRank() {
        return secondPlayerRank;
    }

    public void setSecondPlayerRank(Integer secondPlayerRank) {
        this.secondPlayerRank = secondPlayerRank;
    }
    // 3/10 override sports
    @Override
    public String winnerPrediction() {
        Double chanceFirst =(double)(winsForFirst+secondPlayerRank) / (winsForFirst+winsForSecond+secondPlayerRank );
        Double chanceSecond =(double)(winsForSecond+firstPlayerRank) / (winsForFirst+winsForSecond+firstPlayerRank );

        return "The prediction| for "+ firstPlayerName+" to win is "+chanceFirst+"| for "+secondPlayerName+" to win is "+chanceSecond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TennisGames that = (TennisGames) o;
        return getName()==that.getName() && getNumberTickets()==that.getNumberTickets() && getPrice()==that.getPrice() && getLocation()==that.getLocation() && getWinsForFirst() == that.winsForFirst && winsForSecond == that.winsForSecond && competitionType == that.competitionType && firstPlayerName.equals(that.firstPlayerName) && firstPlayerRank.equals(that.firstPlayerRank) && secondPlayerName.equals(that.secondPlayerName) && secondPlayerRank.equals(that.secondPlayerRank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstPlayerName, firstPlayerRank, secondPlayerName, secondPlayerRank);
    }

    @Override
    public String toString() {
        return "TennisGames{" +
                "firstPlayerName='" + firstPlayerName + '\'' +
                ", firstPlayerRank=" + firstPlayerRank +
                ", secondPlayerName='" + secondPlayerName + '\'' +
                ", secondPlayerRank=" + secondPlayerRank +
                "} " + super.toString();
    }
}

