import Exceptions.NegativeNumberException;
import Problem321Decrementation.Decrementation;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DecrementationTest {
    public Decrementation decrementation;
    public final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void init(){
        this.decrementation = new Decrementation();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void solutionTest(){
        decrementation.solution(64);
        assertTrue(outputStream.toString().toLowerCase().contains("64, 8, 4, 2, 1"));
        decrementation.solution(100);
        assertTrue(outputStream.toString().toLowerCase().contains("100, 10, 5, 4, 2, 1"));
    }

    @Test
    public void expectedExceptionTest(){
        NegativeNumberException thrown = assertThrows(NegativeNumberException.class, ()->decrementation.solution(-1));
        assertTrue(thrown.getMessage().toLowerCase().contains("negative"));
    }
}
