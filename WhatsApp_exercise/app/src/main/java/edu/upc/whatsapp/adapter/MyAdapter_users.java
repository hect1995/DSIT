/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.whatsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.upc.whatsapp.R;
import entity.UserInfo;
import java.util.List;

/**
 *
 * @author upcnet
 */
public class MyAdapter_users extends BaseAdapter {

    Context context;
    public List<UserInfo> users;

    public MyAdapter_users(Context context, List<UserInfo> users) {
      this.context = context;
      this.users = users;
    }

    public int getCount() {
      return users.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) { // convertView is the
        // parameter filled whenever a view dissapears (they new one that appears)
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_twotextviews, parent, false); // Create a new view
            holder = new ViewHolder(convertView);
            convertView.setTag(holder); // save the tags assigned to the view
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        UserInfo persona = (UserInfo) getItem(position);
        holder.name.setText(persona.getName());
        holder.surname.setText(persona.getSurname());
        return convertView;
    }

    public Object getItem(int arg0) {
      return users.get(arg0);
    }

    public long getItemId(int arg0) {
      return users.get(arg0).getId();
    }

    static class ViewHolder { // Structure that keeps record of all the texts in all views
        TextView name;
        TextView surname;
        public ViewHolder(View view){
            name = (TextView)view.findViewById(R.id.row_twotextviews_name);
            surname = (TextView)view.findViewById(R.id.row_twotextviews_surname);

        }
    }
  }
