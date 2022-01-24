import database.*;
import database.ServiceRecord;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * ProviderReport, which extends Report, generates a report for each provider that provided services in the past week.
 * @author Matt Poirier
 *
 */
public class ProviderReport extends Report {

	/**
	 * Class constructor.
	 */
	public ProviderReport() {
	}

	/**
	 * Contains logic for generating a report for each provider that was billed in the past week 
	 */
	@Override
	public void generateReport() {
		ArrayList<ServiceRecord> serviceRecords = Main.serviceRecordDatabase.getentries();
		ArrayList<Integer> providerNumbersBilledInLastWeek = new ArrayList<Integer>();
		
		try {
			// only keep records from the past 7 days
			for(int i = 0; i < serviceRecords.size(); i++) {
				Date currentDate = new Date();
				Date lastWeek = new Date(currentDate.getTime() - (7 * 1000 * 60 * 60 * 24));
				Date date = serviceRecords.get(i).getDateProvided();
				if(date.before(lastWeek)) {
					serviceRecords.remove(i);
				}
				else {
					// keep track of the providers that provided services in the past week
					if(!providerNumbersBilledInLastWeek.contains(serviceRecords.get(i).getProviderNumber())) {
						providerNumbersBilledInLastWeek.add(serviceRecords.get(i).getProviderNumber());
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error accessing service record");
		}
		
		// generate report for each provider billed in the past week
		for (Integer i : providerNumbersBilledInLastWeek) {
			Provider provider = Main.providerDatabase.getEntry(i);
			
			// date formatting
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			Date currentDate = new Date();
			
			
			try {
				// create file
				FileWriter providerReport = new FileWriter(provider.getName() + " " + sdf.format(currentDate) + ".txt");
				// write provider info to file
				providerReport.write("Name: " + provider.getName() + "\nProvider Number: " + provider.getNumber() + "\nAddress: " + provider.getStreet() + "\nCity: " + provider.getCity() + "\nState: " + provider.getState() + "\nZip Code: " + provider.getZip() + "\n\n");
				
				// variables to keep track of consultations and fees for the provider
				int consultations = 0;
				double totalFee = 0;
				
				for(ServiceRecord s : serviceRecords) {
					// if the record was made by the provider
					if(s.getProviderNumber() == i) {
						// add to totals
						consultations++;
						totalFee += s.getFee();
						
						// write service info to file
						String memberName = Main.memberDatabase.getEntry(s.getMemberNumber()).getName();
						providerReport.write("Date/Time Provided: " + sdf.format(s.getDateProvided()) + "\nComputer Date/Time: " + sdf.format(s.getDateTime()) + "\nMember Name: " + memberName + "\nMember Number: " + s.getMemberNumber() + "\nService Code: " + s.getServiceCode() + "\nFee: " + s.getFee() + "\n\n");
					}
				}
				providerReport.write("Total number of consultations: " + consultations + "\nTotal fee for week: " + totalFee + "\n\n");
				providerReport.close();
			}
			catch(Exception e) {
				System.out.println("Error creating report file");
			}
		}
		
	}

}
