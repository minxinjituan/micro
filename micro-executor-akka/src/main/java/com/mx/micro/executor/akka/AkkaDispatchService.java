package com.mx.micro.executor.akka;


import com.nh.esb.core.NhCmdRequest;
import com.nh.esb.core.NhCmdResult;

import akka.actor.UntypedActor;

public class AkkaDispatchService extends UntypedActor {


	@Override
	public void onReceive(Object message) throws Exception {

		try{
		NhCmdRequest nhCmdRequest=(NhCmdRequest) message;
		NhCmdResult nhCmdResult=AkkaExecutor.microExecutor.execNhCmd(nhCmdRequest);
		getSender().tell(nhCmdResult, getSelf());

		}finally{
			getContext().stop(this.getSelf());
		}
	}

}
