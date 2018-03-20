package demo.impl;

import demo.spec.Message;
import demo.spec.MessageWall;
import demo.spec.UserAccess;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserAccess_Impl implements UserAccess {

    private String user;
    private MessageWall messageWall;

    public UserAccess_Impl(MessageWall mw, String usr) {
        messageWall = mw;
        user = usr;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public Message getLast() {
        return messageWall.getLast();
    }

    @Override
    public int getNumber() {
        return messageWall.getNumber();
    }

    @Override
    public void put(String msg) {
        messageWall.put(user, msg);
    }

    @Override
    public boolean delete(int index) {
        return messageWall.delete(this.user, index);
    }

    @Override
    public List<Message> getAllMessages() {
        List <Message> all= messageWall.getAllMessages();
        List <Message> message_user = new ArrayList();
        Iterator<Message> iter_mes = all.iterator();
        while(iter_mes.hasNext()){
            Message message_individual= iter_mes.next();
            if(message_individual.getOwner()==this.user){
                message_user.add(message_individual);
            }
        }
        return message_user;
    }
}
