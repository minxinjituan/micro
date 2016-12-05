package com.mx.dic.holder;

import javax.sql.DataSource;

public class DataSourceHolder {
public static DataSource dataSource=null;

public static DataSource getDataSource() {
	return dataSource;
}

public void setDataSource(DataSource dataSource) {
	DataSourceHolder.dataSource = dataSource;
}

}
