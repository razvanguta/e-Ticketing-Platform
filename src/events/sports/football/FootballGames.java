package events.sports.football;

import events.sports.CompetitionType;
import events.sports.Sports;
import locations.Locations;

import java.util.Objects;
import java.util.Scanner;

public class FootballGames extends Sports{
   private final String message = new String("The access in the stadium is forbidden with objects that could hurt the players or other spectators!");
   private String homeTeam;
   private String awayTeam;
   private String competitionName;
   private Integer equalGames;
   public FootballGames(){
        System.out.println(message);
   }
   public FootballGames(String name, Integer numberTickets, Double price, Locations location, Integer winsForFirst, Integer winsForSecond, CompetitionType competitionType,String homeTeam,String awayTeam,String competitionName, Integer equalGames){
       super(name, numberTickets, price, location, winsForFirst, winsForSecond, competitionType);
       System.out.println(message);
       this.homeTeam=homeTeam;
       this.awayTeam=awayTeam;
       this.competitionName=competitionName;
       this.equalGames=equalGames;
   }
    //!Action
    @Override
    public void addEvent() {
        super.addEvent();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Home team name: ");
        String homeTeam = scanner.nextLine();

        System.out.println("Away team name: ");
        String awayTeam = scanner.nextLine();

        System.out.println("Competition name: ");
        String competitionName = scanner.nextLine();

        System.out.println("Number of equal games: ");
        Integer equalGames = scanner.nextInt();

        this.homeTeam=homeTeam;
        this.awayTeam=awayTeam;
        this.competitionName=competitionName;
        this.equalGames=equalGames;
    }
    //Action!
    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Integer getEqualGames() {
        return equalGames;
    }

    public void setEqualGames(Integer equalGames) {
        this.equalGames = equalGames;
    }

    //!Actions
    @Override
    public String winnerPrediction() {

            Double chanceFirst =(double)(winsForFirst) / (winsForFirst+winsForSecond+equalGames) ;
            Double chanceSecond =(double)(winsForSecond) / (winsForFirst+winsForSecond+equalGames) ;
            Double chanceEqual = (double)(equalGames) / (winsForFirst+winsForSecond+equalGames)         ;
            return "The prediction| for "+ homeTeam+" to win is "+chanceFirst+"| for "+awayTeam+" to win is "+chanceSecond+"| for a draw is "+chanceEqual;
     }
    public String gameDescription(){
       Integer totalGames = winsForFirst+winsForSecond+equalGames;
       if(totalGames>100){
           return "The game between "+homeTeam+" and "+awayTeam+" is a traditional derby in "+competitionName+"!";
       }
       else if(totalGames>50 && totalGames<100){
           return "The game between "+homeTeam+" and "+awayTeam+" has a great rivalry in "+competitionName+"!";
       }
       else if(totalGames>25 && totalGames<=50){
           return "The game between "+homeTeam+" and "+awayTeam+" has high-stakes in "+competitionName+"!";
       }
       else if(totalGames>10 && totalGames<=25){
           return "The game between "+homeTeam+" and "+awayTeam+" is a spectacular confrontation in "+competitionName+"!";
       }
       else{
               return "The game between "+homeTeam+" and "+awayTeam+" has few total games, but it will be a great show to watch in "+competitionName+"!";
       }
    }
    //Actions!

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FootballGames that = (FootballGames) o;
        return getName()==that.getName() && getNumberTickets()==that.getNumberTickets() && getPrice()==that.getPrice() && getLocation()==that.getLocation() && homeTeam.equals(that.homeTeam) && awayTeam.equals(that.awayTeam) && competitionName.equals(that.competitionName) && equalGames.equals(that.equalGames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), homeTeam, awayTeam, competitionName,equalGames);
    }

    @Override
    public String toString() {
        return "FootballGames{" +
                "homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", competitionName='" + competitionName + '\'' +
                ", equalGames=" + equalGames + '\'' +
                "} " + super.toString();
    }
}
