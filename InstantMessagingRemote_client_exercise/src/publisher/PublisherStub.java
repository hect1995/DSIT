package publisher;

import apiREST.apiREST_Publisher;
import util.MyEvent;
// I have to complete it
public class PublisherStub implements Publisher {

  String topic;

  public PublisherStub(String topic) {
    this.topic = topic;
  }

  public void publish(String topic, String event) {
      MyEvent event_new= new MyEvent();
      event_new.content= event;
      event_new.topic= topic;
      apiREST_Publisher.publish(event_new);
    
  }

}
