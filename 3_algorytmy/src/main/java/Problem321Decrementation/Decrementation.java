package Problem321Decrementation;

import Exceptions.NegativeNumberException;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.*;

public class Decrementation {
    List<Integer> steps;

    public Decrementation() {
        this.steps = new LinkedList<>();
    }

    public void solution(int N) {
        if (N < 0)
            throw new NegativeNumberException("Error: value cannot be negative.");
        steps.add(N);
        while (N != 1) {
            if (findMaxDivider(N) != N)
                N = findMaxDivider(N);
            else
                N--;
            steps.add(N);
        }
        System.out.println("Steps: " + steps.toString());
    }

    private int findMaxDivider(int x) {
        int divider = x;
        // iterate from x - first value will be the greatest
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                //System.out.println("checking: i = "+i+" x/i = "+x/i);
                if (Math.max(x / i, i) < divider)
                    divider = max(x / i, i);
            }
        }
        return divider;
    }
}
