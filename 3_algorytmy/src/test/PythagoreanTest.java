import Exceptions.EmptyArrayException;
import Problem282Pythogorean.Pythogorean;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class PythagoreanTest {
    public Pythogorean pythogorean;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void init(){
        this.pythogorean = new Pythogorean();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void solutionTest(){
        int[] arrayTrue = {3,4,5,1,2};
        assertTrue(pythogorean.solution(arrayTrue));
        assertTrue(outputStream.toString().contains("3")&&
                outputStream.toString().contains("4")&&
                outputStream.toString().contains("5"));
        int[] arrayFalse = {3,4,1,2};
        assertFalse(pythogorean.solution(arrayFalse));
    }

    @Test
    public void expectedExceptionTest(){
        int[] emptyArray = {};
        EmptyArrayException thrown = assertThrows(EmptyArrayException.class, ()->pythogorean.solution(emptyArray));
        assertTrue(thrown.getMessage().toLowerCase().contains("empty"));
    }

}
