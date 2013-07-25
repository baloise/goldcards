package ch.basler.webview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import netscape.javascript.JSObject;

public class JWebView extends JFrame {
  private static final long serialVersionUID = 1L;
  protected WebView webView;
  protected WebEngine engine;
  protected JFXPanel panel = new JFXPanel();

  private void initOnFxThread() {
    webView = new WebView();
    panel.setScene(new Scene(webView));
    engine = webView.getEngine();
    Worker<Void> worker = engine.getLoadWorker();
    worker.stateProperty().addListener(new ChangeListener<Worker.State>() {
      @Override
      public void changed(ObservableValue<? extends Worker.State> ov,
          Worker.State oldState, Worker.State newState) {
        if (newState == Worker.State.SUCCEEDED) {
          JSObject window = (JSObject) engine.executeScript("window");
          window.setMember("cb", JWebView.this);
          engine.executeScript(
              "function OnMessage(event) {" +
              "  cb.callback(event.data);" +
              "} " +
              "if (window.addEventListener) {" +
              "  window.addEventListener ('message', OnMessage, false);" +
              "} else {" +
              "if (window.attachEvent) {" +
              "  window.attachEvent('onmessage', OnMessage);" +
              "}}"
          );
        }
      }
    });
//    URL content = getClass().getResource("testhtml.html");
    URL content = getClass().getResource("inner.html");
    engine.load(content.toExternalForm());
  }

  public void callback(final String aMessage) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JOptionPane.showMessageDialog(JWebView.this, aMessage);
      }
    });
  }

  public JWebView() {
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(panel, BorderLayout.CENTER);
    Platform.runLater(new Runnable() {
      public void run() {
        initOnFxThread();
      }
    });
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame view = new JWebView();
        view.setSize(new Dimension(650, 300));
        view.setVisible(true);
      }
    });
  }
}
