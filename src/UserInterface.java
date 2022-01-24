import java.util.Scanner;

public abstract class UserInterface {
  
  public static final Scanner userIn = new Scanner(System.in);
  
  public static String getString(String message) {
    try {
      System.out.println(message);
      String response = userIn.nextLine();
      return response;
    } catch (Exception e) {}
  return null;
  }
  
  public static int getInt(String message) {
    try {
      System.out.println(message);
      int response = Integer.parseInt(userIn.nextLine());
      return response;
    } catch (Exception e) {}
    return -1;
  }
  
  public static double getDouble(String message) {
	  try {
	      System.out.println(message);
	      double response = Double.parseDouble(userIn.nextLine());
	      return response;
	    } catch (Exception e) {}
	    return -1;
  }
  
  public abstract void run();
}
