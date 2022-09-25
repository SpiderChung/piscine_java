

public class User {
        private String name;
        private final int id;
        private int balance;
        private TransactionsList transList;

        public User() {
            this.id = UserIdsGenerator.getInstance().getGenId();
            this.balance = 0;
            this.transList = new TransactionLinkedList();
        }

        public User(String name, int balance) {
            this.id = UserIdsGenerator.getInstance().getGenId();
            this.name = name;
            this.transList = new TransactionLinkedList();

            if (balance < 0) {
                System.err.println("Incorrect balance: " + balance + " User: "  + name);
            } else {
                this.balance = balance;
            }
        }

        public void printUserInfo() {
            System.out.println("User ( id = " + id + ", name = " + name +
                    ", balance = " + balance + " )");
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public int getBalance() {
            return balance;
        }

        public TransactionsList getTransList() {
            return transList;
        }
}
