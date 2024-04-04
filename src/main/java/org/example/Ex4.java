package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Ex4 {
    private static void insertRental(Connection conn, int rentalId, int filmId) throws SQLException {
        String query = "INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis())); // Sample rental_date
            stmt.setInt(2, filmId);
            stmt.setInt(3, 1); // Sample customer_id
            stmt.setTimestamp(4, null); // Sample return_date (null for now)
            stmt.setInt(5, 1); // Sample staff_id
            stmt.executeUpdate();
        }
    }
    public static void main(String[] args) {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        try {
            connection.setAutoCommit(false);
            insertRental(connection, 1, 1);
            insertRental(connection, 2, 2);
            connection.commit();
            System.out.println("Commit successfully inserted rentals");
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Rollback successful");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
