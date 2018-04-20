package subscriber;

import com.google.gson.Gson;
import java.io.IOException;
import javax.websocket.Session;
import util.MySubscriptionClose;
import util.MyEvent;

/**
 *
 * @author upcnet
 */
public class SubscriberImpl implements Subscriber {

  public Session session;

  public SubscriberImpl(Session session) {
    this.session = session;
  }

  @Override
  public void onEvent(String topic, String event) { //cretae new subs
    Gson gson = new Gson();
    MyEvent myEvent = new MyEvent();
    myEvent.topic = topic;
    myEvent.content = event;
    String json = gson.toJson(myEvent); //object json of new object
    try {
      session.getBasicRemote().sendText(json);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void onClose(String topic, String cause) {
    Gson gson = new Gson();
    MySubscriptionClose mySubsClose = new MySubscriptionClose();
    mySubsClose.topic = topic;
    if (cause.equals("PUBLISHER")) {
      mySubsClose.cause = MySubscriptionClose.Cause.PUBLISHER; //publisher deixa de serho
    }
    else{
      mySubsClose.cause = MySubscriptionClose.Cause.SUBSCRIBER; //el subs es desubscriu
    }
    String json = gson.toJson(mySubsClose); //json instatntiate the close
    try {
      session.getBasicRemote().sendText(json);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}
