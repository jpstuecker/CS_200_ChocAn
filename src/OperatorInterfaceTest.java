import org.junit.Test;

import database.Database;
import database.Member;

public class OperatorInterfaceTest {
	public static final Database<Member> memberDatabase = new Database<Member>("testMemberDatabase.dat");
	int number = -1;
	@Test
	public void testCreateNewMember() {
		String name = "TestName";
        String status = "VALID";
        String street = "TestAddress";
        String city = "TestCity";
        String state = "AL";
        String zip = "12345";
        Member newMember = new Member(name, status, street, city, zip, state);
        number = memberDatabase.addEntry(newMember);
        assert(newMember.getState() == "AL");
		assert(number > -1);
	}
	@SuppressWarnings("unused")
	@Test
	public void testEditMember() {
		String name = "TestName1";
        String status = "VALID";
        String street = "TestAddress1";
        String city = "TestCity1";
        String state = "LA";
        String zip = "54321";
        Member newMember = new Member(name, status, street, city, zip, state);
        int number = memberDatabase.addEntry(newMember);
        newMember.setName("NewName");
        assert(newMember.getName() == "NewName");
	}
	@Test
	public void testRemoveMember() {
		String name = "TestName2";
        String status = "VALID";
        String street = "TestAddress2";
        String city = "TestCity2";
        String state = "KY";
        String zip = "15243";
        Member newMember = new Member(name, status, street, city, zip, state);
        int number = memberDatabase.addEntry(newMember);
        memberDatabase.removeEntry(newMember);
        assert(memberDatabase.getEntry(number) == null);
	}
}
