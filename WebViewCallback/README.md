Goldcard
========
_How to use html5 postmessage to integrate in Java-Applications_

This is a simple prototype/showcase, how an html5 aware Webclient can notify his java-based _caller_ (iframe-holder).

Setup
-----
There is an eclipse-project file wich references the JavaFX Library. 
If there is no such library known in the Workspace, simply add a new Userlibrary wich points to the JRE7's jfxrt.jar

Running
-------
The Application shows a Frame with an Browser-Widget from the JavaFX Library. The Browser-Widget loads an simple 
html-File that contains some minimal JavaScript code to deal with the html5 postmessage feature.
You can modify the text-message in the html-textaera and then press the send-button (html-button) to notify and transfer 
the message to the Java-Application's Callback. The Callback will show the message in a simple Swing-Popupdialog.

Have fun!
