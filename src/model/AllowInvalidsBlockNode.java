package model;

/**
 * This strategy allows any character to be included in output, instead of just digits
 */
public class AllowInvalidsBlockNode extends BlockNode{
    public AllowInvalidsBlockNode(String value) {
        super(value);
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
                default -> temp.append(character);
            }
        }
        this.val = temp.toString();
    }
}
