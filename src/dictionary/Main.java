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



//      PrintWriter listFile = new PrintWriter("OrderedLinkedList.dat");
//
//      PrintWriter treeFile = new PrintWriter("BinarySearchTree.dat");

      File listFile = new File("OrderedLinkedList.dat");
      File treeFile = new File("BinarySearchTree.dat");

      PrintStream pS;

      pS = new PrintStream(listFile);

      for (int i = 0; i < MAX_SIZE; i++) {
        pS.println(i + "\t" + listComplex[i]);
//        treeFile.println(i + "\t" + treeComplex[i]);
      }
      pS = new PrintStream(treeFile);
      for (int i = 0; i < MAX_SIZE; i++) {
        pS.println(i + "\t" + listComplex[i]);
//        treeFile.println(i + "\t" + treeComplex[i]);
      }

//
//      listFile.flush();
//      treeFile.flush();
//      listFile.close();
//      treeFile.close();
    }

}
