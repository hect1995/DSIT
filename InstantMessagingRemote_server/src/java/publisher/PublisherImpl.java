package publisher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.websocket.Session;
import subscriber.Subscriber;
import subscriber.SubscriberImpl;
import util.MySubscriptionClose;

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
    return ++numPublishers;
  }

  @Override
  public int decPublishers() {
    return --numPublishers;
  }

  @Override
  public void attachSubscriber(Subscriber subscriber) {
    subscriberSet.add(subscriber);
  }

  @Override
  public void detachSubscriber(Subscriber subscriber) {
    subscriberSet.remove(subscriber); // not sure about this one
  }

  @Override
  public void detachAllSubscribers() {
      Iterator iter = subscriberSet.iterator();
      while(iter.hasNext()){
          Subscriber subs = (Subscriber) iter.next();
          subs.onClose(topic, MySubscriptionClose.Cause.PUBLISHER.name());
      }
    subscriberSet.clear();
  }

  @Override
  public void publish(String topic, String event) {
        Iterator itr = this.subscriberSet.iterator();
        if(event.equals("PUBLISHER")){
            while(itr.hasNext()) {
                Subscriber subs = (Subscriber) itr.next();
                subs.onClose(topic, event);
            }
            this.detachAllSubscribers();
            this.numPublishers = 0;
        }
        else{
            while(itr.hasNext()) {
                Subscriber subs = (Subscriber) itr.next();
                subs.onEvent(topic, event);
            } 
        }  
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
