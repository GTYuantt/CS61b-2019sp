public class ArrayDeque <T>{

    private int size;
    private T[] items;
    private double usageRatio;
    private int nextFirst;
    private int nextLast;

    /*Creates an empty array deque.*/
    public ArrayDeque(){
        size=0;
        nextFirst=0;
        nextLast=1;
        items=(T[])new Object[8];
        usageRatio = size/items.length;
    }

    /*Creates a deep copy of other.*/
    /*Creating a deep copy means that you create an entirely new ArrayDeque, with the exact same items as other.
    However, they should be different objects,
    i.e. if you change other, the new ArrayDeque you created should not change as well.*/
    public ArrayDeque(ArrayDeque other){
        size=other.size;
        nextLast=other.nextLast;
        nextFirst=other.nextFirst;
        usageRatio=other.usageRatio;
        items=(T[]) new Object[other.items.length];
        for(int i=0;i<other.items.length;i++){
            items[i]=(T)other.items[i];
        }
    }

    private int plusOne(int index){
        if((index+1)<=(items.length-1)) return index+1;
        return 0;
    }

    private int minusOne(int index){
        if((index-1)>=0) return index-1;
        return items.length-1;
    }

    private void resize(int cap){
        T[] newItems=(T[]) new Object[cap];
        int index = plusOne(nextFirst);
        for(int i=0;i<size;i++){
            newItems[i]=items[index];
            index=plusOne(index);
        }
        nextFirst=cap-1;
        nextLast=size;
        items=newItems;
        usageRatio = size/items.length;
    }

    /*Adds an item of type T to the front of the deque.*/
    public void addFirst(T item){
        if(size == items.length){
            resize(items.length*2);
        }
        items[nextFirst]=item;
        size+=1;
        usageRatio = size/items.length;
        nextFirst=minusOne(nextFirst);
    }

    /*Adds an item of type T to the back of the deque.*/
    public void addLast(T item){
        if(size == items.length){
            resize(items.length*2);
        }
        items[nextLast]=item;
        size+=1;
        usageRatio = size/items.length;
        nextLast=plusOne(nextLast);
    }

    /*Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty(){
        return size==0;
    }

    /*Returns the number of items in the deque.*/
    public int size(){
        return size;
    }

    /*Prints the items in the deque from first to last, separated by a space.
    Once all the items have been printed, print out a new line.
     */
    public void printDeque(){
        int index=plusOne(nextFirst);
        for(int i=0;i<size;i++){
            System.out.print(items[index]+" ");
            index=plusOne(index);
        }
        System.out.println();
    }

    /*Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    public T removeFirst(){
        if(isEmpty()) return null;
        int index=plusOne(nextFirst);
        T toReturn=items[index];
        items[index]=null;
        nextFirst=plusOne(nextFirst);
        size-=1;
        usageRatio=size/items.length;
        if(items.length>=16&&usageRatio<0.25){
            resize(items.length/2);
        }
        return toReturn;
    }

    /*Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
    public T removeLast(){
        if(isEmpty()) return null;
        int index=minusOne(nextLast);
        T toReturn=items[index];
        items[index]=null;
        nextLast=minusOne(nextLast);
        size-=1;
        usageRatio=size/items.length;
        if(items.length>=16&&usageRatio<0.25){
            resize(items.length/2);
        }
        return toReturn;
    }

    /*Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index){
        if(index<0||index>size-1){
            return null;
        }
        int nowIndex = (plusOne(nextFirst)+index)%items.length;
        return items[nowIndex];
    }



}
