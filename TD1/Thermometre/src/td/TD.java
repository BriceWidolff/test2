/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package td;
import org.eclipse.paho.client.mqttv3.*;
import java.util.Random;
//import java.util.concurrent.TimeUnit;

/**
 *
 * @author e1901451
 */
public class TD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MqttException, InterruptedException {
        // TODO code application logic here
        // Creer un client
        // localhost:1883  Adr. et port du broker
        // client_nom_1 : Nom du client
        MqttClient client2 = new 
                 MqttClient("tcp://localhost:1883",
                  "Thermometre");
        // Connexion du client au broker
        client2.connect();
        // Creation d'un message
        Random random = new Random();
        int nb;
        while(true)
        {
            nb = random.nextInt(30);
            MqttMessage message = new MqttMessage();
            message.setPayload(String.valueOf(nb).getBytes());
            // Publication du message avec le topic "pahodemo/test"
            client2.publish("pahodemo/thermometre", message);
            //TimeUnit.SECONDS.sleep(1);
            Thread.sleep(1000);
        }
        // Le client se deconnecte du broker
        //client2.disconnect();

    }
    
}
