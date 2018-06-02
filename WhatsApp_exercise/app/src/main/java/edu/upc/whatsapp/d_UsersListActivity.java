package edu.upc.whatsapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import edu.upc.whatsapp.comms.RPC;
import edu.upc.whatsapp.adapter.MyAdapter_users;
import entity.UserInfo;

import java.util.List;

public class d_UsersListActivity extends Activity implements ListView.OnItemClickListener {

    _GlobalState globalState;
    MyAdapter_users adapter;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalState = (_GlobalState) getApplication();
        setContentView(R.layout.d_userslist);
        new DownloadUsers_Task().execute();
    }
    
    @Override
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        //String name_clicked = adapter.users.get(position).getName();
        globalState.user_to_talk_to = ((MyAdapter_users) l.getAdapter()).users.get(position);
        Intent int_aux = new Intent(d_UsersListActivity.this, e_MessagesActivity.class);
        startActivity(int_aux);

    }

    private class DownloadUsers_Task extends AsyncTask<Void, Void, List<UserInfo>> {
        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(d_UsersListActivity.this, "UsersListActivity",
                    "downloading the users...");
        }

        @Override
        protected List<UserInfo> doInBackground(Void... nothing) {
            UserInfo mysession = globalState.my_user;
            List<UserInfo> ui = RPC.allUserInfos();
            ui.remove(mysession);
            return ui;
        }

        @Override
        protected void onPostExecute(List<UserInfo> users) {
            progressDialog.dismiss();
            if (users == null) {
                toastShow("There's been an error downloading the users");
            } else {
                adapter = new MyAdapter_users(d_UsersListActivity.this, users);
                ListView lv = findViewById(R.id.listView);
                lv.setAdapter(adapter);
                lv.setItemsCanFocus(false);
                lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                lv.setOnItemClickListener(d_UsersListActivity.this); // perque s'activi que es pugui clicar
            }
        }
    }

    private void toastShow(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.setGravity(0, 0, 200);
        toast.show();
    }

}
