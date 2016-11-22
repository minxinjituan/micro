package com.mx.dic.test;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSourceFactory {
	private DataSourceFactory() {
	};

	private static DataSource dataSource = null;

	public static DataSource createDataSource() {
		if (dataSource != null) {
			return dataSource;
		}
		Properties prop = new Properties();
		try {
			prop.load(DataSourceFactory.class
					.getResourceAsStream("/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("pass");
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pass);
		ds.setInitialSize(5);
		ds.setMaxActive(10);
		ds.setMaxIdle(2);
		ds.setMaxWait(10000);
		dataSource = ds;
		return dataSource;
	}
}
