/*************************
 * 微服务开源框架
 * (C) Copyright 民信  Corporation 2016 All Rights Reserved.
 * 
 *************************/

package com.mx.micro.executor.cxf;


import com.mx.micro.executor.IMicroExecutor;
import com.nh.esb.core.INhCmdService;
import com.nh.esb.core.NhCmdRequest;
import com.nh.esb.core.NhCmdResult;
import com.nh.esb.ws.NhEsbClientFactory;

/**
 * 远程调用cxf执行器实现类
 * @author ninghao
 *
 */
public class CXFExecutor implements IMicroExecutor {

	//@Override
	public NhCmdResult execNhCmd(NhCmdRequest nhCmdRequest) throws Exception {
		String sysId=nhCmdRequest.getToSysId();
		INhCmdService cmdService=NhEsbClientFactory.getClient(sysId);
		NhCmdResult result=cmdService.execNhCmd(nhCmdRequest);	
		return result;
	}
}
