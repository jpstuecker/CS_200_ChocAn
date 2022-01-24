/**
 * GenerateReport is a control class that provides a bridge between other classes and the Report classes.
 * In future versions, this class would also call for the printing and emailing of reports.
 * @author Matt Poirier
 *
 */
public class GenerateReport {
	
	/**
	 * generateReport creates a Report object and generates the respective types of reports.
	 * @param reportType the type of report to be generated
	 * @return 
	 * @throws IllegalArgumentException if report type is invalid
	 */
	public static boolean generateReport(String reportType) {
		Report createReport;
		switch(reportType) {
		case "MemberReport":
			createReport = new MemberReport();
			createReport.generateReport();
			return true;
		case "ProviderReport":
			createReport = new ProviderReport();
			createReport.generateReport();
			return true;
		case "SummaryReport":
			createReport = new SummaryReport();
			createReport.generateReport();
			return true;
		case "EFTReport":
			createReport = new EftReport();
			createReport.generateReport();
			return true;
		default:
			System.out.println("Invalid report type");
			throw new IllegalArgumentException();
		}
	}

}
