import Exceptions.NegativeNumberException;
import Problem233Fibonacci.Fibonacci;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciTest {
    public Fibonacci fibonacci;

    @Before
    public void init(){
        this.fibonacci = new Fibonacci();
    }

    @Test
    public void solutionTest(){
        assertEquals(0, fibonacci.solution(1));
        assertEquals(1, fibonacci.solution(2));
        assertEquals(1,fibonacci.solution(3));
        assertEquals(2,fibonacci.solution(4));
        assertEquals(3,fibonacci.solution(5));
        assertEquals(5,fibonacci.solution(6));
        assertEquals(377,fibonacci.solution(15));
        assertEquals(610,fibonacci.solution(16));
    }

    @Test
    public void expectedExceptionTest(){
        NegativeNumberException thrown = assertThrows(NegativeNumberException.class, ()->fibonacci.solution(-1));
        assertTrue(thrown.getMessage().toLowerCase().contains("positive"));
    }

}
