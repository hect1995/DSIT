package publisher;

import java.util.HashSet;
import java.util.Set;
import javax.websocket.Session;
import subscriber.Subscriber;
import subscriber.SubscriberImpl;

public class PublisherImpl implements PublisherAdmin, Publisher {

  protected Set<Subscriber> subscriberSet;
  protected int numPublishers;
  protected String topic;

  public PublisherImpl(String topic) {
    subscriberSet = new HashSet<Subscriber>();
    numPublishers = 1;
    this.topic = topic;
  }

  @Override
  public int incPublishers() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int decPublishers() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void attachSubscriber(Subscriber subscriber) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void detachSubscriber(Subscriber subscriber) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void detachAllSubscribers() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void publish(String topic, String event) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
  public Subscriber subscriber(Session session) {
    for (Subscriber subscriber : subscriberSet) {
      SubscriberImpl subscriberImpl = (SubscriberImpl) subscriber;
      if (subscriberImpl.session == session) {
        return subscriber;
      }
    }
    return null;
  }
}
