/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thermostat;
import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author e1901451
 */
public class THermostat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MqttException {
        // TODO code application logic here
        
        //int temperature;
        // Creation un client
        MqttClient client = new 
            MqttClient("tcp://localhost:1883",      // Adr. et port du broker
                        "Thermostat");      // Nom du client (tous les clients doivent 
                            // avoir des noms differents)
        client.setCallback(new MqttCallback() {
                        @Override
                        public void connectionLost(Throwable cause) {}
                   // Callback pour la reception des messages
                        @Override
                        public void messageArrived(String topic,
                                MqttMessage message)throws Exception {
                            int temp=Integer.parseInt(new String(message.getPayload()));
                            System.out.println(temp);
                            if(temp<=15)
                            {
                                //envoie message eteins radiateur
                                MqttMessage messagepub = new MqttMessage();
                                messagepub.setPayload("ON".getBytes());
                                // Publication du message avec le topic "pahodemo/test"
                                client.publish("pahodemo/thermostat", messagepub);

                            }
                            else if(temp>15)
                            {
                                //envoie message allume radiateur 
                                MqttMessage messagepub = new MqttMessage();
                                messagepub.setPayload("OFF".getBytes());
                                // Publication du message avec le topic "pahodemo/test"
                                client.publish("pahodemo/thermostat", messagepub);
                            }
                        }
                        @Override
                        public void deliveryComplete(IMqttDeliveryToken token) {}
                    });
        // Connexion du client
        client.connect();
        // Definition des topics pour la reception des messages, un thread est cree
        client.subscribe("pahodemo/thermometre");
        //pour ne pas déconnecter tout de suite
        while(true)
        {
            
        }
        
        // Arret de la connexion, le thread s’arrete
        //client.disconnect();

    }
    
}
