package com.example.marts.mobilelab2v2;

/**
 * Created by marts on 13.03.2018.
 */

public class Xmlitem {
    String link;
    String title;
    String description;

    /**
     * gets the item's link
     * @return - the item's link
     */
    public String getLink(){
        return link;
    }

    /**
     * gets the item's title
     * @return - the item's title
     */
    public String getItemTitle(){
        return title;
    }

    /**
     * gets the description
     * @return - the description
     */
    public String getDescription(){
        return description;
    }

    /**
     * sets the item's link
     * @param link - the string to set the link to
     */
    public void setLink(String link){
        this.link = link;
    }

    /**
     * sets the description
     * @param description - the description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * sets the item's title
     * @param title - the string to set the title to
     */
    public void setItemTitle(String title){
        this.title = title;
    }

}
