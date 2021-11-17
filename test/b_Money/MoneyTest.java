package b_Money;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
    Currency SEK, DKK, NOK, EUR;
    Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
        SEK100 = new Money(10000d, SEK);
        EUR10 = new Money(1000d, EUR);
        SEK200 = new Money(20000d, SEK);
        EUR20 = new Money(2000d, EUR);
        SEK0 = new Money(0d, SEK);
        EUR0 = new Money(0d, EUR);
        SEKn100 = new Money(-10000d, SEK);
    }

    @Test
    public void testGetAmount() {
        assertEquals(Double.valueOf(20000), SEK200.getAmount());
		assertEquals(Double.valueOf(10000), SEK100.getAmount());
		assertEquals(Double.valueOf(2000), EUR20.getAmount());
    }

    @Test
    public void testGetCurrency() {
        assertEquals(SEK, SEK100.getCurrency());
		assertEquals(SEK, SEK200.getCurrency());
		assertEquals(EUR, EUR20.getCurrency());
    }

    @Test
    public void testToString() {
        assertEquals("20000.0 SEK", SEK200.toString());
    }

    @Test
    public void testGlobalValue() {
        assertEquals(Double.valueOf(20000d * 0.15), SEK200.universalValue());
    }

    @Test
    public void testEqualsMoney() {
        assertFalse(SEK100.equals(SEK200));
        assertFalse(SEK0.equals(EUR0));
    }

    @Test
    public void testAdd() {
        assertEquals(SEK200.getAmount(), SEK100.add(SEK100).getAmount());
    }

    @Test
    public void testSub() {
        assertEquals(SEK100.getAmount(), SEK200.sub(SEK100).getAmount());
    }

    @Test
    public void testIsZero() {
        assertFalse(SEK200.isZero());
    }

    @Test
    public void testNegate() {
        assertEquals(Double.valueOf(-10000d), SEK100.negate().getAmount());
    }

    @Test
    public void testCompareTo() {
        assertEquals(0, SEK100.compareTo(SEK100));
        assertEquals(-1, SEK100.compareTo(SEK200));
        assertEquals(1, SEK200.compareTo(SEK100));
    }
}
