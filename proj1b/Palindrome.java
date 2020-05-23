public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            deque.addLast(c);
        }
        return deque;
    }


    public boolean isPalindrome(String word){
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque);
    }

    private boolean isPalindrome(Deque<Character> deque){
        if(deque.size() == 0 || deque.size() == 1){
            return true;
        }
        if(deque.removeFirst() == deque.removeLast()) {
            return isPalindrome(deque);
        }
        return false;
    }


    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque, cc);
    }

    private boolean isPalindrome(Deque<Character> deque, CharacterComparator cc){
        if (deque.size() == 0 || deque.size() == 1){
            return true;
        }
        if(cc.equalChars(deque.removeFirst(),deque.removeLast())){
            return isPalindrome(deque, cc);
        }
        return false;
    }

}
