
import edu.princeton.cs.algs4.*;

public class GiantBook {

    public static void main(String[] args) {

        // Three arrays, to do statistics on later
        int T; // No. of networks (no. of times of experiment)
        // How many sites in the network?
        int N;


        if (args.length == 0) {
            T = 10;
            N = 10000;
        } else {
            T = Integer.parseInt(args[0]);
            N = Integer.parseInt(args[1]);
        }

        int[] giantArr = new int[T];
        int[] nonisolatedArr = new int[T];
        int[] connectedArr = new int[T];
        for (int j = 0; j < T; j++) {

            // A single network:

            // When did the whole network become connected?
            int connected = -1;
            // When did giant component emerge
            int giant = -1;
            // When did non-isolation happen
            int nonisolated = -1;

            MyUnionFind uf = new MyUnionFind(N);
            //StdOut.println("No. of sites: " + N);
            for (int i = 0; true; i++) {

                // Check if non-isolated
                if (uf.isNonisolated() && nonisolated == -1){
                    nonisolated = i;
                }

                // Check if giant component exists
                // Casting to double and comparing doubles, to avoid bugs when
                // e.g. N == 3, and thus 3/2 == 1.
                if ((double)(uf.maxComponentSize()) >= (N*0.5) && giant == -1){
                    giant = i;
                }

                // Stop when all sites are connected
                if (uf.count() == 1) {
                    connected = i;
                    break;
                }

                int p = StdRandom.uniform(N);
                //StdOut.print("p = " + p + " and ");
                int q = StdRandom.uniform(N);
                //StdOut.println("q = " + q);
                if (uf.connected(p, q)) continue;
                uf.union(p, q);
                //StdOut.println(p + " " + q);

            }
            // Uncomment these four lines to print results of this network
            //StdOut.println(uf.count() + " components");
            //StdOut.println("Giant emerged at operation # " + giant);
            //StdOut.println("non-isolation at operation # " + nonisolated);
            //StdOut.println("Network connected at operation # " + connected);

            // Please note:
            // Operation # is i+1, because we check at the beginning of i+1
            // instead of at the end of i, due to the way the loop is
            // constructed.
            // But, when we count as humans would, starting from 1
            // and not from 0, the result is correct.

            giantArr[j] = giant;
            nonisolatedArr[j] = nonisolated;
            connectedArr[j] = connected;

        }

        // Calculating statistic data

        StdOut.println("T: " + T);
        StdOut.println("N: " + N);
        StdOut.println("Giant mean: " + StdStats.mean(giantArr));
        StdOut.println("Giant stddev: " + StdStats.stddev(giantArr));
        StdOut.println("Nonisolated mean: " + StdStats.mean(nonisolatedArr));
        StdOut.println("Nonisolated stddev: " + StdStats.stddev(nonisolatedArr));
        StdOut.println("Connected mean: " + StdStats.mean(connectedArr));
        StdOut.println("Connected stddev: " + StdStats.stddev(connectedArr));

    }

}
