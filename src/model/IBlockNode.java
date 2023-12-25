package model;

public interface IBlockNode {

    public void solve();

    public void reverse();

    public void encrypt();

    public void setPrevious(IBlockNode prev);

    public void setNext(IBlockNode next);

    public IBlockNode getNext();

    public String toString();



}
