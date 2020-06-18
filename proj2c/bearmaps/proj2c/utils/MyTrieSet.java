package bearmaps.proj2c.utils;

import bearmaps.proj2c.utils.TrieSet61B;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {

    private Node root;
    private int size;

    public MyTrieSet(){
        root = new Node('\0', false);
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root.map.clear();
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        Node curr = root;
        for (int i=0;i<key.length();i++){
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)){
                return false;
            } else {
                curr = curr.map.get(c);
            }
        }
        if(!curr.isKey){
            return false;
        }
        return true;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        if(!curr.isKey){
            curr.isKey = true;
            size++;
        }


    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node curr = root;
        for(int i = 0;i < prefix.length();i++){
            char c = prefix.charAt(i);
            if(!curr.map.containsKey(c)){
                return new ArrayList<>();
            }
            curr = curr.map.get(c);
        }

        List<String> toReturn = new ArrayList<>();
        if(curr.isKey){
            toReturn.add(prefix);
        }
        for (Character c: curr.map.keySet()) {
            Node currChild = curr.map.get(c);
            allKeyFromCurrNode(currChild, prefix, toReturn);
        }

        return toReturn;
    }


    private void allKeyFromCurrNode(Node n, String s, List<String> toReturn){

        s += n.curChar;
        if(n.isKey) {
            toReturn.add(s);
        }
        for (Character c:n.map.keySet()) {
            Node nchild = n.map.get(c);
            allKeyFromCurrNode(nchild, s, toReturn);
        }

    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    private class Node{
        private char curChar;
        private HashMap<Character, Node> map;
        private boolean isKey;

        public Node(char c, boolean isKey){
            curChar = c;
            this.isKey = isKey;
            map = new HashMap<>();
        }


    }


}
