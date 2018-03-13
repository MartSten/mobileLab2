package com.example.marts.mobilelab2v2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by marts on 13.03.2018.
 */

public class RssReader extends AsyncTask<Void, Void, Void> {

    Context context;
    String address = "https://news.google.com/news/rss/?ned=us&gl=US&hl=en";
    URL url;
    ArrayList<Xmlitem>xmlitemArrayList;
    RecyclerView recyclerView;

    public RssReader(Context context, RecyclerView recyclerView){
        this.context = context;
        this.recyclerView = recyclerView;
        if(getUrl() != "NOURL"){
            //address = getUrl();
            String temp = getUrl();
            Log.d("rssUrl", temp);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        recyclerAdapter adapter = new recyclerAdapter(context, xmlitemArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    /*@Override
    protected void onPreExecute() {
        super.onPreExecute();
    }*/

    @Override
    protected Void doInBackground(Void... voids) {
        ProcessXml(getData());
        return null;
    }

    private void ProcessXml(Document data){
        if(data != null){
            xmlitemArrayList = new ArrayList<>();
            //Log.d("Root", data.getDocumentElement().getNodeName());
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(0); //Channel is item 1 of root
            NodeList items = channel.getChildNodes();
            //for(int k = 0; k < getLimit(); k++){ does nothing
                for(int i=0; i < items.getLength(); i++){  //doesn't run with getLimit
                    Node currentChild = items.item(i);
                    Xmlitem xmlitem = new Xmlitem();
                    //  for(int k = 0; k < getLimit(); k++){  //Does not limit total amount, but it limits it to only re-use k items over and over
                    if(currentChild.getNodeName().equalsIgnoreCase("item")){
                        NodeList itemChild = currentChild.getChildNodes();
                        for(int j = 0; j < itemChild.getLength(); j++){
                            Node current = itemChild.item(j);
                            //Log.d("textcontent", current.getTextContent());
                            if(current.getNodeName().equalsIgnoreCase("title")){
                                xmlitem.setItemTitle(current.getTextContent());
                            }else if(current.getNodeName().equalsIgnoreCase("link")){
                                xmlitem.setLink(current.getTextContent());
                            }else if(current.getNodeName().equalsIgnoreCase("description")){
                                xmlitem.setDescription(current.getTextContent());
                            }
                        }
                        xmlitemArrayList.add(xmlitem);
                        //Log.d("xmlItemLink",xmlitem.getLink());
                        //Log.d("xmlItemTitle",xmlitem.getTitle());
                    }
                    //}
                //}
            }

        }
    }

    private Document getData(){
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            return builder.parse(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the limit set by the user from the preferences
     * @return - the limit set by the user in the preferences activity
     */
    private int getLimit(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt("limit", 1);  //Default = 1 so that the program will always get minimum one item
    }

    /**
     * Gets the url set by the user from the preferences
     * @return - the url set by the user in the preferences activity
     */
    private String getUrl() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("rssUrl", "NOURL");
    }
}
