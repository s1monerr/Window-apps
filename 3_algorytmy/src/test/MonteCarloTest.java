import Exceptions.NegativeNumberException;
import Problem14MonteCarloPi.MonteCarloPi;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class MonteCarloTest {
    public MonteCarloPi monteCarloPi;

    @Before
    public void init(){monteCarloPi = new MonteCarloPi();}

    @Test
    public void positiveResultTest(){
        for(int i = 1; i < 10; i++) {
            System.out.println("monte = "+i+ monteCarloPi.solution(i));
            boolean tempValue = monteCarloPi.solution(i) > 0;
            assertTrue(tempValue);
        }
    }

    @Test
    public void expectedExceptionTest(){
        NegativeNumberException thrown = assertThrows(NegativeNumberException.class, ()->monteCarloPi.solution(-1));
        assertTrue(thrown.getMessage().toLowerCase().contains("negative"));
    }

}
