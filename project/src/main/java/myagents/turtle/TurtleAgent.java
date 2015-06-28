package myagents.turtle;
import edu.wpi.rail.jrosbridge.Ros;
import edu.wpi.rail.jrosbridge.Topic;
import edu.wpi.rail.jrosbridge.callback.TopicCallback;
import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.geometry.Twist;
import edu.wpi.rail.jrosbridge.messages.geometry.Vector3;
import jade.content.schema.facets.RegexFacet;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.RequestFIPAServiceBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class TurtleAgent extends Agent{
	private static final long serialVersionUID = 1L;
	
	private Ros ros;
	private Topic turtleTopic;
	
	
	
	public TurtleAgent(){
		//inicia o ROS com suas configurações
		ros = new Ros("localhost"); //localização do ROS
	    	ros.connect(); //conecta ao ROS
	    	turtleTopic = new Topic(ros, "/turtle1/cmd_vel", "geometry_msgs/Twist"); //define o topico, com o tipo de mensagem e o recipiente
	    	turtleTopic.subscribe(new TopicCallback() { //assina o tópico e define uma função para ser executada quando for respondidade
			public void handleMessage(Message arg0) {
				//do nothing
			}
		});
	
	}
	
	private void moveTurtle(Vector3 linear, Vector3 angular){ //envia a mensagem para o ROS
		Message toSend = new Twist(linear, angular);
	    turtleTopic.publish(toSend);
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void moveTurtle(Float[] linear, Float[] angular){ //envia a mensagem para o ROS
		Vector3 linearVector = new Vector3(linear[0],linear[1],linear[2]);
		Vector3 angularVector = new Vector3(angular[0],angular[1],angular[2]);
		this.moveTurtle(linearVector, angularVector);
		
	}
	
	
	protected void setup(){
		addBehaviour(new OfferRequestsServer()); //adiciona comportamento ao agente
		System.out.println("Hello, "+getAID().getName()+" is ready");
	}

	//classe que define o comportamento de receber mensagens
	private class OfferRequestsServer extends CyclicBehaviour {
		@Override
		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);//apenas recebe mensagens do tipo REQUEST
			ACLMessage msg = myAgent.receive(mt); //recebe a mensagem, remove da pilha
			if (msg != null){
				try {
					//deserializa o conteúdo da mensagem
					Float[][] content = (Float[][]) msg.getContentObject(); 
					//chama o metódo de enviar mensagem para o ROS
					moveTurtle(new Vector3(content[0][0],content[0][1],content[0][2]), new Vector3(content[1][0],content[1][1],content[1][2]));
				} catch (UnreadableException e) {
					e.printStackTrace();
				}
			}
			else{
				block();
			}
		}
	
	}
	

}
