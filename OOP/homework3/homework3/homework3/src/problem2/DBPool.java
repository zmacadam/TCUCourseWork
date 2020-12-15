package problem2;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBPool {
    // Container for storing DB connections
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();

    public DBPool(int initialSize) {
        this.connectionPool = new ArrayList<>(10);
        for (int i = 0; i < initialSize; i++) {
            this.connectionPool.add(new BingConnectionImpl());
        }
    }

    public Connection fetchConn() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public void releaseConn(Connection conn) {
        connectionPool.add(conn);
        usedConnections.remove(conn);
    }
}
