package database;

@SuppressWarnings("serial")
public class Provider extends Database.Person{
  /***
   * Creates a Provider
   * @param name      provider's name
   * @param street    provider's street address
   * @param city      provider's city
   * @param state     provider's state
   * @param zip       provider's zipcode
   */
  public Provider(String name, String street, String city, String state, String zip){
      this.name = name;
      this.street = street;
      this.city = city;
      this.zip = zip;
      this.state = state;
  }
}
