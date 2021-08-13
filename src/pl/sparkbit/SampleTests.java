package pl.sparkbit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SampleTests {

    private Code code;

    @Before
    public void setup() {
        this.code = new CodeImpl();
    }

    @Test
    public void testExampleString() {
        Node result = code.createCode("abacaca");
        assertEquals(new InnerNode(
                        new InnerNode(
                                new Leaf('b', 1),
                                new Leaf('c', 2)
                        ), new Leaf('a', 4)
                ),
                result);
    }

    @Test
    public void testStringWithOneLetter() {
        Node result = code.createCode("aaaaa");
        assertEquals(new Leaf('a', 5), result);
    }
}
