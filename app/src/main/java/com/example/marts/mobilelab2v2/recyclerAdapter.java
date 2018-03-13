package com.example.marts.mobilelab2v2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by marts on 13.03.2018.
 */

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.AdapterViewHolder> {

    private ArrayList<Xmlitem>xmlitemArrayList;
    private Context context;

    recyclerAdapter(Context context, ArrayList<Xmlitem> xmlitemArrayList){
        this.context = context;
        this.xmlitemArrayList = xmlitemArrayList;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_content, parent, false);
        //AdapterViewHolder holder = new AdapterViewHolder(view);
        //return holder;
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        if(!xmlitemArrayList.isEmpty()){
            Xmlitem currentItems = xmlitemArrayList.get(position);
            holder.Title.setText(currentItems.getItemTitle());
            //holder.Description.setText(currentItems.getDescription());    //Messes things up. Have therefore been removed for now
            holder.Link.setText(currentItems.getLink());
        }
    }

    @Override
    public int getItemCount() {
        //return xmlitemArrayList.size();
        SharedPreferences pref = context.getSharedPreferences("lab2Prefs", Context.MODE_PRIVATE);
        return pref.getInt("limit", 10);    //default number of items is 10
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView Title;
        TextView Description;
        TextView Link;
        AdapterViewHolder(View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.Title);
            Description = itemView.findViewById(R.id.Title);
            Link = itemView.findViewById(R.id.Link);
        }
    }
}
