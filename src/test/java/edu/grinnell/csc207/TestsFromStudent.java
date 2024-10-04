package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.util.AssociativeArray;
import edu.grinnell.csc207.util.KeyNotFoundException;
import edu.grinnell.csc207.util.NullKeyException;

/**
 * Tests for the AssociativeArray class.
 *
 * @author Moise Milenge
 */
public class TestsFromStudent {

    /**
     * Test setting and getting a key-value pair.
     */
    @Test
    public void testSetAndGet() throws Exception {
        AssociativeArray<String, Integer> array = new AssociativeArray<>();
        array.set("key1", 10);
        assertEquals(10, array.get("key1"), "Should return the value set for key1");
    } // testSetAndGet()

    /**
     * Test updating an existing key-value pair.
     */
    @Test
    public void testUpdateValue() throws Exception {
        AssociativeArray<String, String> array = new AssociativeArray<>();
        array.set("key2", "value1");
        array.set("key2", "value2");
        assertEquals("value2", array.get("key2"), "Value should be updated for key2");
    } // testUpdateValue()

    /**
     * Test removing a key-value pair.
     */
    @Test
    public void testRemoveKey() throws Exception {
        AssociativeArray<String, Double> array = new AssociativeArray<>();
        array.set("key3", 3.14);
        array.remove("key3");
        assertThrows(KeyNotFoundException.class, () -> array.get("key3"), "Key should be removed");
    } // testRemoveKey()

    /**
     * Edge case test: Attempt to get a value for a non-existent key.
     */
    @Test
    public void testGetNonExistentKey() {
        AssociativeArray<String, Integer> array = new AssociativeArray<>();
        assertThrows(KeyNotFoundException.class, () -> array.get("nonexistent"), "Should throw exception for non-existent key");
    } // testGetNonExistentKey()

    /**
     * Edge case test: Attempt to set a null key.
     */
    @Test
    public void testSetNullKey() {
        AssociativeArray<String, String> array = new AssociativeArray<>();
        assertThrows(NullKeyException.class, () -> array.set(null, "some value"), "Should throw exception for null key");
    } // testSetNullKey()

    /**
     * Test checking the existence of a key.
     */
    @Test
    public void testHasKey() throws Exception {
        AssociativeArray<String, Integer> array = new AssociativeArray<>();
        array.set("key4", 20);
        assertTrue(array.hasKey("key4"), "Should return true for existing key");
        assertFalse(array.hasKey("nonexistent"), "Should return false for non-existent key");
    } // testHasKey()
    
    /**
     * Test the size of the associative array.
     */
    @Test
    public void testSize() throws Exception {
        AssociativeArray<String, String> array = new AssociativeArray<>();
        assertEquals(0, array.size(), "Size should be 0 initially");
        array.set("key5", "value5");
        assertEquals(1, array.size(), "Size should be 1 after adding one key-value pair");
        array.set("key6", "value6");
        assertEquals(2, array.size(), "Size should be 2 after adding two key-value pairs");
        array.remove("key5");
        assertEquals(1, array.size(), "Size should be 1 after removing one key-value pair");
    } // testSize()
    
} // class TestsFromStudent
