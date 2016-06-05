import java.util.PriorityQueue;
import java.util.Comparator;
import edu.princeton.cs.algs4.*;

public class Congress {

    public static void main(String[] args) {
        StdOut.println("=====");

        int numberOfStates = StdIn.readInt(); //2
        int numberOfSeats = StdIn.readInt() - numberOfStates; //4-2

        PriorityQueue<States> pq = new PriorityQueue<States>(numberOfStates, new SeatComparator());
        for (int i = 0; i < numberOfStates; i++) {
            String tmpName = StdIn.readString();
            StdOut.println(i);
            int tmpPopulation = StdIn.readInt();
            States state = new States(tmpPopulation, tmpName, 1);
            pq.add(state);
        } 

        for (int i = numberOfSeats; i > 0; i--) {
            States head = pq.poll();
            head.numberOfRemainingResidents -= 10;
            head.seatsReceived += 1;
            pq.add(head);
        }

        for (States element : pq) {
            StdOut.println(element.name + " " + element.seatsReceived);
        }

        StdOut.println("=====");
    }
}

class SeatComparator implements Comparator<States> {
    @Override
    public int compare(States x, States y) {
        if (x.numberOfRemainingResidents < y.numberOfRemainingResidents) {
            return -1;
        }
        if (x.numberOfRemainingResidents > y.numberOfRemainingResidents) {
            return 1;
        }
        return 0;
    }
}

class States {
    int numberOfRemainingResidents;
    String name;
    int seatsReceived;

    States(int val1, String val2, int val3) {
        this.numberOfRemainingResidents = val1;
        this.name = val2;
        this.seatsReceived = val3;
    }
}