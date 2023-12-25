package model;

/**
 * Original BlockNode Implementation
 * Uses a doubly linked List structure
 *
 */
public class BlockNode implements IBlockNode{

    //Item before Node in list
    public IBlockNode previous;

    //Item after node in list
    public IBlockNode next;

    //Initial value of the node
    protected String val;


    public BlockNode(String value){
        this.val = value;
    }

    @Override
    public String toString(){
        return this.val;
    }


    @Override
    public void solve() {
        char[] characters = val.toCharArray();
        StringBuilder temp = new StringBuilder();
        for (char character : characters){
            switch(character){
                case '!' -> temp.append(previous.toString());
                case '^' -> previous.reverse();
                case '%' -> previous.encrypt();
                default -> {
                    if (Character.isDigit(character)) {
                        temp.append(character);
                    }
                }
            }
        }
        this.val = temp.toString();
    }

    //Reverses the value of the blocknode
    @Override
    public void reverse(){
        StringBuilder revVal = new StringBuilder();
        for (int i = this.val.length() - 1; i >= 0; i--){
            revVal.append(val.charAt(i));
        }
        this.val = revVal.toString();
    }

    @Override
    public void encrypt(){
        StringBuilder acc = new StringBuilder();
        for (char character:
             val.toCharArray()) {
            if (character == '0'){
                acc.append('0');
            }
            else {
                acc.append((Character.getNumericValue(character) * 2) % 10);
            }
        }
        val = acc.toString();
    }


    @Override
    public void setPrevious(IBlockNode prev){
        this.previous = prev;
    }

    @Override
    public void setNext(IBlockNode next){
        this.next = next;
    }

    @Override
    public IBlockNode getNext(){
        return this.next;
    }

}
