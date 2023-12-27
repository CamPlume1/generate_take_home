package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This strategy does not execute tags until AFTER filtering all digits in the string.
 */
public class PostProcessingBlockNode extends BlockNode{


    public PostProcessingBlockNode(String value) {
        super(value);
    }

    @Override
    public void solve() {
        char[] characters = val.toCharArray();
        StringBuilder temp = new StringBuilder();
        List<Character> tagQueue = new ArrayList<>();
        for (char character : characters) {
            switch (character) {
                case '!', '^', '%' -> tagQueue.add(character);
                default -> {
                    if (Character.isDigit(character)) {
                        temp.append(character);
                    }
                }
            }
        }
        for (Character tag : tagQueue){
            switch (tag){
                case '!' -> temp.append(previous.toString());
                case '^' -> previous.reverse();
                case '%' -> previous.encrypt();
            }
        }
        this.val = temp.toString();
    }
}
