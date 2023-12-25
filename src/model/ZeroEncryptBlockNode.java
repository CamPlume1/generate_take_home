package model;

public class ZeroEncryptBlockNode extends BlockNode{
    public ZeroEncryptBlockNode(String value) {
        super(value);
    }


    @Override
    public void encrypt(){
        if (super.val.contains("0")){
            return;
        }
        StringBuilder acc = new StringBuilder();

        for (char character:
                super.val.toCharArray()) {
            if (character == '0'){
                acc.append('0');
            }
            else {
                acc.append((Character.getNumericValue(character) * 2) % 10);
            }
        }
        val = acc.toString();
    }
}
