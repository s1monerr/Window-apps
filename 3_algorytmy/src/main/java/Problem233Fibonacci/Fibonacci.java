package Problem233Fibonacci;

import Exceptions.NegativeNumberException;

import java.util.LinkedList;

public class Fibonacci {
    // first elements in Fibonacci series
    private int a;
    private int b;
    private int c;

    public Fibonacci(){
        this.a = 0;
        this.b = 1;
        this.c = 0;
    }

    public int solution(int n){
        if(n <= 0)
            throw new NegativeNumberException("Error: number index must be positive.");
        if(n == 1)
            return a;
        else if(n == 2)
            return b;

        for(int i = 0; i < n - 2; i++){ // i < n - 2 => first two iterations are already counted (a, b)

            c = a + b;
            a = b;
            b = c;
        }
        // for next function calls
        a = 0;
        b = 1;
        return c;
    }
}
