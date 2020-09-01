package Interpreter.test;

import Interpreter.main.*;
import org.junit.Assert;
import org.junit.Test;

public class SqlTest {
    @Test
    public void testEqCondition() {
        String expected = String.format("select * from table where %s", "company name = htsc");
        Assert.assertEquals(expected, GetSql.parseAST("company name = htsc"));
    }

    @Test
    public void testAND() {
        String expected = String.format("select * from table where %s", "((companyname = htsc) and (age<30)) and (sex != male)");
        Assert.assertEquals(expected, GetSql.parseAST("((companyname = htsc) and (age<30)) and (sex != male)"));
    }

    @Test
    public void testOR() {
        String expected = String.format("select * from table where %s", "((companyname = htsc) or (age<30)) or (sex != male)");
        Assert.assertEquals(expected, GetSql.parseAST("((companyname = htsc) or (age<30)) or (sex != male)"));
    }

    @Test
    public void testAndOR() {
        String expected = String.format("select * from table where %s", "(companyname = htsc) and ((age<30) or (sex != male))");
        Assert.assertEquals(expected, GetSql.parseAST("(companyname = htsc) and ((age<30) or (sex != male))"));
    }

    @Test
    public void testNOT() {
        String expected = String.format("select * from table where %s", "not companyname = htsc");
        Assert.assertEquals(expected, GetSql.parseAST("!(companyname = htsc)"));
    }

}