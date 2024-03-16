package org.example;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class MigrationRunner {
    private static final String DB_URL = "jdbc:h2:./database";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "123";

    public static void main(String[] args) {
        DataSource dataSource = new DataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                return DriverManager.getConnection(DB_URL, username, password);
            }

            @Override
            public PrintWriter getLogWriter() throws SQLException {
                return new PrintWriter(System.out);
            }

            @Override
            public void setLogWriter(PrintWriter out) throws SQLException {
                // Нічого не робимо
            }

            @Override
            public void setLoginTimeout(int seconds) throws SQLException {
                // Нічого не робимо
            }

            @Override
            public int getLoginTimeout() throws SQLException {
                return 0;
            }

            @Override
            public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                throw new SQLFeatureNotSupportedException();
            }

            @Override
            public <T> T unwrap(Class<T> iface) throws SQLException {
                throw new SQLException("Unwrapping is not supported");
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                return false;
            }
        };

        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("filesystem:migration")
                .load();

        flyway.migrate();
    }
}