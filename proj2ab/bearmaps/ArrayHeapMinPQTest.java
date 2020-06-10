package bearmaps;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;


public class ArrayHeapMinPQTest {

    @Test
    public void testAdd(){
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(3,4.5);
        minPQ.add(4,5);
        minPQ.add(6,1);
        minPQ.add(9,10);
        minPQ.add(7,3);
        assertEquals(5,minPQ.size());
        assertEquals(6,(int)minPQ.getSmallest());
    }

    @Test
    public void testContains(){
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(3,4.5);
        minPQ.add(4,5);
        minPQ.add(6,1);
        minPQ.add(9,10);
        minPQ.add(7,3);
        assertTrue(minPQ.contains(9));
        assertFalse(minPQ.contains(18));
    }

    @Test
    public void testGetSmallet(){
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(3,4.5);
        minPQ.add(4,5);
        minPQ.add(6,1);
        minPQ.add(9,10);
        minPQ.add(7,3);
        assertEquals(6,(int)minPQ.getSmallest());
        assertEquals(6,(int)minPQ.getSmallest());
    }

    @Test
    public void testRemoveSmallest(){
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(3,4.5);
        minPQ.add(4,5);
        minPQ.add(6,1);
        minPQ.add(9,10);
        minPQ.add(7,3);
        assertEquals(6,(int)minPQ.removeSmallest());
        assertEquals(7,(int)minPQ.removeSmallest());
        assertEquals(3,(int)minPQ.removeSmallest());
        assertEquals(4,(int)minPQ.removeSmallest());
        assertEquals(9,(int)minPQ.removeSmallest());
    }

    @Test
    public void testSize(){
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(3,4.5);
        minPQ.add(4,5);
        minPQ.add(6,1);
        minPQ.add(9,10);
        minPQ.add(7,3);
        assertEquals(5,minPQ.size());
        minPQ.add(10,10);
        assertEquals(6,minPQ.size());
    }

    @Test
    public void testChangePriority(){
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(3,4.5);
        minPQ.add(4,5);
        minPQ.add(6,1);
        minPQ.add(9,10);
        minPQ.add(7,3);
        assertEquals(6,(int)minPQ.getSmallest());
        minPQ.changePriority(9,0.5);
        assertEquals(9,(int)minPQ.getSmallest());
    }



    public static void main(String[] args) {
        NaiveMinPQ<Integer> nMinPQ = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> aMinPQ =new ArrayHeapMinPQ<>();

        long start1 = System.currentTimeMillis();
        for (int i = 0 ;i < 1000000;i++){
            nMinPQ.add(i,1000000-i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Total time for NaiveMinPQ to insert is " + (end1-start1) +"ms");

        long start2 = System.currentTimeMillis();
        for (int i = 0 ;i < 1000000;i++){
            aMinPQ.add(i,1000000-i);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time for ArrayHeapMinPQ to insert is " + (end2-start2) +"ms");


        long start3 = System.currentTimeMillis();
        for (int i = 0 ;i < 1000;i++){
            nMinPQ.changePriority(StdRandom.uniform(0,1000000),StdRandom.uniform());
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Total time for NaiveHeapMinPQ to change the priority is " + (end3-start3) +"ms");

        long start4 = System.currentTimeMillis();
        for (int i = 0 ;i < 1000;i++){
            aMinPQ.changePriority(StdRandom.uniform(0,1000000),StdRandom.uniform());
        }
        long end4 = System.currentTimeMillis();
        System.out.println("Total time for ArrayHeapMinPQ to change the priority is " + (end4-start4) +"ms");

    }

}
