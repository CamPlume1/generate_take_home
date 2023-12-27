package model;

/**
 * Repeat full blocks when repeating
 * This cannot be the solution within the given specifications, but I'm out of ideas
 */
public class repeatFullBlockNode extends BlockNode{
    public repeatFullBlockNode(String value) {
        super(value);
    }

    @Override
    public void solve() {
        char[] characters = val.toCharArray();
        StringBuilder temp = new StringBuilder();
        for (char character : characters){
            switch(character){
                case '!' -> this.repeatNode();
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

    //Creates a new node with orginal raw text in front of the current node
    private void repeatNode() {
        IBlockNode repeated = new repeatFullBlockNode(previous.getOriginal());
        if (this.next != null){this.next.setPrevious(repeated);}
        repeated.setNext(this.next);
        this.next = repeated;
        repeated.setPrevious(this);

    }
}
