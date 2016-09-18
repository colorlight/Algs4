import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
public class Subset{
    public static void main(String[] args){
        String[] inArray;
        String output;
        String argument = args[0];
        int k = Integer.parseInt(argument);
        RandomizedQueue<String> test = new RandomizedQueue<String>();
     
        inArray = StdIn.readAllStrings();
        for(int i = 0; i<inArray.length; i++)
            test.enqueue(inArray[i]);
   
        for(int i = 0; i<k; i++){
            output = test.dequeue();
            StdOut.println(output);
        }
    }
}