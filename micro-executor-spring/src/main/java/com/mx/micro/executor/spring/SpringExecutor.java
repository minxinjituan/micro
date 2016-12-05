
/*************************
 * 民信微服技术框架
 * (C) Copyright minxin  Corporation 2016 All Rights Reserved.
 * 
 *************************/
package com.mx.micro.executor.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.mx.micro.executor.IMicroExecutor;
import com.nh.esb.core.INhCmdConst;
import com.nh.esb.core.INhCmdHandler;
import com.nh.esb.core.NhCmdRequest;
import com.nh.esb.core.NhCmdResult;

/**
 * spingbean执行器
 * @author ninghao
 *
 */
public class SpringExecutor implements IMicroExecutor,ApplicationContextAware {
	private static ApplicationContext context;
	@Override
	public NhCmdResult execNhCmd(NhCmdRequest nhCmdRequest) throws Exception {
		NhCmdResult result=new NhCmdResult();
		result.setRequestId(nhCmdRequest.getRequestId());

		String cmdName=nhCmdRequest.getCmdName();
		if(cmdName==null || "".equals(cmdName)){
			result.setResultStatus(INhCmdConst.STATUS_ERROR);
			result.setResultCode("cmdname_null");
			return result;
		}

		String handlerName="ws"+cmdName+"CmdHandler";
		INhCmdHandler handler=null;
		try{
			handler=(INhCmdHandler) getContext().getBean(handlerName);
		}catch(Exception ex){

			result.setResultStatus(INhCmdConst.STATUS_ERROR);
			result.setResultCode("handler_not_found");
			return result;			
		}
		try{
			handler.execHandler(nhCmdRequest,result);
		}catch(Exception ex2){
			result.setResultStatus(INhCmdConst.STATUS_ERROR);
			result.setResultCode("handler_exec_error");
			return result;	
		}
		
		return result;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		SpringExecutor.context=context;
		
	}
	
	public static ApplicationContext getContext(){
		  return context;
	}

}
