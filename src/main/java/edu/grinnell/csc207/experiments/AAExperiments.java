package edu.grinnell.csc207.experiments;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.AssociativeArray;
import edu.grinnell.csc207.util.KeyNotFoundException;
import edu.grinnell.csc207.util.NullKeyException;

/**
 * Experiments with Associative Arrays.
 * This class is designed to test the behaviors and functions of the
 * AssociativeArray class using various keys and values.
 * 
 */
public class AAExperiments {
  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Log and conduct a call to `set` for the associative array.
   *
   * @param pen Where to log the message.
   * @param aa  The associative array we're using.
   * @param key The key to set.
   * @param val The value to set.
   */
  public static void set(PrintWriter pen, AssociativeArray<String, String> aa, 
      String key, String val) {
    pen.printf("set(\"%s\", \"%s\") -> ", key, val);
    try {
      aa.set(key, val);
      pen.println("OK");
    } catch (NullKeyException e) {
      pen.println("[FAILED due to null key: " + e.toString() + "]");
    } catch (Exception e) {
      pen.println("[FAILED because " + e.toString() + "]");
    } // try/catch
  } // set(PrintWriter, AssociativeArray<String, String>, String, String)

  /**
   * Log and conduct a call to `get` from the associative array.
   *
   * @param pen Where to log the message.
   * @param aa  The associative array.
   * @param key The key.
   */
  public static void get(PrintWriter pen, AssociativeArray<String, String> aa, String key) {
    pen.printf("get(\"%s\") -> ", key);
    try {
      pen.println(aa.get(key));
    } catch (KeyNotFoundException e) {
      pen.println("[FAILED because key not found: " + e.toString() + "]");
    } catch (Exception e) {
      pen.println("[FAILED because " + e.toString() + "]");
    } // try/catch
  } // get(PrintWriter, AssociativeArray<String, String>, String)

  /**
   * Log and conduct a call to `hasKey` from the associative array.
   *
   * @param pen Where to log the message.
   * @param aa  The associative array.
   * @param key The key.
   */
  public static void hasKey(PrintWriter pen, AssociativeArray<String, String> aa, String key) {
    pen.printf("hasKey(\"%s\") -> ", key);
    try {
      pen.println(aa.hasKey(key));
    } catch (Exception e) {
      pen.println("[FAILED because " + e.toString() + "]");
    } // try/catch
  } // hasKey(PrintWriter, AssociativeArray<String, String>, String)

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Run experiments with the associative array to explore its behavior
   * when adding, retrieving, and checking for keys.
   *
   * @param args Command-line parameters. (Ignored.)
   *
   * @throws Exception When something goes wrong.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    // Create a new associative array to test.
    AssociativeArray<String, String> strings2strings = new AssociativeArray<>();

    // Test 1: Check for a key in an empty array.
    pen.println("Test 1: Checking for key 'k' in an empty array.");
    hasKey(pen, strings2strings, "k");

    // Test 2: Add a key-value pair and retrieve it.
    pen.println("Test 2: Adding key 'k' with value 'key'.");
    set(pen, strings2strings, "k", "key");
    pen.println("Test 2: Checking for key 'k'.");
    hasKey(pen, strings2strings, "k");
    pen.println("Test 2: Retrieving value for key 'k'.");
    get(pen, strings2strings, "k");

    // Test 3: Check behavior for a non-existing key.
    pen.println("Test 3: Checking for non-existing key 'q'.");
    hasKey(pen, strings2strings, "q");
    pen.println("Test 3: Retrieving value for non-existing key 'q'.");
    get(pen, strings2strings, "q");

    // Test 4: Handling null keys.
    pen.println("Test 4: Attempting to add a null key.");
    set(pen, strings2strings, null, "nothing");
    pen.println("Test 4: Checking for a null key.");
    hasKey(pen, strings2strings, null);
    pen.println("Test 4: Retrieving value for a null key.");
    get(pen, strings2strings, null);

    // End of experiments.
    pen.close();
  } // main(String[])
} // class AAExperiments