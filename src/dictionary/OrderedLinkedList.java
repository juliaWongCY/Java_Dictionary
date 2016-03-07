package dictionary;

import java.util.ConcurrentModificationException;
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
    if(head == null){
      throw new NoSuchElementException(
          "the given key has no associated value");
    } else if (head.getKey() == key){
      return head.getValue();
    } else {
      OrderedLinkedListEntry<K, V> next = head.getNext();
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
      = new OrderedLinkedListEntry<>(key, value);
      if(previous == null){
        head = newNode;
        numElems++;
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
    NoSuchElementException noSuchElementException
        = new NoSuchElementException("There is zno elements with matching key.");
    if(head != null){
      if(head.getKey() == key){
        head = head.getNext();
        numElems --;
      } else {
        OrderedLinkedListEntry<K, V> node = head;
        while(node.getNext() != null && node.getNext().getKey().compareTo(key) < 0){
          node = node.getNext();
        }
        if(node.getNext() != null && node.getNext().getKey() == key){
          node.setNext(node.getNext().getNext());
          numElems--;
        } else {
          throw noSuchElementException;
        }
      }
    } else {
      throw noSuchElementException;
    }
  }

  @Override
  public void clear() {
    head = null;
    numElems = 0;
  }

 @Override
 public Iterator<DictionaryEntry<K, V>> iterator() {
   return new ListIterator();
 }

 private class ListIterator implements Iterator<DictionaryEntry<K, V>>{
   private int expectedNum = numElems;
   private OrderedLinkedListEntry<K, V> current;

   private ListIterator(){
     current = head;
   }

   @Override
   public boolean hasNext() {
     return current != null;
   }

   @Override
   public DictionaryEntry<K, V> next() {

     if (!hasNext()) {
       throw new NoSuchElementException("The list is empty");
     } else if(expectedNum != numElems) {
       throw new ConcurrentModificationException();
     }
       if (current == null) {
         return head;
       } else {
         OrderedLinkedListEntry<K, V> result = current;
         current = current.getNext();
         return result;
       }
   }

   @Override
   public void remove() {
     throw new UnsupportedOperationException();
   }
 }

}



// private int expectedNum = numElems;
// private OrderedLinkedListEntry<K, V> current;
/*
  @Override
  public Iterator<DictionaryEntry<K, V>> iterator() {
    int expectedNum = numElems;
    OrderedLinkedListEntry<K, V> current = head;
    return new Iterator<DictionaryEntry<K, V>>() {
     @Override
   public boolean hasNext() {
     return current != null;
   }

   @Override
   public DictionaryEntry<K, V> next() {

     if (!hasNext()) {
       throw new NoSuchElementException("The list is empty");
     } else if(expectedNum != numElems) {
       throw new ConcurrentModificationException();
     }
       if (current == null) {
         return head;
       } else {
         OrderedLinkedListEntry<K, V> result = current;
         current = current.getNext();
         return result;
       }
   }

   @Override
   public void remove() {
     throw new UnsupportedOperationException();
   }
    };
  }
  */