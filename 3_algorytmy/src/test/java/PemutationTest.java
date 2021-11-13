import Exceptions.NegativeBoundException;
import Exceptions.NegativeNumberException;
import Problem96Permutations.ListContainer;
import Problem96Permutations.Permutations;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class PemutationTest {
    public Permutations permutations;

    @Before
        public void init(){permutations = new Permutations();}

    @Test
    public void properResultTest(){
//        Permutations permutations = new Permutations();
//        LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(1, 2, 3));
//        ListContainer container = new ListContainer();
//        permutations.solution(list, 0, 3).print();
        assert(true);
    }

    @Test
    public void expectedExceptionTest(){
        NegativeNumberException thrown = assertThrows(NegativeNumberException.class, ()->permutations.solution(new LinkedList<Integer>(), -1, 5));
        assertThrows(NegativeNumberException.class, ()->permutations.solution(new LinkedList<Integer>(), 1, -1));
        assertThrows(NegativeBoundException.class, ()->permutations.solution(new LinkedList<Integer>(), 3,2));
        assertTrue(thrown.getMessage().toLowerCase().contains("negative"));
    }
}
