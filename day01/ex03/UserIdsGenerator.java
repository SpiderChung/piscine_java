

public class UserIdsGenerator {
    private static int id = 0;
    private static UserIdsGenerator userIdsGenerator;

    public static UserIdsGenerator getInstance() {
        if (userIdsGenerator == null) {
            userIdsGenerator = new UserIdsGenerator();
        }
        return userIdsGenerator;
    }

    public static int getUserId() {
        return id;
    }

    public int getGenId() {
        return ++id;
    }
}
