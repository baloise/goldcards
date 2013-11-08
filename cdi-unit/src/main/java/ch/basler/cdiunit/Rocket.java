package ch.basler.cdiunit;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Rocket {

  @Inject
  private Engine engine;

  @PostConstruct
  public void postConstruct() {
    engine.ignite();
  }

  public String launch() {
    return "Launching via " + engine.boost();
  }

}