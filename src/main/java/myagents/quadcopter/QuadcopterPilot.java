package myagents.quadcopter;

import edu.wpi.rail.jrosbridge.messages.geometry.Twist;
import edu.wpi.rail.jrosbridge.messages.geometry.Vector3;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import myagents.quadcopter.gpsutils.GPSPosition;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class QuadcopterPilot extends Agent {
    private static final long serialVersionUID = 1L;

    protected void setup(){
        System.out.println("Hello, " + getAID().getName() + " is ready");

        addBehaviour(IssueCommandToQuad.subir());
        System.out.println("enviei subir!");
        wait(60000);
        addBehaviour(IssueCommandToQuad.parar());
        System.out.println("enviei parar!");

        GPSPosition g = new GPSPosition(1,2,3);
        addBehaviour(new IssueGPSToQuad(g));

        //doDelete(); //marca como finalizado
    }



    private boolean wait(int ms){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

            }
        }, ms);
        return true;
    }




    /*Behaviour para enviar comando para a tartaruga*/
    private static class IssueCommandToQuad extends OneShotBehaviour {
        private Float[] linear;
        private Float[] angular;
        private static final long serialVersionUID = 5328804229040293382L;

        public IssueCommandToQuad(Float[] linear, Float[] angular){
            this.linear = linear;
            this.angular = angular;
        }
        public static IssueCommandToQuad parar(){
            Float[] linear = {(float) 0, (float) 0, (float) 0};
            Float[] angular = {(float) 0, (float) 0, (float) 0};
            return new IssueCommandToQuad(linear, angular);
        }
        public static IssueCommandToQuad frente(){
            Float[] linear = {(float) 2, (float) 0, (float) 0};
            Float[] angular = {(float) 0, (float) 0, (float) 0};
            return new IssueCommandToQuad(linear, angular);
        }



        public static IssueCommandToQuad subir(){
            Float[] linear = {(float) 0, (float) 0, (float) 2.0};
            Float[] angular = {(float) 0, (float) 0, (float) 0};
            return new IssueCommandToQuad(linear, angular);
        }


        public void action() {
            AID quad1 = new AID("quad1", AID.ISLOCALNAME); //nome da instância do agente dentro do JADE
            ACLMessage message = new ACLMessage(ACLMessage.REQUEST); //mensagem do tipo REQUEST
            message.addReceiver(quad1); //adiciona recipiente da mensagem

            // inicio do conteudo da mensagem
            // Float[] linear = {(float) 0,(float) 0,(float) 2.0};
            // Float[] angular = {(float) 0,(float) 0,(float) 0};
            Float[][] vectors = {this.linear, this.angular};
            //fim do conteudo da mensagem -> dois vetores

            try {
                //serializa o conteúdo na mensagem
                message.setContentObject(vectors);
            } catch (IOException e) {
                e.printStackTrace();
            }
            myAgent.send(message); //envia a mensagem para os recipiente
        }

    }

    private static class IssueGPSToQuad extends OneShotBehaviour {
        private static final long serialVersionUID = 5328804229040293382L;
        private GPSPosition gpsPosition;

        public IssueGPSToQuad(GPSPosition gpsPosition){
            this.gpsPosition = gpsPosition;
        }


        public void action() {
            AID quad1 = new AID("quad1", AID.ISLOCALNAME); //nome da instância do agente dentro do JADE
            ACLMessage message = new ACLMessage(ACLMessage.REQUEST); //mensagem do tipo REQUEST
            message.addReceiver(quad1); //adiciona recipiente da mensagem


            try {
                //serializa o conteúdo na mensagem
                message.setContentObject(gpsPosition);
            } catch (IOException e) {
                e.printStackTrace();
            }
            myAgent.send(message); //envia a mensagem para os recipiente
        }

    }

}