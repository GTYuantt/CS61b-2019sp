package bearmaps.hw4;

import bearmaps.proj2ab.DoubleMapPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    Map<Vertex, Double> distTo;
    Map<Vertex, Vertex> edgeTo;
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private double explorationTime;
    private int numStatesExplored;

    /**Constructor which finds the solution, computing everything necessary for all other methods to return
     * their results in constant time. Note that timeout passed in is in seconds.
     */
    /**In pseudocode, this memory optimized version of A* is given below:

    Create a PQ where each vertex v will have priority p equal to the sum of v’s distance from the source plus the heuristic estimate from v to the goal, i.e. p = distTo[v] + h(v, goal).
    Insert the source vertex into the PQ.
    Repeat until the PQ is empty, PQ.getSmallest() is the goal, or timeout is exceeded:
    p = PQ.removeSmallest()
    relax all edges outgoing from p
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        Stopwatch sw = new Stopwatch();
        numStatesExplored = 0;
        solution = new ArrayList<>();
        solutionWeight = 0;
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        distTo.put(start, 0.0);
        edgeTo.put(start, start);
        ExtrinsicMinPQ<Vertex> fringe = new DoubleMapPQ<>();
        fringe.add(start, input.estimatedDistanceToGoal(start, end));
        while(fringe.size()>0 && !fringe.getSmallest().equals(end) && sw.elapsedTime()<=timeout*1000){
            Vertex v = fringe.removeSmallest();
            numStatesExplored+=1;
            List<WeightedEdge<Vertex>> neighbors = input.neighbors(v);
            for (WeightedEdge<Vertex> e:neighbors){
                relax(e,fringe,input,end);
            }
        }
        if (sw.elapsedTime() > timeout*1000){
            outcome = SolverOutcome.TIMEOUT;
        }else if (fringe.size() == 0){
            outcome = SolverOutcome.UNSOLVABLE;
        }else {
            outcome = SolverOutcome.SOLVED;
            solution.add(end);
            solutionWeight = distTo.get(end);
            Vertex v = end;
            while(!edgeTo.get(v).equals(start)){
                solution.add(edgeTo.get(v));
                v=edgeTo.get(v);
            }
            if(!v.equals(start)) {
                solution.add(start);
            }
            Collections.reverse(solution);
        }

        explorationTime = sw.elapsedTime()/1000;
    }

    /**relax(e):
    p = e.from(), q = e.to(), w = e.weight()
            if distTo[p] + w < distTo[q]:
    distTo[q] = distTo[p] + w
            if q is in the PQ: changePriority(q, distTo[q] + h(q, goal))
            if q is not in PQ: add(q, distTo[q] + h(q, goal))*/
    private void relax(WeightedEdge<Vertex> edge, ExtrinsicMinPQ<Vertex> fringe, AStarGraph<Vertex> input, Vertex end){
        Vertex p = edge.from();
        Vertex q = edge.to();
        double w = edge.weight();
        if(!distTo.containsKey(q) || distTo.get(p)+w < distTo.get(q)){
            distTo.put(q,distTo.get(p)+w);
            edgeTo.put(q,p);
            if (fringe.contains(q)){
                fringe.changePriority(q,distTo.get(q)+input.estimatedDistanceToGoal(q,end));
            } else {
                fringe.add(q, distTo.get(q)+input.estimatedDistanceToGoal(q,end));
            }
        }
    }

    /**Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE.
     * Should be SOLVED if the AStarSolver was able to complete all work in the time given.
     * UNSOLVABLE if the priority queue became empty.
     * TIMEOUT if the solver ran out of time. You should check to see if you have run out of time every time you dequeue.
     */
    public SolverOutcome outcome(){
        return outcome;
    }

    /**A list of vertices corresponding to a solution. Should be empty if result was TIMEOUT or UNSOLVABLE.*/
    public List<Vertex> solution(){
        return solution;
    }


    /** The total weight of the given solution, taking into account edge weights. Should be 0 if result was TIMEOUT or UNSOLVABLE.*/
    public double solutionWeight(){
        return solutionWeight;
    }

    /**The total number of priority queue dequeue operations.*/
    public int numStatesExplored(){
        return numStatesExplored;
    }

    /** The total time spent in seconds by the constructor.*/
    public double explorationTime(){
        return explorationTime;
    }

}
