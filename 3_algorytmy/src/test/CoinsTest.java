import Exceptions.NegativeNumberException;
import Problem138Coins.Coins;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class CoinsTest {
    public Coins coins;

    @Before
        public void init(){coins = new Coins();}

    @Test
    public void solutionTest(){
        LinkedList<Integer> expected = new LinkedList<>(Arrays.asList(25, 25, 25, 10, 5));
        assertEquals(expected, coins.solution(90));
        assertEquals(5, coins.solution(90).size());
    }

    @Test
    public void expectedExceptionTest(){
        NegativeNumberException thrown = assertThrows(NegativeNumberException.class, ()->coins.solution(-1));
        assertTrue(thrown.getMessage().toLowerCase().contains("negative"));
    }

}
