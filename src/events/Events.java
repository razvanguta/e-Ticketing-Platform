package events;

import clients.Clients;

import events.sports.CompetitionType;
import events.sports.tennis.TennisGames;
import locations.Locations;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public abstract class Events {
   protected String name;
   protected Integer numberTickets;
   protected Double price;
   protected ArrayList<Clients> clients = new ArrayList<Clients>();
   protected Locations location;
    public Events(){

    }
   public Events(String name, Integer numberTickets,Double price, Locations location) {
       this.name=name;
       this.numberTickets=numberTickets;
       this.price=price;
       this.location=location;
   }
    // 10/10
    public void addEvent(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name of the event: ");
        String name = scanner.nextLine();

        System.out.println("Location of the event: ");
        Locations location = new Locations();
        location.addLocation();

        System.out.println("Number of tickets for the event: ");
        Integer numberTickets = scanner.nextInt();

        System.out.println("Price per ticket: ");
        Double price = scanner.nextDouble();

        this.name=name;
        this.numberTickets=numberTickets;
        this.location=location;
        this.price=price;

    }
    // 1/10
   public void addClient(){
       if(numberTickets==0)
       {
           System.out.println("There are no tickets for this event! :(");
           return;
       }
       Scanner scanner = new Scanner(System.in);
       System.out.print("#####################"+'\n');
       System.out.print("Before you can book a ticket to an event, you have to register with your personal data!"+'\n');
       System.out.print("Your Personal ID = ");
       String personalID = scanner.nextLine();

       for(int i=0;i<personalID.length();i++) {
           if (personalID.charAt(i) < 48 || personalID.charAt(i) > 57) {
               throw new RuntimeException("Your Personal ID must contains only digits!");
           }
       }
        for(int i=0;i<clients.size();i++){
            if(clients.get(i).getPersonalID().equals(personalID)){
                System.out.println("You already registered with this ID!");
                return;
            }
        }
       System.out.print("Your full name = ");
       String name = scanner.nextLine();

       System.out.print("Your email = ");
       String email = scanner.nextLine();

       System.out.print("Your phone number = ");
       String phoneNumber = scanner.nextLine();

       for(int i=0;i<phoneNumber.length();i++) {
           if (phoneNumber.charAt(i) < 48 || phoneNumber.charAt(i) > 57) {
               throw new RuntimeException("Your phone number must contains only digits!");
           }
       }

       System.out.print("Your age (in years) = ");
       Integer age = scanner.nextInt();

       System.out.print("#####################"+'\n');

       clients.add(new Clients(personalID,name,age,email,phoneNumber));
       numberTickets--;

   }

   // 5/10
    public void modifyClientInformation(String personID){
       Boolean exist = false;
       for(int q=0;q<clients.size();q++){
           if(personID.equals(clients.get(q).getPersonalID())){
               Scanner scanner = new Scanner(System.in);
               System.out.print("#####################"+'\n');
               System.out.print("Change the informations!"+'\n');

               System.out.print("Your new email = ");
               String nemail = scanner.nextLine();

               System.out.print("Your new phone number = ");
               String nphoneNumber = scanner.nextLine();

               for(int i=0;i<nphoneNumber.length();i++) {
                   if (nphoneNumber.charAt(i) < 48 || nphoneNumber.charAt(i) > 57) {
                       throw new RuntimeException("Your phone number must contains only digits!");
                   }
               }
               this.clients.get(q).setPhoneNumber(nphoneNumber);
               this.clients.get(q).setEmail(nemail);

               System.out.print("#####################"+'\n');

               exist=true;
               break;
           }
       }
       if(exist==false)
           System.out.println("There is no client at this event with the Personal ID you provided!");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberTickets() {
        return numberTickets;
    }

    public void setNumberTickets(Integer numberTickets) {
        this.numberTickets = numberTickets;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Locations getLocation(){
       return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Events events = (Events) o;
        return name.equals(events.name) && numberTickets.equals(events.numberTickets) && price.equals(events.price) && clients.equals(events.clients) && location.equals(events.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberTickets, price, clients, location);
    }

    @Override
    public String toString() {
        return "Events{" +
                "name='" + name + '\'' +
                ", numberTickets=" + numberTickets +
                ", price=" + price +
                ", clients=" + clients +
                ", location=" + location +
                '}';
    }
}
