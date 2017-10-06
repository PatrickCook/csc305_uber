package pcook01;

public class PaymentSystem {
	public static final double RIDE_RATE = 2.00;
	
	public static void processTransaction(Transaction transaction) {
		User sender = transaction.getSender();
		User recipient = transaction.getRecipient();
		
		sender.removeBalance(transaction.getAmount());
		recipient.addBalance(transaction.getAmount());
		
		System.out.println(sender.toString() + " paid " + 
				recipient.toString() + " " + transaction.getAmount());
	}
}
