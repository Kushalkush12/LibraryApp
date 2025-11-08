package model;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private int memberId;
    private int bookId;
    private String type;
    private LocalDate date;

    public Transaction(int memberId, int bookId, String type) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.type = type;
        this.date = LocalDate.now();
    }

    public String toString() {
        return date + " | Member: " + memberId + " | Book: " + bookId + " | " + type;
    }
}
