package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 1. DB Connection
 * 2. SQL 실행 -> INSERT INTO 쿼리
 * 3. 결과 출력 -> INSERT 갯수
 */

public class InsertJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "0000";

    public static void main(String[] args) {
        // try-with-resources
        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement()
        ) {
            String query = "INSERT INTO students (name, age, address) VALUES "
                    + "('name11', 25, 'address11'), "
                    + "('name22', 30, 'address22'), "
                    + "('name33', 27, 'address33')";

            int count = statement.executeUpdate(query);

            System.out.println("INSERT count: " + count);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
