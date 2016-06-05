public class StateAsger implements Comparable<StateAsger> {
    String name;
    int population;
    int seat = 1;

    public StateAsger(String name, int population) {
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

    public int compareTo(StateAsger state) {
        if (this.getPriority() < state.getPriority()) {
            return -1;
        } else if (this.getPriority() > state.getPriority()) {
            return 1;
        } else {
            return 0;
        }
//        return (int) Math.round(state.getPriority() - this.getPriority());
    }
}
