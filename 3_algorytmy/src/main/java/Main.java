import Exceptions.NegativeNumberException;
import Problem138Coins.Coins;
import Problem14MonteCarloPi.MonteCarloPi;
import Problem157Palindrome.Palindrome;
import Problem159FirstOccuring.FirstOccure;
import Problem165Right.RightSmaller;
import Problem233Fibonacci.Fibonacci;
import Problem282Pythogorean.Pythogorean;
import Problem303Clock.Clock;
import Problem321Decrementation.Decrementation;
import Problem96Permutations.Permutations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.solution("gamgka"));

        MonteCarloPi monteCarloPi = new MonteCarloPi();
//        try {
//            System.out.println(monteCarloPi.solution(-50));
//        }catch(NegativeNumberException e){
//            System.err.println("Caught the exception: "+e);
//        }

        int[] array = {3, 4, 5,1,6};
        Pythogorean pythogorean = new Pythogorean();
        System.out.println(pythogorean.solution(array));

        Clock clock = new Clock();
        System.out.println(clock.solution(12,20));
        System.out.println(clock.solution(12,0));
        System.out.println(clock.solution(6,30));

        Decrementation decrementation = new Decrementation();
        decrementation.solution(64);
        decrementation.solution(100);


        Coins coins = new Coins();
        System.out.println("Coins solution: "+coins.solution(90).size());
        System.out.println(coins.solution(90).toString());

        FirstOccure firstOccure = new FirstOccure();
        System.out.println(firstOccure.solution("acbbac"));
        System.out.println(firstOccure.solution("abcdef"));

        LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(3, 4, 9, 6, 1));
        RightSmaller right = new RightSmaller();
        System.out.println(right.solution(list2).toString());

        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.solution(16));
    }
}
