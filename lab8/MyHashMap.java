import java.util.*;

public class MyHashMap<K,V> implements Map61B<K,V>{
    /**
     * a HashSet instance variable that holds all your keys.
     */
    private HashSet<K> keySet;
    private Bucket[] buckets;
    private int size;
    private double loadFactor;

    private class Bucket<K,V>{
        private int hashcode;
        private List<Node> list;


        public Bucket(int hash){
            hashcode = hash;
            list = new LinkedList<>();
        }

    }

    private class Node{
        private K key;
        private V value;

        public Node(K k,V v){
            key = k;
            value = v;
        }
    }

    public MyHashMap(){
        keySet = new HashSet<K>();
        buckets = new Bucket[16];
        for (int i=0;i<16;i++){
            buckets[i] = new Bucket(i);
        }
        size = 0;
        this.loadFactor = 0.75;
    }

    public MyHashMap(int initialSize){
        keySet = new HashSet<K>();
        buckets = new Bucket[initialSize];
        for (int i=0;i<initialSize;i++){
            buckets[i] = new Bucket(i);
        }
        size = 0;
        this.loadFactor = 0.75;
    }

    public MyHashMap(int initialSize, double loadFactor){
        keySet = new HashSet<K>();
        buckets = new Bucket[initialSize];
        for (int i=0;i<initialSize;i++){
            buckets[i] = new Bucket(i);
        }
        size = 0;
        this.loadFactor = loadFactor;
    }

    private int hashCode(K key,int bucketLength){
        if (key == null) {
            throw new IllegalArgumentException();
        }
        // Cited from https://algs4.cs.princeton.edu/34hash/SeparateChainingHashST.java.html
        return (key.hashCode() & 0x7fffffff) % bucketLength;
    }


    /** Removes all of the mappings from this map. */
    public void clear(){
        keySet = new HashSet<K>();
        buckets = new Bucket[buckets.length];
        for (int i=0;i<buckets.length;i++){
            buckets[i] = new Bucket(i);
        }
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        int hashCode = hashCode(key,buckets.length);
        int i = 0;
        while(i < buckets[hashCode].list.size()){
            Node n = (Node)buckets[hashCode].list.get(i);
            if(n.key.equals(key)){
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        if (key == null){
            throw new IllegalArgumentException();
        }
        if(!containsKey(key)){
            return null;
        }
        int hashCode = hashCode(key,buckets.length);
        int i = 0;
        while(i < buckets[hashCode].list.size()){
            Node n = (Node)buckets[hashCode].list.get(i);
            if(n.key.equals(key)){
                return n.value;
            }
            i++;
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value){
        boolean flag = false;
        if (key == null){
            throw new IllegalArgumentException();
        }
        int hashCode = hashCode(key,buckets.length);
        int i = 0;
        while(i < buckets[hashCode].list.size()){
            Node n = (Node)buckets[hashCode].list.get(i);
            if(n.key.equals(key)){
                n.value = value;
                flag = true;
            }
            i++;
        }
        if(flag == false){
            buckets[hashCode].list.add(new Node(key,value));
            size++;
            keySet.add(key);
            if ((size/buckets.length)>loadFactor){
                resize(buckets.length*2);
            }
        }
    }

    public void resize(int newBucketsLength){
        Bucket[] newBuckets =  new Bucket[newBucketsLength];
        for (int i=0;i<newBuckets.length;i++){
            newBuckets[i] = new Bucket(i);
        }
        for(K key:keySet){
            V value = get(key);
            int hashCode = hashCode(key,newBuckets.length);
            newBuckets[hashCode].list.add(new Node(key,value));
        }
        buckets = newBuckets;
    }



    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        return keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }


    /**
     *  returns an Iterator that iterates over the stored keys.
     */
    public Iterator<K> iterator(){
        return keySet.iterator();
    }




}
