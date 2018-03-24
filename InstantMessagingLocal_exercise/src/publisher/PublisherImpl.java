package publisher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import subscriber.Subscriber;

public class PublisherImpl implements PublisherAdmin, Publisher {

    private Set<Subscriber> subscriberSet;
    private int numPublishers;
    private String topic;
    
    public PublisherImpl(String topic){
        subscriberSet = new HashSet<Subscriber>();
        numPublishers = 1;
        this.topic = topic;
    }
    public int incPublishers(){
        return ++numPublishers;
    }
    public int decPublishers(){
        return --numPublishers;
    }
    public void attachSubscriber(Subscriber subscriber) {
        subscriberSet.add(subscriber);
    }
    public void detachSubscriber(Subscriber subscriber) {
        subscriberSet.remove(subscriber); // not sure about this one
    }
    public void detachAllSubscribers() {
        subscriberSet.clear();
    }
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
}
