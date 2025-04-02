package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 1. DB Connection
 * 2. SQL 실행 -> DELETE 쿼리
 * 3. 결과 출력 -> DELETE 갯수
 */

public class DeleteJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "0000";

    public static void main(String[] args) {
        // try-with-resources
        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement()
        ) {
            String query = "DELETE FROM students WHERE age >= 30";

            int count = statement.executeUpdate(query);

            System.out.println("DELETE count: " + count);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
