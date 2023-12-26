package model;

public class BarcodeSolver {

    public static String solve(String barcode, Strategies strategy){

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
