import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are <= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    PriorityQueue<Flight> minStartTimeQueue;
    PriorityQueue<Flight> minEndTimeQueue;

    /**
     * Constructor that takes in an ArrayList of Flight objects as described above.
     */
    public FlightSolver(ArrayList<Flight> flights) {
        /* FIX ME */
        //Lambada expression:
        //Comparator<Flight> startTimeComparator = (i, j) -> i.startTime() - j.startTime();
        //Comparator<Flight> endTimeComparator = (i, j) -> i.endTime() - j.endTime();
        minStartTimeQueue = new PriorityQueue<>(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return o1.startTime()-o2.startTime();
            }
        });
        minEndTimeQueue = new PriorityQueue<>(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return o1.endTime()-o2.endTime();
            }
        });
        for (Flight f:flights ){
            minStartTimeQueue.add(f);
            minEndTimeQueue.add(f);
        }
    }

    /**
     * Returns the solution to the problem described above.
     */
    public int solve() {
        /* FIX ME */
        int maxPassenger = 0;
        int totalNow = 0;
        while(minStartTimeQueue.size()!=0){
            if (minStartTimeQueue.peek().startTime() <= minEndTimeQueue.peek().endTime()){
                totalNow += minStartTimeQueue.poll().passengers();
                if (totalNow > maxPassenger){
                    maxPassenger = totalNow;
                }
            }
            else {
                totalNow -= minEndTimeQueue.poll().passengers();
            }
        }
        return maxPassenger;
    }

}
