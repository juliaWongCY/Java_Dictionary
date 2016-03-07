package dictionary;



import java.io.*;
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
      int[] treeComplex = complexities.getInsertComplexities(tree, MAX_SIZE, REPITITIONS);



      PrintWriter listFile = new PrintWriter("OrderedLinkedList.dat");

      PrintWriter treeFile = new PrintWriter("BinarySearchTree.dat");


      for (int i = 0; i < MAX_SIZE; i++) {
        listFile.println(i + "\t" + listComplex[i]);
        treeFile.println(i + "\t" + treeComplex[i]);
      }

      listFile.flush();
      treeFile.flush();
      listFile.close();
      treeFile.close();
    }

}
