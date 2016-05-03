package com.drug.platform.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * Created by Yaochao on 2015/12/8.
 * 拥有事务
 */
public abstract class JDBCTransCallBack<T> {

    protected Connection con;

    protected PreparedStatement statement;

    protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public abstract T doInJDBCCallBack() throws SQLException;

    public T execute() {
        con = DataSource.getConnection();
        try {
            con.setAutoCommit(false);
            T t = doInJDBCCallBack();
            con.commit();
            return t;
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
