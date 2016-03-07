package dictionary;

// Implementation class representing nodes of the binary search tree.
public class BinarySearchTreeEntry<K, V> implements DictionaryEntry<K, V> {
  private K key;
  private V value;
  private BinarySearchTreeEntry<K, V> left;
  private BinarySearchTreeEntry<K, V> right;

  public BinarySearchTreeEntry(K key, V value) {
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

  public void setValue(V newValue){
    value = newValue;
  }

  public void setLeft(BinarySearchTreeEntry<K, V> newNode){
    left = newNode;
  }

  public void setRight(BinarySearchTreeEntry<K, V> newNode){
    right = newNode;
  }

  public BinarySearchTreeEntry<K, V> getLeft(){
    return left;
  }

  public BinarySearchTreeEntry<K, V> getRight(){
    return right;
  }
}
