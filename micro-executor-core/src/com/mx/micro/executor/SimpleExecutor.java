/*************************
 * 微服务开源框架
 * (C) Copyright 民信  Corporation 2016 All Rights Reserved.
 * 
 *************************/

package com.mx.micro.executor;

import java.util.HashMap;
import java.util.Map;

import com.nh.esb.core.INhCmdHandler;
import com.nh.esb.core.NhCmdRequest;
import com.nh.esb.core.NhCmdResult;

/**
 * 内部调用反射实现执行器
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

	private static Map handlerMap = new HashMap();

	public NhCmdResult execNhCmd(NhCmdRequest nhCmdRequest) throws Exception {
		String cmdName = nhCmdRequest.getCmdName();
		INhCmdHandler handler = (INhCmdHandler) handlerMap.get(cmdName);
		if (handler == null) {
			String className = rootPackage + ".Ws" + cmdName + "CmdHandler";
			handler = (INhCmdHandler) Class.forName(className).newInstance();
		}
		NhCmdResult nhCmdResult = new NhCmdResult();
		handler.execHandler(nhCmdRequest, nhCmdResult);
		return nhCmdResult;
	}

}
