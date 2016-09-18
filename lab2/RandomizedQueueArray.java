import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
public class RandomizedQueueArray<Item> implements Iterable<Item>
{
    private int N = 0;
    private int Capacity = 2;
    private Item[] s;
    public RandomizedQueueArray(){s = (Item[]) new Object[Capacity];}
    public boolean isEmpty(){return N == 0;}
    private void resize(int capacity ){
        Item[] copy = (Item[]) new Object[capacity];
        for(int i = 0; i<N; i++)
            copy[i] = s[i];
        s = copy;
    }
    
    public void enqueue(Item item){
        if(N == s.length) resize(2*s.length);
        s[N++] = item;
    }
    
    public Item dequeue(){
        Item result;
        int deleteNum;
        if(N  == s.length/4) resize(s.length / 2);
        deleteNum = StdRandom.uniform(N);
        result = s[deleteNum];
        for(int i = deleteNum+1; i<N; i++)
            s[i-1] = s[i];
        N--;
        return result;
    }
    
    public Item sample(){
        Item result;
        int selectNum;
        selectNum = StdRandom.uniform(N);
        result = s[selectNum];
        return result;
    }
    
    public Iterator<Item> iterator() {return new DequeIterator();}
    
    private class DequeIterator implements Iterator<Item>
    {
        private int current = N;
        Item item;
        public boolean hasNext() {return current !=0; }
        public void remove() {throw new java.lang.UnsupportedOperationException();}
        public Item next() 
        {
            if(hasNext() == false)
                throw new java.util.NoSuchElementException();
            item = s[current-1];
            current--;
            return item;
        }    
    }
    
    public static void main(String[] arg){
        RandomizedQueue<Integer>test = new RandomizedQueue<Integer>();
        for(int i = 0; i<10 ; i++)
            test.enqueue(i);
        
        for(int i : test)
            StdOut.print(i+" ");
        StdOut.println();
        StdOut.println(test.dequeue());
        for(int i : test)
            StdOut.print(i+" ");
    }
}