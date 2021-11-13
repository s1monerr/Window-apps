package Problem14MonteCarloPi;

import Exceptions.NegativeNumberException;

import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloPi {
    private double circlePoints;
    private double squarePoints;
    private final double RADIUS = 1;

    public MonteCarloPi(){
        circlePoints = 0;
        squarePoints = 0;
    }

    public Double solution(int iterations) throws NegativeNumberException{
       // try {
            if (iterations < 0)
                throw new NegativeNumberException("Error: negative iterations number");
            for (int i = 0; i < iterations; i++) {
                // generate 2 values <0 ; 4>
                double x = ThreadLocalRandom.current().nextDouble(-1, 1);
                double y = ThreadLocalRandom.current().nextDouble(-1, 1);
                Point point = new Point(x, y);
                squarePoints++; // each point belongs to the square
                if (x * x + y * y < RADIUS) // if circle equation satisfied
                    circlePoints++;
            }

            System.out.println("square = " + squarePoints + " circle = " + circlePoints);
            return (4 * circlePoints) / squarePoints;
       // }catch(NegativeNumberException e){
         //   System.err.println("Caught exception: "+e);
//            return null;
        //}
    }

}
