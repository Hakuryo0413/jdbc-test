package org.example;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex2 {
    public static void main(String[] args) throws SQLException {
        Database database = Database.getInstance();
        String query = "INSERT INTO actor (first_name, last_name) VALUES ('John', 'Doe')";
        try {
            Statement statement = database.getConnection().createStatement();
            int rows = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            if (rows > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    System.out.println("Inserted actor with ID " + resultSet.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
