import java.util.Comparator;

public class StateComparator implements Comparator<State> {
    @Override
    public int compare(State x, State y) {
        if (x.priorityValue < y.priorityValue) { return -1; }
        if (x.priorityValue > y.priorityValue) { return 1; }
        return 0;
    }
}