import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class GiftCardTest
{
    @Test
    public void getIssuingStore()
    {
        double       balance;        
        GiftCard     card;
        int          issuingStore;
        
        issuingStore = 1337;
        balance      = 100.00;
        card = new GiftCard(issuingStore, balance);

        assertEquals("getIssuingStore()", 
                     issuingStore, card.getIssuingStore());
    }
    
    @Test
    public void getBalance()
    {
    	double       balance;        
        GiftCard     card;
        int          issuingStore;
        
        issuingStore = 1337;
        balance      = 100.00;
        card = new GiftCard(issuingStore, balance);
        
        assertEquals("getBalance()", balance, card.getBalance(), 0.001);
    }
    
    @Test
    public void deductAmount()
    {
    	double       balance;        
        GiftCard     card;
        int          issuingStore;
        String		 test;
        
        issuingStore = 1337;
        balance      = 100.00;
        test		 = "Remaining Balance: " + String.format("%6.2f", 0.00);
        card = new GiftCard(issuingStore, balance);
        
        assertEquals("deductAmount()", test, card.deduct(balance));
    }
    
    @Test
    public void deductAmount2()
    {
    	double       balance;        
        GiftCard     card;
        int          issuingStore;
        String		 test;
        
        issuingStore = 1337;
        balance      = 100.00;
        test		 = "Amount Due: " + String.format("%6.2f", 10.00);
        card = new GiftCard(issuingStore, balance);
        
        assertEquals("deductAmount2()", test, card.deduct(110.00));
    }
    
    @Test
    public void deductAmount3()
    {
    	double       balance;        
        GiftCard     card;
        int          issuingStore;
        String		 test;
        
        issuingStore = 1337;
        balance      = 100.00;
        test		 = "Invalid Transaction";
        card = new GiftCard(issuingStore, balance);
        
        assertEquals("deductAmount3()", test, card.deduct(0.0-1));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void constructor_IncorrectBalance() throws IllegalArgumentException
    {
        new GiftCard(1, -100.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_IncorrectID_Low() throws IllegalArgumentException
    {
    	new GiftCard(-1, -100.00);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void constructor_IncorrectID_High() throws IllegalArgumentException
    {
    	new GiftCard(10000, -100.00);
    }
}
