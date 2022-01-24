package database;

/**
 * This class holds member data
 * @author  Jonathan Stuecker
 */
public class Member extends Database.Person{

    enum Status{DEFAULT, VALID, SUSPENDED};
    private Status memberStatus = Status.DEFAULT;

    /***
     * Creates a Member instance
     * @param name              the name of the member
     * @param status      the status of the membership of the member
     * @param addressStreet     the street the member lives on
     * @param addressCity       the city the member lives in
     * @param addressZipCode    the zip code the member lives in
     * @param addressState      the state the member lives in
     */
    public Member(String name, String status, String street, String city, String zip, String state){
        this.name = name;
        this.memberStatus = Status.valueOf(status);
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.state = state;
    }

    public String getStatus() {
        return memberStatus.toString();
    }

    public void setStatus(String status) {
        this.memberStatus = Status.valueOf(status);
    }
}