package com.drug.platform.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Yaochao on 2015/12/8.
 */
public abstract class JDBCCallBack<T> {

    protected Connection con;

    protected PreparedStatement statement;

    protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public abstract T doInJDBCCallBack() throws SQLException, ParseException;

    public T execute() {
        con = DataSource.getConnection();
        try {
            con.setAutoCommit(true);
            return doInJDBCCallBack();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
