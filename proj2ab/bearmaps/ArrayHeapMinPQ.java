package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<PriorityNode> minHeap;
    private HashMap<T,Integer> itemToIndex;

    public ArrayHeapMinPQ(){
        minHeap = new ArrayList<>();
        itemToIndex = new HashMap<>();
    }

    private class PriorityNode implements Comparable<PriorityNode>{

        private T item;
        private double priority;

        public PriorityNode(T item, double priority){
            this.item = item;
            this.priority = priority;
        }

        public T getItem(){
            return item;
        }

        public double getPriority(){
            return priority;
        }

        public void setPriority(double priority){
            this.priority = priority;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((ArrayHeapMinPQ.PriorityNode) o).getItem().equals(this.getItem());
            }
        }

        @Override
        public int hashCode(){
            return item.hashCode();
        }

        @Override
        public int compareTo(PriorityNode o) {

            return Double.compare(this.priority, o.priority);
        }
    }


    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority){
        if (contains(item)){
            throw new IllegalArgumentException();
        }
        minHeap.add(new PriorityNode(item, priority));
        itemToIndex.put(item, minHeap.size()-1);
        swimUp(minHeap.size()-1);
    }

    private void swimUp(int index){
        if(index == 0){
            return;
        }
        if(minHeap.get(index).getPriority() < minHeap.get(parent(index)).getPriority()){
            swap(index,parent(index));
            swimUp(parent(index));
        }
    }

    private int parent(int index){
        return (index-1)/2;
    }

    private void swap(int index1, int index2){
        PriorityNode temp = minHeap.get(index2);
        minHeap.set(index2, minHeap.get(index1));
        minHeap.set(index1,temp);
        itemToIndex.put(minHeap.get(index1).item,index1);
        itemToIndex.put(minHeap.get(index2).item,index2);
    }


    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item){
        if(item == null){
            throw new IllegalArgumentException();
        }
        return itemToIndex.containsKey(item);
    }


    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return minHeap.get(0).item;
    }

    private boolean isEmpty(){
        return minHeap.isEmpty();
    }



    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        T itemToReturn = minHeap.get(0).item;
        swap(0,minHeap.size()-1);
        minHeap.remove(minHeap.size()-1);
        itemToIndex.remove(itemToReturn);
        sinkDown(0);
        return itemToReturn;
    }

    private void sinkDown(int index){
        if(leftChildren(index)>=minHeap.size()){
            return;
        }
        if(leftChildren(index)<minHeap.size()&&rightChildren(index)>=minHeap.size()){
            if(minHeap.get(index).priority > minHeap.get(leftChildren(index)).priority){
                swap(index,leftChildren(index));
                sinkDown(leftChildren(index));
            }
            return;
        }
        int smallerChildrenIndex = minHeap.get(leftChildren(index)).priority > minHeap.get(rightChildren(index)).priority ? rightChildren(index) : leftChildren(index);
        if(minHeap.get(smallerChildrenIndex).priority < minHeap.get(index).priority){
            swap(index,smallerChildrenIndex);
            sinkDown(smallerChildrenIndex);
        }
        return;
    }

    private int leftChildren(int index){
        return 2*index+1;
    }

    private int rightChildren(int index){
        return 2*(index+1);
    }

    /* Returns the number of items in the PQ. */
    public int size(){
        return minHeap.size();
    }


    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority){
        if(!contains(item)){
            throw new NoSuchElementException();
        }
        int index = itemToIndex.get(item);
        double oldPriority = minHeap.get(index).getPriority();
        minHeap.get(index).setPriority(priority);
        if(oldPriority > priority){
            swimUp(index);
        }
        if (oldPriority < priority){
            sinkDown(index);
        }

    }

}
