# mobileLab2 - RSS reader
lab 2 for mobile dev at NTNU

The app fetches and pares a RSS v2.0 feed, provided by the user in the form of an url, and lists the items in a recyclerview.
If the user clicks on any of the items in the recyclerview, a new activity with a webview opens and loads the page.
The user can also at any point change the RSS url and the number of items shown in the recyclerview. (The user can also set the rate of how often the app should pull updates, however this is not fully implemented jet (as it was not listed in the checklist requierments))

# Project description
Lab 02: Simple RSS reader


## The idea

Create an application that allows the user to read content from any RSS feed. The app will consist of 3 activities: one with the list of items (ListView, for selecting content), one for article content display (for reading content), and User Preferences (for user to specify the preferences). 


## Preferences

The user should be able to specify in the preferences the URL to the RSS feed (RSS2.0-based or Atom-based), and, the limiting number of items that should be displayed in the ListView (10, 20, 50, 100), and the frequency at which the app fetches the content (10min, 60min, once a day). The app will fetch the RSS feed and populate the list UP to the limit number. When user clicks on a particular item, a detailed view should be shown, with the content of the article for that item. 


## Checklist


 The git repository URL is correctly provided, such that command works: git clone <url>


 The code is well, logically organised and structured into appropriate classes. Everything should be in a single package.
 

 It is clear to the user what RSS feed formats are supported (RSS2.0 and/or Atom).
 

 The user can go to Preferences and set the URL of the RSS feed.
 

 The user can go to Preferences and set the feed item limit.
 

 The user can go to Preferences and set the feed refresh frequency.
 

 The user can see the list of items from the feed on the home Activity ListView.
 

 The user can go to a particular item by clicking on it. The content will be displayed in newly open activity. The back button puts the user back onto the main ListView activity to select another item. 
 

 The user can press the back button from the main activity to quit the app. 
 

 When the content article has graphics, it is rendered correctly.
 
 
 
# OTHER INFO
The code is based on this tutorial:  https://www.youtube.com/watch?v=19NSla4FNdQ

The app (as of this moment) has no url validation. Pleas do only use rss v2.0 urls.
