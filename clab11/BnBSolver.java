import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.List;


/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {

    List<Bear> bears;
    List<Bed> beds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        // TODO: Fix me.
        this.bears = new ArrayList<>();
        this.beds = new ArrayList<>();
        Queue<Bear> bearsQueue = new Queue<>();
        Queue<Bed> bedQueue = new Queue<>();
        for (Bear i:bears){
            bearsQueue.enqueue(i);
        }
        for (Bed i:beds){
            bedQueue.enqueue(i);
        }
        Queue<Pair<Bear, Bed>> p = quickSort(bearsQueue,bedQueue);

        for (Pair<Bear,Bed> i: p){
            this.bears.add(i.first());
            this.beds.add(i.second());
        }


    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.

        return bears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        // TODO: Fix me.
        return beds;
    }

    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     *
     * @param q1  A Queue of items
     * @param q2  A Queue of items
     * @return    A Queue containing the items of
     *            q1 followed by the items of q2.
     */
    private static Queue<Pair<Bear,Bed>> catenate(Queue<Pair<Bear,Bed>> q1, Queue<Pair<Bear,Bed>> q2) {
        Queue<Pair<Bear,Bed>> catenated = new Queue<Pair<Bear,Bed>>();
        for (Pair<Bear,Bed> item : q1) {
            catenated.enqueue(item);
        }
        for (Pair<Bear,Bed> item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }


    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }


    private static  void partitionBear(
            Queue<Bear> unsorted, Bed pivot,
            Queue<Bear> less, Queue<Bear> equal, Queue<Bear> greater) {
        // Your code here!
        for (Bear i:unsorted) {
            int cmp = i.compareTo(pivot);
            if(cmp < 0 ){
                less.enqueue(i);
            } else if(cmp > 0){
                greater.enqueue(i);
            } else {
                equal.enqueue(i);
            }

        }

    }

    private static  void partitionBed(
            Queue<Bed> unsorted, Bear pivot,
            Queue<Bed> less, Queue<Bed> equal, Queue<Bed> greater) {
        // Your code here!
        for (Bed i:unsorted) {
            int cmp = i.compareTo(pivot);
            if(cmp < 0 ){
                less.enqueue(i);
            } else if(cmp > 0){
                greater.enqueue(i);
            } else {
                equal.enqueue(i);
            }

        }

    }


    private static Queue<Pair<Bear, Bed>> quickSort(
            Queue<Bear> bears, Queue<Bed> beds) {
        // Your code here!
        if(bears.size() == 0&&beds.size()==0){
            Queue<Pair<Bear,Bed>> q = new Queue<>();
            return q;
        }
        if(bears.size() == 1&&beds.size()==1){
            Queue<Pair<Bear, Bed>> q = new Queue<>();
            q.enqueue(new Pair<>(bears.dequeue(),beds.dequeue()));
            return q;
        }

        Queue<Bear> lessBear = new Queue<>();
        Queue<Bear> greaterBear = new Queue<>();
        Queue<Bear> equalBear = new Queue<>();
        Queue<Bed> lessBed = new Queue<>();
        Queue<Bed> greaterBed = new Queue<>();
        Queue<Bed> equalBed = new Queue<>();

        Bed pivotBed = getRandomItem(beds);
        Bear pivotBear = new Bear(pivotBed.getSize());

        partitionBear(bears,pivotBed,lessBear,equalBear,greaterBear);
        partitionBed(beds,pivotBear,lessBed,equalBed,greaterBed);

        Queue<Pair<Bear, Bed>> less = quickSort(lessBear,lessBed);
        Queue<Pair<Bear, Bed>> greater = quickSort(greaterBear, greaterBed);
        Queue<Pair<Bear, Bed>> equal = new Queue<>();
        equal.enqueue(new Pair<>(equalBear.dequeue(),equalBed.dequeue()));
        Queue<Pair<Bear, Bed>> sorted = catenate(less, equal);
        Queue<Pair<Bear, Bed>> toReturn = catenate(sorted,greater);

        return toReturn;
    }
}
