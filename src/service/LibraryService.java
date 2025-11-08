package service;

import db.DBConnection;
import model.Book;
import model.Member;
import model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    // ---------- BOOK OPERATIONS ----------
    public void addBook(Book book) {
        String sql = "INSERT INTO books(title, author) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.executeUpdate();
            System.out.println("✅ Book added: " + book.getTitle());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("isIssued")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void issueBook(int bookId, int memberId) {
        String updateBook = "UPDATE books SET isIssued = TRUE WHERE id = ?";
        String insertTrans = "INSERT INTO transactions(memberId, bookId, type, date) VALUES (?, ?, 'Issued', CURDATE())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement bStmt = conn.prepareStatement(updateBook);
             PreparedStatement tStmt = conn.prepareStatement(insertTrans)) {
            bStmt.setInt(1, bookId);
            bStmt.executeUpdate();

            tStmt.setInt(1, memberId);
            tStmt.setInt(2, bookId);
            tStmt.executeUpdate();

            System.out.println("✅ Book issued successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int bookId, int memberId) {
        String updateBook = "UPDATE books SET isIssued = FALSE WHERE id = ?";
        String insertTrans = "INSERT INTO transactions(memberId, bookId, type, date) VALUES (?, ?, 'Returned', CURDATE())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement bStmt = conn.prepareStatement(updateBook);
             PreparedStatement tStmt = conn.prepareStatement(insertTrans)) {
            bStmt.setInt(1, bookId);
            bStmt.executeUpdate();

            tStmt.setInt(1, memberId);
            tStmt.setInt(2, bookId);
            tStmt.executeUpdate();

            System.out.println("✅ Book returned successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
