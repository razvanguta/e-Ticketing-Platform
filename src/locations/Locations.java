package locations;

import events.sports.CompetitionType;

import java.util.Objects;
import java.util.Scanner;

public class Locations {
    private String country;
    private String city;
    private String address;
    private String ZIP;
    public Locations(){

    }
    public Locations(String country,String city,String address, String ZIP){
        this.country=country;
        this.city=city;
        this.address=address;
        this.ZIP=ZIP;
        for(int i=0;i<ZIP.length();i++) {
            if (ZIP.charAt(i) < 48 || ZIP.charAt(i) > 57) {
                throw new RuntimeException("Your ZIP must contains only digits!");
            }
        }

    }
    //9/10
    public void addLocation(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("######################");

        System.out.println("Introduce the country: ");
        String country = scanner.nextLine();

        System.out.println("Introduce the city: ");
        String city = scanner.nextLine();

        System.out.println("Introduce the address: ");
        String address = scanner.nextLine();

        System.out.println("Introduce the ZIP: ");
        String ZIP = scanner.nextLine();

        this.country=country;
        this.city=city;
        this.address=address;
        this.ZIP=ZIP;


        System.out.println("######################");
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZIP() {
        return ZIP;
    }

    public void setZIP(String ZIP) {
        this.ZIP = ZIP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locations locations = (Locations) o;
        return country.equals(locations.country) && city.equals(locations.city) && address.equals(locations.address) && ZIP.equals(locations.ZIP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, address, ZIP);
    }

    @Override
    public String toString() {
        return "Locations{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", ZIP='" + ZIP + '\'' +
                '}';
    }

}
