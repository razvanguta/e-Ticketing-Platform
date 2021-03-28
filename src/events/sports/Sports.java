package events.sports;

import events.Events;
import locations.Locations;

import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.Scanner;
import java.util.function.IntToDoubleFunction;

public abstract class Sports extends Events{
    protected Integer winsForFirst;
    protected Integer winsForSecond;
    protected CompetitionType competitionType;
    public Sports(){

    }
    public Sports(String name, Integer numberTickets, Double price, Locations location,Integer winsForFirst, Integer winsForSecond, CompetitionType competitionType){
      super(name,numberTickets,price,location);
       this.winsForFirst=winsForFirst;
       this.winsForSecond=winsForSecond;
       this.competitionType=competitionType;
    }
    //!Action
    @Override
    public void addEvent() {
        super.addEvent();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Competition type(NATIONAL/INTERNATIONAL): ");
        String cT = scanner.nextLine();
        CompetitionType competitionType;
        if(cT.equals("NATIONAL"))
            competitionType = CompetitionType.NATIONAL;
        else if(cT.equals("INTERNATIONAL"))
            competitionType = CompetitionType.INTERNATIONAL;
        else
            throw new RuntimeException("No correct Competition Type");

        System.out.println("Wins for the first player: ");
        Integer winsForFirst = scanner.nextInt();

        System.out.println("Wins for the second player: ");
        Integer winsForSecond = scanner.nextInt();



        this.competitionType=competitionType;
        this.winsForSecond=winsForSecond;
        this.winsForFirst=winsForFirst;
    }
    //Action!
    public CompetitionType getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }

    public Integer getWinsForFirst() {
        return winsForFirst;
    }

    public void setWinsForFirst(int winsForFirst) {
        this.winsForFirst = winsForFirst;
    }

    public Integer getWinsForSecond() {
        return winsForSecond;
    }

    public void setWinsForSecond(int winsForSecond) {
        this.winsForSecond = winsForSecond;
    }
    // !Action
    public abstract String winnerPrediction();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sports sports = (Sports) o;
        return getName()==sports.getName() && getNumberTickets()==sports.getNumberTickets() && getPrice()==sports.getPrice() && getLocation()==sports.getLocation() && winsForFirst == sports.winsForFirst && winsForSecond == sports.winsForSecond && competitionType == sports.competitionType;
    }
    //Action!
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),winsForFirst, winsForSecond, competitionType);
    }

    @Override
    public String toString() {
        return "Sports{" +
                "winsForFirst=" + winsForFirst +
                ", winsForSecond=" + winsForSecond +
                ", competitionType=" + competitionType +
                "} " + super.toString();
    }
}
