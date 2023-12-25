import model.BarcodeSolver;
import model.Strategies;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BarcodeSolverTest {


    /**
     * Tests BASIC strategy
     */
    @Test
    public void basicSolverTest(){
        String baseCorrect = "1221430867";
        Strategies strategy = Strategies.BASIC;

        //SECTION: Basic test cases
        this.givenTestCases(strategy);

        //SECTION: Try operations in first block, should all be equal,
        String b1 = BarcodeSolver.solve("#!^%##12#34!#59^#67%####", strategy);
        String b2 = BarcodeSolver.solve("#^##12#34!#59^#67%####", strategy);
        String b3 = BarcodeSolver.solve("#%##12#34!#59^#67%####", strategy);
        String b4 = BarcodeSolver.solve("#!^%1##12#34!#59^#67%####", strategy);
        assertEquals(b1, baseCorrect);
        assertEquals(b3, baseCorrect);
        assertEquals(b2, baseCorrect);
        assertEquals(b4, "1" + baseCorrect);



        //SECTION: Try doubling up the different operations, make sure they work sequentially

        //double repeat, before each combo
        String c1  = BarcodeSolver.solve("#!##12#3!4!#59^#67%####", strategy);
        assertEquals(c1, "122142130867");
        String c2 = BarcodeSolver.solve("#!##12#34!#!59^####", strategy);
        assertEquals(c2, "122143341259");



        //Double reverse + Double Encrypt
        String comparison3 = BarcodeSolver.solve("#!##12#3!4!#59^^#6%7%####", strategy);
        assertEquals(comparison3, "123124120667");

    }


    private void givenTestCases(Strategies strategy){
        String baseCorrect = "1221430867";
        //Original test Case, empty
        String comparison = BarcodeSolver.solve("#12#34!#59^#67%#", strategy);
        String comparison2 = BarcodeSolver.solve("######", strategy);
        assertEquals(comparison, baseCorrect);
        assertEquals(comparison2, "");

    }
}
