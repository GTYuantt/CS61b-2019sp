public class LinkedListDeque<T> {

    private class TNode{
        private TNode prev;
        private T item;
        private TNode next;

        private TNode(TNode p, T i, TNode n){
            prev=p;
            item=i;
            next=n;
        }
    }

    private int size;
    private TNode Sentinel;

    /*Creates an empty linked list deque.*/
    public LinkedListDeque(){
        size=0;
        Sentinel=new TNode(null, null, null);
        Sentinel.next=Sentinel;
        Sentinel.prev=Sentinel;
    }

    /*Creates a deep copy of other*/
    public LinkedListDeque(LinkedListDeque other){
        this();
        TNode p=other.Sentinel.next;
        for(int i=0;i<other.size;i++){
            this.addLast(p.item);
            p=p.next;
        }
    }


    /*Same as get, but uses recursion.*/
    public T getRecursive(int index){
        if(index<0||index>=size){
            return null;
        }
        return getRecursive(index,Sentinel.next);
    }

    private T getRecursive(int index, TNode curr) {
        if(index==0) return curr.item;
        return getRecursive(index-1,curr.next);
    }


    /*Adds an item of type T to the front of the deque. */
    public void addFirst(T item){
        Sentinel.next=new TNode(Sentinel, item, Sentinel.next);
        Sentinel.next.next.prev=Sentinel.next;
        size+=1;
    }

    /*Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        Sentinel.prev.next=new TNode(Sentinel.prev, item, Sentinel);
        Sentinel.prev=Sentinel.prev.next;
        size+=1;
    }

    /*Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty(){
        if(size==0) return true;
        return false;
    }

    /*Returns the number of items in the deque.*/
    public int size(){
        return size;
    }

    /**Prints the items in the deque from first to last,
     *separated by a space. Once all the items have been printed,
     * print out a new line.
     */
    public void printDeque(){
        TNode p=Sentinel.next;
        for (int i=1;i<=size;i++){
            System.out.print(p.item+" ");
            p=p.next;
        }
        System.out.println();
    }

    /**Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst(){
        TNode p=Sentinel.next;
        Sentinel.next=Sentinel.next.next;
        Sentinel.next.prev=Sentinel;
        if(!isEmpty()){
            size-=1;
        }
        return p.item;
    }

    /**Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast(){
        TNode p=Sentinel.prev;
        p.prev.next=Sentinel;
        Sentinel.prev=p.prev;
        if(!isEmpty()){
            size-=1;
        }
        return p.item;
    }

    /**Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index){
        if(index<0||index>=size){
            return null;
        }
        TNode p=Sentinel;
        for (int i=0;i<=index;i++){
            p=p.next;
        }
        return p.item;
    }





}
