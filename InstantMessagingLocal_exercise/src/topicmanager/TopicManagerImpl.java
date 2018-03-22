package topicmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import publisher.Publisher;
import publisher.PublisherAdmin;
import publisher.PublisherImpl;
import subscriber.Subscriber;

public class TopicManagerImpl implements TopicManager {

    private Map<String,PublisherAdmin> topicMap;

    public TopicManagerImpl() {
        topicMap = new HashMap<String,PublisherAdmin>();
    }
    public boolean isTopic(String topic){
        //...
        return false;
    }
    public Set<String> topics(){
        //...
        return null;
    }
    public Publisher addPublisherToTopic(String topic){        
        //...
        return null;
    }
    public int removePublisherFromTopic(String topic){
        //...
        return -1;
    }
    public boolean subscribe(String topic, Subscriber subscriber){
        //...        
        return true;
    }
    public boolean unsubscribe(String topic, Subscriber subscriber){
        //...
        return true;
    }
}