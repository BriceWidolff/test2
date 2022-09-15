/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sub;
import org.eclipse.paho.client.mqttv3.*;
/**
 *
 * @author e1901451
 */
public class Sub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MqttException {
        // TODO code application logic here
        // Creation un client
        MqttClient client3 = new 
            MqttClient("tcp://localhost:1883",      // Adr. et port du broker
                        "Radiateur");      // Nom du client (tous les clients doivent 
                            // avoir des noms differents)
         client3.setCallback(new MqttCallback() {
                        @Override
                        public void connectionLost(Throwable cause) {}
                   // Callback pour la reception des messages
                        @Override
                        public void messageArrived(String topic,
                                MqttMessage message)throws Exception {
                            System.out.println(new String(message.getPayload()));
                        }
                        @Override
                        public void deliveryComplete(IMqttDeliveryToken token) {}
                    });
        // Connexion du client
        client3.connect();
        // Definition des topics pour la reception des messages, un thread est cree
        client3.subscribe("pahodemo/thermostat");
        while(true)
        {
            
        }
        // Arret de la connexion, le thread s’arrete
        //client3.disconnect();

    }
    
}
