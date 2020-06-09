public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        if(pattern.length() > input.length()){
            return -1;
        }
        int index;
        RollingString rollingPattern = new RollingString(pattern,pattern.length());
        int hashOfPattern = rollingPattern.hashCode();
        String partOfInput = input.substring(0, pattern.length());
        RollingString rollingPart = new RollingString(partOfInput,partOfInput.length());
        int hashOfPart = rollingPart.hashCode();
        for(index = 0;index < (input.length()-pattern.length()+1);index++){
            if(hashOfPart == hashOfPattern){
                return index;
            }
            if(index + pattern.length() <= input.length()-1) {
                hashOfPart = (int) ((RollingString.UNIQUECHARS * (hashOfPart - input.charAt(index) * Math.pow(RollingString.UNIQUECHARS, pattern.length() - 1)%RollingString.PRIMEBASE) + input.charAt(index + pattern.length()))%RollingString.PRIMEBASE);
            }
        }
        return -1;
    }

}
