import model.BlockNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodeTest {

    @Test
    public void nodeTest1(){
        BlockNode head = new BlockNode("");
        BlockNode first = new BlockNode("12");
        BlockNode second = new BlockNode("34!");
        BlockNode third = new BlockNode("59^");
        BlockNode last = new BlockNode("67%");
        head.next = first;
        first.next = second;
        second.next = third;
        third.next = last;
        last.previous = third;
        third.previous = second;
        second.previous = first;
        first.previous = head;

        head.solve();
        assertEquals("", head.toString());

        //standard block
        first.solve();
        assertEquals("", head.toString());
        assertEquals("12", first.toString());

        //repeat block
        second.solve();
        assertEquals("", head.toString());
        assertEquals("12", first.toString());
        assertEquals("3412", second.toString());

        //reverse block
        third.solve();

        assertEquals("12", first.toString());
        assertEquals("2143", second.toString());
        assertEquals("59", third.toString());

        last.solve();

        assertEquals("12", first.toString());
        assertEquals("2143", second.toString());
        assertEquals("08", third.toString());
        assertEquals("67", last.toString());

        String acc = head.toString() + first.toString() + second.toString() + third.toString() + last.toString();
        assertEquals(acc, "1221430867");

    }


}
