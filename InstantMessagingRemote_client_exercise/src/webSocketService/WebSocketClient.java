package webSocketService;

import apiREST.Cons;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import subscriber.Subscriber;
import util.MyEvent;
import util.MySubscriptionRequest;
import util.MySubscriptionClose;
import util.MySubscriptionClose.Cause;

@ClientEndpoint
public class WebSocketClient {

  static Map<String, Subscriber> subscriberMap;
  static Session session;

  public static void newInstance() { //newInstance per generar connexio TCP amb WebSocketServer
    subscriberMap = new HashMap<String, Subscriber>();
    try {
      WebSocketContainer container = ContainerProvider.getWebSocketContainer();
      session = container.connectToServer(WebSocketClient.class,
        URI.create(Cons.SERVER_WEBSOCKET)); //la direccio acava amb /ws que es la de server
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //only one subscriber per topic allowed:
  public static synchronized void addSubscriber(String topic_name, Subscriber subscriber) {
        subscriberMap.put(topic_name, subscriber);   
        // TODO:enviar i crear JSON
        MySubscriptionRequest add_inserver = new MySubscriptionRequest();
        add_inserver.topic = topic_name;
        add_inserver.type = MySubscriptionRequest.Type.ADD;
        Gson gson = new Gson();
        String json = gson.toJson(add_inserver);
        try {
          session.getBasicRemote().sendText(json);
        } catch (IOException ex) {
          ex.printStackTrace();
        }   
  }

  public static synchronized void removeSubscriber(String topic_name) { //avisar al server que testas anant, creo objecte json i envio a server
        MySubscriptionRequest remove_inserver = new MySubscriptionRequest();
        remove_inserver.topic = topic_name;
        remove_inserver.type = MySubscriptionRequest.Type.REMOVE;
        Gson gson = new Gson();
        String json = gson.toJson(remove_inserver);
        try {
          session.getBasicRemote().sendText(json);
        } catch (IOException ex) {
          ex.printStackTrace();
        }  
  }

  public static void close() {
    try {
      session.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @OnMessage
  public void onMessage(String message) {
      System.out.println(message);
    Gson gson = new Gson();
    MyEvent myEvent = gson.fromJson(message, MyEvent.class);
    String content = myEvent.content; //nomes ho te el de crear subscriber

    //message to notify subscription close:
    if (content == null) { //si han cridat el close nomes te content i potser per Publisher o subscriber
        MySubscriptionClose mySubs = gson.fromJson(message, MySubscriptionClose.class);
        String words = mySubs.topic;
        Cause cause = mySubs.cause;
        System.out.println(words);
        Subscriber subs= subscriberMap.get(words);
        subs.onClose(words, cause.name()); 
        subscriberMap.remove(words);
    } 
    //ordinary message from topic:
    else {
       String topic = myEvent.topic;
       Subscriber subs1= subscriberMap.get(topic);
       subs1.onEvent(topic,content);    
    }
  }

}
