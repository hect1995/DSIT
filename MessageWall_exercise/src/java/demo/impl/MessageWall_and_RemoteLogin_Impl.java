package demo.impl;

import demo.spec.Message;
import demo.spec.MessageWall;
import demo.spec.RemoteLogin;
import demo.spec.UserAccess;
import demo.impl.UserAccess_Impl;
import demo.impl.Message_Impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MessageWall_and_RemoteLogin_Impl implements RemoteLogin, MessageWall {

    private List<Message> messages;
    
    public MessageWall_and_RemoteLogin_Impl(){
        messages= new ArrayList<Message>();
    }
    public static HashMap create_hash_map(){
        HashMap hm = new HashMap();
        // Put elements to the map
        hm.put("hector", "esteban");
        hm.put("alvaro", "enrich");
        hm.put("rachid", "boukrab");
        
        return hm;
    }

    @Override
    public UserAccess connect(String usr, String passwd) {
        HashMap users_with_access = create_hash_map();
        if (users_with_access.containsKey(usr)){
            String password = (String) users_with_access.get(usr);
            if (password.equals(passwd)){
                return new UserAccess_Impl(this, usr);
            }
        }
        return null;
    }

    @Override
    public void put(String user, String msg) {
        Message mes = new Message_Impl(user,msg);
        messages.add(mes);
    }

    @Override
    public boolean delete(String user, int index) {
        int counter=0;
        boolean bool= false;

        for(Iterator<Message> iter = messages.iterator();iter.hasNext();){
            Message mes= iter.next();
            if(index==counter){
                bool= messages.remove(mes);
                break;
            }else{
               counter++;
            }          
        }

        return bool;
    }

    @Override
    public Message getLast() {
        if(messages.isEmpty()){
            this.put("admin", "La TIN!!!");
        }
        return messages.get(messages.size()-1);
    }

    @Override
    public int getNumber() {
        return messages.size();
    }

    @Override
    public List<Message> getAllMessages() {
        return messages;
    }   
}
