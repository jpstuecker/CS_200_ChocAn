package database;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Main database class used to save member and Provider objects
 * @author Jonathan Stuecker
 */
public final class Database<T> {

  /**
   * Person parent class that Member and Provider inherit from
   */
  @SuppressWarnings("serial")
  public static class Person implements Serializable {
     
      public String name, street, city, zip, state;
      private int number = -1;

      /**
      * Gets the number of the database member
      * @return  the number
      */   
      public int getNumber() throws Exception{
          if (number != -1) {
              return number;
          }
          throw new Exception("This "+ this.getClass().getSimpleName() + "'s number is not found in the database.");
      }

      /**
      * Sets the number of the database member
      * @param obj   Person object
      * @param number   The number of the Person object
      */
      public void setnumber(Object obj, int number) throws Exception{
          if (this.number == -1 && obj instanceof Database){
              this.number = number;
              return;
          }
          throw new Exception("This "+ this.getClass().getSimpleName() + " is not found in the database.");
      }
      public String getName() {
          return name;
      }
      
      public void setName(String name) {
          this.name = name;
          //saveData();
      }

      public String getStreet() {
          return street;
      }
      
      public void setStreet(String street) {
          this.street = street;
          //saveData();
      }

      public String getCity() {
          return city;
      }
      
      public void setCity(String city) {
          this.city = city;
          //saveData();
      }

      public String getZip() {
          return zip;
      }
      
      public void setZip(String zip) {
          this.zip = zip;
          //saveData();
      }

      public String getState() {
          return state;
      }
      
      public void setState(String state) {
          this.state = state;
          //saveData();
      }
  }
  
  public interface DateValidator {
    boolean isValid(String dateStr);
 }
  public static class DateFormatValidator implements DateValidator {
    private String dateFormat;

    public DateFormatValidator(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
    private static String dateFormat = "MM-dd-yyyy";   //set the correct date format
    
    public static String getDateFormat() {
      return dateFormat;
    }
    
    public static DateFormatValidator dateValidator = new DateFormatValidator(dateFormat);

    //The name of the database file
    private String fileName;

    //An ArrayList that holds each entity of the database
    private ArrayList<Person> entries = new ArrayList<Person>();
    
    /**
     * Creates a database
     * @param fileName  the file name of the database
     */
    public Database(String fileName){
      this.fileName = fileName;
      File data = new File(fileName);
      if (!data.exists()) {
        try {
          if (data.createNewFile()) {
            System.out.println("New database created.");
          }
          else {
            System.out.println("File already exists.");
          }
        }catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        entries = new ArrayList<Person>();
      }else {
        loadData();
      }
    }

    /**
     * Loads the the file into an ArrayList
     */
    @SuppressWarnings("unchecked")
    private void loadData(){
      try {
          //Read in the contents of the database file
          FileInputStream inFile = new FileInputStream(fileName);
          ObjectInputStream OS = new ObjectInputStream(inFile);
          entries = (ArrayList<Person>) OS.readObject();

          //Close the files
          OS.close();
          inFile.close();
      } catch (Exception e) {
          System.out.println(e.toString());  //Catch out any exceptions
      }
  }
    /**
     * Saves the database to the file
     */
    public void saveData() {
        try {
            //Write the bytes of the object into an output stream
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream OS = new ObjectOutputStream(fileOut);

            OS.writeObject(entries);

            //Close the files
            fileOut.close();
            OS.close();

        } catch (IOException e) {
          e.printStackTrace();  //Catch out any exceptions
        }
    }

    /***
     * Adds an object into the database and returns its number
     * @param entry The object to be entered into the database
     * @return      The number of the object in the database
     */
    public int addEntry(Person entry) {
        try {
            //Add the entry to the entry list
            int number = entries.size();
            entry.setnumber(this, number);
            entries.add(entry);
            saveData();  //Save the new database

            return number;
        } catch (Exception e) {
            System.out.println(e);
            System.out.print("This "+ this.getClass().getSimpleName() + " is already found in the database.");
        }

        return -1;
    }

    /**
     * Returns the object stored in the database with the specified number
     * @param number    The number of the object to be returned
     * @return      The object with the specified number or null if no object exists
     */
    @SuppressWarnings("unchecked")
    public T getEntry(int number){
      if (number >= 0 && number < entries.size()){
        return (T) entries.get(number);}
      return null;
    }

    /**
     * Returns an array list containing the database members
     * @return  the array list of the entries
     */
    @SuppressWarnings("unchecked")
    public ArrayList<T> getEntries() {
        ArrayList<T> outList = new ArrayList<T>();
        for (int i=0;i<entries.size();i++){
            outList.add((T) entries.get(i));
        }
        return outList;
    }
    /**
     * Remove the entry from the database
     * @param entry the entry to remove
     */
    public void removeEntry(Person personToDelete){
        try {
            int idToDelete = personToDelete.getNumber();
            entries.set(idToDelete, null);
            saveData();
        } catch (Exception e) {
            System.out.println(e);
            System.out.print("This "+ this.getClass().getSimpleName() + " is not found in the database.");
        }
        saveData();
    }
    
    public void clear(){
      entries.clear();
      saveData();
      return;
    }

    /**
     * Gets the number of entries in the database
     * @return  the number of entries in the database
     */
    public int size() {
        return entries.size();
    }
}
