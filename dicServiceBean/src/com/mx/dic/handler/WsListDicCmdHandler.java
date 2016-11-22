package com.mx.dic.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mx.dic.test.DataSourceFactory;
import com.nh.esb.core.INhCmdConst;
import com.nh.esb.core.INhCmdHandler;
import com.nh.esb.core.NhCmdRequest;
import com.nh.esb.core.NhCmdResult;

public class WsListDicCmdHandler implements INhCmdHandler{

	public void execHandler(NhCmdRequest request, NhCmdResult result)
			 {
		
		DataSource ds=DataSourceFactory.createDataSource();
		Connection conn=null;
		try{
			conn=ds.getConnection();
			String sql="select * from common_dic_name";
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			JSONArray array=resultSetToJson(rs);
			Map retMap=new HashMap();
			retMap.put("size", array.size());
			retMap.put("rows", array.toString());
			String retStr=JSONObject.fromObject(retMap).toString();
			result.setResultData(retStr);
			System.out.println(retStr);
		}catch(Exception e){
			result.setResultStatus(INhCmdConst.STATUS_ERROR);
		}finally{
		
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn=null;
			}
		}
		
	}
	private JSONArray resultSetToJson(ResultSet rs) throws Exception  
	{  
	   // json数组  
	   JSONArray array = new JSONArray();  
	    
	   // 获取列数  
	   ResultSetMetaData metaData = rs.getMetaData();  
	   int columnCount = metaData.getColumnCount();  
	    
	   // 遍历ResultSet中的每条数据  
	    while (rs.next()) {  
	        JSONObject jsonObj = new JSONObject();  
	         
	        // 遍历每一列  
	        for (int i = 1; i <= columnCount; i++) {  
	            String columnName =metaData.getColumnLabel(i);  
	            String value = rs.getString(columnName);  
	            jsonObj.put(columnName, value);  
	        }   
	        array.add(jsonObj);  
	    }  
	    
	   return array;  
	} 
}
