package apiREST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import java.io.*;
import java.net.*;
import util.MyLogin;

public class apiREST_User {
  public static User create_and_return_User(User user) {
    try {
      URL url = new URL(Cons.SERVER_REST+"/entity.user/create");
      HttpURLConnection ucon = (HttpURLConnection) url.openConnection();

      ucon.setRequestMethod("POST");
      ucon.setDoInput(true);
      ucon.setDoOutput(true);
      ucon.setRequestProperty("Content-Type", "application/json");
      ucon.setRequestProperty("Accept", "application/json");

      PrintWriter out = new PrintWriter(ucon.getOutputStream(), true);
      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss+02:00").create();
      String json = gson.toJson(user);
      System.out.println(json);
      out.println(json);
      out.flush();
      ucon.connect();

      BufferedReader in = new BufferedReader(new InputStreamReader(ucon.getInputStream()));
      gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss+02:00").create();
      user = gson.fromJson(in, User.class);
      return user;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static User loginUser(MyLogin login) {
    try {
      URL url = new URL(Cons.SERVER_REST+"/entity.user/login");
      HttpURLConnection ucon = (HttpURLConnection) url.openConnection();

      ucon.setRequestMethod("POST");
      ucon.setDoInput(true);
      ucon.setDoOutput(true);
      ucon.setRequestProperty("Content-Type", "application/json");
      ucon.setRequestProperty("Accept", "application/json");
      
      PrintWriter out = new PrintWriter(ucon.getOutputStream(), true);
      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss+02:00").create();
      String json = gson.toJson(login);
      System.out.println(json);
      out.println(json);
      out.flush();
      ucon.connect();

      BufferedReader in = new BufferedReader(new InputStreamReader(ucon.getInputStream()));
      gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss+02:00").create();
      User user = gson.fromJson(in, User.class);
      return user;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

}
