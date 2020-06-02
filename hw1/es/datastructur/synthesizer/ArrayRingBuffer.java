package es.datastructur.synthesizer;
import java.util.Iterator;



public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {

        rb =  (T[])new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * return size of the buffer
     */
    @Override
    public int capacity(){

        return rb.length;
    }

    /**
     * return number of items currently in the buffer
     */
    @Override
    public int fillCount(){

        return fillCount;
    }

    private int plusOne(int index){
        if(index < rb.length-1) {
            return index+1;
        }
        else return 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {

        if (isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = plusOne(last);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {

        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T returnItem = rb[first];
        rb[first] = null;
        first = plusOne(first);
        fillCount -=1;
        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {

        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator<T> implements Iterator<T>{

        private int wizard ;

        public ArrayRingBufferIterator(){
            wizard = first;
        }

        @Override
        public T next() {
            int next = wizard;
            wizard = plusOne(wizard);
            return (T) rb[next];
        }

        @Override
        public boolean hasNext() {
            return rb[wizard] != null;
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if(o == null){
            return false;
        }
        if (this.getClass()!=o.getClass()){
            return false;
        }
        ArrayRingBuffer arb = (ArrayRingBuffer) o;
        if(this.fillCount != arb.fillCount){
            return false;
        }
        Iterator arbIterator = arb.iterator();
        Iterator thisIterator = this.iterator();
        while (arbIterator.hasNext()&&thisIterator.hasNext()){
            if(arbIterator.next()!=thisIterator.next()){
                return false;
            }
        }
        return true;


    }


}
    // TODO: Remove all comments that say TODO when you're done.
