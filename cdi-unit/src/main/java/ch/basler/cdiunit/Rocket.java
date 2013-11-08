package ch.basler.cdiunit;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class Rocket {

  @EJB
  private Engine engine;

  @PostConstruct
  public String postConstruct() {
    return engine.ignite();
  }

  public String launch() {
    return "Launching via " + engine.boost();
  }

  /**
  * ACHTUNG: Bitte nur aus Tests aufrufen!
  */
  public void setEngine(Engine engine) {
    this.engine = engine;
  }

}