import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){
        assertTrue(palindrome.isPalindrome("AonoA"));
        assertFalse(palindrome.isPalindrome("Aonoa"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("apple"));
    }

    @Test
    public void testNewIsPalindrome(){
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("alakb",obo));
        assertTrue(palindrome.isPalindrome("cnmb",obo));
        assertTrue(palindrome.isPalindrome("",obo));
        assertTrue(palindrome.isPalindrome("s",obo));
        assertFalse(palindrome.isPalindrome("Apple",obo));
        assertFalse(palindrome.isPalindrome("Appqb",obo));
        assertTrue(palindrome.isPalindrome("%ppq&",obo));
    }
}