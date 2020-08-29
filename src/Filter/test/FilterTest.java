package Filter.test;

import static org.junit.Assert.*;

import Filter.main.*;
import org.junit.Test;

import java.awt.*;

public class FilterTest {
    @Test
    public void equalTest(){
        Condition c = new EqualCondition(Property.NAME,"ANNA");
        assertEquals("name=ANNA", c.getExpression());
    }

    @Test
    public void notEqualTest(){
        Condition c = new NotEqualCondition(Property.NAME,"ANNA");
        assertEquals("name!=ANNA", c.getExpression());
    }

    @Test
    public void containTest(){
        Condition c = new ContainCondition(Property.NAME,"ANNA");
        assertEquals("CONTAINS(name,ANNA)", c.getExpression());
    }

    @Test
    public void andTest(){
        Condition c = new EqualCondition(Property.NAME,"ANNA");
        Condition c1 = new NotEqualCondition(Property.COMPANYNAME,"APPLE");
        assertEquals("name=ANNA AND companyName!=APPLE", c.and(c1));
    }

    @Test
    public void orTest(){
        Condition c = new EqualCondition(Property.NAME,"ANNA");
        Condition c1 = new ContainCondition(Property.COUNTRY,"USA");
        assertEquals("name=ANNA OR CONTAINS(country,USA)", c.or(c1));
    }

}
