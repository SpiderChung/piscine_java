package edu.school21.numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {

    NumberWorker worker;

    @BeforeEach
    void beforeEachMethod() {
        worker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 17, 31, 73, 127, 131})
    public void isPrimeForPrime(int number) {
        assertTrue(worker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 9, 12, 27, 63, 81, 121, 144, 169})
    public void isPimeForNotPrimes(int number) {
        assertFalse(worker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, -12, -27, -169})
    public void isPrimeForIncorrectNumber(int number) {
        assertThrows(NumberWorker.IllegalNumberException.class, () -> worker.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void checkDigitsSum(int number, int expected) {
        assertEquals(expected, worker.digitsSum(number));
    }

}
