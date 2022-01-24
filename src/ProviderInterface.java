/**
 * Interface class for providers -- an instance of this class will be created if provider is specified in command line arguments of main
 * @author Jonathan Stuecker
 */
import database.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class ProviderInterface extends UserInterface {
  /**
   * Main method of ProviderInterface class, immediately executed following creation of an instance of class
   */
  public void run() {
    String choice = "DEFAULT";
    while (choice != "RETURN") {
      System.out.println("------Provider------");
      Provider thisProvider = null;
      while(thisProvider == null) {
	      int ID = getInt("Enter provider number: ");
	      thisProvider = Main.providerDatabase.getEntry(ID);
	      if (thisProvider == null) {
	        System.out.println("Invalid Provider Number. ");
	      }
      }
      choice = getString("1. Validate Membership / Bill Member\n2. Provider Directory\n3. Exit\n");
      switch (choice) {
        case "1":
          int memberNumber = getInt("Enter member number: ");
          Member thisMember = Main.memberDatabase.getEntry(memberNumber);
          if (thisMember != null) {
            String currentStatus = thisMember.getStatus();
            switch (currentStatus){
              case "VALID":
                System.out.println("VALIDATED");
                String chooseToBill = getString("1. Bill Member\n2. Exit\n");
                if (chooseToBill.equals("1")) {
                  billMember(thisProvider);
                  break;
                }
                else {break;}
              case "SUSPENDED":
                System.out.println("MEMBER SUSPENDED");
                break;
              default:
                System.out.println("MEMBER HAS INVALID STATUS");
                break;
            }
          }//end of if thisMember!=null
          else {System.out.println("Invalid Number");}
        case "2":
        	CreateProviderDirectory.directoryReport();
        	break;
        case "3":
        	choice = "RETURN";
            break;
        default:
        	System.out.println("Invalid choice");
        	break;
      }//end of switch (choice)
      Main.memberDatabase.saveData();
      Main.providerDatabase.saveData();
      Main.serviceRecordDatabase.saveData();
      Main.providerDirectory.saveData();
    }//end of while choice != return
    //return statement at end of run()
  }//end of run()
  
  /**
   * Bill member for service provided
   * @param provider passes through the provider recording the bill to the billMember method
   */
  public void billMember(Provider provider) {
	int i = 0;
	Format f = new SimpleDateFormat("MM-dd-yyyy");
	String strDate = f.format(new Date());
    int memberNumber = getInt("Enter Member Number Again: ");
    Member memberToBill = Main.memberDatabase.getEntry(memberNumber);
    if (memberToBill == null) {
      System.out.println("Member not found. Please revalidate and try again.");
      return;
    }
    //i don't think i need an else here
    //prompt provider for service record data
    String date = getString("Please enter the date service was provided (MM-DD-YYYY): ");
    while (!(Database.dateValidator.isValid(date))) {
      date = getString("Date must be formatted as (" + Database.getDateFormat() + ")\nDate: ");
    }
    ArrayList<ProviderDirectory> services = Main.providerDirectory.getEntries();
    int serviceCode = getInt("Enter service code corresponding to provided service:\n");
    
    while(i < services.size() && services.get(i).getServiceCode() != serviceCode) {
    	i++;
    }
    if(i == services.size()) {
    	System.out.println("Invalid service code. Please try again.");
    	return;
    }
    
    System.out.println();
    String verify = getString("Service entered is " + services.get(i).getName() + ". Enter YES if this is correct.\n");
    if (verify == "YES") {
      System.out.println("Operation cancelled.\n");
      return;
    }
    String comments = getString("Enter comments if desired. Simply press enter to skip entering comments.");
    double fee = Main.providerDirectory.getEntry(serviceCode).getServiceFee();
    try {
    	ServiceRecord newServiceRecord = new ServiceRecord(strDate, date, provider.getNumber(), memberNumber, serviceCode, comments, fee);
		Main.serviceRecordDatabase.addEntry(newServiceRecord);
	}
    catch (Exception e) {
		System.out.println("Error getting provider number");
	}
  }//end of billMember
  
}//end of ProviderInterface
