package topicmanager;

import apiREST.apiREST_TopicManager;
import java.util.Set;
import publisher.Publisher;
import publisher.PublisherStub;
import subscriber.Subscriber;
import webSocketService.WebSocketClient;

public class TopicManagerStub implements TopicManager {

  public String user;

  public TopicManagerStub(String user) {
    WebSocketClient.newInstance();
    this.user = user;
  }

  public void close() {
    WebSocketClient.close();
  }

  public Publisher addPublisherToTopic(String topic) {

    //...
    return null;

  }

  public int removePublisherFromTopic(String topic) {

    //...
    return -1;

  }

  public boolean isTopic(String topic_name) {

    //...
    return false;

  }

  public Set<String> topics() {

    //...
    return null;

  }

  public boolean subscribe(String topic, Subscriber subscriber) {

    //...
    return true;

  }

  public boolean unsubscribe(String topic, Subscriber subscriber) {

    //...
    return true;

  }

}
