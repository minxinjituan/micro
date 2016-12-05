/*************************
 * 民信微服技术框架
 * (C) Copyright minxin  Corporation 2016 All Rights Reserved.
 * 
 *************************/

package com.mx.micro.executor;

import java.util.HashMap;
import java.util.Map;

import com.nh.esb.core.INhCmdHandler;
import com.nh.esb.core.NhCmdRequest;
import com.nh.esb.core.NhCmdResult;

/**
 * 简单执行器
 * @author ninghao
 *
 */
public class SimpleExecutor implements IMicroExecutor {
	public static String rootPackage = "";

	public static String getRootPackage() {
		return rootPackage;
	}

	public void setRootPackage(String rootPackage) {
		SimpleExecutor.rootPackage = rootPackage;
	}
	public static boolean cacheFlag=true;//Ĭ�ϻ���handler����


	public void setCacheFlag(boolean cacheFlag) {
		SimpleExecutor.cacheFlag = cacheFlag;
	}
	private static Map handlerMap = new HashMap();

	public NhCmdResult execNhCmd(NhCmdRequest nhCmdRequest) throws Exception {
		String cmdName = nhCmdRequest.getCmdName();
		String className = rootPackage + ".Ws" + cmdName + "CmdHandler";
		INhCmdHandler handler=null;
		if(cacheFlag){
			handler = (INhCmdHandler) handlerMap.get(cmdName);
			if (handler == null) {
				handler = (INhCmdHandler) Class.forName(className).newInstance();
			}
		}else{
			handler = (INhCmdHandler) Class.forName(className).newInstance();
		}
		NhCmdResult nhCmdResult = new NhCmdResult();
		handler.execHandler(nhCmdRequest, nhCmdResult);
		return nhCmdResult;
	}

}
