package dictionary;


public class OrderedLinkedListEntry<K, V> implements DictionaryEntry<K, V> {

  private K key;
  private V value;
  private OrderedLinkedListEntry<K, V> head;

  public OrderedLinkedListEntry(K key, V value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public K getKey() {
    return key;
  }

  @Override
  public V getValue() {
    return value;
  }

  public OrderedLinkedListEntry<K, V> getNext(){
    return head;
  }

  public void setValue(V newValue){
    this.value = newValue;
  }

  public void setNext(OrderedLinkedListEntry<K, V> newHead){
    head = newHead;
  }
}
