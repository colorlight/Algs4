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
        Node prev;
    }
    
    public void addFirst(Item item)
    {
        if(item == null)
            throw new java.lang.NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if(N++ !=0) oldFirst.prev = first;
        if(N == 1) last = first;
    }
    
    public void addLast(Item item)
    {
        if(item == null)
            throw new java.lang.NullPointerException();

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        if(N++ != 0) oldLast.next = last;
        if(N == 1) first = last;
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
        if(N-- != 1) {
            first = first.next;
            first.prev = null;
        }
        else{first = last = null;}
        return item;
    }
    
    public Item removeLast()
    {
        if (isEmpty() == true)
            throw new java.util.NoSuchElementException();
        Item item = last.item;
        if(N-- != 1){
            last = last.prev;
            last.next = null;
        }
        else{first = last = null;}
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
//       for(int i = 0; i<6; i++)
//            test.addFirst(i);
        test.addLast(0);
        StdOut.println(test.size());
        test.addLast(2);
        test.addLast(3);
        test.addFirst(4);
        test.removeLast();
        for(int i:test)
            StdOut.println(i);
       // test.addLast(null);
       // StdOut.println(test.size());
    }
    
}