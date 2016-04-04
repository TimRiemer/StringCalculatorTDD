package mobi.riemer.StringCalculatorTDD;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Tim Riemer
 * @version 1.0 - 20.09.15.
 */
public class StringCalculatorTest {
    @Test
    public void when2NumbersAreUsedThenNoExceptionIsThrown() throws Exception {
        StringCalculator.add("1,2");
        assertTrue(true);
    }

    @Test(expected = RuntimeException.class)
    public void whenNonNumberIsUsedThenExceptionIsThrown() throws Exception {
        StringCalculator.add("1,x");
    }

    @Test
    public void whenEmptyStringIsUsedThenReturnValueIs0() throws Exception {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void whenOneNumberIsUsedThenReturnValueIsThatSameNumber() throws Exception {
        assertEquals(3, StringCalculator.add("3"));
    }

    @Test
    public void whenTwoNumbersAreUsedThenReturnValueIsTheirSum() throws Exception {
        assertEquals(3 + 6, StringCalculator.add("3,6"));
    }

    @Test
    public void whenAnyNumberOfNumbersIsUsedThenReturnValuesAreTheirSums() throws Exception {
        assertEquals(3 + 6 + 15 + 18 + 46 + 33, StringCalculator.add("3,6,15,18,46,33"));
    }

    @Test
    public void whenNewLineIsUsedBetweenNumbersThenReturnValuesAreTheirSums() throws Exception {
        assertEquals(3 + 6 + 15, StringCalculator.add("3,6\n15"));
    }

    @Test
    public void whenDelimiterIsSpecifiedThenItIsUsedToSeparateNumbers() throws Exception {
        assertEquals(3 + 6 + 15, StringCalculator.add("//;\n3;6;15"));
    }

    @Test(expected = RuntimeException.class)
    public void whenNegativNumberIsUsedThenRuntimeExceptionIsThrown() throws Exception {
        StringCalculator.add("3,-6,15,18,46,33");
    }

    @Test
    public void whenNegativeNumbersAreUsedThenRuntimeExceptionIsThrown() throws Exception {
        RuntimeException exception = null;
        try {
            StringCalculator.add("3,-6,15,-18,46,33");
        } catch (RuntimeException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals("Negatives not allowed: [-6, -18]", exception.getMessage());
    }

    @Test
    public void whenOneOrMoreNumbersAreGreaterThan1000IsUsedThenItIsNotIncludedInSum() throws Exception {
        assertEquals(3 + 1000 + 6, StringCalculator.add("3,1000,1001,6,1234"));
    }
}
