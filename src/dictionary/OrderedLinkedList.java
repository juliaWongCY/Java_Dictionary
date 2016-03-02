package dictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Ordered linked list based implementation of the Dictionary
 * interface. The nodes of the list are ordered in ascending order by
 * the key-attribute of type K. Duplicate keys are not permitted.
 */
public class OrderedLinkedList<K extends Comparable<? super K>, V> implements
        Dictionary<K, V> {

  private K keys;
  private V values;

  public OrderedLinkedList(K keys, V values){
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public V get(K key) throws NoSuchElementException {
    return null;
  }

  @Override
  public void put(K key, V value) {

  }

  @Override
  public void remove(K key) throws NoSuchElementException {

  }

  @Override
  public void clear() {

  }
}
