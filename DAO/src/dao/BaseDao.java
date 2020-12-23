package src.dao;

import src.util.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装针对数据表的通用操作
 *
 * @version: 1.0
 * @author: cpprto
 * @date: 2020/9/23 16:59
 */
public abstract class BaseDao<T> {
    private Class<T> cls;

    {
        /* 获取带泛型的父类 */
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        /* 获取父类的泛型 */
        Type[] types = parameterizedType.getActualTypeArguments();
        cls = (Class<T>) types[0];
    }

    public int update(Connection connection, String sql, Object ...args) {
        /**
         * 通用的增删改操作
         *
         * @param: connection
         * @param: sql
         * @param: args
         * @return: int
         * @author: cpprto
         * @date: 2020/9/23 17:00
         */
        PreparedStatement pStatement = null;

        try {
            pStatement = connection.prepareCall(sql);

            for (int i = 0; i < args.length; ++i) {
                pStatement.setObject(i + 1, args[i]);
            }

            return pStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, pStatement);
        }

        return 0;
    }

    public T getInstance(Connection connection, String sql, Object ...args) {
        /**
         * 通用的查询操作，用于返回数据表中的一条记录
         *
         * @param: connection
         * @param: cls
         * @param: sql
         * @param: args
         * @return: T
         * @author: cpprto
         * @date: 2020/9/23 17:09
         */
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;

        try {
            pStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; ++i) {
                pStatement.setObject(i + 1, args[i]);
            }

            resultSet = pStatement.executeQuery();
            ResultSetMetaData resultSetMetaDate = resultSet.getMetaData();
            int columnCount = resultSetMetaDate.getColumnCount();

            if (resultSet.next()) {
                T t = cls.newInstance();
                for (int i = 0; i < columnCount; ++i) {
                    String columnLabel = resultSetMetaDate.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);

                    Field field = cls.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }

                return t;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, pStatement, resultSet);
        }

        return null;
    }

    public List<T> getForList(Connection connection, String sql, Object ...args) {
        /**
         * 通用的查询操作，用于返回数据表中的多条记录构成的集合
         *
         * @param: connection
         * @param: cls
         * @param: sql
         * @param: args
         * @return: java.util.List<T>
         * @author: cpprto
         * @date: 2020/9/23 17:19
         */

        PreparedStatement pStatement = null;
        ResultSet resultSet = null;

        try {
            pStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; ++i) {
                pStatement.setObject(i + 1, args[i]);
            }

            resultSet = pStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            ArrayList<T> list = new ArrayList<>();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                T t = cls.newInstance();
                for (int i = 0; i < columnCount; ++i) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);

                    Field field = cls.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }

            return list;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, pStatement, resultSet);
        }

        return null;
    }

    public <E> E getValue(Connection connection, String sql, Object ...args) {
        /**
         * 查询特殊值的通用方法
         *
         * @param: connection
         * @param: sql
         * @param: args
         * @return: E
         * @author: cpprto
         * @date: 2020/9/23 17:34
         */
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;

        try {
            pStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; ++i) {
                pStatement.setObject(i + 1, args[i]);
            }

            resultSet = pStatement.executeQuery();

            if (resultSet.next()) {
                return (E) resultSet.getObject(1);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, pStatement, resultSet);
        }

        return null;
    }
}