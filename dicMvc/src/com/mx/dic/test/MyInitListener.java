package com.mx.dic.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import com.mx.dic.holder.DataSourceHolder;
import com.mx.dic.test.DataSourceFactory;


public class MyInitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {

		DataSource dataSource=DataSourceFactory.createDataSource();
		DataSourceHolder.dataSource=dataSource;
		
	}

}
