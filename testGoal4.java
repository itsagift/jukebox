import java.util.Scanner;

public class testGoal4 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    PurchaseQueue testQueue = new PurchaseQueue("songListTest.csv", scan);
    testQueue.main(args);
  }
}
