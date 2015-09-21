package myagents.quadcopter;
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
import myagents.quadcopter.gpsutils.GPSPosition;
import myagents.quadcopter.gpsutils.GPSUtils;

public class QuadcopterAgent extends Agent{
    private static final long serialVersionUID = 1L;

    private Ros ros;
    private Topic quadTopic;



    public QuadcopterAgent(){
        //inicia o ROS com suas configurações
        ros = new Ros("localhost"); //localização do ROS
        ros.connect(); //conecta ao ROS
        quadTopic = new Topic(ros, "/cmd_vel", "geometry_msgs/Twist"); //define o topico, com o tipo de mensagem e o recipiente
        quadTopic.subscribe(new TopicCallback() { //assina o tópico e define uma função para ser executada quando for respondidade
            public void handleMessage(Message arg0) {
                //do nothing
                System.out.println("Mensagem recebida: " + arg0);
            }
        });

    }

    private void moveQuad(Vector3 linear, Vector3 angular){ //envia a mensagem para o ROS
        Message toSend = new Twist(linear, angular);
        quadTopic.publish(toSend);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    private void moveQuad(Float[] linear, Float[] angular){ //envia a mensagem para o ROS
        Vector3 linearVector = new Vector3(linear[0],linear[1],linear[2]);
        Vector3 angularVector = new Vector3(angular[0],angular[1],angular[2]);
        this.moveQuad(linearVector, angularVector);

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

                    if (msg.getContentObject().getClass() == GPSPosition.class){
                        //GPSUtils.generateWay((GPSPosition) msg.getContentObject(), getMyGPSPosition());
                        GPSPosition gp = (GPSPosition) msg.getContentObject();
                        System.out.println(gp);
                        System.out.println("recebi um GPSPostion!");
                    }
                    else {
                        //deserializa o conteúdo da mensagem
                        System.out.println("recebi um array de float");
                        Float[][] content = (Float[][]) msg.getContentObject();

                        //chama o metódo de enviar mensagem para o ROS
                        moveQuad(new Vector3(content[0][0], content[0][1], content[0][2]), new Vector3(content[1][0], content[1][1], content[1][2]));
                    }


                } catch (UnreadableException e) {
                    e.printStackTrace();
                }
            }
            else{
                block();
            }
        }

    }

    private void getMyGPSPosition() {
        final Message[] m = new Message[1];
        Topic gpsTopic = new Topic(ros, "/fix", "std_msgs/String");

        gpsTopic.subscribe(new TopicCallback() {
            public void handleMessage(Message message) {
                System.out.println(message);
                m[0] = message;
            }
        });
        gpsTopic.unsubscribe();

        System.out.println(m[0]);
    }


}