package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Individual method unit tests
 *
 */
public class BlockNodeTest {

    BlockNode basicNode;

    BlockNode repeatNode;

    BlockNode reverseNode;

    BlockNode encryptNode;

    private void init(){
        basicNode  = new BlockNode("123a");
        repeatNode = new BlockNode("123!");
        reverseNode  = new BlockNode("123^");
        encryptNode = new BlockNode("123%");
    }

    @Test
    public void testToString() {
        this.init();
        assertEquals("123a", basicNode.toString());
        assertEquals("123!", repeatNode.toString());
        assertEquals("123^", reverseNode.toString());
        assertEquals("123%", encryptNode.toString());
    }

    @Test
    public void solve() {
        this.init();
        repeatNode.setPrevious(basicNode);
        reverseNode.setPrevious(basicNode);
        encryptNode.setPrevious(basicNode);

        //check bad input filtering
        assertEquals("123a", basicNode.toString());
        basicNode.solve();
        assertEquals("123", basicNode.toString());

        //check repeatNode Solve()
        repeatNode.solve();
        assertEquals("123123", repeatNode.toString());

        //check reverseNode Solve()
        reverseNode.solve();
        assertEquals("321", basicNode.toString());
        assertEquals("123", reverseNode.toString());

        //check encryptnode Solve()
        encryptNode.solve();
        assertEquals("642", basicNode.toString());
        assertEquals("123", encryptNode.toString());

    }

    @Test
    public void reverse() {
        this.init();
        this.basicNode.solve();
        assertEquals(this.basicNode.toString(), "123");
        this.basicNode.reverse();
        assertEquals(this.basicNode.toString(), "321");
    }

    @Test
    public void encrypt() {
        this.init();
        this.basicNode.solve();
        assertEquals(this.basicNode.toString(), "123");
        this.basicNode.encrypt();
        assertEquals(this.basicNode.toString(), "246");
    }

    @Test
    public void setPrevious() {
        this.init();
        assertNull(this.basicNode.previous);
        basicNode.setPrevious(repeatNode);
        assertEquals(basicNode.previous, repeatNode);
    }

    @Test
    public void setAndGetNext() {
        this.init();
        assertNull(this.basicNode.getNext());
        basicNode.setNext(repeatNode);
        assertEquals(basicNode.next, repeatNode);
    }
}