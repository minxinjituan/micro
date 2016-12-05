/*************************
 * 民信微服技术框架
 * (C) Copyright minxin  Corporation 2016 All Rights Reserved.
 * 
 *************************/

package com.mx.micro.executor;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author ninghao
 * 
 *
 */
public class ExecutorFactory {
	private static Map<String, IMicroExecutor> executorMap = new HashMap();
	static {
		executorMap.put("default", new SimpleExecutor());
	}

	public static Map<String, IMicroExecutor> getExecutorMap() {
		return executorMap;
	}

	public void setExecutorMap(Map<String, IMicroExecutor> executorMap) {
		ExecutorFactory.executorMap = executorMap;
	}

	public static IMicroExecutor getExecutor(String executorName) {
		if (executorName == null || executorName == "") {
			return executorMap.get("default");
		}
		return executorMap.get(executorName);
	}
}
