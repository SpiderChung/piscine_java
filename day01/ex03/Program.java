

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User us1 = new User("Mike", 3010);
        User us2 = new User("Vova", 1000);
        User us3 = new User("Artem", 11000);

        UserArrayList userList = new UserArrayList();
        userList.addUser(us1);
        userList.addUser(us2);
        userList.addUser(us3);

        TransactionLinkedList list = new TransactionLinkedList();
        Transaction trang1 = new Transaction(userList.getUserByID(1), userList.getUserByID(2), -300, Transaction.TransferCategory.CREDIT);
        Transaction trang2 = new Transaction(us2, us1, -300, Transaction.TransferCategory.CREDIT);
        Transaction trang3 = new Transaction(us1, us2, 600, Transaction.TransferCategory.DEBIT);
        Transaction trang4 = new Transaction(us3, us2, 1000, Transaction.TransferCategory.DEBIT);

        list.addTransaction(trang1);
        list.addTransaction(trang3);
        list.addTransaction(trang4);
        list.addTransaction(trang2);

        list.showTransaction();
        list.removeTransactionById(trang2.getId());
        System.out.println(list.getSize());
        list.showTransaction();

        Transaction[] tranqArray = list.toArray();

        System.out.println();
        for (int i = 0; i < list.getSize(); i++){
            tranqArray[i].printTransaction();
        }

        try {
            list.removeTransactionById(UUID.randomUUID());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
