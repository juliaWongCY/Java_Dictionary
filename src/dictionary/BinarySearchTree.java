package dictionary;

//import com.sun.xml.internal.messaging.saaj.soap.impl.TreeException;

import java.util.*;

/*
 * Binary search tree based implementation of the Dictionary
 * interface. The nodes of the tree are ordered by an associated key-attribute
 * key of type K, such that every node's left subtree contains only nodes 
 * whose key-attributes are less than key, and every node's right subtree
 * contains only nodes whose key-attributes are greater than key. A
 * linear order is defined on keys through the Comparable interface.
 * Duplicate keys are not permitted.
 */
public class BinarySearchTree<K extends Comparable<? super K>, V> implements
        Dictionary<K, V> {

  private BinarySearchTreeEntry<K, V> root;
  private int numNode;

  public BinarySearchTree() {
    root = null;
    numNode = 0;
  }

  @Override
  public int size() {
    return numNode;
  }

  @Override
  public boolean isEmpty() {
    return numNode == 0;
  }

  public boolean contians(K searchKey){
    return search(root, searchKey);
  }

  public boolean search(BinarySearchTreeEntry<K, V> node, K searchKey){
    if(node == null){
      return false;
    } else if(node.getKey() == searchKey){
      return true;
    } else if(node.getKey().compareTo(searchKey) < 0){
      search(node.getLeft(), searchKey);
    } else {
      search(node.getRight(), searchKey);
    }
    return false;
  }

  @Override
  public V get(K searchKey) throws NoSuchElementException {
    return getElem(root, searchKey).getValue();
  }

  private BinarySearchTreeEntry<K, V>
    getElem(BinarySearchTreeEntry<K, V> node, K searchKey){

    if(node != null){
      K key = node.getKey();
      if(key == searchKey){
        return node;
      } else if (searchKey.compareTo(key) < 0){
        return getElem(node.getLeft(), searchKey);
      } else {
        return getElem(node.getRight(), searchKey);
      }
    }
    else {
      throw new NoSuchElementException("No such element: ");
    }
  }

  @Override
  public void put(K searchKey, V newValue) {
    root = putElem(root, searchKey, newValue);
    numNode ++;
  }

  private BinarySearchTreeEntry<K, V>
    putElem(BinarySearchTreeEntry<K, V> node, K key, V newValue){

    if(node == null){
      BinarySearchTreeEntry<K, V> newNode = new BinarySearchTreeEntry<>(key, newValue);
      node = newNode;
    } else {
      if (key == node.getKey()){
        node.setValue(newValue);
      } else if (key.compareTo(node.getKey()) < 0){
        node.setLeft(putElem(node.getLeft(), key, newValue));
      } else {
        node.setRight(putElem(node.getRight(), key, newValue));
      }
    }
    return node;
  }

  @Override
  public void remove(K searchKey) throws NoSuchElementException {
    root = deleteElem(root, searchKey);
    numNode--;
  }

  private boolean isLeaf(BinarySearchTreeEntry<K, V> node){
    return node.getLeft() == null && node.getRight() == null;
  }

  private BinarySearchTreeEntry<K, V>
    deleteElem(BinarySearchTreeEntry<K, V> node, K key){

    if(node == null){
      throw new NoSuchElementException("There is no matching elements.");
    } else if(node.getKey() == key){
      node = deleteNode(node);
    } else if(node.getKey().compareTo(key) > 0){
      node.setLeft(deleteElem(node.getLeft(), key));
    } else {
      node.setRight(deleteElem(node.getRight(), key));
    }
    return node;
  }
  private BinarySearchTreeEntry<K, V>
    deleteNode(BinarySearchTreeEntry<K, V> node){

    if(isLeaf(node)){
      return null;
    } else if(node.getRight() == null){
      return node.getLeft();
    } else if (node.getLeft() == null){
      return node.getRight();
    } else {
      BinarySearchTreeEntry<K, V> replacementNode;
      BinarySearchTreeEntry<K, V> newRight;
      replacementNode = findLeftMost(node.getRight());
      newRight = deleteLeftMost(node.getRight());
      replacementNode.setRight(newRight);
      replacementNode.setLeft(node.getLeft());
      return replacementNode;
    }
  }

  private BinarySearchTreeEntry<K, V>
    findLeftMost (BinarySearchTreeEntry<K, V> node){

    if(node.getLeft() == null){
      return node;
    } else {
      return findLeftMost(node.getLeft());
    }
  }

  private BinarySearchTreeEntry<K, V>
    deleteLeftMost(BinarySearchTreeEntry<K, V> node){

    if(node.getLeft() == null){
      return node.getRight();
    } else {
      BinarySearchTreeEntry<K, V> newChild;
      newChild = deleteLeftMost(node.getLeft());
      node.setLeft(newChild);
      return node;
    }
  }

  @Override
  public void clear() {
    root = null;
    numNode = 0;

  }
  @Override
  public Iterator<DictionaryEntry<K, V>> iterator() {
    return new TreeIterator();
  }
  private class TreeIterator implements Iterator<DictionaryEntry<K, V>>{
    private int expectedNum = numNode;
    private BinarySearchTreeEntry<K, V> current;
    Stack<BinarySearchTreeEntry<K, V>> workList = new Stack<>();


    private TreeIterator(){
      current = root;
    }

    @Override
    public boolean hasNext() {
      return current != null || !workList.isEmpty();
    }

    @Override
    public DictionaryEntry<K, V> next() {

      if (!hasNext()) {
        throw new NoSuchElementException("The list is empty");
      } else if (expectedNum != numNode) {
        throw new ConcurrentModificationException();
      }

        while (current != null) {
          workList.add(current);
          current = current.getLeft();
        }

          if(!workList.isEmpty()) {
            current = workList.pop();
            BinarySearchTreeEntry<K, V> result = current;
            current = result.getRight();
            return result;
          }
          return root;
     }
      public void remove () {
        throw new UnsupportedOperationException();
      }
    }

}
