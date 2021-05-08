package events.tours;

import events.Events;
import locations.Locations;

import java.util.Objects;
import java.util.Scanner;

public class Tours extends Events {
    String tourDate;
    Integer numberOfDays;
    public Tours(){

    }
    public Tours(String name, Integer numberTickets,Double price, Locations location, String tourDate,Integer numberOfDays){
        super(name,numberTickets,price,location);
        this.tourDate=tourDate;
        this.numberOfDays=numberOfDays;
    }
    @Override
    public void addEvent() {
        super.addEvent();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tour date (in dd/mm/yyyy format): ");
        String tourDate = scanner.nextLine();

        System.out.println("Number of days: ");
        Integer numberOfDays= scanner.nextInt();

        this.tourDate=tourDate;
        this.numberOfDays=numberOfDays;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void renewNumberOfDays(){
        Integer newNumberOfDays;
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many days do you wish to stay? ");
        newNumberOfDays = scanner.nextInt();
        System.out.println("");
        price = (Double) ((Double) price/numberOfDays)*newNumberOfDays;
        System.out.println("The price after renewal is: "+price+" euro");
        numberOfDays=newNumberOfDays;
    }

    public String getTourDate() {
        return tourDate;
    }

    public void setTourDate(String tourDate) {
        this.tourDate = tourDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tours tours = (Tours) o;
        return getName()==tours.getName() && getNumberTickets()==tours.getNumberTickets() && getPrice()==tours.getPrice() && getLocation()==tours.getLocation() &&  tourDate.equals(tours.tourDate) && numberOfDays.equals(tours.numberOfDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tourDate, numberOfDays);
    }

    @Override
    public String toString() {
        return "Tours{" +
                "tourDate='" + tourDate + '\'' +
                ", numberOfDays=" + numberOfDays +
                "} " + super.toString();
    }
}
