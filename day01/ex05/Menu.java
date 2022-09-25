

import java.util.Scanner;

public class Menu {
    private TransactionService service;
    private Scanner scanner;
    private final boolean devMode;


    public Menu(boolean devMode) {
        service = new TransactionService();
        scanner = new Scanner(System.in);
        this.devMode = devMode;
    }

    public void run() {
        while (true) {
            printMainScreen();
            int action = action();
            if (action == 1) {
                addUser();
            } else if (action == 2) {
                getBalance();
            } else if (action == 3) {
                tranche();
            } else if (action == 4) {
                showTrans();
            }  else if (action == 5) {
                devRemoveTransById();
            } else if (action == 6) {
                devCheckTrans();
            } else {
                System.exit(0);
            }
            System.out.println("---------------------------------------------------------");
        }
    }

    private void printMainScreen() {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if (devMode) {
            System.out.println("5. DEV - remove a transfer by ID");
            System.out.println("6. DEV - check transfer validity");
        }
        System.out.println("7. Finish execution");
    }

    private int action() {
        int action;

        try {
            String str = scanner.nextLine().trim();
            action = Integer.parseInt(str);
            if (!isValidAction(action)) {
                throw new InvalidDataException();
            }
        } catch (RuntimeException e) {
            System.out.println("Invalid action. Enter correct number: ");
            action = action();
        }
        return action;
    }

    private boolean isValidAction(int action) {
        if (action > 0 && devMode && action <= 7) {
            return true;
        }
        if (((action > 0 && action <= 4) || action == 7) && !devMode) {
            return true;
        }
        return false;
    }

    private void addUser() {
        System.out.println("Enter a user name and a balance");
        String str = scanner.nextLine().trim();

        try {
            String[] data = str.split("\\s+");
            if (data.length != 2) {
                throw new InvalidDataException("Invalid data");
            }
            String name = data[0];
            int balance = checkBalance(data[1]);
            User user = new User(name, balance);
            service.addUser(user);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in: " + str);
            addUser();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            addUser();
        }
    }

    private int checkBalance(String balance) {
        int data = Integer.parseInt(balance);

        if (data < 0) {
            throw new InvalidDataException("Balance cannot be negative");
        }
        return data;
    }

    private void getBalance() {
        System.out.println("Enter user ID");
        String str = scanner.nextLine().trim();

        try {
            int id = Integer.parseInt(str);
            User user = service.getUserList().getUserByID(id);
            System.out.println(user.getName() + " - " + user.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void tranche() {
        System.out.println("Enter a sender ID, a recipient ID and a transfer amount");
        String str = scanner.nextLine().trim();

        try {
            String[] data = str.split("\\s+");
            if (data.length != 3) {
                throw new InvalidDataException("Invalid data");
            }
            int senderId = Integer.parseInt(data[0]);
            int recipientId = Integer.parseInt(data[1]);
            int amount = Integer.parseInt(data[2]);
            service.executeTransaction(senderId, recipientId, amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid data");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showTrans() {
        System.out.println("Enter a user ID");
        String str = scanner.nextLine().trim();

        try {
            int userId = Integer.parseInt(str);
            Transaction[] trans = service.getTransactionByUserId(userId);
            if (trans.length == 0) {
                return;
            }
            Transaction[] list = service.getTransactionList(userId);
            for (Transaction transaction : list) {
                transaction.printTransaction();
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number in: " + str);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void devRemoveTransById() {
        System.out.println("Enter a user ID and a transfer ID");
        String str = scanner.nextLine().trim();

        try {
            String[] data = str.split("\\s+");
            Transaction trans = null;
            if (data.length != 2) {
                throw new InvalidDataException("Invalid data");
            }
            int userId = Integer.parseInt(data[0]);
            String uuid = data[1];
            Transaction[] transactions = service.getUserList().getUserByID(userId).getTransList().toArray();

            for (int i = 0; i < transactions.length; i++) {
                if (uuid.equals(transactions[i].getId().toString())) {
                    trans = transactions[i];
                    break;
                }
            }
            if (trans == null) {
                throw new InvalidDataException("Transaction with id = "
                        + uuid + " not found from User with id = " + userId);
            }
            service.removeTransactionList(trans.getId(), userId);
            if (userId == trans.getSender().getId()) {
                User recipient = trans.getRecipient();
                System.out.println("Transfer To " + recipient.getName()
                        + "(id = " + recipient.getId() + ") " + ((-1) * trans.getAmount()) + " removed");
            } else {
                User sender = trans.getSender();
                System.out.println("Transfer From " + sender.getName()
                        + "(id = " + sender.getId() + ") " + trans.getAmount() + " removed");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in: " + str);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void devCheckTrans() {
        System.out.println("Check results:");
        Transaction[] trans = service.getInvalidTransaction();
        for (Transaction transaction : trans) {
            User recipient = transaction.getRecipient();
            User sender = transaction.getSender();

            if (transaction.getAmount() > 0) {
                System.out.println(recipient.getName() + "(id = " + recipient.getId()
                        + ") has an unacknowledged transfer id = " + transaction.getId()
                        + " from " + sender.getName() + "(id = " + sender.getId()
                        + ") for " + transaction.getAmount());
            } else {
                System.out.println(sender.getName() + "(id = " + sender.getId()
                        + ") has an unacknowledged transfer id = " + transaction.getId()
                        + " to " + recipient.getName() + "(id = " + recipient.getId()
                        + ") for " + transaction.getAmount());
            }
        }
    }
}
