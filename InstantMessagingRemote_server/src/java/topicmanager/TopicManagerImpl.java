package topicmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import publisher.Publisher;
import publisher.PublisherAdmin;
import publisher.PublisherImpl;
import subscriber.Subscriber;

public class TopicManagerImpl implements TopicManager {

  protected Map<String, PublisherAdmin> topicMap;

  public TopicManagerImpl() {
    topicMap = new HashMap<String, PublisherAdmin>();
  }

  @Override
  public Publisher addPublisherToTopic(String topic) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int removePublisherFromTopic(String topic) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean isTopic(String topic) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Set<String> topics() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean subscribe(String topic, Subscriber subscriber) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean unsubscribe(String topic, Subscriber subscriber) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
  public Publisher publisher(String topic) {
    return topicMap.get(topic);
  }
}
