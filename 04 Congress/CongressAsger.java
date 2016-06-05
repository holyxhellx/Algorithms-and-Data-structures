import java.util.Comparator;
import edu.princeton.cs.algs4.*;
import java.util.PriorityQueue;

public class CongressAsger {

	public static void main(String[] args) {
        //Create a string of array for each line in the file.
		String[] lines = new In(args[0]).readAll().split("\\n");

		int totalStates = lines[0];
		int seats = lines[1];

		PriorityQueue<State> priority = new PriorityQueue<State>();

		//Create the number of states
		for (int i = 2 ; i < lines.length; i++) {
			//Start from line 3;
			priority.add(new State(lines[i], lines[i+1]));
		}

		//All states get atleast one seat
		seats = seats - totalStates;

		//Organize the remaining seats
        for (int i = seats ; i > 0 ; i--) {
			State current = priority.poll();
			current.increaseSeat();
			seats--;
            priority.add(current);
		}

		for (int i = priority.size() ; i > 0 ; i--) {
			State current = priority.poll();
			StdOut.println(current.getName()+" "+current.getSeat());
		}
	}

    public class State {
        String name;
        int population;
        int seat = 1;
        
        public State(String name, int population) {
            name = this.name;
            population = this.population;
        }
        
        public String getName() {
            return name;
        }
        
        public int getPopulation() {
            return population;
        }
        
        public int getSeat() {
            return seat;
        }
        
        public int getPriority() {
            return (int) (population / (Math.sqrt(seat * (seat + 1))));
        }
        
        public void increaseSeat() {
            seat++;
        }
        
        public int compare(State state) {
            return (int) Math.round(state.getPriority() - this.getPriority());
        }
    }
}