# Peer to peer chat application

## What has been implemented

* Presence server with a user database
* Multiple chats at the same time with different users on different tabs
* Connected users list manually or automatically updated
* Two simple but important tests
* Maven structured project

## How to use the project

* Compile
    >mvn compile

* Run tests
    >mvn test

## How to use the application
* Start the server
    >mvn exec:java@start-server
* Start one client interface
    >mvn exec:java@start-server

## How to use the interface
* Enter your user name on the first window  
_You may have any arror printed over your username field._

* In the **Community** widget:  
    * Start a chat by clicking on a username
    * Update the connected users list
    * Switch to next chat tab if more than one has been started

* In a **Chat** widget:  
    * Enter your message in the 2nd field
    * Click "Send" to send your message
    * Visualize the discussion on the first field

    
## A few comments

There are a some warnings on the client application start. They are due to the _Darcula Look And Feel_ library that has not yet been updated to this java version.  

A very important part of the work has been put on the graphical interface, the use of flexible tools like _JFormDesigner_ demanded a lot of learning.

If you want to run the server on another computer in the local network, the new address has to be written in **MainWindow.java** in the _MANAGEMENT_HOSTNAME_ variable. We have not worked on the  flexibility of this feature because it would only be relevant if the application was to be realease. Wich is not feasible in its actual state due to some imperfections.

The code is not very much commented, but the variable/methods names speak very much by themselves.
