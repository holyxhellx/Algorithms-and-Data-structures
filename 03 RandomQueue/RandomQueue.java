import edu.princeton.cs.algs4.*;
import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item>
{
    // Data fields
    private int N = 0; // number of items
    private Item[] a;

    // Constructor
    @SuppressWarnings("unchecked")
    public RandomQueue() // create an empty random queue
    { a = (Item[]) new Object[1]; } // set array to size 1

    // Methods
    @SuppressWarnings("unchecked")
    private void resize(int max) // resize array
    {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }
    @SuppressWarnings("unchecked")
    private void resize(Item[] a, int max, int N) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    private void exch(Item[] a, int i, int j) // exchange two items in the array
    {
        Item t = a[i];
        a[i] = a [j];
        a[j] = t;
    }

    public boolean isEmpty() // is it empty?
    { return N == 0; }

    public int size() // return the number of elements
    { return N; }

    public void enqueue(Item item) // add an item
    {
        if (N == a.length) resize(2*a.length); // resize
        a[N++] = item;
    }

    public Item sample() // return (but do not remove) a random item
    {
        int r = StdRandom.uniform(0, N); // random value in [0,N)
        return a[r];
    }

    public Item dequeue() // remove and return a random item
    {
        int r = StdRandom.uniform(0, N); // random value in [0,N)
        exch(a, r, (N-1)); // exchange random item with last item
        Item item = a[--N]; // set new last item as the item to be returned
        a[N] = null; // avoid loitering, set old last item to null
        if (N > 0 && N == a.length/4) resize(a.length/2); // resize
        return item;
    }

    private Item dequeue(Item[] a, int N) {
        int r = StdRandom.uniform(0, N);
        exch(a, r, (N-1));
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length/4) resize(a, a.length/2, N);
        return item;
    }

    public Iterator<Item> iterator() // return an iterator over the items in random order
    { return new RandomIterator(); }

    private class RandomIterator implements Iterator<Item>
    {
        private int i = N;
        Item[] b = a.clone();

        public boolean hasNext()
        {
            return i > 0;
        }

        public Item next()
        {
            //completely ugly implementation, but it works :)
            return dequeue(b, i--); 
        }
    }



    // The main method below tests your implementation. Do not change it.
    public static void main(String args[])
    {
    // Build a queue containing the Integers 1,2,...,6:
    RandomQueue<Integer> Q= new RandomQueue<Integer>();
    for (int i = 1; i < 7; ++i) Q.enqueue(i); // autoboxing! cool!

    // Print 30 die rolls to standard output
    StdOut.print("Some die rolls: ");
    for (int i = 1; i < 30; ++i) StdOut.print(Q.sample() +" ");
    StdOut.println();

    // Let's be more serious: do they really behave like die rolls?
    int[] rolls= new int [10000];
    for (int i = 0; i < 10000; ++i)
      rolls[i] = Q.sample(); // autounboxing! Also cool!
    StdOut.printf("Mean (should be around 3.5): %5.4f\n", StdStats.mean(rolls));
    StdOut.printf("Standard deviation (should be around 1.7): %5.4f\n",
      StdStats.stddev(rolls));

    // Now remove 3 random values
    StdOut.printf("Removing %d %d %d\n", Q.dequeue(), Q.dequeue(), Q.dequeue());
    // Add 7,8,9
    for (int i = 7; i < 10; ++i) Q.enqueue(i);
    // Empty the queue in random order
    while (!Q.isEmpty()) StdOut.print(Q.dequeue() +" ");
    StdOut.println();

    // Let's look at the iterator. First, we make a queue of colours:
    RandomQueue<String> C= new RandomQueue<String>();
    C.enqueue("red"); C.enqueue("blue"); C.enqueue("green"); C.enqueue("yellow");

    Iterator I= C.iterator();
    Iterator J= C.iterator();

    StdOut.print("Two colours from first shuffle: "+I.next()+" "+I.next()+" ");

    StdOut.print("\nEntire second shuffle: ");
    while (J.hasNext()) StdOut.print(J.next()+" ");

    StdOut.print("\nRemaining two colours from first shuffle: "+I.next()+" "+I.next()+"\n");
    }
}
