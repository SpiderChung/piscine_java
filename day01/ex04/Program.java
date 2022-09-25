

public class Program {
    public static void main(String[] args) {
        User us1 = new User("Mike", 3010);
        User us2 = new User("Vova", 1000);
        User us3 = new User("Artem", 11000);

        TransactionService transactionService = new TransactionService();
        transactionService.addUser(us1);
        transactionService.addUser(us2);
        transactionService.addUser(us3);
        us1.printUserInfo();
        us2.printUserInfo();
        us3.printUserInfo();
        System.out.println();

        transactionService.executeTransaction(us1.getId(), us2.getId(), 300);
        transactionService.executeTransaction(us2.getId(), us1.getId(), 300);
        transactionService.executeTransaction(us1.getId(), us2.getId(), 600);
        transactionService.executeTransaction(us3.getId(), us2.getId(), 1000);

        us1.printUserInfo();
        us2.printUserInfo();
        us3.printUserInfo();

        System.out.println("User1");
        us1.printTransactionList();
        System.out.println("User2");
        us2.printTransactionList();
        System.out.println("User3");
        us3.printTransactionList();

        Transaction tranq1 = new Transaction(us1, us2, 500, Transaction.TransferCategory.DEBIT);
        Transaction tranq2 = new Transaction(us1, us2, 200, Transaction.TransferCategory.DEBIT);
        us1.getTransList().addTransaction(tranq1);
        us1.getTransList().addTransaction(tranq2);
        Transaction[] invalid = transactionService.getInvalidTransaction();
        System.out.println("User1 after invalid transaction");
        us1.printTransactionList();
        System.out.println();
        System.out.println(" id = 2 - balance:" + transactionService.getUserBalance(2));
        System.out.println(" User = " + us3.getName() +" - balance:" + transactionService.getUserBalance(us3));
        System.out.println();

        us1.printUserInfo();
        us2.printUserInfo();
        us3.printUserInfo();
        System.out.println();
        System.out.println("Invalid Array");
        for (int i = 0; i < invalid.length; i++) {
            invalid[i].printTransaction();
        }
        System.out.println();
        for (int i = 0; i < invalid.length; i++) {
            transactionService.removeTransactionList(invalid[i].getId(), invalid[i].getSender().getId());
        }

        us1.printTransactionList();
        System.out.println("end");
        System.out.println();

        try {
            transactionService.executeTransaction(us1.getId(), us1.getId(), 200);;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Transaction[] listok = transactionService.getTransactionList(us1.getId());
        for (int i = 0; i < listok.length; i++) {
            listok[i].printTransaction();
        }
    }
}
