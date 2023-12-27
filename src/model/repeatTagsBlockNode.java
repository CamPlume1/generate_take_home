package model;

/**
 * This is a logically flawed solution,
 * leading to an infinite loop the in the case of two sequential blocks containing "repeat" characters.
 */
public class repeatTagsBlockNode extends BlockNode{
    public repeatTagsBlockNode(String value) {
        super(value);
    }

    @Override
    public void solve() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < val.length(); i++){
            char character = val.charAt(i);
            switch(character){
                case '!' -> temp.append(this.helper(i));
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

    /**
     * Broken
     * @param index index in val that the repeat character was found
     * @return string returned by executing over the repeated block and remnants of current
     */
    private String helper(int index){
        String template = this.previous.getOriginal() + this.val.substring(index +1);
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < template.length(); i++){
            char character = template.charAt(i);
            switch(character){
                case '!' -> temp.append(this.helper(i));
                case '^' -> previous.reverse();
                case '%' -> previous.encrypt();
                default -> {
                    if (Character.isDigit(character)) {
                        temp.append(character);
                    }
                }
            }
        }
        return temp.toString();
    }
}

