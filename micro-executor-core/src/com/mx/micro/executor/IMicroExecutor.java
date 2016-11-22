/*************************
 * 微服务开源框架
 * (C) Copyright 民信  Corporation 2016 All Rights Reserved.
 * 
 *************************/

package com.mx.micro.executor;

import com.nh.esb.core.NhCmdRequest;
import com.nh.esb.core.NhCmdResult;

/**
 * 微服务执行器接口
 * @author ninghao
 *
 */
public interface IMicroExecutor {
	public NhCmdResult execNhCmd(NhCmdRequest nhCmdRequest) throws Exception;
}
