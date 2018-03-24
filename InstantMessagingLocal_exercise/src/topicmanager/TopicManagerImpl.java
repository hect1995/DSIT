package topicmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import publisher.Publisher;
import publisher.PublisherAdmin;
import publisher.PublisherImpl;
import subscriber.Subscriber;

public class TopicManagerImpl implements TopicManager {

    private Map<String,PublisherAdmin> topicMap; // String, which is topic,
    // is key and Publisher is value

    public TopicManagerImpl() {
        topicMap = new HashMap<String,PublisherAdmin>();
    }
    public boolean isTopic(String topic){  
        return topicMap.containsKey(topic);
    }
    public Set<String> topics(){ 
        return topicMap.keySet();
    }
    public Publisher addPublisherToTopic(String topic){ 
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
    public int removePublisherFromTopic(String topic){
        PublisherAdmin publisher_admin = (PublisherAdmin) topicMap.get(topic);
        int number_pub_bytop = publisher_admin.decPublishers();
        if (number_pub_bytop == 0){
            topicMap.remove(publisher_admin);
        }
        return number_pub_bytop;
    }
    public boolean subscribe(String topic, Subscriber subscriber){
        boolean to_return= false;
        if(this.isTopic(topic)){
            PublisherAdmin publisher_admin = (PublisherAdmin) topicMap.get(topic);
            publisher_admin.attachSubscriber(subscriber);
            to_return= true;
        }
        return to_return;
    }
    public boolean unsubscribe(String topic, Subscriber subscriber){
        boolean to_return= false;
        if(this.isTopic(topic)){
            PublisherAdmin publisher_admin = (PublisherAdmin) topicMap.get(topic);
            publisher_admin.detachSubscriber(subscriber);
            subscriber.onClose(topic, "SUBSCRIBER");
            to_return= true;
        }
        return to_return;
    }
}