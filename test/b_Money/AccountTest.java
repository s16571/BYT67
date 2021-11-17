package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", .5);
		DKK = new Currency("DKK", 1d);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000d, SEK));

		SweBank.deposit("Alice", new Money(1000000d, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("test", 3600, 7200, new Money(1d, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("test"));
		testAccount.removeTimedPayment("test");
		assertFalse(testAccount.timedPaymentExists("test"));
	}
	
	@Test(expected = AccountDoesNotExistException.class)
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("test", 3600, 0, new Money(1d, SEK), SweBank, "AccountThatDoesntExist");
		testAccount.tick();
	}

	@Test
	public void testAddWithdraw() {
		testAccount.withdraw(new Money(5000000d, SEK));
		assertTrue(testAccount.getBalance().equals(new Money(5000000d, SEK)));

		testAccount.withdraw(new Money(5000000d, SEK));

		testAccount.deposit(new Money(10d, SEK));
		testAccount.withdraw(new Money(5d, DKK));
		assertTrue(testAccount.getBalance().equals(new Money(0d, SEK)));
	}
	
	@Test
	public void testGetBalance() {
		assertTrue(testAccount.getBalance().equals(new Money(10000000d, SEK)));
	}
}
