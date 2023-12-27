package model;

import java.util.ArrayList;
import java.util.List;


/**
 * The `BarcodeSolver` class provides methods for solving barcodes based on a specified strategy.
 *
 * The barcodes are represented as a list of strings, and the solving strategy is determined by the
 * `Strategies` enum, which includes options such as `BASIC`, `ZERO_ENCRYPT`, and 'POST_PROCESSING'.
 *
 * The class contains a static method `solveAll` that takes a list of barcodes and a strategy as
 * parameters, and returns a list of solved barcodes using the specified strategy.
 *
 * Example Usage:
 * ```java
 * List<String> barcodes = Arrays.asList("barcode1", "barcode2", "barcode3");
 * List<String> solvedBarcodes = BarcodeSolver.solveAll(barcodes, Strategies.BASIC);
 * ```
 */
public class BarcodeSolver {

    /**
     * Solves all barcodes in the given list using the specified strategy.
     *
     * @param barcodes  A list of barcodes to be solved.
     * @param strategy  The strategy used for solving the barcodes.
     * @return A list of solved barcodes.
     */
    public static List<String> solveAll(List<String> barcodes, Strategies strategy){
        //initialize accumulator
        List<String> retList = new ArrayList<>();

        //call solve on each barcode, and append to accumulator
        for (String barcode : barcodes){
            retList.add(solve(barcode, strategy));
        }
        //return accumulator
        return retList;
    }

    //Solves a single barcode with a given Strategy
    private static String solve(String barcode, Strategies strategy){

        //Split into array on # symbols
        String[] splitCode = barcode.split("#");
        //Define pointer to head
        IBlockNode head = new BlockNode("");

        //set previous pointer to head, DOUBLE HEAD IS BEING CREATED
        IBlockNode previous = head;

        //create a Blocknode for each block
        for (String current : splitCode){

            //Create new node
            IBlockNode temp = null;
            if (strategy == Strategies.BASIC){
                temp = new BlockNode(current);
            }
            else if (strategy == Strategies.ZERO_ENCRYPT){
                temp = new ZeroEncryptBlockNode(current);
            }
            else if(strategy == Strategies.REPEAT_FULL_BLOCK){
                temp = new repeatFullBlockNode(current);
            }
            else if (strategy == Strategies.POST_PROCESS){
                temp = new PostProcessingBlockNode(current);
            }
            else if (strategy == Strategies.ALLOW_INVALIDS){
                temp = new AllowInvalidsBlockNode(current);
            }



            //Set Nodes
            previous.setNext(temp);
            assert temp != null;
            temp.setPrevious(previous);

            //set previous to temp, advancing along list
            previous = temp;
        }

        //Set value of tail
        IBlockNode tail = previous;


        //Get pointer to head and execute tag all
        IBlockNode current = head;
        while(true){
            current.solve();
            if(current.equals(tail)){
                break;
            }
            current = current.getNext();
        }

        StringBuilder acc = new StringBuilder();
        //reset current pointer
        current = head;
        while(true){
            acc.append(current.toString());
            if(current.equals(tail)){
                break;
            }
            current = current.getNext();
        }
        //return
        return acc.toString();
    }



}
