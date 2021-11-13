import Problem165Right.RightSmaller;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;

public class RightTest {
    public RightSmaller rightSmaller;

    @Before
    public void init(){
        this.rightSmaller = new RightSmaller();
    }

    @Test
    public void solutionTest(){
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(3, 4, 9, 6, 1));
        LinkedList<Integer> expected = new LinkedList<>(Arrays.asList(1, 1, 2, 1, 0));
        assertEquals(expected, rightSmaller.solution(list));
    }
}
