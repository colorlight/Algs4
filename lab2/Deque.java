import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
public class Deque<Item> implements Iterable<Item> 
{
    private Node first = null;
    private Node last = null;
    private int N = 0;
    public Deque() {}
    private class Node
    {
        Item item;
        Node next;
    }
    
    public void addFirst(Item item)
    {
        if(item == null)
            throw new java.lang.NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }
    
    public void addLast(Item item)
    {
        if(item == null)
            throw new java.lang.NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = oldLast;
        N++;
    }
    
    public int size()
    {return N;}
    
    public boolean isEmpty()
    {return N == 0;}
    
    public Item removeFirst()
    {
        if (isEmpty() == true)
            throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        return item;
    }
    
    public Item removeLast()
    {
        if (isEmpty() == true)
            throw new java.util.NoSuchElementException();
        Item item = last.item;
        last = last.next;
        return item;
    }
    
    public Iterator<Item> iterator() {return new DequeIterator();}
    
    private class DequeIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext() {return current !=null; }
        public void remove() {throw new java.lang.UnsupportedOperationException();}
        public Item next() 
        {
            if(hasNext() == false)
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current  = current.next;
            return item;
        }    
    }
    
    public static void main(String[] args)
    {
        Deque<Integer> test = new Deque<Integer>();
        for(int i = 0; i<3; i++)
            test.addFirst(i);

        for(int i:test)
            StdOut.println(i);
       // test.addLast(null);
        StdOut.println(test.size());
    }
    
}