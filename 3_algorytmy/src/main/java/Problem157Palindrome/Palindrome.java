package Problem157Palindrome;

import Exceptions.EmptyStringException;

import java.util.Arrays;

public class Palindrome {
    // function returns true if it is possible to make a palindrome from string
    // false if non-possible
    public boolean solution(String string){
        if(string.length() == 0)
            throw new EmptyStringException("Error: string cannot be empty.");
        final int CHAR_LIMIT = 256;
        int[] odds = new int[CHAR_LIMIT];
        Arrays.fill(odds, 0);

        // fill ascii numbers to index in odds array
        for(int i = 0; i < string.length(); i++){
            odds[(int)(string.charAt(i))]++;
        }

        // find odd values
        int oddCounter = 0;
        for(int i = 0; i < CHAR_LIMIT; i++){
            // if character has even number - can be "palindromed"
            if(odds[i]%2 != 0)
                oddCounter++; // count odd characters
            if(oddCounter > 1) // return false if more than 1 odd character
                return false;
        }

        return true;
    }
}
