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
	
	//JADE things
	private static final long serialVersionUID = 1L;
	
	//ROS things
	private Ros ros;
	private Topic turtleTopic;
	
	
	
	public TurtleAgent(){
		ros = new Ros("localhost");
	    ros.connect();
	    turtleTopic = new Topic(ros, "/turtle1/cmd_vel", "geometry_msgs/Twist");
	    turtleTopic.subscribe(new TopicCallback() {
			public void handleMessage(Message arg0) {
				//do nothing
			}
		});
	
	}
	
	private void moveTurtle(Vector3 linear, Vector3 angular){
		Message toSend = new Twist(linear, angular);
	    turtleTopic.publish(toSend);
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void moveTurtle(Float[] linear, Float[] angular){
		Vector3 linearVector = new Vector3(linear[0],linear[1],linear[2]);
		Vector3 angularVector = new Vector3(angular[0],angular[1],angular[2]);
		this.moveTurtle(linearVector, angularVector);
		
	}
	
	
	protected void setup(){
		addBehaviour(new OfferRequestsServer());
		System.out.println("Hello, "+getAID().getName()+" is ready");
	}

	
	private class OfferRequestsServer extends CyclicBehaviour {
		@Override
		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
			ACLMessage msg = myAgent.receive(mt);
			if (msg != null){
				try {
					Float[][] content = (Float[][]) msg.getContentObject();
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
