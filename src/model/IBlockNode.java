package model;

public interface IBlockNode {

    /**
     * Processes each character in the node's value based on specific rules.
     * - If the character is '!', appends the string representation of the previous node.
     * - If the character is '^', reverses the value of the previous node.
     * - If the character is '%', encrypts the value of the previous node.
     * - If the character is a digit, appends it.
     * Updates the value of the current node with the processed string.
     */
    public void solve();

    /**
     * Reverses the value of the current node.
     */
    public void reverse();

    /**
     * Encrypts the value of the current node based on specific rules.
     * - If the character is '0', appends '0'.
     * - Otherwise, appends the result of `(Character.getNumericValue(character) * 2) % 10`.
     */
    public void encrypt();

    /**
     * Returns a string representation of the node's current value.
     *
     * @return The string representation of the node's value.
     */
    public String toString();


    /**
     * gets the original value of a Node
     *
     * @return the String originally passed into the constructor
     */
    public String getOriginal();

    /**
     * Sets the previous node in the doubly linked list.
     *
     * @param prev The previous node.
     */
    public void setPrevious(IBlockNode prev);

    /**
     * Sets the next node in the doubly linked list.
     *
     * @param next The next node.
     */
    public void setNext(IBlockNode next);

    /**
     * Returns the next node in the doubly linked list.
     *
     * @return The next node.
     */
    public IBlockNode getNext();

}
