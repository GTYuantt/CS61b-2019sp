import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars(){
        assertFalse(offByOne.equalChars('c','c'));
        assertFalse(offByOne.equalChars('A','b'));
        assertFalse(offByOne.equalChars('a','e'));
        assertFalse(offByOne.equalChars('z','a'));
        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('d','c'));
        assertTrue(offByOne.equalChars('&','%'));
    }


}