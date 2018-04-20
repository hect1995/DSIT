package topicmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import publisher.Publisher;
import publisher.PublisherAdmin;
import publisher.PublisherImpl;
import subscriber.Subscriber;
import webSocketService.WebSocketServer;

public class TopicManagerImpl implements TopicManager {

  protected Map<String, PublisherAdmin> topicMap;

  public TopicManagerImpl() {
    topicMap = new HashMap<String, PublisherAdmin>();
  }

  @Override
  public Publisher addPublisherToTopic(String topic) {
        if(this.isTopic(topic)){
            PublisherAdmin publisher_admin = (PublisherAdmin) topicMap.get(topic);
            publisher_admin.incPublishers();
            return publisher_admin;
        }else{
            PublisherImpl publisher_adm = new PublisherImpl(topic);
            topicMap.put(topic, publisher_adm);
            return publisher_adm;
        }  
  }

  @Override
  public int removePublisherFromTopic(String topic) {
        PublisherAdmin publisher_admin = (PublisherAdmin) topicMap.get(topic);
        int number_pub_bytop = publisher_admin.decPublishers();
        if (number_pub_bytop == 0){ //no publishers for topic
            topicMap.remove(topic);
            publisher_admin.detachAllSubscribers();
        }
        return number_pub_bytop;  
  }

  @Override
  public boolean isTopic(String topic) {
    return topicMap.containsKey(topic);
  }

  @Override
  public Set<String> topics() {
    return topicMap.keySet();
  }

  @Override
  public boolean subscribe(String topic, Subscriber subscriber) {
        boolean to_return= false;
        if(this.isTopic(topic)){
            topicMap.get(topic).attachSubscriber(subscriber);
            to_return= true;
        }
        return to_return;  
  }

  @Override
  public boolean unsubscribe(String topic, Subscriber subscriber) {
        boolean to_return= false;
        if(this.isTopic(topic)){
            topicMap.get(topic).detachSubscriber(subscriber);
            subscriber.onClose(topic, "SUBSCRIBER");
            to_return= true;
        }
        return to_return;  
  }
  
  public Publisher publisher(String topic) {
    return topicMap.get(topic);
  }
}
