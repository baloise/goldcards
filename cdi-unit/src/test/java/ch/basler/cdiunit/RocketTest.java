package ch.basler.cdiunit;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(CdiRunner.class)
public class RocketTest {

  @Produces
  @Mock
  private Engine mockEngine;

  @Inject
  private Rocket rocket;

  @Test
  public void launch() {
    Mockito.when(mockEngine.boost()).thenReturn("mock boost");
    assertEquals("Launching via mock boost", rocket.launch());
  }

}
