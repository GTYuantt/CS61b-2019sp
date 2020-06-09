
import java.util.LinkedList;
import java.util.Queue;

/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString{

    private int length;
    private Queue<Character> rollingString;

    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;

    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s, int length) {
        assert(s.length() == length);
        /* FIX ME */
        this.length = length;
        rollingString = new LinkedList<>() ;
        for (int i = 0;i < length;i++){
            char c = s.charAt(i);
            rollingString.add(c);
        }
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        /* FIX ME */
        rollingString.add(c);
        rollingString.remove();
    }


    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() {
        StringBuilder strb = new StringBuilder();
        /* FIX ME */
        for (char c:rollingString){
            strb.append(c);
        }
        String toReturn = strb.toString();
        return toReturn;
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        /* FIX ME */
        return length;
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        /* FIX ME */
        if(this == o){
            return true;
        }
        if(o == null){
            return false;
        }
        if (o.getClass() != this.getClass()){
            return false;
        }
        RollingString s = (RollingString) o;
        if(s.length != this.length){
            return false;
        }
        return this.toString().equals(s.toString());
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        int hashCode = 0;
        for (char c:rollingString){
            hashCode = hashCode * UNIQUECHARS + c;
        }
        hashCode = hashCode % PRIMEBASE;
        return hashCode;
    }
}
