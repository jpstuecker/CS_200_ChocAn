/**
 * Interface class for operators -- an instance of this class will be created if operator is specified in command line arguments of main
 * @author Jonathan Stuecker
 */
import database.*;
public class OperatorInterface extends UserInterface{
  private enum Type{DEFAULT, RETURN, MEMBER, PROVIDER, SERVICE};
  private enum MemberData{NAME, STATUS, STREET, CITY, STATE, ZIP};    
  private enum ProviderData{NAME, STREET, CITY, STATE, ZIP}
  
  public void run() {
    String choice = "DEFAULT";
    while (choice != "RETURN") {
      System.out.println("------Operator------");
      choice = getString("1. Member Database\n2. Provider Database\n3. Provider Directory\n4. Exit");
      switch(choice) {
        case "1":
          accessData(Type.MEMBER);
          break;
        case "2":
          accessData(Type.PROVIDER);
          break;
        case "3":
          accessData(Type.SERVICE);
          break;
        case "4":
          System.out.println("");
          choice = "RETURN";
          break;
        default:
          System.out.println("Invalid");
          break;
      } //end of switch
      Main.memberDatabase.saveData();
      Main.providerDatabase.saveData();
      Main.serviceRecordDatabase.saveData();
      Main.providerDirectory.saveData();
    }//end of while
    return;
  } // end of run()
  /**
   * Used to access data in databases
   * @param selection The type of data to be modified
   */
  public void accessData(Type selection) {
    int accessChoice = 0;
    while (accessChoice != 5) {
      accessChoice = getInt("1. Get " + selection + " info\n2. Add a new " + selection + ".\n3. Remove a " + selection + ".\n4. Modify a " + selection + ".\n5. Exit");
      switch(accessChoice) {
        case 1:
          if(selection == Type.MEMBER) {
            int ID = getInt("Enter member number: ");
            Member memberInfo = Main.memberDatabase.getEntry(ID);
            if (memberInfo != null) {
            //Output member data
              System.out.println("Member # " + String.format("%09d", ID));
              System.out.println("Name: " + memberInfo.getName());
              System.out.println("Status: " + memberInfo.getStatus());
              System.out.println("Street: " + memberInfo.getStreet());
              System.out.println("City: " + memberInfo.getCity());
              System.out.println("State: " + memberInfo.getState());
              System.out.println("Zip Code: " + memberInfo.getZip());
              System.out.println("\n");
              break;
            }
            else {
              System.out.println("Member not found.");
              break;
            }
          }
          else if(selection == Type.PROVIDER) {
            int ID = getInt("Enter provider number: ");
            Provider providerInfo = Main.providerDatabase.getEntry(ID);
            if (providerInfo != null) {
            //Output member data
              System.out.println("Provider # " + String.format("%09d", ID));
              System.out.println("Name: " + providerInfo.getName());
              System.out.println("Street: " + providerInfo.getStreet());
              System.out.println("City: " + providerInfo.getCity());
              System.out.println("State: " + providerInfo.getState());
              System.out.println("Zip Code: " + providerInfo.getZip());
              System.out.println("\n");
              break;
            }
            else {
              System.out.println("Provider not found.");
              System.out.println("\n");
              break;
            }
          }
          else if(selection == Type.SERVICE) {
              int ID = getInt("Enter service ID #: ");
              ProviderDirectory serviceInfo = Main.providerDirectory.getEntry(ID);
              if (serviceInfo != null) {
              //Output service data
                System.out.println("Service ID # " + String.format("%09d", ID));
                System.out.println("Name: " + serviceInfo.getName());
                System.out.println("Service Code: " + serviceInfo.getServiceCode());
                System.out.println("Fee: " + serviceInfo.getServiceFee());
                System.out.println("\n");
                break;
              }
              else {
                System.out.println("Service code not found.");
                System.out.println("\n");
                break;
              }
           }
          else {
           break;
          }
        case 2:
          if(selection == Type.MEMBER) {
            //Values for member fields
            String name = getString("Name:");
            while (name.length() > 25) {
              name = getString("Name must be 25 characters or less\nName: ");
            }
            String status = getString("Status:").toUpperCase();
            if (status != "VALID" && status != "SUSPENDED") {
              status = getString("Invalid status. Status may only be VALID or SUSPENDED.\nStatus: ").toUpperCase();
            }
            String street = getString("Street:");
            while (street.length() > 25) {
              street = getString("Street address must be 25 characters or less\nStreet: ");
            }
            String city = getString("City:");
            while (city.length() > 14) {
              city = getString("City must be 14 characters or less.\nCity: ");
            }
            String state = getString("State:");
            while (state.length() != 2) {
              state = getString("Please use the 2-character abbreviation of the state's name.\nState: ");
            }
            String zip = getString("Zip Code:").toUpperCase();
            while (zip.length() != 5) {
              zip = getString("Zipcode must be 5 digits long.\nZip: ").toUpperCase();
            }
            //add member to database
            Member newMember = new Member(name, status, street, city, zip, state);
            int number = Main.memberDatabase.addEntry(newMember);
            System.out.println(name + " has been added.\nMember Number: " + String.format("%09d",number));
            System.out.println("\n");
            break;
          }
          else if(selection == Type.PROVIDER) {
            //Get the properties for the provider
            String name = getString("Name:");
            while (name.length() > 25) {
              name = getString("Name must be 25 characters or less\nName: ");
            }
            String street = getString("Street:");
            while (street.length() > 25) {
              street = getString("Street address must be 25 characters or less\nStreet: ");
            }
            String city = getString("City:");
            while (city.length() > 14) {
              city = getString("City must be 14 characters or less.\nCity: ");
            }
            String state = getString("State:");
            while (state.length() != 2) {
              state = getString("Please use the 2-character abbreviation of the state's name.\nState: ");
            }
            String zip = getString("Zip Code:");
            while (zip.length() != 5) {
              zip = getString("Zipcode must be 5 digits long.\nZip: ");
            }
              
            //Add the provider to the database
            Provider newProvider = new Provider(name, street, city, state, zip);
            int number = Main.providerDatabase.addEntry(newProvider);
            System.out.println(name + " has been added.\nProvider Number: " + String.format("%09d",number));
            System.out.println("\n");
            break;
          }
          
          else if (selection == Type.SERVICE) {
        	String name = getString("Name of service:");
            while (name.length() > 25) {
              name = getString("Name must be 25 characters or less\nName: ");
            }
            double fee = getDouble("Enter fee: ");
            ProviderDirectory newService = new ProviderDirectory(name, fee);
            int serviceCode = Main.providerDirectory.addEntry(newService);
            newService.setServiceCode(serviceCode);
            System.out.println(name + " has been added.\nService Code: " + String.format("%06d", serviceCode));
            newService.setServiceCode(serviceCode);
            System.out.println("\n");
          }
          
          /*
          else if(selection == Type.SERVICE) {
        	 //Get the properties for the service
              String name = getString("Name:");
              while (name.length() > 20) {
                name = getString("Name must be 20 characters or less\nName: ");
              }
              int code = getInt("Service Code:");
              while (code > 999999) {
            	  code = getInt("Service Code must be 6 digits\nService Code: ");
              }
              double fee = getDouble("Fee: ");
              while (fee < 0) {
                fee = getDouble("Fee must be positive.\nFee: ");
              }
              //Add the service to the database
              ProviderDirectory newService = new ProviderDirectory(name, code, fee);
              int number = Main.providerDirectory.addEntry(newService);
              System.out.println(name + " has been added.\nService ID #: " + String.format("%09d",number));
              System.out.println("\n");
              break;
           }
           */
          else {
            break;
          }
          break;
        case 3:
          if (selection == Type.MEMBER) {
            int number = getInt("Enter member number to delete: ");
            //get member number
            Member memberToDelete = Main.memberDatabase.getEntry(number);
            //create member to delete
            if (memberToDelete != null) {
              //ask consent before deleting member
              String consent = getString("WARNING, THIS ACTION CANNOT BE UNDONE. ARE YOU SURE YOU WANT TO DELETE MEMBER " + memberToDelete.getName() + "? (ENTER 'YES')");
              //System.out.println(consent);
              if (consent.equals("Yes") || consent.equals("yes") || consent.equals("YES")) {
                Main.memberDatabase.removeEntry(memberToDelete);
                System.out.println("Member #" + String.format("%09d", number) + " successfully deleted.");
                System.out.println("\n");
                break;
              }
              else {
                System.out.println("Member #" + String.format("%09d", number) + " not deleted");
                System.out.println("\n");
                break;
              }
            }
            else {
              System.out.println("Member not found.");
              System.out.println("\n");
              break;
            }
          }
          else if (selection == Type.PROVIDER) {
            int number = getInt("Enter provider number to delete: ");
            Provider providerToDelete = Main.providerDatabase.getEntry(number);
            if (providerToDelete != null) {
              String consent = getString("WARNING, THIS ACTION CANNOT BE UNDONE. ARE YOU SURE YOU WANT TO DELETE PROVIDER " + providerToDelete.getName() + "? (ENTER 'YES')");
              if (consent.equals("YES") || consent.equals("yes")) {
                Main.providerDatabase.removeEntry(providerToDelete);
                System.out.println("Provider #" + String.format("%09d", number) + " successfully deleted.");
                System.out.println("\n");
                break;
              }
              else {
                System.out.println("Provider #" + String.format("%09d", number) + " not deleted.");
                System.out.println("\n");
                break;
              }
            }
            else {
              System.out.println("Provider not found.");
              System.out.println("\n");
              break;
            }
          }
          else if (selection == Type.SERVICE) {
              int number = getInt("Enter service ID # to delete: ");
              ProviderDirectory serviceToDelete = Main.providerDirectory.getEntry(number);
              if (serviceToDelete != null) {
                String consent = getString("WARNING, THIS ACTION CANNOT BE UNDONE. ARE YOU SURE YOU WANT TO DELETE SERVICE " + serviceToDelete.getName() + "? (ENTER 'YES')");
                if (consent == "YES" || consent == "yes") {
                  Main.providerDatabase.removeEntry(serviceToDelete);
                  System.out.println("Service ID #" + String.format("%09d", number) + " successfully deleted.");
                  System.out.println("\n");
                  break;
                }
                else {
                  System.out.println("Service ID #" + String.format("%09d", number) + " not deleted.");
                  System.out.println("\n");
                  break;
                }
              }
              else {
                System.out.println("Service not found.");
                System.out.println("\n");
                break;
              }
            }
          else {
              break;
            }
        case 4:
          if(selection == Type.MEMBER) {
            int idNumber = getInt("Enter the member number of the provider to modify: ");
            Member memberToModify = Main.memberDatabase.getEntry(idNumber);
            if (memberToModify != null) {
              int toModify = getInt("Please select option to update:\n1. Name\n2. Street\n3. City\n4. State\n5. Zip Code\n6. To go back");
              switch (toModify) {
                case 1:
                  memberToModify.setName(getString("Enter name: "));
                  System.out.println("Member name updated");
                  break;
                case 2:
                  memberToModify.setStreet(getString("Enter street: "));
                  System.out.println("Member street updated");
                  break;
                case 3:
                  memberToModify.setCity(getString("Enter city: "));
                  System.out.println("Member city updated");
                  break;
                case 4: 
                  memberToModify.setState(getString("Enter state: "));
                  System.out.println("Member state updated");
                  break;
                case 5:
                  memberToModify.setZip(getString("Enter zipcode: "));
                  System.out.println("Member zipcode updated");
                  break;
                case 6:
                  break;
                default:
                  System.out.println("Invalid");
              }
            }
            else {
              System.out.println("Member not found.");
            }
          }
          else if(selection == Type.PROVIDER) {
            int idNumber = getInt("Enter the provider number of the provider to modify: ");
            Provider providerToModify = Main.providerDatabase.getEntry(idNumber);
            if (providerToModify != null) {
              int toModify = getInt("Please select option to update:\n1. Name\n2. Street\n3. City\n4. State\n5. Zip Code\n6. To go back");
              switch (toModify) {
                case 1:
                  providerToModify.setName(getString("Enter name: "));
                  System.out.println("Member name updated");
                  break;
                case 2:
                  providerToModify.setStreet(getString("Enter street: "));
                  System.out.println("Member street updated");
                  break;
                case 3:
                  providerToModify.setCity(getString("Enter city: "));
                  System.out.println("Member city updated");
                  break;
                case 4: 
                  providerToModify.setState(getString("Enter state: "));
                  System.out.println("Member state updated");
                  break;
                case 5:
                  providerToModify.setZip(getString("Enter zipcode: "));
                  System.out.println("Member zipcode updated");
                  break;
                case 6:
                  break;
                default:
                  System.out.println("Invalid");
              }
            }
            else {
              System.out.println("Provider not found");
            }
          }
          else if(selection == Type.SERVICE) {
              int idNumber = getInt("Enter the service ID # of the service to modify: ");
              ProviderDirectory serviceToModify = Main.providerDirectory.getEntry(idNumber);
              if (serviceToModify != null) {
                int toModify = getInt("Please select option to update:\n1. Name\n2. Service Code\n3. Fee\n4. To go back");
                switch (toModify) {
                  case 1:
                    serviceToModify.setName(getString("Enter name: "));
                    System.out.println("Service name updated");
                    break;
                  case 2:
                    serviceToModify.setServiceCode(getInt("Enter service code: "));
                    System.out.println("Service code updated");
                    break;
                  case 3:
                    serviceToModify.setServiceFee(getDouble("Enter fee: "));
                    System.out.println("Fee updated");
                    break;
                  case 4: 
                    break;
                  default:
                    System.out.println("Invalid");
                }
              }
              else {
                System.out.println("Service not found");
              }
            }
          else {
              break;
            }
        case 5:
          break;
          }
      //end of switch
    }
}
}
