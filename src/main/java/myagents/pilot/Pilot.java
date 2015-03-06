package myagents.pilot;

import java.io.IOException;

import edu.wpi.rail.jrosbridge.messages.geometry.Vector3;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class Pilot extends Agent {
	private static final long serialVersionUID = 1L;
	
	
	protected void setup(){
		System.out.println("Hello, "+getAID().getName()+" is ready");
		addBehaviour(new IssueCommandToTurtle());
	}
	
	
	
	private class IssueCommandToTurtle extends OneShotBehaviour{

		private static final long serialVersionUID = 5328804229040293382L;

		@Override
		public void action() {
			AID turtle1 = new AID("turtle1", AID.ISLOCALNAME);
			ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
			message.addReceiver(turtle1);
			Float[] linear = {(float) 2.0,(float) 2.0,(float) 0.0};
			Float[] angular = {(float) 0,(float) 0,(float) 0};
			Float[][] vectors = {linear, angular};
			
			try {
				message.setContentObject(vectors);
			} catch (IOException e) {
				e.printStackTrace();
			}
			myAgent.send(message);
			doDelete();
			
		}
		
	}
	
}
