package com.mx.dic.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.json.JSONObject;

import com.mx.dic.holder.DataSourceHolder;
import com.nh.esb.core.INhCmdHandler;
import com.nh.esb.core.NhCmdRequest;
import com.nh.esb.core.NhCmdResult;

public class WsCreateDicNameCmdHandler implements INhCmdHandler {

	public void execHandler(NhCmdRequest request, NhCmdResult result)  {
		String cmdData=request.getCmdData();
		Map inMap=(Map) JSONObject.toBean(JSONObject.fromObject(cmdData),Map.class);
		String uuid=(String) inMap.get("uuid");
		String dicId=(String) inMap.get("dicId");
		String dicName=(String) inMap.get("dicName");
		String sysId=(String) inMap.get("sysId");
		String remark=(String) inMap.get("remark");
		DataSource dataSource=DataSourceHolder.getDataSource();
		Connection conn=null;
		try{
		conn=dataSource.getConnection();
		String insertSql="insert into common_dic_name(uuid,dicId,dicName,sysId,remark) "+
				" values(?,?,?,?,?)";
		PreparedStatement statement=conn.prepareStatement(insertSql);
		statement.setString(1, uuid);
		statement.setString(2, dicId);
		statement.setString(3, dicName);
		statement.setString(4, sysId);
		statement.setString(5, remark);
		statement.execute();
		}catch(Exception e){
			
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

}
