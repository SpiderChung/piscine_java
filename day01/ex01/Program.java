public class Program {
    public static void main(String[] args) {
        User user1 = new User("Mike", 300);
        User user2 = new User("Vova", 100);
        User user3 = new User("Art", 1100);

        user1.printUserInfo();
        user2.printUserInfo();
        user3.printUserInfo();

        System.out.println("The last id: " + UserIdsGenerator.getUserId());
    }
}
