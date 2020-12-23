package src.dao;

import src.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * CustomerDAO 的具体实现类
 *
 * @version: 1.0
 * @author: cpprto
 * @date: 2020/9/23 17:50
 */
public class CustomerDAOImpl extends BaseDao<Customer> implements CustomerDAO {
    @Override
    public void insert(Connection connection, Customer customer) {
        String sql = "insert into customers(name, email, birth) values(?, ?, ?)";
        update(connection, sql, customer.getName(), customer.getEmail(), customer.getBirth());
    }

    @Override
    public void deleteById(Connection connection, int id) {
        String sql = "delete from customers where id = ?";
        update(connection, sql, id);
    }

    @Override
    public void update(Connection connection, Customer customer) {
        String sql = "update customers set name = ?, email = ?, birth = ? where id = ?";
        update(connection, sql, customer.getName(), customer.getEmail(), customer.getBirth(), customer.getId());
    }

    @Override
    public Customer getCustomerById(Connection connection, int id) {
        String sql = "select id, name, email, birth from customers where id = ?";
        return getInstance(connection, sql, id);
    }

    @Override
    public List<Customer> getAll(Connection connection) {
        String sql = "select id, name, email, birth from customers";
        return getForList(connection, sql);
    }

    @Override
    public long getCount(Connection connection) {
        String sql = "select count(*) from customers";
        return getValue(connection, sql);
    }

    @Override
    public Date getMaxBirthDay(Connection connection) {
        String sql = "select max(birth) from customers";
        return getValue(connection, sql);
    }
}
