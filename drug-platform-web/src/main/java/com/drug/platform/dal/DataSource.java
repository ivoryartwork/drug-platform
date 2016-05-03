package com.drug.platform.dal;

import com.drug.platform.utils.Assert;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Yaochao on 2015/12/8.
 */
public class DataSource {

    private static BasicDataSource basicDataSource;

    static {
        String path = DataSource.class.getClassLoader().getResource("").getPath() + "config/jdbc.properties";
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(path));
            Properties prop = new Properties();
            try {
                prop.load(in);
                basicDataSource = new BasicDataSource();
                basicDataSource.setDriverClassName(prop.getProperty("jdbc.driverClassName"));
                basicDataSource.setUrl(prop.getProperty("jdbc.url"));
                basicDataSource.setUsername(prop.getProperty("jdbc.username"));
                basicDataSource.setPassword(prop.getProperty("jdbc.password"));
                basicDataSource.setInitialSize(Integer.parseInt(prop.getProperty("jdbc.initialSize")));
                basicDataSource.setMaxActive(Integer.parseInt(prop.getProperty("jdbc.maxActive")));
                basicDataSource.setMinIdle(Integer.parseInt(prop.getProperty("jdbc.minIdle")));
                basicDataSource.setMaxIdle(Integer.parseInt(prop.getProperty("jdbc.maxIdle")));
                basicDataSource.setMaxWait(Integer.parseInt(prop.getProperty("jdbc.maxWait")));
                basicDataSource.setTestOnBorrow(Boolean.parseBoolean(prop.getProperty("jdbc.testOnBorrow")));
                basicDataSource.setTestOnReturn(Boolean.parseBoolean(prop.getProperty("jdbc.testOnReturn")));
                basicDataSource.setTestWhileIdle(Boolean.parseBoolean(prop.getProperty("jdbc.testWhileIdle")));
                basicDataSource.setValidationQuery(prop.getProperty("jdbc.validationQuery"));
                basicDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(prop.getProperty("jdbc.timeBetweenEvictionRunsMillis")));
                basicDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(prop.getProperty("jdbc.minEvictableIdleTimeMillis")));
            } catch (IOException e) {

            }
        } catch (FileNotFoundException e) {

        }
    }

    public static Connection getConnection() {
        try {
            if (Assert.notNull(basicDataSource)) {
                return basicDataSource.getConnection();
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
