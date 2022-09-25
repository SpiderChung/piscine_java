

import java.util.UUID;

public class TransactionLinkedList implements TransactionsList {
    private Transaction start;
    private Transaction end;
    private int size  = 0;

    @Override
    public void addTransaction(Transaction newTransaction) {
        if (start == null) {
            start = newTransaction;
        } else {
            end.setNext(newTransaction);
        }
        end = newTransaction;
        size++;
    }

    @Override
    public void removeTransactionById(UUID id) {
        Transaction prev;
        Transaction temp;

        if (start != null) {
            temp = start.getNext();
            prev = start;

            if (prev.getId() == id) {
                start = temp;
                size--;
                return;
            }

            while (temp != null) {
                if (temp.getId() == id) {
                    prev.setNext(temp.getNext());
                    size--;
                    return;
                }
                prev = prev.getNext();
                temp = temp.getNext();
            }
        }
        throw new TransactionNotFoundException("Transaction not found");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transArray = new Transaction[size];
        int i = 0;
        Transaction temp = start;

        while (i < this.size) {
            transArray[i] = temp;
            i++;
            temp = temp.getNext();
        }
        return transArray;
    }

    public void showTransaction() {
        Transaction temp;
        temp = start;

        while (temp != null) {
            temp.printTransaction();
            temp = temp.getNext();
        }
    }

    public int getSize() {
        return size;
    }
}
