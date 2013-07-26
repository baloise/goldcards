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

/**
 * Demonstrates the use of JavaFX2's WebView in Swing-Application.
 * This gives the capability to use JavaScript and html5-features. 
 * It is a technical prototype to see how to hook the function <code>window.postmessage</code>.
 * 
 * Running with swt, a further solution could be by using the SWT-Browser (supports also Webkit).
 * see {@link org.eclipse.swt.browser.WebBrowser#WebBrowser.evaluate(java.lang.String)}
 */
public class JWebView extends JFrame {
  private static final long serialVersionUID = 1L;
  WebView   webView;
  WebEngine engine;
  JFXPanel  panel;

  private void initOnFxThread() {
    webView = new WebView();
    panel.setScene(new Scene(webView));
    engine = webView.getEngine();
    Worker<Void> worker = engine.getLoadWorker();
    worker.stateProperty().addListener(new ChangeListener<Worker.State>() {
      public void changed(ObservableValue<? extends Worker.State> ov,
          Worker.State oldState, Worker.State newState) {
        if (newState == Worker.State.SUCCEEDED) {
          // page-loading is complete. Continue with script-stuff ...
          
          // register the callback-member
          JSObject window = (JSObject) engine.executeScript("window");
          window.setMember("cb", JWebView.this);
          
          // install an event-listener that routes the postmessage to the callback
          // (equivalent to swt's WebBrowser.evaluate(script) -function)
          engine.executeScript(
              "function OnMessage(event) {" +
              "  cb.callback(event.origin, event.data);" +
              "} " +
              "if (window.addEventListener) {" +
              "  window.addEventListener ('message', OnMessage, false);" +
              "} else if (window.attachEvent) {" +
              "  window.attachEvent('onmessage', OnMessage);" +
              "}"
          );
        }
      }
    });
    
//    URL content = getClass().getResource("testhtml.html");
    URL content = getClass().getResource("inner.html");
    engine.load(content.toExternalForm());
  }

  /**
   * Called from JavaScript (from WebEngine).
   */
  public void callback(final String aOrigin, final String aMessage) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JOptionPane.showMessageDialog(JWebView.this, "Origin="+aOrigin + "\nMessage=" + aMessage);
      }
    });
  }

  public JWebView() {
    getContentPane().setLayout(new BorderLayout());
    panel = new JFXPanel();
    getContentPane().add(panel, BorderLayout.CENTER);
    Platform.runLater(new Runnable() {
      public void run() {
        initOnFxThread();
      }
    });
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setSize(new Dimension(650, 300));
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new JWebView().setVisible(true);
      }
    });
  }
}
