import database.Provider;
import database.ServiceRecord;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The SummaryReport class, which extends the Report class, generates the summary report to the manager for accounts payable
 
 * @author Travis Nguyen
 */


public class SummaryReport extends Report {

  private boolean success;
  @Override
  public void generateReport() {
	  
	//Creates an arraylist of service records and an array of provider numbers
    ArrayList<ServiceRecord> serviceRecords = Main.serviceRecordDatabase.getentries();
    ArrayList<Integer> providerNumbersBilledInLastWeek = new ArrayList<Integer>();
    
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    Date currentDate = new Date();
    
    setSuccess(false);
    
    try {
    	//removes service records that did not happen in the current week, otherwise it adds provider number
	    for (int i = 0; i < serviceRecords.size(); i++) {
	      Date date = serviceRecords.get(i).getDateProvided();
		  Date lastWeek = new Date(currentDate.getTime() - (7 * 1000 * 60 * 60 * 24));
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
    	System.out.println("Error accessing service records");
    }
    
    
    try {
    	//calculates the total fee and consultations of each provider, as well as the overall fee and total consultations of all providers in the week
	    int totalConsultations = 0;
	    double overallFee = 0;
	
	    FileWriter summaryReport;
	    summaryReport = new FileWriter("Summary Report " + sdf.format(currentDate) + ".txt");
	    
	    for (Integer i : providerNumbersBilledInLastWeek) {
	      Provider provider = Main.providerDatabase.getEntry(i);
	      
	      summaryReport.write(provider.getName() + "\n");
	
	      int consultations = 0;
	      double totalFee = 0;
	
	      for (ServiceRecord s : serviceRecords) {
	        if (s.getProviderNumber() == i) {
	          consultations++;
	          totalFee += s.getFee();
	        }
	      }
	      
	      totalConsultations += consultations;
	      overallFee += totalFee;
	      summaryReport.write("Consultations: " + consultations + "\n" + "Total fee for week: " + totalFee + "\n\n");
	    }
	    setSuccess(true);
	    summaryReport.write("Total Consultations: " + totalConsultations + "\nOverall Fee: " + overallFee + "\n");
	    summaryReport.close();
	   
    }
    catch(Exception e) {
    	System.out.println("Error writing report file");
    }
  }
public boolean getSuccess() {
	return success;
}
public void setSuccess(boolean success) {
	this.success = success;
}
}
