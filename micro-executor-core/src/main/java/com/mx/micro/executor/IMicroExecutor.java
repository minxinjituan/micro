/*************************
 * 民信微服技术框架
 * (C) Copyright minxin  Corporation 2016 All Rights Reserved.
 * 
 *************************/

package com.mx.micro.executor;

import com.nh.esb.core.NhCmdRequest;
import com.nh.esb.core.NhCmdResult;

/**
 * 执行器接口
 * @author ninghao
 *
 */
public interface IMicroExecutor {
	public NhCmdResult execNhCmd(NhCmdRequest nhCmdRequest) throws Exception;
}
