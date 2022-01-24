/**
 * ManagerInterface class allows managers to create reports
 * @author Jonathan Stuecker
 *
 */
import database.*;
public class ManagerInterface extends UserInterface{
	public void run() {
		String choice = null;
		while(choice != "5") {
			choice = getString("Please select which report to run:\n1. Member Report\n2. Provider Report\n3. Summary Report\n4. EFT Report\n5. Exit");
			switch(choice) {
	        case "1":
	          GenerateReport.generateReport("MemberReport");
	          break;
	        case "2":
	          GenerateReport.generateReport("ProviderReport");
	          break;
	        case "3":
	          GenerateReport.generateReport("SummaryReport");
	          break;
	        case "4":
	          GenerateReport.generateReport("EFTReport");
	          break;
	        case "5":
	          return;
	        default:
	          System.out.println("Invalid");
	          break;
	      }
		}
	}
}
