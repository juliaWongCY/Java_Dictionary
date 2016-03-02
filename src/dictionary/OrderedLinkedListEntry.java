package dictionary;


public class OrderedLinkedListEntry<K, V> implements DictionaryEntry<K, V> {
    // TODO

  private K key;
  private V value;

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
}
