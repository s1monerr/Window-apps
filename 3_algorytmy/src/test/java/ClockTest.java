import Exceptions.NegativeNumberException;
import Exceptions.OutOfRangeException;
import Problem303Clock.Clock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClockTest {
    private Clock clock;

    @Before
    public void init(){
        this.clock = new Clock();
    }

    @Test
    public void solutionTest(){
        assertEquals(119, clock.solution(12,20));
        assertEquals(0, clock.solution(12,00));
        assertEquals(2, clock.solution(6,30));
        assertNotEquals(20, clock.solution(7,30));
    }

    @Test
    public void expectedExceptionTest(){
        assertThrows(OutOfRangeException.class, ()->clock.solution(13,20));
        assertThrows(OutOfRangeException.class, ()->clock.solution(10,61));
        assertThrows(NegativeNumberException.class, ()->clock.solution(-1,10));
        assertThrows(NegativeNumberException.class, ()->clock.solution(1,-1));
        NegativeNumberException thrown = assertThrows(NegativeNumberException.class, ()->clock.solution(-1,-1));
        assertTrue(thrown.getMessage().toLowerCase().contains("negative"));
    }
}
