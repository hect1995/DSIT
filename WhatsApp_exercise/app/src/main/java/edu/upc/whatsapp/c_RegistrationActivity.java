package edu.upc.whatsapp;

import edu.upc.whatsapp.comms.RPC;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import edu.upc.whatsapp.service.PushService;
import entity.User;
import entity.UserInfo;

public class c_RegistrationActivity extends Activity implements View.OnClickListener {

  _GlobalState globalState;
  ProgressDialog progressDialog;
  User user;
  OperationPerformer operationPerformer;

  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    globalState = (_GlobalState)getApplication();
    setContentView(R.layout.c_registration);
    ((Button) findViewById(R.id.editregistrationButton)).setOnClickListener(this); // R is res
  }

  public void onClick(View arg0) {
    if (arg0 == findViewById(R.id.editregistrationButton)) {
      EditText loginname_aux = findViewById(R.id.textToFill);
      String loginname = loginname_aux.getText().toString();
      EditText passname_aux = findViewById(R.id.textToFillPassw);
      String passname = passname_aux.getText().toString();
      EditText name_aux = findViewById(R.id.textToFillName);
      String name = name_aux.getText().toString();
      EditText surname_aux = findViewById(R.id.textToFillSurname);
      String surname = surname_aux.getText().toString();

      EditText email_aux = findViewById(R.id.textToFillEmail);
      String email = email_aux.getText().toString();


      user = new User();
      user.setPassword(passname);
      user.setLogin(loginname);
      user.setEmail(email);
      UserInfo ui = new UserInfo();
      ui.setName(name);
      ui.setSurname(surname);
      user.setUserInfo(ui);


      progressDialog = ProgressDialog.show(this, "RegistrationActivity", "Registering for service...");
      // if there's still a running thread doing something, we don't create a new one
      if (operationPerformer == null) {
        operationPerformer = new OperationPerformer();
        operationPerformer.start();
      }
    }
  }

  private class OperationPerformer extends Thread {

    @Override
    public void run() {
      Message msg = handler.obtainMessage();
      Bundle b = new Bundle();

      //...
      UserInfo info = RPC.registration(user);
      b.putSerializable("userInfo", info);

      globalState.my_user = info;

      msg.setData(b);
      handler.sendMessage(msg);
    }
  }

  Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {

      operationPerformer = null;
      progressDialog.dismiss();

      UserInfo userInfo = (UserInfo) msg.getData().getSerializable("userInfo");

      if (userInfo.getId() >= 0) {
        toastShow("Registration successful");

        //...
        // user.setUserInfo(userInfo);
        Intent int_aux = new Intent(c_RegistrationActivity.this,d_UsersListActivity.class);

        finish();
      }
      else if (userInfo.getId() == -1) {
        toastShow("Registration unsuccessful,\nlogin already used by another user");
      }
      else if (userInfo.getId() == -2) {
        toastShow("Not registered, connection problem due to: " + userInfo.getName());
        System.out.println("--------------------------------------------------");
        System.out.println("error!!!");
        System.out.println(userInfo.getName());
        System.out.println("--------------------------------------------------");
      }
    }
  };

  private void toastShow(String text) {
    Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
    toast.setGravity(0, 0, 200);
    toast.show();
  }
}
