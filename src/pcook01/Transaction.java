package pcook01;

import java.util.Date;

/**
 * Transaction class is used to keep records of a specific transaction
 * @author patrickcook
 */
public class Transaction {
	private User sender;
	private User recipient;
	private double amount;
	private Date date;
	
	/**
	 * Default transaction constructor
	 * @param sender - sender of payment
	 * @param recipient - recipient of payment
	 * @param amount - cost associated with transaction 
	 */
	public Transaction(User sender, User recipient, double amount) {
		this.sender = sender;
		this.recipient = recipient;
		this.amount = amount;
		this.date = new Date();
	}
	
	/**
	 * Getter for transaction sender
	 * @return User who is paying
	 */
	public User getSender() {
		return sender;
	}

	/**
	 * Getter for transaction recipient
	 * @return User who is receiving payment
	 */
	public User getRecipient() {
		return recipient;
	}

	/**
	 * Getter for transaction amount
	 * @return Amount associated with transaction
	 */
	public double getAmount() {
		return amount;
	}
}
