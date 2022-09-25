

import java.util.UUID;

public class TransactionService {
    UserList userList = new UserArrayList();

    public void addUser(User user) {
        this.userList.addUser(user);
    }

    public int getUserBalance(int id) {
        return this.userList.getUserByID(id).getBalance();
    }

    public int getUserBalance(User user) {
        return user.getBalance();
    }

    public void executeTransaction(int senderId, int recipientId, int amount) {
        User sender = userList.getUserByID(senderId);
        User recipient = userList.getUserByID(recipientId);

        if (senderId == recipientId || amount < 0 || sender.getBalance() < amount) {
            throw new IlleagalTransactionException("Illegal Transaction");
        }
        Transaction credit = new Transaction(sender, recipient, -amount, Transaction.TransferCategory.CREDIT);
        Transaction debit = new Transaction(sender, recipient, amount, Transaction.TransferCategory.DEBIT);
        debit.setId(credit.getId());
        recipient.getTransList().addTransaction(debit);
        sender.getTransList().addTransaction(credit);
        recipient.setBalance(recipient.getBalance() + amount);
        sender.setBalance(sender.getBalance() - amount);
    }

    public Transaction[] getTransactionList(int userId) {
        return userList.getUserByID(userId).getTransList().toArray();
    }

    public void removeTransactionList(UUID transId, int userId) {
        userList.getUserByID(userId).getTransList().removeTransactionById(transId);
    }

    public Transaction[] getInvalidTransaction() {
        TransactionLinkedList list = new TransactionLinkedList();
        for (int i = 0; i < userList.getAmountOfUsers(); i++) {
            User user = userList.getUserByInd(i);
            if (user != null) {
                int size = user.getTransList().getSize();
                for (int j = 0; j < size; j++) {
                    list.addTransaction(user.getTransList().toArray()[j]);
                }
            }
        }

        TransactionLinkedList res = new TransactionLinkedList();
        for (int i = 0; i < list.getSize(); i++) {
            int count = 0;
            for (int j = 0; j < list.getSize(); j++) {
                if (list.toArray()[i].getId() == list.toArray()[j].getId()) {
                    count++;
                }
            }
            if (count != 2) {
                res.addTransaction(list.toArray()[i]);
            }
        }
        return res.toArray();
    }
}
