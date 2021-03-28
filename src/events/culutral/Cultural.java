package events.culutral;

import events.Events;
import locations.Locations;

import java.security.PublicKey;
import java.util.Objects;
import java.util.Scanner;

public abstract class Cultural extends Events {
    protected ShowCategory showCategory;
    protected Integer showLength;
    public Cultural(){

    }
    public Cultural(String name, Integer numberTickets,Double price, Locations location,ShowCategory showCategory,Integer showLength){
        super(name,numberTickets,price,location);
        this.showCategory=showCategory;
        this.showLength=showLength;
    }
    //!Action
    @Override
    public void addEvent() {
        super.addEvent();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the show category(DRAMA/THRILLER/COMEDY/ROMANTIC/HORROR/SF)");
        String sC = scanner.nextLine();

        ShowCategory showCategory;

        if(sC.equals("DRAMA"))
            showCategory=ShowCategory.DRAMA;
        else if(sC.equals("THRILLER"))
            showCategory=ShowCategory.THRILLER;
        else if(sC.equals("COMEDY"))
            showCategory=ShowCategory.COMEDY;
        else if(sC.equals("ROMANTIC"))
            showCategory=ShowCategory.ROMANTIC;
        else if(sC.equals("HORROR"))
            showCategory=ShowCategory.HORROR;
        else if(sC.equals("SF"))
            showCategory=ShowCategory.SF;
        else
            throw new RuntimeException("Incorrect show category");

        System.out.println("Length of the show: ");
        Integer showLength = scanner.nextInt();

        this.showLength=showLength;
        this.showCategory=showCategory;
    }
    //Action!
    public ShowCategory getShowCategory() {
        return showCategory;
    }

    public void setShowCategory(ShowCategory showCategory) {
        this.showCategory = showCategory;
    }

    public Integer getShowLength() {
        return showLength;
    }

    public void setShowLength(Integer showLength) {
        this.showLength = showLength;
    }
    //!Action
    public abstract void reviewShow();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cultural cultural = (Cultural) o;
        return getName()==cultural.getName() && getNumberTickets()==cultural.getNumberTickets() && getPrice()==cultural.getPrice() && getLocation()==cultural.getLocation() && showCategory == cultural.showCategory && showLength.equals(cultural.showLength);
    }
    //Action!
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), showCategory, showLength);
    }

    @Override
    public String toString() {
        return "Cultural{" +
                "showCategory=" + showCategory +
                ", showLength=" + showLength +
                "} " + super.toString();
    }
}
