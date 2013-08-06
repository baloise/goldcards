package ch.basler.cdiunit;

import static org.junit.Assert.assertEquals;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

@RunWith(CdiRunner.class)
public class RocketTest {

  @Produces
  @Mock
  private Engine engine;

  @Inject
  private Rocket rocket;

  @Test
  public void launch() {
    Mockito.when(engine.start()).thenReturn("starting mock engine");
    assertEquals("starting mock engine", rocket.launch());
  }

}
