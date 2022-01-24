package database;
/**
 * 
 * @author  Joseph Ruzicka
 */
@SuppressWarnings("serial")
public class ProviderDirectory extends Database.Person {
   
    private int serviceNumber;
    private double serviceFee;
    /**
     * creates ProviderDirectory
     * @param name      name of service
     * @param serviceNumber    6 digit code corresponding to a service
     */
    public ProviderDirectory(String name, double serviceFee)
    {
        this.name = name;
        this.serviceFee = serviceFee;
    }
    /**
     * sets the service code
     * @param servNumber    6 digit code
     */
    public void setServiceCode(int servNumber)
    {
       serviceNumber = servNumber;
    }
    /**
     * Gets the service code
     * @return serviceNumber the 6 digit code corresponding to a service
     */
    public int getServiceCode()
    {
        return serviceNumber;
    }
    /**
     * This method prints the service code out as 6 digits
     * @return A string method is used to make it a 6 digit number
     */
    public String printServiceCode()
    {
        return String.format("%06d", serviceNumber);
    }
    
    /**
     * sets the service fee
     * @param servFee   price of service
     */
    public void setServiceFee(double servFee)
    {
        serviceFee = servFee;
    }
    
    /**
     * gets service fee
     * @return serviceFee the fee of the service provided
     */
    public double getServiceFee()
    {
        return serviceFee;
    }

}
