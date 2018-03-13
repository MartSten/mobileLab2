package com.example.marts.mobilelab2v2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by marts on 13.03.2018.
 */

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.AdapterViewHolder> {

    ArrayList<Xmlitem>xmlitemArrayList;
    Context context;

    public recyclerAdapter(Context context, ArrayList<Xmlitem> xmlitemArrayList){
        this.context = context;
        this.xmlitemArrayList = xmlitemArrayList;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_content, parent, false);
        AdapterViewHolder holder = new AdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        Log.d("sizeOfArray", Integer.toString(xmlitemArrayList.size()));
        //SET LIMIT HERE
        return xmlitemArrayList.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        public AdapterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
