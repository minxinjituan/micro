/*************************
 * ΢����Դ���
 * (C) Copyright ����  Corporation 2016 All Rights Reserved.
 * 
 *************************/

package com.mx.micro.executor;

import com.nh.esb.core.NhCmdRequest;
import com.nh.esb.core.NhCmdResult;

/**
 * ΢����ִ�����ӿ�
 * @author ninghao
 *
 */
public interface IMicroExecutor {
	public NhCmdResult execNhCmd(NhCmdRequest nhCmdRequest) throws Exception;
}
