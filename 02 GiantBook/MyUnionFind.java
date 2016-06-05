import edu.princeton.cs.algs4.*;
import java.util.*;

public class MyUnionFind {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components
    private int maxComponentSize;
    private Set<Integer> isolated;

    public MyUnionFind(int N) {
        isolated = new HashSet<Integer>();
        maxComponentSize = 1;
        count = N;
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            isolated.add(i);
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int N = parent.length;
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N-1));
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];

            // Update maxComponentSize if needed
            if (size[rootQ] > maxComponentSize)
                maxComponentSize = size[rootQ];

        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];

            // Update maxComponentSize if needed
            if (size[rootP] > maxComponentSize)
                maxComponentSize = size[rootP];
        }

        // Update list of isolated sites
        isolated.remove(rootP);
        isolated.remove(rootQ);

        count--;
    }

    public int maxComponentSize() {
        return maxComponentSize;
    }

    public boolean isNonisolated() {
        return isolated.isEmpty();
    }

}
