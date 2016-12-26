/*************************
 * 民信微服技术框架
 * (C) Copyright minxin  Corporation 2016 All Rights Reserved.
 * 
 *************************/

package com.mx.micro.executor.cxf;


import com.mx.micro.executor.IMicroExecutor;
import com.nh.esb.core.INhCmdService;
import com.nh.esb.core.NhCmdRequest;
import com.nh.esb.core.NhCmdResult;
import com.nh.esb.core.NhEsbAddress;
import com.nh.esb.ws.NhEsbClientFactory;

/**
 * cxf框架webservice执行器
 * @author ninghao
 *
 */
public class CXFExecutor implements IMicroExecutor {

	@Override
	public NhCmdResult execNhCmd(NhCmdRequest nhCmdRequest) throws Exception {
		String sysId=nhCmdRequest.getToSysId();
		INhCmdService cmdService=NhEsbClientFactory.getClient(sysId);
		NhCmdResult result=cmdService.execNhCmd(nhCmdRequest);	
		return result;
	}
	
	public NhCmdResult execNhCmd(NhCmdRequest nhCmdRequest,NhEsbAddress address) throws Exception {
		INhCmdService cmdService=NhEsbClientFactory.getClient(address);
		NhCmdResult result=cmdService.execNhCmd(nhCmdRequest);	
		return result;
	}
}
