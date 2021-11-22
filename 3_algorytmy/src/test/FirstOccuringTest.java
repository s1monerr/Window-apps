import Exceptions.EmptyStringException;
import Problem159FirstOccuring.FirstOccure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FirstOccuringTest {
    public FirstOccure firstOccure;

    @Before
    public void init(){
        this.firstOccure = new FirstOccure();
    }

    @Test
    public void solutionTest(){
        Character expected = 'b';
        assertEquals(expected, firstOccure.solution("abcbab"));
        expected = 'z';
        assertEquals(expected, firstOccure.solution("azbcdefghijkz"));
        assertNull(firstOccure.solution("abcdef"));
    }

    @Test
    public void expectedExceptionTest(){
        EmptyStringException thrown = assertThrows(EmptyStringException.class, ()->firstOccure.solution(""));
        assertTrue(thrown.getMessage().toLowerCase().contains("empty"));
    }

}
