import database.*;
import java.util.Scanner;
public class Main{
  //create/load member database
  private static final String memberDatabaseFile = "member_database.dat";
  public static final Database<Member> memberDatabase = new Database<Member>(memberDatabaseFile);
  //create/load provider database
  private static final String providerDatabaseFile = "provider_database.dat";
  public static final Database<Provider> providerDatabase = new Database<Provider>(providerDatabaseFile);
  //create/load service record database
  private static final String serviceRecordDatabaseFile = "serviceRecord_database.dat";
  public static final ServiceDatabase<ServiceRecord> serviceRecordDatabase = new ServiceDatabase<ServiceRecord>(serviceRecordDatabaseFile);
  //create/load provider directory database
  private static final String providerDirectoryDatabaseFile = "providerDirectory_database.dat";
  public static final Database<ProviderDirectory> providerDirectory = new Database<ProviderDirectory>(providerDirectoryDatabaseFile);
  
  public static void main(String[] args) {
	String choice = "DEFAULT";
	while (!choice.equals("EXIT")) {
	choice = UserInterface.getString("ChocAn System Menu\n1. Operator\n2. Provider\n3. Manager\n4. Run Main Accounting Procedure\n5. Exit System");
    switch (choice) {
      case("1"):
        OperatorInterface OI = new OperatorInterface();
        OI.run();
        break;
      case("2"):
        ProviderInterface PI = new ProviderInterface();
        PI.run();
        break;
      case("3"):
    	ManagerInterface MI = new ManagerInterface();
      	MI.run();
      	break;
      case("4"):
    	MainAccountingProcedure.runReports();
      	break;
      case("5"):
    	  choice = "EXIT";
    }
	}
    return;
  }
}