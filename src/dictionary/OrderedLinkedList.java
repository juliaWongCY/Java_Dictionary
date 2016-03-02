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

  private OrderedLinkedListEntry<K, V> head;
  private int numElems;

  public OrderedLinkedList(OrderedLinkedListEntry<K, V> head){
    this.head = head;
  }

  @Override
  public int size() {
    return numElems;
  }

  @Override
  public boolean isEmpty() {
    return (numElems == 0);
  }

  @Override
  public V get(K key) throws NoSuchElementException {
    OrderedLinkedListEntry<K, V> next = head.getNext();
    if(head == null){
      throw new NoSuchElementException(
          "the given key has no associated value");
    } else if (head.getKey() == key){
      return head.getValue();
    } else {
      while(next != null && next.getKey().compareTo(key) < 0){
        head = next;
        next = next.getNext();
      }
      if(next != null && next.getKey().equals(key)){
        return next.getValue();
      }
    }
    return null;
  }

  @Override
  public void put(K key, V value) {
    OrderedLinkedListEntry<K, V> previous = findPrev(key);
      if(previous == null){
        //this.head = previous; //TODO: Check
        this.put(key, value);
    } else if(previous.getKey() == key){
        previous.setValue(value);
      } else if(previous.getKey().compareTo(key)< 0){
        OrderedLinkedListEntry<K, V> afterPrev
          = new OrderedLinkedListEntry<>(key, value);
        this.put(key, value);//TODO
      } else {
        this.put(key, value);
      }
  }

  private OrderedLinkedListEntry<K, V> findPrev(K searchKey){
    OrderedLinkedListEntry<K, V> prev = head;
    if( (prev != null) && (prev.getKey().compareTo(searchKey)<0)){
      OrderedLinkedListEntry<K, V> curr = prev.getNext();

      while((curr != null) && (curr.getKey().compareTo(searchKey)<= 0)){
        prev = curr;
        curr = curr.getNext();
      }
    }
    return prev;
  }

  @Override
  public void remove(K key) throws NoSuchElementException {

  }

  @Override
  public void clear() {

  }

  @Override
  public Iterator<DictionaryEntry<K, V>> iterator() {
    return null;
  }
}
