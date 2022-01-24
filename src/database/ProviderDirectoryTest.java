package database;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProviderDirectoryTest {
       ProviderDirectory ProvD;
    @Before
    public void setUp() throws Exception {
       ProvD = new ProviderDirectory("Climbing", 34);
    }

    @Test
    public void test() {
        ProvD.setServiceFee(30);
        System.out.println(ProvD.printServiceCode());
    }

}
