package ch.basler.ejb;

import javax.ejb.Stateless;

@Stateless
public class FooEJB {

    public String getFoo() {
        return "foo";
    }

}
