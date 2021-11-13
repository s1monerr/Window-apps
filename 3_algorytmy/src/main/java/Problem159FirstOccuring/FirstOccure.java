package Problem159FirstOccuring;

import Exceptions.EmptyStringException;

import java.util.Arrays;

public class FirstOccure {
    private int[] ASCII;

    public FirstOccure() {
        this.ASCII = new int[256];
        Arrays.fill(ASCII, 0);
    }

    public java.lang.Character solution(String string){
        if(string.length() == 0)
            throw new EmptyStringException("Error: string cannot be empty.");
        for (int i = 0; i < string.length(); i++) {
                ASCII[(int)(string.charAt(i))]++; // increment value in ascii array
                if(ASCII[(int)(string.charAt(i))] > 1) {
                    Arrays.fill(ASCII, 0);
                    return string.charAt(i);
                }
            }
        Arrays.fill(ASCII, 0);
        return null;
        }
    }
