import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item>{
    private Node first = null;
    private int N = 0;
   
    public RandomizedQueue(){}
   
    private class Node{
        Item item;
        Node next;
    }
    public boolean isEmpty(){return first == null;}
    
    public int size() {return N;}
    
    public void enqueue(Item item){
        if(item == null)
            throw new java.lang.NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }
    
    public Item dequeue(){
        if (isEmpty() == true)
            throw new java.util.NoSuchElementException();
        int delNodeNum;
        Node delNode = first;
        Node prevNode = first;
        Item result;
        delNodeNum = StdRandom.uniform(N);
        
        if(delNodeNum != 0){
            for(int i = 0; i<delNodeNum-1; i++){
                prevNode = prevNode.next;
            }
            delNode = prevNode.next;
            result = delNode.item;
            prevNode.next = delNode.next;
        }
        else{
            result = first.item;
            first = first.next;
        }
        N--;
        return result;
    }
    
    public Item sample(){
        if (isEmpty() == true)
            throw new java.util.NoSuchElementException();
        Item result;
        int randNum;
        Node randNode = first;
                
        randNum = StdRandom.uniform(N);
        
        for(int i = 0; i<randNum; i++){
            randNode = randNode.next;
        }
        
        result = randNode.item;
        return result;
    }
    
    public Iterator<Item> iterator() {return new RandomQueueIterator();}
    
    private class RandomQueueIterator implements Iterator<Item>
    {
        Node current = first;
        Node copy;
        Node copyFirst;
        int delNodeNum1;
        Node delNode1;
        Node prevNode1;
        Item result;
        int size = N;
        public boolean hasNext() {return size != 0; }
        
        public void remove() {throw new java.lang.UnsupportedOperationException();}
        
        public RandomQueueIterator(){
            copy = new Node();
            copy.item = current.item;
            copy.next = current.next;
            current = current.next;
            copyFirst = copy;
            for(int i = 1; i<N;i++){
                copy = new Node();
                copy.item = current.item;
                copy.next = current.next;
                current = current.next;
            }
        }
        
        public Item next() 
        {
            if(hasNext() == false)
                throw new java.util.NoSuchElementException();
            prevNode1 = copyFirst;
            delNode1 = copyFirst;
            delNodeNum1 = StdRandom.uniform(size);
            if(delNodeNum1 != 0){
                for(int i = 0; i<delNodeNum1-1; i++)
                    prevNode1 = prevNode1.next;
                delNode1 = prevNode1.next;
                result = delNode1.item;
                prevNode1.next = delNode1.next;
            }
            else{
                result = copyFirst.item;
                copyFirst = copyFirst.next;
            }
            size--;
            return result;
        }    
    }
      
    public static void main(String[] arg){
        RandomizedQueue<Integer>test = new RandomizedQueue<Integer>();
        for(int i = 0; i<5 ; i++)
            test.enqueue(i);
        
        for(int i : test)
            StdOut.print(i+" ");
        StdOut.println();
        StdOut.println(test.dequeue());
        for(int i : test)
            StdOut.print(i+" ");
    }
}