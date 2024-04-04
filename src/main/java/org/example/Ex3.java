package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex3 {
    public static void main(String[] args) throws SQLException {
        Database database = Database.getInstance();

        // Ex1 using PreparedStatement
        String query = "SELECT f.title " +
                "FROM film f " +
                "INNER JOIN film_actor fa ON f.film_id = fa.film_id " +
                "WHERE fa.actor_id = ?";
        PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Films featuring actor with ID 1:");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("title"));
        }


        // Ex2 using PreparedStatement
        query = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
        preparedStatement = database.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, "John");
        preparedStatement.setString(2, "Doe");
        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println("Inserted actor with ID " + resultSet.getInt(1));
            }
        }
    }
}
