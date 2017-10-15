package pcook01;

public class PaymentSystem {
	public static double RIDE_RATE = 0.70;
	public static final double DRIVER_PERCENTAGE = 0.80;
	
	public static void processTransaction(Transaction transaction) {
		User sender = transaction.getSender();
		User recipient = transaction.getRecipient();
		
		sender.removeBalance(transaction.getAmount());
		recipient.addBalance(DRIVER_PERCENTAGE * transaction.getAmount());
		
		System.out.printf("%s paid %s $%.2f.\n", sender.toString(), recipient.toString(), transaction.getAmount());
	}
	
	public static void setPaymentRate(double rate) {
		RIDE_RATE = rate;
	}
}
