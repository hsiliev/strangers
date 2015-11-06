package ista.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.stream.LongStream;

import static junit.framework.TestCase.assertEquals;

public class FiscalStorageTest {

    private FiscalStorage storage;
    private long sumAmount;

    @Before
    public void setup() throws IOException {
        storage = new FiscalStorage();
        sumAmount = 0;
    }

    @After
    public void cleanup() throws IOException {
        saveCash(-sumAmount);
    }

    @Test
    public void loadNew() throws IOException {
        assertEquals(0, storage.load());
    }

    private void saveCash(long amount) throws IllegalStateException {
        try {
            storage.save(amount);
            sumAmount += amount;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void sumMany() throws IOException {
        LongStream stream = new Random().longs(2000);
        stream.forEach(this::saveCash);

        assertEquals(sumAmount, storage.load());
    }

}
