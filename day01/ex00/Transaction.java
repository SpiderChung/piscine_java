

import java.util.UUID;

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;
    private TransferCategory category;
    private int amount;

    public Transaction(User sender, User recipient, int amount, TransferCategory category) {
        if ((category == TransferCategory.CREDIT && amount > 0) ||
                (category == TransferCategory.DEBIT && amount < 0)) {
            System.err.print("Your transaction cannot be completed: ");
            System.err.println("The wrong transfer category");
        } else if ((category == TransferCategory.CREDIT && -amount > sender.getBalance()) ||
                (category == TransferCategory.DEBIT && amount > sender.getBalance())) {
            System.err.print("Your transaction cannot be completed: ");
            System.err.println("The unsufficient balance");
        } else {
            this.id = UUID.randomUUID();
            this.recipient = recipient;
            this.sender = sender;
            this.amount = amount;
            this.category = category;
        }
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransferCategory getCategory() {
        return category;
    }

    public UUID getId() {
        return id;
    }

    public void printTransaction() {
        System.out.print("Sender: " + sender.getName() + " Recipient: " + recipient.getName());
        System.out.println(" Category: " + category + " Amount: " + amount + " ID: " + id);
    }

    enum TransferCategory {
        DEBIT,
        CREDIT
    }
}