import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item>{
    private Node first = null;
    private Node last = null;
    private int N = 0;
    
    public RandomizedQueue() {}
    
    private class Node{
        Item item;
        Node next;
        Node prev;
    }
    
    private void addFirst(Item item)
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
      
      private void addLast(Item item)
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
    
      private Item removeFirst()
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
    
      private Item removeLast()
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
    
      public void enqueue(Item item){
          if(item == null)
              throw new java.lang.NullPointerException();
          if(StdRandom.bernoulli() == true)
          {this.addFirst(item);}
          else
          {this.addLast(item);}
    }
    
      public Item dequeue(){
          if (isEmpty() == true)
              throw new java.util.NoSuchElementException();
          Item result;
          if(StdRandom.bernoulli() == true)
          {result = this.removeFirst();}
          else
          {result = this.removeLast();}
          return result;
      }
    
      private Item readLast(){
          
      }
      public Item sample(){
          if (isEmpty() == true)
              throw new java.util.NoSuchElementException();
          Item result;
          if(StdRandom.bernoulli() == true)
          {result = randDeque.readFirst();}
          else
          {result = randDeque.readLast();}
          return result;
      }
    
      public Iterator<Item> iterator() {
          return new RandomQueueIterator();
    }
    
    private class RandomQueueIterator implements Iterator<Item>
    {

        public boolean hasNext() {return size != 0; }
        
        public void remove() {throw new java.lang.UnsupportedOperationException();}
        
        public RandomQueueIterator(){

        }
        
        public Item next() 
        {
         
        }    
    }
      
    public static void main(String[] arg){
        RandomizedQueue<Integer>test = new RandomizedQueue<Integer>();
        for(int i = 0; i<5 ; i++)
           test.enqueue(i);
        
        Iterator<Integer> s = test.iterator();
        while(s.hasNext() == true){
            int i = s.next();
            StdOut.println(i);
        }
//        StdOut.println();
//        StdOut.println(test.dequeue());
//        for(int i : test)
//            StdOut.print(i+" ");

    }
}