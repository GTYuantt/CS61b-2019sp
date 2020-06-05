import edu.princeton.cs.algs4.BST;

import java.lang.reflect.Array;
import java.util.*;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{

    private Node root;
    private int size;

    private class Node{
        K key;
        V value;
        Node left;
        Node right;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
        }
    }

    public BSTMap(){
        size = 0;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        return containsKey(key, root);
    }

    private boolean containsKey(K key, Node n){
        if(n == null) return false;
        int cmp = n.key.compareTo(key);
        if(cmp < 0){
            return containsKey(key,n.right);
        } else if(cmp > 0){
            return containsKey(key,n.left);
        } else return true;

    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        if(containsKey(key) == false){
            return null;
        }
        return get(key,root);
    }

    private V get(K key,Node n){
        int cmp = n.key.compareTo(key);
        if (cmp < 0){
            return get(key,n.right);
        }else if(cmp > 0){
            return get(key,n.left);
        }else {
            return n.value;
        }

    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        if(key == null){
            throw new IllegalArgumentException();
        }
        root = put(key,value,root);
    }

    private Node put(K key,V value,Node n){
        if (n == null){
            size+=1;
            return new Node(key,value);
        }
        int cmp = n.key.compareTo(key);
        if(cmp < 0){
            n.right = put(key,value,n.right);
        }else if(cmp > 0){
            n.left = put(key,value,n.left);
        }else {
            n.value = value;
        }
        return n;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        HashSet<K> keySet = new HashSet<>();
        for(int i=1;i<=size;i++){
            keySet.add(ithNode(i).key);
        }
        return keySet;
    }




    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key){
        if(containsKey(key)==false){
            return null;
        }
        V toRemove = get(key);
        root = remove(key, root);
        size-=1;
        return toRemove;

    }

    private Node remove(K key,Node n){
        int cmp = n.key.compareTo(key);
        if(cmp<0){
            n.right = remove(key,n.right);
        } else if(cmp>0){
            n.left = remove(key,n.left);
        } else {
            if (n.left==null&&n.right==null){
                return null;
            }
            else if(n.left==null&&n.right!=null){
                return n.right;
            }
            else if(n.left!=null&&n.right==null){
                return n.left;
            }
            else{
                Node suc = mostLeft(n.right);
                n.key = suc.key;
                n.value = suc.value;
                n.right = remove(suc.key,n.right);
                return n;
            }
        }
        return n;
    }

    private Node mostLeft(Node n){
        while (n.left!=null){
            n=n.left;
        }
        return n;
    }
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value){
        if(containsKey(key)==false){
            return null;
        }
        if(get(key)!=value){
            return null;
        }
        V toReturn = remove(key);
        return toReturn;
    }

    @Override
    public Iterator<K> iterator() {
        return new bstIterator();
    }

    private class bstIterator<K> implements Iterator<K>{

        ArrayList<Node> nodes;
        int nextPos;

        public bstIterator(){
            nodes = new ArrayList<>();
            for(int i=1;i<=size;i++){
                nodes.add(ithNode(i));
            }
            nextPos = 0;
        }


        @Override
        public boolean hasNext() {
            return nextPos < nodes.size();
        }

        @Override
        public K next() {
            K toReturn = (K) nodes.get(nextPos).key;
            nextPos++;
            return toReturn;
        }
    }

    //prints out your BSTMap in order of increasing Key
    public void printInOrder(){
        for (int i = 1;i <= size;i++){
            System.out.println(ithNode(i).key + " " + ithNode(i).value);
        }
    }

    //return the ith smallest Node
    private Node ithNode (int i){
        return ithNode(i,root);
    }

    //help method
    private Node ithNode (int i, Node n){
        if(n == null){
            throw new IllegalArgumentException();
        }
        int smallerNode = sizeOfNode(n.left);
        if (smallerNode > i-1){
            return ithNode(i,n.left);
        } else if(smallerNode < i-1){
            return ithNode(i-smallerNode-1,n.right);
        } else return n;
    }

    private int sizeOfNode(Node n){
        if(n == null){
            return 0;
        }
        return 1+sizeOfNode(n.left)+sizeOfNode(n.right);
    }

    public static void main(String[] args) {
        BSTMap<String,Integer> bstMap = new BSTMap<>();

        bstMap.put("Orange",4);
        bstMap.put("Banana",7);
        bstMap.put("Watermelon",9);
        bstMap.put("Strawberry",1);
        bstMap.put("Apple",3);

        bstMap.printInOrder();
        System.out.println();

        bstMap.remove("Apple",4);
        bstMap.remove("Watermelon");

        bstMap.printInOrder();
        System.out.println();

        int value = bstMap.remove("Apple",3);
        bstMap.printInOrder();
        System.out.println(bstMap.keySet());
        System.out.println("The value of the removed key is:"+" "+value);

        System.out.println();
        for (String s:bstMap){
            System.out.println(s);
        }


    }
}
