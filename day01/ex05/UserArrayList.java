

public class UserArrayList implements UserList {
    private int arraySize = 10;
    private User[] users = new User[arraySize];
    private int count = 0;

    public int getArraySize() {
        return arraySize;
    }

    @Override
    public void addUser(User newUser) {
        if (this.count == this.arraySize) {
            User[] users = new User[arraySize * 2];
            for (int i = 0; i < this.arraySize; i++) {
                users[i] = this.users[i];
            }
            this.arraySize = arraySize * 2;
            this.users = users;
            this.users[count++] = newUser;
        } else {
            this.users[count++] = newUser;
        }
        System.out.println("User with id = " + newUser.getId() + " is added");
    }

    @Override
    public User getUserByID(int id) {
        for (int i = 0; i < this.count; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User not found by ID = " + id);
    }

    @Override
    public User getUserByInd(int ind) {
        if (ind <= this.count && ind >= 0) {
            return users[ind];
        }
        throw new UserNotFoundException("User not found by Index = " + ind);
    }

    @Override
    public int getAmountOfUsers() {
        return this.count;
    }

    public void printUserArrayInfo() {
        for (int i = 0; i < this.count; i++) {
            System.out.print("Name:\t" + users[i].getName() + "\tBalance:\t" + users[i].getBalance() + "\t\t");
            System.out.println("id:\t" + users[i].getId());
        }
    }
}
