package clients;

import java.util.*;

public class Clients {
   private String personalID;
   private String name;
   private Integer age;
   private String email;
   private String phoneNumber;
   private static Integer numberOfClients=0;
   Set<ClientPreferences> cp = new HashSet<ClientPreferences>();


   public Clients(String personalID,String name, Integer age, String email, String phoneNumber){
       numberOfClients++;
       this.personalID=personalID;
       this.name=name;
       this.age=age;
       this.email=email;
       this.phoneNumber=phoneNumber;
       for(int i=0;i<personalID.length();i++) {
           if (personalID.charAt(i) < 48 || personalID.charAt(i) > 57) {
               throw new RuntimeException("Your Personal ID must contains only digits!");
           }
       }

       for(int i=0;i<phoneNumber.length();i++) {
           if (phoneNumber.charAt(i) < 48 || phoneNumber.charAt(i) > 57) {
               throw new RuntimeException("Your phone number must contains only digits!");
           }
       }
   }

    public Integer getAge() {
        return age;
    }

    public void addClientPreferences(ClientPreferences clientPreference){
        cp.add(clientPreference);
    }
    public String aboutClientPreferences(){
       if(cp.size()==0)
           return "No preferences recorded";
       if(cp.size()==1)
           return "The client preference is related to "+cp;
           return "The client preferences are related to "+cp;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static Integer getNumberOfClients(){
       return numberOfClients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return personalID.equals(clients.personalID) && name.equals(clients.name) && age.equals(clients.age) && email.equals(clients.email) && phoneNumber.equals(clients.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalID, name, age, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "Clients{" +
                "personalID='" + personalID + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
