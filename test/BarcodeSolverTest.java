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

    /**
     * Tests PostProcessing strategy
     */
    @Test
    public void postProcessingSolverTest(){
        String baseCorrect = "1221430867";
        Strategies strategy = Strategies.POST_PROCESS;

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
        //THIS IS WHERE POST PROCESS DIFFERS FROM BASIC


        //double repeat, before each combo
        String c1  = BarcodeSolver.solveAll(List.of("#!##12#3!4!#59^#67%####"), strategy).get(0);
        assertEquals(c1, "122121430867");
        String c2 = BarcodeSolver.solveAll(List.of("#!##12#34!#!59^####"), strategy).get(0);
        assertEquals(c2, "122143593412");



        //Double reverse + Double Encrypt
        String comparison3 = BarcodeSolver.solveAll(List.of("#!##12#3!4!#59^^#6%7%####"), strategy).get(0);
        assertEquals(comparison3, "123412120667");

    }


    /**
     * Tests Zero Encrypt Strategy
     */
    @Test
    public void zeroEncryptSolverTest(){
        String baseCorrect = "1221430867";
        Strategies strategy = Strategies.ZERO_ENCRYPT;

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



        //Double reverse + Double Encrypt: THIS IS WHERE ZERO ENCRYPT DIFFERS FROM BASIC
        String comparison3 = BarcodeSolver.solveAll(List.of("#!##12#3!4!#59^^#6%7%####"), strategy).get(0);
        assertEquals(comparison3, "123124120867");

    }


    //This function tests all of the given test cases from the problem statement
    private void givenTestCases(Strategies strategy){
        String baseCorrect = "1221430867";
        //Original test Case, empty
        String comparison = BarcodeSolver.solveAll(List.of("#12#34!#59^#67%#", "#0%#1%#2%#3%#4%#5%#6%#7%#8%#9%#"), strategy).get(0);
        String comparison4 = BarcodeSolver.solveAll(List.of("#12#34!#59^#67%#", "#0%#1%#2%#3%#4%#5%#6%#7%#8%#9%#"), strategy).get(1);
        String comparison2 = BarcodeSolver.solveAll(List.of("######", ""), strategy).get(0);
        String comparison3 = BarcodeSolver.solveAll(List.of("######", ""), strategy).get(1);
        assertEquals(comparison, baseCorrect);
        assertEquals(comparison2, "");
        assertEquals(comparison3, "");
        assertEquals(comparison4, "0246802469");


    }




}
