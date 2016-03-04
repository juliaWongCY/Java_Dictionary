package dictionary;

import java.util.Iterator;
import java.util.ListIterator;
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

  public OrderedLinkedList(){
    head = null;
    numElems = 0;

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
    OrderedLinkedListEntry<K, V> newNode
      = new OrderedLinkedListEntry<K, V>(key, value);
      if(previous == null){
        head = newNode;
    } else if(previous.getKey() == key){
        previous.setValue(value);
      } else if(previous.getKey().compareTo(key)< 0){
        newNode.setNext(previous.getNext());
        previous.setNext(newNode);
        numElems++;
      } else {
        newNode.setNext(head);
        head = newNode;
        numElems++;
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
    //boolean hasMatch = false;
    Iterator<DictionaryEntry<K, V>> iterators = iterator();
    OrderedLinkedListEntry<K, V> node = (OrderedLinkedListEntry<K, V>) iterators.next();

    if (head == null) {
      throw new NoSuchElementException("There's no elements with matching key");

    } else {
      while (iterators.hasNext()) {
        if (node.getKey() == key) {
          if (node != head) {
            findPrev(key).setNext(node.getNext());
            node = null;
          } else {
            head = null;
          }
        }
        numElems--;
      }
    }
  }

  @Override
  public void clear() {

  }

  @Override
  public Iterator<DictionaryEntry<K, V>> iterator() {
    return new Iterator<DictionaryEntry<K, V>>() {
      @Override
      public boolean hasNext() {
        return !isEmpty();
      }

      @Override
      public DictionaryEntry<K, V> next() {
        if (hasNext()) {
          if (head.getNext() == null) {
            return head;
          } else {
            return head.getNext();
          }
        } else {
          throw new NoSuchElementException("The list is empty");
          // TODO: ConcurrentModificationException
        }
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
}
