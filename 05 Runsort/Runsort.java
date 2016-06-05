import edu.princeton.cs.algs4.*;
import java.util.Comparator;

public class Runsort {
  
    private Runsort() { } // This class should not be instantiated.

    // stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        //copy elements from original to aux array. 
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];  // this copying is unneccessary
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a, int N) {
        Comparable[] aux = new Comparable[N];

        outerloop:
        while(true)
        {
	        //StdOut.println("new round");
	        //show(a);

	        int i = 0;
	      
	        while(i<N-1) {
	        	
	        	// find first run
	            int lo = i;
	        	while(i<N-1 && ! less(a[i+1],a[i])) i++; 
	        	int m = i;
	        	if(lo == 0 && m == N-1) break outerloop; 
	        	if(m == N-1) continue; // skip to next round if there's no second run to merge with

	        	// find second run
	        	i++;
	        	while(i<N-1 && !less(a[i+1],a[i])) i++;
	        	int hi = i;

        		merge(a, aux, lo, m, hi); // merge runs

	        	// if there was only two runs before merging
	        	// there will now be one and the array will be sorted
				if(lo == 0 && hi == N-1) break outerloop; 

				i++;
	        }
        }
    }

  	/***********************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
   
    // is v < w ?
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

	private static void show(Comparable[] a)
	{
		for (int i = 0; i < a.length; i ++) StdOut.print(a[i] + " ");
		StdOut.println();
	}

	private static boolean isSorted(Comparable[] a)
	{
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i-1])) return false;
		return true;
	}

	public static void main(String[] args)
	{
		String[] a = StdIn.readAllStrings();
        int N = a.length;
        if (N > 1) {
            sort(a, N);
            StdOut.println("Is sorted: " + isSorted(a));
            //show(a);
        } else {
            StdOut.println("Nothing to sort");
            //show(a);
        }
	}
}
