import Exceptions.EmptyStringException;
import Problem157Palindrome.Palindrome;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeTest {
    public Palindrome palindrome;

    @Before
    public void init(){
        palindrome = new Palindrome();
    }

    @Test
    public void solutionTest(){
        boolean resultTrue = palindrome.solution("abbcbba");
        boolean resultFalse = palindrome.solution("szymonewilak");
        assertTrue(resultTrue);
        assertFalse(resultFalse);
    }

    @Test
    public void expectedExceptionTest(){
        EmptyStringException thrown = assertThrows(EmptyStringException.class, ()->palindrome.solution(""));
        assertTrue(thrown.getMessage().toLowerCase().contains("empty"));
    }

}
