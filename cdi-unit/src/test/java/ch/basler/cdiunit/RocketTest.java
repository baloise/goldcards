package ch.basler.cdiunit;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class RocketTest {

  private Engine mockEngine;

  private Rocket rocket;

  @Before
  public void before() {
    this.mockEngine = Mockito.mock(Engine.class);
    this.rocket = new Rocket();
    this.rocket.setEngine(mockEngine);
  }

  @Test
  public void launch() {
    Mockito.when(mockEngine.boost()).thenReturn("mock boost");
    assertEquals("Launching via mock boost", rocket.launch());
  }

}
