import edu.princeton.cs.algs4.*;
import java.lang.Math.*;

public class Congress {

    public static void main(String[] args) {
        StdOut.println("=====");

        int numberOfStates = Integer.parseInt(StdIn.readLine());
        int numberOfSeats = Integer.parseInt(StdIn.readLine()) - numberOfStates;

        MaxPQ<State> pq = new MaxPQ<State>(numberOfStates, new StateComparator());
        while (StdIn.hasNextLine()) {
            String stateName = StdIn.readLine();
            int statePopulation = Integer.parseInt(StdIn.readLine());
            int currentSeat = 1;
            int priorityValue = calculatePriorityValue(statePopulation, currentSeat);
            pq.insert(new State(stateName, currentSeat, statePopulation, priorityValue));
        }

        while (numberOfSeats > 0) {
            State currentState = pq.delMax();
            int newSeats = currentState.seats + 1;
            int priorityValue = calculatePriorityValue(currentState.population, newSeats);
            pq.insert(new State(currentState.name, newSeats, currentState.population, priorityValue));
            numberOfSeats--;
        }

        for (State element : pq) {
            StdOut.println(element.name + " " + element.seats);
        }

        StdOut.println("=====");
    }

    private static int calculatePriorityValue(int statePopulation, int currentSeat) {
        return (int) (statePopulation / (Math.sqrt(currentSeat * (currentSeat + 1))));
    }
}