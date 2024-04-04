package org.example;

import java.sql.ResultSet;

public class Ex1 {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        String query = "SELECT f.title " +
                "FROM film f " +
                "INNER JOIN film_actor fa ON f.film_id = fa.film_id " +
                "WHERE fa.actor_id = 1";
        ResultSet resultSet = database.query(query);
        System.out.println("Films featuring actor with ID 1:");
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("title"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
    }
}
