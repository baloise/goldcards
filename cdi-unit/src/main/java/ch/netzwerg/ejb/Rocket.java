package ch.netzwerg.ejb;

import javax.inject.Inject;

public class Rocket {
	
	@Inject
	private Engine engine;
	
	public String launch() {
		return engine.start();
	}


}
