import database.*;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The EftReport class, which extends the Report class, generates the electronic funds transfer(EFT)
 * report for banking computers to validate that the provider has been credited the right amount.

 * @author Travis Nguyen
 */

public class EftReport extends Report {

  private boolean success;
  @Override
  public void generateReport() {
	 
	//Creates an array list of service records and an array list of provider numbers 
    ArrayList<ServiceRecord> serviceRecords = Main.serviceRecordDatabase.getentries();
    ArrayList<Integer> providerNumbersBilledInLastWeek = new ArrayList<Integer>();
    
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    Date currentDate = new Date();
    Date lastWeek = new Date(currentDate.getTime() - (7 * 1000 * 60 * 60 * 24));
    
    setSuccess(false);
    //Removes the list of providers that is before the current week
    try {
	    for (int i = 0; i < serviceRecords.size(); i++) {
	      Date date = serviceRecords.get(i).getDateProvided();
	      if (date.before(lastWeek)) {
	        serviceRecords.remove(i);
	      } else {
	        if (!providerNumbersBilledInLastWeek.contains(serviceRecords.get(i).getProviderNumber())) {
	          providerNumbersBilledInLastWeek.add(serviceRecords.get(i).getProviderNumber());
	        }
	      }
	    }
    }
	catch(Exception e) {
		System.out.println("Error accessing service record");
	}

    // Adds the total fee of the provider as well as displaying the provider name, number, and total fee.
    try {
    	FileWriter eftReport = new FileWriter("EFT Report " + sdf.format(currentDate) + ".txt");
	    for (Integer i : providerNumbersBilledInLastWeek) {
	      Provider provider = Main.providerDatabase.getEntry(i);
	      double totalFee = 0;
	      for (ServiceRecord s : serviceRecords) {
	        if (s.getProviderNumber() == i) {
	          totalFee += s.getFee();
	        }
	      }
	      eftReport.write(provider.getName() + "\n" + provider.getNumber() + "\n" + totalFee + "\n\n");
	      
	    }
	    eftReport.close();
	    setSuccess(true);
    }
    catch(Exception e) {
    	System.out.println("Error creating report file");
    }
  }
public boolean getSuccess() {
	return success;
}
public void setSuccess(boolean success) {
	this.success = success;
}
}

