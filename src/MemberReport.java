import database.*;
import database.ServiceDatabase.Record;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * MemberReport, which extends Report, generates a report for each member that was serviced in the past week.
 * @author Matt Poirier
 */
public class MemberReport extends Report {

	
	/**
	 * Class constructor.
	 */
	public MemberReport() {
	}

	/**
	 * Contains logic for generating a report for each member that was serviced in the past week and creating a file with the report.
	 */
	@Override
	public void generateReport() {
		ArrayList<ServiceRecord> serviceRecords = Main.serviceRecordDatabase.getentries();
		ArrayList<Integer> memberNumbersServicedInLastWeek = new ArrayList<Integer>();
		
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
					// keep track of members serviced in the last week
					if(!memberNumbersServicedInLastWeek.contains(serviceRecords.get(i).getMemberNumber())) {
						memberNumbersServicedInLastWeek.add(serviceRecords.get(i).getMemberNumber());
					}
				}
			}
		}
		
		catch(Exception e) {
			System.out.println("Error accessing service records");
		}
		
		try {
			// generate report for each member serviced in the past week
			for (Integer i : memberNumbersServicedInLastWeek) {
				Member member = Main.memberDatabase.getEntry(i);
				
				// date formatting
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				Date currentDate = new Date();
				
				// create file
				FileWriter memberReport = new FileWriter(member.getName() + " " + sdf.format(currentDate) + ".txt");
				// write member info to file
				
				
				memberReport.write("Name: " + member.getName() + "\nMember Number: " + String.format("%09d", member.getNumber()) + "\nAddress: " + member.getStreet() + "\nCity: " + member.getCity() + "\nState: " + member.getState() + "\nZip Code: " + member.getZip() + "\n\n");
				ArrayList<ProviderDirectory> pd = Main.providerDirectory.getEntries();
				
				for(Record s : serviceRecords) {
					// if the record was billed to member
					if(s.getMemberNumber() == i) {
						// write to file
						String providerName = Main.providerDatabase.getEntry(s.getProviderNumber()).getName();
						String serviceName = null;
						for(ProviderDirectory service : pd) {
							if(service.getServiceCode() == s.getServiceCode()) {
								serviceName = Main.providerDirectory.getEntry(service.getNumber()).getName();
							}
						}
						memberReport.write("Date of service: " + sdf.format(s.getDateProvided()) + "\nProvider Name: " + providerName + "\nService Name: " + serviceName + "\n\n");
					}
				}
				// close file
				memberReport.close();
			}
		}
		catch(Exception e) {
			System.out.println("Error creating report file");
		}
		
	}

}
