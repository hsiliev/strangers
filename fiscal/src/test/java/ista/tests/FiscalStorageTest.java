package ista.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class FiscalStorageTest {

    private FiscalStorage storage;

    @Before
    public void setup() throws IOException {
        storage = new FiscalStorage();
    }

    @After
    public void cleanup() throws IOException {
        storage.reset();
    }

    @Test
    public void loadNew() throws IOException {
        assertEquals(0f, storage.load());
    }

    @Test
    public void sumPositive() throws IOException {
        storage.save(10);
        storage.save(6);
        assertEquals(16f, storage.load());
    }

    @Test
    public void sumNegative() throws IOException {
        storage.save(10);
        storage.save(-4);
        storage.save(14);
        assertEquals(20f, storage.load());
    }

    @Test
    public void resetStorage() throws IOException {
        storage.save(10);
        storage.reset();
        assertEquals(0f, storage.load());
    }
}
