

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Mike", 1, 300);
        User user2 = new User("Alex", 2, 500);
        User user3 = new User("Vova", 3, 1000);
        user1.printUserInfo();
        user2.printUserInfo();
        user3.printUserInfo();

        Transaction trang1 = new Transaction(user3, user1, -100, Transaction.TransferCategory.CREDIT);
        Transaction trang2 = new Transaction(user2, user1, 300, Transaction.TransferCategory.DEBIT);

        trang1.printTransaction();
        trang2.printTransaction();
    }
}
