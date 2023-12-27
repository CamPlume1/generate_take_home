package model;

import java.util.ArrayList;
import java.util.List;

public class InQueueBlockNode extends BlockNode{
    public InQueueBlockNode(String value) {
        super(value);
    }

    @Override
    public void solve() {
        char[] characters = val.toCharArray();
        StringBuilder temp = new StringBuilder();
        List<Character> tagQueue = new ArrayList<>();
        for (char character : characters) {
            switch (character) {
                case '^', '%' -> tagQueue.add(character);
                case '!' -> temp.append(previous.toString());
                default -> {
                    if (Character.isDigit(character)) {
                        temp.append(character);
                    }
                }
            }
        }
        for (Character tag : tagQueue){
            switch (tag){
                case '^' -> previous.reverse();
                case '%' -> previous.encrypt();
            }
        }
        this.val = temp.toString();
    }
}
