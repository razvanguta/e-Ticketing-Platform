package events.concerts;

import events.Events;
import locations.Locations;

import java.util.Objects;
import java.util.Scanner;

public class Concerts extends Events {
    private String artistName;
    private Integer numberOfAlbums;
    public Concerts(){

    }
    public Concerts(String name, Integer numberTickets, Double price, Locations location,String artistName, Integer numberOfAlbums){
        super(name,numberTickets,price,location);
        this.artistName=artistName;
        this.numberOfAlbums=numberOfAlbums;
    }
    @Override
    public void addEvent() {
        super.addEvent();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Artist Name: ");
        String artistName = scanner.nextLine();

        System.out.println("Number of Albums");
        Integer numberOfAlbums = scanner.nextInt();

        this.artistName=artistName;
        this.numberOfAlbums=numberOfAlbums;
    }
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Integer getNumberOfAlbums() {
        return numberOfAlbums;
    }

    public void setNumberOfAlbums(Integer numberOfAlbums) {
        this.numberOfAlbums = numberOfAlbums;
    }

    public void capacityDistribution(){
        System.out.println((int)numberTickets*0.1+" tickets are in the first 5 rows");
        System.out.println((int)numberTickets*0.3+" tickets are in the next 15 rows");
        System.out.println((int)numberTickets*0.4+" tickets are in the next 25 rows");
        System.out.println((int)numberTickets*0.2+" tickets are in the next 25+ rows");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Concerts concerts = (Concerts) o;
        return getName()==concerts.getName() && getNumberTickets()==concerts.getNumberTickets() && getPrice()==concerts.getPrice() && getLocation()==concerts.getLocation() && artistName.equals(concerts.artistName) && numberOfAlbums.equals(concerts.numberOfAlbums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), artistName, numberOfAlbums);
    }

    @Override
    public String toString() {
        return "Concerts{" +
                "artistName='" + artistName + '\'' +
                ", numberOfAlbums=" + numberOfAlbums +
                "} " + super.toString();
    }
}
