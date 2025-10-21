package org.tcs.readers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * Database Utility with thread-safe connections.
 * Reads config from db.properties once and provides instance-level operations.
 */
public class DBUtil {
    private static final Logger LOGGER = LogManager.getLogger(DBUtil.class);

    private final Properties props = new Properties();
    private final String url;
    private final String username;
    private final String password;
    private final String driver;

    // Thread-safe connection storage
    private final ThreadLocal<Connection> threadLocalConnection = new ThreadLocal<>();

    public DBUtil() {
        try {
            props.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");
            driver = props.getProperty("db.driver");

            if (driver != null) {
                Class.forName(driver);
            }

            LOGGER.info("✅ DBUtility initialized with URL: {}", url);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to initialize DBUtility", e);
        }
    }

    private Connection getConnection() {
        Connection conn = threadLocalConnection.get();
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, username, password);
                threadLocalConnection.set(conn);
            }
        } catch (SQLException e) {
            throw new RuntimeException("❌ Failed to create DB connection", e);
        }
        return conn;
    }

    public List<Map<String, String>> executeQuery(String query) {
        List<Map<String, String>> resultList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(query);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, String> rowMap = new LinkedHashMap<String, String>();
                for (int i = 1; i <= columnCount; i++) {
                    rowMap.put(metaData.getColumnLabel(i), rs.getString(i));
                }
                resultList.add(rowMap);
            }

            LOGGER.info("✅ Query executed: {} ({} rows)", query, resultList.size());
        } catch (SQLException e) {
            LOGGER.error("❌ Query failed: {}", query, e);
            throw new RuntimeException("Database query failed: " + query, e);
        } finally {
            closeQuietly(rs);
            closeQuietly(stmt);
        }
        return resultList;
    }

    public int executeUpdate(String query) {
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            int rows = stmt.executeUpdate(query);
            LOGGER.info("✅ Update executed: {} ({} rows affected)", query, rows);
            return rows;
        } catch (SQLException e) {
            LOGGER.error("❌ Update failed: {}", query, e);
            throw new RuntimeException("Database update failed: " + query, e);
        } finally {
            closeQuietly(stmt);
        }
    }

    public String getSingleValue(String query) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(query);
            return rs.next() ? rs.getString(1) : null;
        } catch (SQLException e) {
            LOGGER.error("❌ Scalar query failed: {}", query, e);
            throw new RuntimeException("Database scalar query failed: " + query, e);
        } finally {
            closeQuietly(rs);
            closeQuietly(stmt);
        }
    }

    public boolean recordExists(String query) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(query);
            return rs.next();
        } catch (SQLException e) {
            LOGGER.error("❌ Record existence check failed: {}", query, e);
            throw new RuntimeException("Database record existence check failed: " + query, e);
        } finally {
            closeQuietly(rs);
            closeQuietly(stmt);
        }
    }

    public int getRowCount(String query) {
        return executeQuery(query).size();
    }

    public boolean validateValueInColumn(String query, String columnName, String expectedValue) {
        List<Map<String, String>> rows = executeQuery(query);
        for (Map<String, String> row : rows) {
            if (expectedValue.equalsIgnoreCase(row.get(columnName))) {
                return true;
            }
        }
        return false;
    }

    /** Close connection for current thread */
    public void closeConnection() {
        try {
            Connection conn = threadLocalConnection.get();
            if (conn != null && !conn.isClosed()) {
                conn.close();
                LOGGER.info("✅ Connection closed for thread {}", Thread.currentThread().getName());
            }
            threadLocalConnection.remove();
        } catch (SQLException e) {
            LOGGER.error("❌ Failed to close DB connection", e);
        }
    }

    private void closeQuietly(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception ignored) {
            }
        }
    }
}
