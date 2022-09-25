

public class User {
    private String name;
    private int id;
    private int balance;

    public User(String name, int id, int balance) {
        this.id = id;
        this.name = name;

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
}
