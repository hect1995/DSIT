package entity;

import entity.Topic;
import entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-31T16:47:17")
@StaticMetamodel(Publisher.class)
public class Publisher_ { 

    public static volatile SingularAttribute<Publisher, Topic> topic;
    public static volatile SingularAttribute<Publisher, Integer> id;
    public static volatile SingularAttribute<Publisher, User> user;

}