package src.dao;

import src.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 用于规范针对于 customers 表的常用操作
 *
 * @version: 1.0
 * @author: cpprto
 * @date: 2020/9/23 17:36
 */
public interface CustomerDAO {
    /* 将 customer 对象插入到 customers 表中 */
    void insert(Connection connection, Customer customer);

    /* 针对指定的 id，删除表中的一条记录 */
    void deleteById(Connection connection, int id);

    /* 针对内存中的 customer 对象，修改 customers 表中指定的记录 */
    void update(Connection connection, Customer customer);

    /* 针对指定的 id 查询得到对应的 customer 对象 */
    Customer getCustomerById(Connection connection, int id);

    /* 查询表中所有记录构成的集合 */
    List<Customer> getAll(Connection connection);

    /* 返回数据表中的数据的条目数 */
    long getCount(Connection connection);

    /* 返回数据表中距今最近的生日日期 */
    Date getMaxBirthDay(Connection connection);
}
