import org.junit.Test;
import static org.junit.Assert.*;


public class TestArrayDequeGold {

    @Test
    public void testTask1(){

        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        //addFirst
        for(int i = 0; i < 10; i++){
            Integer randomNum = StdRandom.uniform(100);
            sad.addFirst(randomNum);
            ads.addFirst(randomNum);
        }
        for (int i = 0; i < 10; i++){
            Integer actual = sad.get(i);
            Integer expected = ads.get(i);
            assertEquals("Oh noooo!\naddFirst() is bad:\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }



        //removeFirst
        for (int i = 0; i < 10; i++){
            Integer actual = sad.removeFirst();
            Integer expected = ads.removeFirst();
            assertEquals("Oh noooo!\nremoveFirst() is bad:\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }




        //addLast
        for(int i = 0; i < 10; i++){
            Integer randomNum = StdRandom.uniform(100);
            sad.addLast(randomNum);
            ads.addLast(randomNum);
        }
        for (int i = 0; i < 10; i++){
            Integer actual = sad.get(i);
            Integer expected = ads.get(i);
            assertEquals("Oh noooo!\naddLast() is bad:\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }



        //removeLast
        for (int i = 0; i < 10; i++){
            Integer actual = sad.removeLast();
            Integer expected = ads.removeLast();
            assertEquals("Oh noooo!\nremoveLast() is bad:\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }



    }



    @Test
    public void testTask2(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        //addFirst
        for(int i=0;i<5;i++) {
            Integer randomNum = StdRandom.uniform(100);
            sad.addFirst(randomNum);
            ads.addFirst(randomNum);
            Integer expected = ads.get(0);
            Integer actual = sad.get(0);
            assertEquals("addFirst(" + randomNum + ")", expected, actual);
            System.out.println("addFirst(" + randomNum + ")");
        }

        //addLast
        for(int i=0;i<5;i++) {
            Integer randomNum1 = StdRandom.uniform(100);
            sad.addLast(randomNum1);
            ads.addLast(randomNum1);
            Integer expected1 = ads.get(1);
            Integer actual1 = sad.get(1);
            assertEquals("addLast(" + randomNum1 + ")", expected1, actual1);
            System.out.println("addLast(" + randomNum1 + ")");
        }


        //removeFirst
        for(int i=0;i<5;i++) {
            Integer expected2 = ads.removeFirst();
            Integer actual2 = sad.removeFirst();
            assertEquals("removeFirst()", expected2, actual2);
            System.out.println("removeFirst()");
        }


        //removeLast
        for(int i=0;i<5;i++) {
            Integer expected3 = ads.removeLast();
            Integer actual3 = sad.removeLast();
            assertEquals("removeLast()", expected3, actual3);
            System.out.println("removeLast()");
        }





    }


}
