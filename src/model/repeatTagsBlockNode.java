package model;

public class repeatTagsBlockNode extends BlockNode{
    public repeatTagsBlockNode(String value) {
        super(value);
    }

    //TODO: This is harder than I thought initially
    // flag = false
    @Override
    public void solve() {
        char[] characters = val.toCharArray();
        StringBuilder temp = new StringBuilder();
        for (char character : characters){
            switch(character){
                case '!' -> temp.append(previous.getOriginal());
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
}

/*
Algorithm:
iterate over each character in string

 */
