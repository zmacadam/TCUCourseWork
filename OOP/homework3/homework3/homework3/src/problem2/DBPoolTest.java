package problem2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is the client program that uses database connections
 */
public class DBPoolTest {
    static DBPool pool = new DBPool(10);

    public static void main(String[] args) throws Exception {
        // Step1: Asking for a connection from the pool
        Connection connection = pool.fetchConn();

        // Step2: Using this connection to CRUD database
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Employee");
        // ......

        // Step3: Returning the connection back to the pool
        pool.releaseConn(connection);

    }


}
