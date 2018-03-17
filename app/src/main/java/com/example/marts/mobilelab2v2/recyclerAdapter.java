package com.example.marts.mobilelab2v2;

import android.content.Context;
import android.content.Intent;
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

    /**
     * constructor
     * @param context - the context to use
     * @param xmlitemArrayList - a list containing xmlItems
     */
    recyclerAdapter(Context context, ArrayList<Xmlitem> xmlitemArrayList){
        this.context = context;
        this.xmlitemArrayList = xmlitemArrayList;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_content, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterViewHolder holder, int position) {
        if(!xmlitemArrayList.isEmpty()){
            final Xmlitem currentItems = xmlitemArrayList.get(position);
            holder.Title.setText(currentItems.getItemTitle());
            //holder.Description.setText(currentItems.getDescription());    //Messes things up. Have therefore been removed for now
            holder.Link.setText(currentItems.getLink());
            //Sets am onClickListener on all recyclerView items
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, RssContent.class);
                    i.putExtra("link", currentItems.link);
                    context.startActivity(i);
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        //Returns the number of items to be shown in the recyclerView
        SharedPreferences pref = context.getSharedPreferences("lab2Prefs", Context.MODE_PRIVATE);
        return pref.getInt("limit", 10);    //default number of items is 10
    }

    /**
     * Class for crating/holding items for the recyclerView.
     */
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
