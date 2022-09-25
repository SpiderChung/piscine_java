

public class Program {
    public static void main(String[] args) {
        User us1 = new User("Mike", 3010);
        User us2 = new User("Vova", 1000);
        User us3 = new User("Artem", 11000);
        User us4 = new User("Lova", 1300);
        User us5 = new User("Korova", 3020);
        User us6 = new User("Petr", 1600);
        User us7 = new User("Kalach", 2300);
        User us8 = new User("Ruslan", 1000);
        User us9 = new User("Garik", 134000);
        User us10 = new User("Willy", 3500);
        User us11 = new User("Dilly", 1100);
        User us12 = new User("Billy", 1800);
        User us13 = new User("Bick", 1800);

        UserArrayList userList = new UserArrayList();
        userList.addUser(us1);
        userList.addUser(us2);
        userList.addUser(us3);
        userList.addUser(us4);
        userList.addUser(us5);
        userList.addUser(us6);
        userList.addUser(us7);
        userList.addUser(us8);
        userList.addUser(us9);
        userList.addUser(us10);
        userList.addUser(us11);
        userList.addUser(us12);
        userList.addUser(us13);

        userList.printUserArrayInfo();

        User user14 = userList.getUserByID(us2.getId());
        user14.printUserInfo();

        try {
            User user15 = userList.getUserByInd(12);
            user15.printUserInfo();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            User user16 = userList.getUserByID(400);
            user16.printUserInfo();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("ArraySize:\t" + userList.getArraySize());
        System.out.println("UserCount:\t"+userList.getAmountOfUsers());
    }

}
