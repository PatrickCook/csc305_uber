package pcook01;

import java.util.Date;

public class Transaction {
	private User sender;
	private User recipient;
	private double amount;
	private Date date;
	
	public Transaction(User sender, User recipient, double amount) {
		this.sender = sender;
		this.recipient = recipient;
		this.amount = amount;
		this.date = new Date();
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
