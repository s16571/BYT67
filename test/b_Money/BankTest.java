package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
        assertEquals(SEK, Nordea.getCurrency());
        assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test(expected = AccountExistsException.class)
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("Ulrika");
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testDeposit() throws AccountDoesNotExistException {
		DanskeBank.deposit("NotExistingAccount", new Money(1000000d, DKK));
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testWithdraw() throws AccountDoesNotExistException {
		DanskeBank.withdraw("NotExistingAccount", new Money(1000000d, DKK));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(Double.valueOf(0), SweBank.getBalance("Bob"));
	}
	
	@Test(expected = AccountDoesNotExistException.class)
	public void testTransfer() throws AccountDoesNotExistException {
		DanskeBank.transfer("NotExistingAccount", "Bob", new Money(1000000d, DKK));
	}
	
	@Test(expected = AccountDoesNotExistException.class)
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("NotExistingAccount", "testid", 10, 0, new Money(1000d, SEK), DanskeBank, "Gertrud");
	}
}
