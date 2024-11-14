package edu.grinnell.csc207.util;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of associative arrays, which store key-value pairs
 * and allow lookup of values by key. The associative array is implemented
 * as a dynamically expanding array of {@link KVPair} objects.
 *
 * @param <K> the type of keys maintained by this associative array
 * @param <V> the type of mapped values
 *
 * @author Moise Milenge
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  private static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key-value pairs).
   */
  private int size;

  /**
   * The array of key-value pairs.
   */
  private KVPair<K, V>[] pairs;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a new, empty associative array with the default capacity.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    this.pairs = (KVPair<K, V>[]) newInstance(KVPair.class, DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()


  /**
   * Returns a string representation of the associative array.
   *
   * @return a string representation of this associative array
   */
// +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Creates a copy of this associative array, including all key-value pairs.
   *
   * @return a new copy of the associative array
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> newArray = new AssociativeArray<>();
    for (int i = 0; i < this.size; i++) {
      if (this.pairs[i] != null) {
        try {
          newArray.set(this.pairs[i].getKey(), this.pairs[i].getValue());
        } catch (NullKeyException e) {
          System.err.println("Warning: Attempted to clone with a null key.");
        }
      }
    }
    return newArray;
  } // clone()
  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Sets the value associated with the specified key.
   *
   * @param key the key to be added or updated
   * @param value the value to be associated with the specified key
   * @throws NullKeyException if the specified key is null
   */
  public void set(K key, V value) throws NullKeyException {
    if (key == null) {
      throw new NullKeyException("Key cannot be null.");
    }

    for (int i = 0; i < this.size; i++) {
      if (this.pairs[i] != null && this.pairs[i].getKey().equals(key)) {
        this.pairs[i].setValue(value);
        return;
      }
    }

    if (this.size >= this.pairs.length) {
      this.expand();
    }
    this.pairs[this.size] = new KVPair<>(key, value);
    this.size++;
  } // set(K, V)

  /**
   * Gets the value associated with the specified key.
   *
   * @param key the key whose associated value is to be returned
   * @return the value associated with the specified key
   * @throws KeyNotFoundException if the key does not exist in the associative array
   */
  public V get(K key) throws KeyNotFoundException {
    int index = this.find(key);
    return this.pairs[index].getValue();
  } // get(K)

  /**
   * Checks if the associative array contains the specified key.
   *
   * @param key the key to be checked for existence
   * @return {@code true} if the key is found, {@code false} otherwise
   */
  public boolean hasKey(K key) {
    try {
      this.find(key);
      return true;
    } catch (KeyNotFoundException e) {
      return false;
    }
  } // hasKey(K)

  /**
   * Removes the key-value pair associated with the specified key.
   *
   * @param key the key whose key-value pair is to be removed
   */
  public void remove(K key) {
    try {
      int index = this.find(key);
      for (int i = index; i < this.size - 1; i++) {
        this.pairs[i] = this.pairs[i + 1];
      }
      this.pairs[this.size - 1] = null;
      this.size--;
    } catch (KeyNotFoundException e) {
      // Key not found, do nothing.
    }
  } // remove(K)

  /**
   * Returns the number of key-value pairs in the associative array.
   *
   * @return the number of key-value pairs in this associative array
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expands the underlying array to accommodate more key-value pairs.
   */
  private void expand() {
    KVPair<K, V>[] newPairs = (KVPair<K, V>[]) newInstance(
        this.pairs.getClass().getComponentType(), this.pairs.length * 2);
    System.arraycopy(this.pairs, 0, newPairs, 0, this.pairs.length);
    this.pairs = newPairs;
  } // expand()

  /**
   * Finds the index of the key-value pair containing the specified key.
   *
   * @param key the key to find in the associative array
   * @return the index of the key-value pair containing the key
   * @throws KeyNotFoundException if the key is not found in the associative array
   */
  private int find(K key) throws KeyNotFoundException {
    for (int i = 0; i < this.size; i++) {
      if (this.pairs[i] != null && this.pairs[i].getKey().equals(key)) {
        return i;
      }
    }
    throw new KeyNotFoundException("Key not found.");
  } // find(K)

     // Additional methods

} // class AssociativeArray
