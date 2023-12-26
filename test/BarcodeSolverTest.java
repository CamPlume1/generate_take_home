import model.BarcodeSolver;
import model.Strategies;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
        String b1 = BarcodeSolver.solveAll(List.of("#!^%##12#34!#59^#67%####"), strategy).get(0);
        String b2 = BarcodeSolver.solveAll(List.of("#^##12#34!#59^#67%####"), strategy).get(0);
        String b3 = BarcodeSolver.solveAll(List.of("#%##12#34!#59^#67%####"), strategy).get(0);
        String b4 = BarcodeSolver.solveAll(List.of("#!^%1##12#34!#59^#67%####"), strategy).get(0);
        assertEquals(b1, baseCorrect);
        assertEquals(b3, baseCorrect);
        assertEquals(b2, baseCorrect);
        assertEquals(b4, "1" + baseCorrect);



        //SECTION: Try doubling up the different operations, make sure they work sequentially

        //double repeat, before each combo
        String c1  = BarcodeSolver.solveAll(List.of("#!##12#3!4!#59^#67%####"), strategy).get(0);
        assertEquals(c1, "122142130867");
        String c2 = BarcodeSolver.solveAll(List.of("#!##12#34!#!59^####"), strategy).get(0);
        assertEquals(c2, "122143341259");



        //Double reverse + Double Encrypt
        String comparison3 = BarcodeSolver.solveAll(List.of("#!##12#3!4!#59^^#6%7%####"), strategy).get(0);
        assertEquals(comparison3, "123124120667");

    }


    private void givenTestCases(Strategies strategy){
        String baseCorrect = "1221430867";
        //Original test Case, empty
        String comparison = BarcodeSolver.solveAll(List.of("#12#34!#59^#67%#"), strategy).get(0);
        String comparison2 = BarcodeSolver.solveAll(List.of("######"), strategy).get(0);
        assertEquals(comparison, baseCorrect);
        assertEquals(comparison2, "");

    }
}
