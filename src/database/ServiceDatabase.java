package database;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

import database.Database.Person;

/**
 * Main record class used to save service record
 * @author Rollins Baird
 */
public final class ServiceDatabase<T> {

  /**
   * Record parent class that ServiceRecord inherit from
   */
  public static class Record implements Serializable {
     
	  protected Date dateTime, dateProvided;
      protected String comments;
      protected int providerNumber, memberNumber, serviceCode, serviceNumber = -1;
      protected double fee = -1.0;

      /**
      * Gets the date & time of the ServiceRecord member
      * @return dateTime
      */   
      public Date getDateTime() throws Exception{
          if (dateTime != null) {
              return dateTime;
          }
          throw new Exception("This "+ this.getClass().getSimpleName() + "'s date & time is not found in the database.");
      }

      /**
      * Sets the serviceCode of the ServiceRecord member
      * @param obj   ServiceRecord object
      * @param str   The date & time of the ServiceRecord object
      */
      public void setDateTime(Object obj, Date str) throws Exception{
          if (obj instanceof Record){
              this.dateTime = str;
              return;
          }
          throw new Exception("This "+ this.getClass().getSimpleName() + " is not found in the database.");
      }
      
      /**
       * Gets the date of the ServiceRecord member was provided
       * @return dateProvided
       */   
       public Date getDateProvided() throws Exception{
           if (dateProvided != null) {
               return dateProvided;
           }
           throw new Exception("This "+ this.getClass().getSimpleName() + "'s date provided is not found in the database.");
       }

       /**
       * Sets the dateProvided of the ServiceRecord member
       * @param obj   ServiceRecord object
       * @param str   The date provided of the ServiceRecord object
       */
       public void setDateProvided(Object obj, Date str) throws Exception{
           if (obj instanceof Record){
               this.dateProvided = str;
               return;
           }
           throw new Exception("This "+ this.getClass().getSimpleName() + " is not found in the database.");
       }
      
      /**
      * Gets the number of the provider
      * @return providerNumber
      */   
      public int getProviderNumber() throws Exception{
          if (providerNumber != -1) {
              return providerNumber;
          }
          throw new Exception("This "+ this.getClass().getSimpleName() + "'s provider number is not found in the database.");
      }

      /**
      * Sets the number of the ServiceRecord member
      * @param obj   ServiceRecord object
      * @param number   The provider number of the ServiceRecord object
      */
      public void setProviderNumber(Object obj, int number) throws Exception{
          if (obj instanceof Record){
              this.providerNumber = number;
              return;
          }
          throw new Exception("This "+ this.getClass().getSimpleName() + " is not found in the database.");
      }

      /**
      * Gets the number of the member
      * @return memberNumber
      */   
      public int getMemberNumber() throws Exception{
          if (memberNumber != -1) {
              return memberNumber;
          }
          throw new Exception("This "+ this.getClass().getSimpleName() + "'s number is not found in the database.");
      }

      /**
      * Sets the memberNumber of the ServiceRecord member
      * @param obj   ServiceRecord object
      * @param number   The member number of the ServiceRecord object
      */
      public void setMemberNumber(Object obj, int number) throws Exception{
          if (obj instanceof Record){
              this.memberNumber = number;
              return;
          }
          throw new Exception("This "+ this.getClass().getSimpleName() + " is not found in the database.");
      }

      /**
      * Gets the number of the provider
      * @return providerNumber
      */   
      public int getServiceCode() throws Exception{
          if (serviceCode != -1) {
              return serviceCode;
          }
          throw new Exception("This "+ this.getClass().getSimpleName() + "'s service code is not found in the database.");
      }

      /**
      * Sets the serviceCode of the ServiceRecord member
      * @param obj   ServiceRecord object
      * @param number   The service code of the ServiceRecord object
      */
      public void setServiceCode(Object obj, int number) throws Exception{
          if (obj instanceof Record){
              this.serviceCode = number;
              return;
          }
          throw new Exception("This "+ this.getClass().getSimpleName() + " is not found in the database.");
      }
      
      /**
       * Gets the comments of the ServiceRecord member
       * @return dateTime
       */   
       public String getComments() throws Exception{
           if (comments != null) {
               return comments;
           }
           throw new Exception("This "+ this.getClass().getSimpleName() + "'s comments are not found in the database.");
       }

       /**
       * Sets the comments of the ServiceRecord member
       * @param obj   ServiceRecord object
       * @param number   The comments of the ServiceRecord object
       */
       public void setComments(Object obj, String str) throws Exception{
           if (obj instanceof Record){
               this.comments = str;
               return;
           }
           throw new Exception("This "+ this.getClass().getSimpleName() + " is not found in the database.");
       }

      /**
      * Gets the fee of the provider
      * @return providerNumber
      */   
      public double getFee() throws Exception{
          if (fee != -1.0) {
              return fee;
          }
          throw new Exception("This "+ this.getClass().getSimpleName() + "'s number is not found in the database.");
      }

      /**
      * Sets the fee of the ServiceRecord member
      * @param obj   ServiceRecord object
      * @param number   The fee of the ServiceRecord object
      */
      public void setFee(Object obj, double number) throws Exception{
          if (obj instanceof Record){
              this.fee = number;
              return;
          }
          throw new Exception("This "+ this.getClass().getSimpleName() + " is not found in the database.");
      }
      
      /**
       * Gets the number of the service record
       * @return  the serviceNumber
       */   
       public int getServiceNumber() throws Exception{
           if (serviceNumber != -1) {
               return serviceNumber;
           }
           throw new Exception("This "+ this.getClass().getSimpleName() + "'s number is not found in the database.");
       }

       /**
       * Sets the number of the service record
       * @param obj   ServiceRecord object
       * @param number   The number of the ServiceRecord object
       */
       public void setServiceNumber(Object obj, int number) throws Exception{
           if (this.serviceNumber == -1 && obj instanceof ServiceDatabase){
               this.serviceNumber = number;
               return;
           }
           throw new Exception("This "+ this.getClass().getSimpleName() + " is not found in the database.");
       }
  }
  

    //The name of the record file
    private String fileName;

    //An ArrayList that holds each entity of the record
    private ArrayList<Record> entries = new ArrayList<Record>();
    
    /**
     * Creates a record
     * @param fileName  the file name of the record
     */
    public ServiceDatabase(String fileName){
      this.fileName = fileName;
      File data = new File(fileName);
      if (!data.exists()) {
        try {
          if (data.createNewFile()) {
            System.out.println("New record created.");
          }
          else {
            System.out.println("File already exists.");
          }
        }catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        entries = new ArrayList<Record>();
      }else {
        loadData();
      }
    }

    /**
     * Loads the the file into an ArrayList
     */
    private void loadData(){
      try {
          //Read in the contents of the database file
          FileInputStream inFile = new FileInputStream(fileName);
          ObjectInputStream OS = new ObjectInputStream(inFile);
          entries = (ArrayList<Record>) OS.readObject();

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
            FileOutputStream BS = new FileOutputStream(fileName);
            ObjectOutputStream OS = new ObjectOutputStream(BS);

            OS.writeObject(entries);

            //Close the files
            BS.close();
            OS.close();

        } catch (IOException e) {
          e.printStackTrace();;  //Catch out any exceptions
        }
    }

    /***
     * Adds an object into the database and returns its number
     * @param entry The object to be entered into the database
     * @return      The number of the object in the database
     */
    public int addEntry(Record entry) {
        try {
            //Add the entry to the entry list
            int number = entries.size();
            entry.setServiceNumber(this, number);
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
    public T getEntry(int number){
      if (number >= 0 && number < entries.size()){
        return (T) entries.get(number);}
      return null;
    }

    /**
     * Returns an array list containing the database members
     * @return  the array list of the entries
     */
    public ArrayList<T> getentries() {
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
    public void removeEntry(Record entry){
        try {
            int number = entry.getServiceNumber();
            entries.set(number,null);
            saveData();
        } catch (Exception e) {
            System.out.println(e);
            System.out.print("This "+ this.getClass().getSimpleName() + " is not found in the database.");
        }
    }

    /**
     * Gets the number of entries in the database
     * @return  the number of entries in the database
     */
    public int size() {
        return entries.size();
    }
}
