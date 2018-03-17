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
    private String address;
    URL url;
    ArrayList<Xmlitem>xmlitemArrayList;
    RecyclerView recyclerView;

    public RssReader(Context context, RecyclerView recyclerView){
        this.context = context;
        this.recyclerView = recyclerView;
        address = getUrl();
        //address = "https://news.google.com/news/rss/?ned=us&gl=US&hl=en";
        //getUrl();
        Log.d("address", address);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
            recyclerAdapter adapter = new recyclerAdapter(context, xmlitemArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("new Execute", "Running new execution");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ProcessXml(getData());
        return null;
    }

    /**
     * processes the xml by reading the dom of the provided document
     * @param data - an xml document
     */
    private void ProcessXml(Document data){
        if(data != null){
            xmlitemArrayList = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(0);
            //Added this check because trying to use the google-news rss feed would crash the app if channel != root.getChildNodes().item(0)
            //while other rss feeds needs item(1)
            if(channel.getNodeName().equalsIgnoreCase("#text")){
                channel = root.getChildNodes().item(1);
            }

            NodeList items = channel.getChildNodes();
            //Log.d("numbOfItems", Integer.toString(items.getLength()));
                for(int i=0; i < items.getLength(); i++){
                    Node currentChild = items.item(i);
                    Xmlitem xmlitem = new Xmlitem();
                    if(currentChild.getNodeName().equalsIgnoreCase("item")){
                        NodeList itemChild = currentChild.getChildNodes();
                        for(int j = 0; j < itemChild.getLength(); j++){
                            Node current = itemChild.item(j);
                            Log.d("textcontent", current.getTextContent());
                            if(current.getNodeName().equalsIgnoreCase("title")){
                                xmlitem.setItemTitle(current.getTextContent());
                            }else if(current.getNodeName().equalsIgnoreCase("link")){
                                xmlitem.setLink(current.getTextContent());
                            }else if(current.getNodeName().equalsIgnoreCase("description")){
                                xmlitem.setDescription(current.getTextContent());
                            }
                        }
                        xmlitemArrayList.add(xmlitem);
                    }
            }

        }
    }

    /**
     * gets the dom from the provided url, and returns it as a document.
     * @return - the document containing the xml for the provided link
     */
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
        SharedPreferences preferences = context.getSharedPreferences("lab2Prefs", Context.MODE_PRIVATE);
        //address = preferences.getString("rssUrl", "https://news.google.com/news/rss/?ned=us&gl=US&hl=en");
        return preferences.getString("rssUrl", "http://www.nrk.no/toppsaker.rss");
    }
}
