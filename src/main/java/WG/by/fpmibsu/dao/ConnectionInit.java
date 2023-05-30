package WG.by.fpmibsu.dao;

import WG.by.fpmibsu.dao.pull.ConnectionPool;

public class ConnectionInit {
    private static final String DB_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;" +
            "user=TestUser2;password=pwd;databaseName=WorldGuruDataBase";
    private static final String DB_USER = "TestUser2";
    private static final String DB_PASSWORD = "pwd";
    private static final int DB_POOL_START_SIZE = 10;
    private static final int DB_POOL_MAX_SIZE = 1000;
    private static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;
    ConnectionPool connectionPool = new ConnectionPool(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);

    public ConnectionInit() throws DaoException {
    }
}
