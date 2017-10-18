package pcook01;

/**
 * Payment System is used to process transactions for Uber Rides
 * Contains final constants used for calculations internally and externally
 * @author patrickcook
 *
 */
public class PaymentSystem {
	public static double RIDE_RATE = 0.30;
	public static final double DRIVER_PERCENTAGE = 0.80;
	
	/**
	 * Processes a transaction which corresponds to an Uber ride
	 * @param transaction
	 */
	public static void processTransaction(Transaction transaction) {
		User sender = transaction.getSender();
		User recipient = transaction.getRecipient();
		
		sender.removeBalance(transaction.getAmount());
		recipient.addBalance(DRIVER_PERCENTAGE * transaction.getAmount());
		
		System.out.printf("%s paid %s $%.2f.\n", sender.toString(), recipient.toString(), transaction.getAmount());
	}
}
