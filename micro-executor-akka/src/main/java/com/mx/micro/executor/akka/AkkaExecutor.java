package com.mx.micro.executor.akka;



import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;

import com.mx.micro.executor.IMicroExecutor;
import com.nh.esb.core.NhCmdRequest;
import com.nh.esb.core.NhCmdResult;


public class AkkaExecutor implements IMicroExecutor{
	public static ActorSystem system = ActorSystem.create("micro");
	public static IMicroExecutor microExecutor=null;
	
	public static IMicroExecutor getMicroExecutor() {
		return microExecutor;
	}

	public void setMicroExecutor(IMicroExecutor microExecutor) {
		AkkaExecutor.microExecutor = microExecutor;
	}
        

	public NhCmdResult execNhCmd(NhCmdRequest nhCmdRequest) throws Exception {
        Timeout timeout = new Timeout(Duration.create(60, "seconds"));
        ActorRef ref=system.actorOf(Props.create(AkkaDispatchService.class));
        Future<Object> future = Patterns.ask(ref, nhCmdRequest,timeout);
        NhCmdResult nhCmdResult = (NhCmdResult) Await.result(future, timeout.duration());
		return nhCmdResult;
	}

}
