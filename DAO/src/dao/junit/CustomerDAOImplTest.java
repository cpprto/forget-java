package src.dao.junit;

import src.bean.Customer;
import src.dao.CustomerDAOImpl;
import src.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;

public class CustomerDAOImplTest {
    private final CustomerDAOImpl dao = new CustomerDAOImpl();

    @Test
    public void testInsert() {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            Customer customer = new Customer(0, "X", "X@gmail.com", new Date(12345678910L));
            dao.insert(connection, customer);
            System.out.println("Success");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testDeleteById() {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            dao.deleteById(connection, 13);
            System.out.println("Success");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testUpdate() {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            Customer customer = new Customer(14, "强尼", "hand@gmail.com", new Date(12345678910L));
            dao.update(connection, customer);

            System.out.println("Success");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testGetCustomerById() {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            Customer customer = dao.getCustomerById(connection, 14);
            System.out.println(customer);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testGetAll() {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            dao.getAll(connection).forEach(System.out::println);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testGetCount() {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            System.out.println("Count: " + dao.getCount(connection));
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testGetMaxBirthDay() {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            System.out.println("MaxBirthDay: " + dao.getMaxBirthDay(connection));
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }
}