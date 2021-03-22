package events.culutral.movies;

import clients.Clients;
import events.culutral.Cultural;
import events.culutral.ShowCategory;
import locations.Locations;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Movies extends Cultural {
    private Double reviewScore;
    private String movieDirectorName;
    private ArrayList<String> mainActorsName = new ArrayList<String>();
    public Integer minimumAge;
    public Movies(){

    }
    public Movies(String name, Integer numberTickets, Double price, Locations location, ShowCategory showCategory, Integer showLength,String movieDirectorName, ArrayList<String> mainActorsName, Integer minimumAge){
        super(name,numberTickets,price,location,showCategory,showLength);
        this.movieDirectorName=movieDirectorName;
        this.mainActorsName=mainActorsName;
        this.minimumAge=minimumAge;
        this.reviewScore=0d;
    }
    //10/10 override

    @Override
    public void addEvent() {
        super.addEvent();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Movie Director name: ");
        String movieDirectorName = scanner.nextLine();

        System.out.println("Main Actors: ");
        ArrayList<String> mainActorsName = new ArrayList<String>();
        while (true){
            System.out.println("Press an empty enter if you finish the list of actors");
            String nextActor = scanner.nextLine();
            if(nextActor.equals(""))
                break;
            else
                mainActorsName.add(nextActor);
        }

        System.out.println("Minimum age: ");
        Integer minimumAge = scanner.nextInt();

        this.movieDirectorName=movieDirectorName;
        this.mainActorsName=mainActorsName;
        this.minimumAge=minimumAge;
    }

    public ArrayList<String> getMainActorsName() {
        return mainActorsName;
    }

    public void setMainActorsName(ArrayList<String> mainActorsName) {
        this.mainActorsName = mainActorsName;
    }

    public String getMovieDirectorName() {
        return movieDirectorName;
    }

    public void setMovieDirectorName(String movieDirectorName) {
        this.movieDirectorName = movieDirectorName;
    }

    public Integer getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(Integer minimumAge) {
        this.minimumAge = minimumAge;
    }
    // 1/10 override

    @Override
    public void addClient() {
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
                throw new ArithmeticException("Your Personal ID must contains only digits!");
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
                throw new ArithmeticException("Your phone number must contains only digits!");
            }
        }

        System.out.print("Your age (in years) = ");
        Integer age = scanner.nextInt();

        System.out.print("#####################"+'\n');

        if(age<minimumAge){
            throw new ArithmeticException("You don't have the minimum age to attend this movie!");
        }
        clients.add(new Clients(personalID,name,age,email,phoneNumber));
        numberTickets--;

    }
    // 5/10 override
    @Override
    public void modifyClientInformation(String personID) {
        super.modifyClientInformation(personID);
    }
    // 6/10 override
    @Override
    public void reviewShow() {
        double numberOfStars=0d;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a number from 1 to 5!");
        System.out.println("Was the movie a match with the category we selected?");
        numberOfStars+=scanner.nextInt();
        System.out.println();

        System.out.println("How was the plot?");
        numberOfStars+=scanner.nextInt();
        System.out.println();

        System.out.println("How were the actors?");
        numberOfStars+=scanner.nextInt();
        System.out.println();

        System.out.println("How was the ending scene?");
        numberOfStars+=scanner.nextInt();
        System.out.println();

        System.out.println("Would you recommend the movie?");
        numberOfStars+=scanner.nextInt();
        System.out.println();

        numberOfStars=(double) numberOfStars/5;

        if(reviewScore+numberOfStars>5)
        reviewScore=(double) (reviewScore+numberOfStars)/2;
        else
            reviewScore=(double) numberOfStars;
    }
    // 6/10 extention
    public double overallScore(){
        return reviewScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Movies movies = (Movies) o;
        return getName()==movies.getName() && getNumberTickets()==movies.getNumberTickets() && getPrice()==movies.getPrice() && getLocation()==movies.getLocation() && showCategory == movies.showCategory && showLength.equals(movies.showLength)&&movieDirectorName.equals(movies.movieDirectorName) && mainActorsName.equals(movies.mainActorsName) && minimumAge.equals(movies.minimumAge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), movieDirectorName, mainActorsName, minimumAge);
    }

    @Override
    public String toString() {
        return "Movies{" +
                "movieDirectorName='" + movieDirectorName + '\'' +
                ", mainActorsName=" + mainActorsName +
                ", minimumAge=" + minimumAge +
                "} " + super.toString();
    }
}
