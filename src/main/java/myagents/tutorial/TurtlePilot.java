package myagents.tutorial;

import java.io.IOException;

import edu.wpi.rail.jrosbridge.messages.geometry.Vector3;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class TurtlePilot extends Agent {
	private static final long serialVersionUID = 1L;
	
	protected void setup(){
		System.out.println("Hello, "+getAID().getName()+" is ready");
		addBehaviour(new IssueCommandToTurtle());
	}
	
	
	/*Behaviour para enviar comando para a tartaruga*/
	private class IssueCommandToTurtle extends OneShotBehaviour{

		private static final long serialVersionUID = 5328804229040293382L;

		@Override
		public void action() {
			AID turtle1 = new AID("turtle1", AID.ISLOCALNAME); //nome da instância do agente dentro do JADE
			ACLMessage message = new ACLMessage(ACLMessage.REQUEST); //mensagem do tipo REQUEST
			message.addReceiver(turtle1); //adiciona recipiente da mensagem
			
			//inicio do conteudo da mensagem
			Float[] linear = {(float) 2.0,(float) 2.0,(float) 0.0};
			Float[] angular = {(float) 0,(float) 0,(float) 0};
			Float[][] vectors = {linear, angular};
			//fim do conteudo da mensagem -> dois vetores
			
			try {
				//serializa o conteúdo na mensagem
				message.setContentObject(vectors); 
			} catch (IOException e) {
				e.printStackTrace();
			}
			myAgent.send(message); //envia a mensagem para os recipiente
			doDelete(); //marca o agente como "finalizado"
			
		}
		
	}
	
}
