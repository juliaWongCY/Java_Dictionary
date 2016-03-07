package dictionary;

import com.sun.org.apache.regexp.internal.RE;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class Main {

    private static final int MAX_SIZE = 500;
    private static final int REPITITIONS = 500;

    public static void main(String[] args) throws FileNotFoundException {

      Random random = new Random();

      InsertComplexities complexities = new InsertComplexities(random);

      Dictionary<InsertComplexities.InstrumentedKey, Integer> list = new OrderedLinkedList<>();
      int[] listComplex = complexities.getInsertComplexities(list, MAX_SIZE, REPITITIONS);

      Dictionary<InsertComplexities.InstrumentedKey, Integer> tree = new BinarySearchTree<>();
      int[] treeCompex = complexities.getInsertComplexities(tree, MAX_SIZE, REPITITIONS);

      File treeFile = new File("BinarySearchTree.dat");
      File listFile = new File(" OrderedLinkedList.dat");
      //FileWriter treeWriter = new FileWriter("BinarySearchTree.dat");

    }

}
