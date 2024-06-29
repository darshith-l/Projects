import java.util.Scanner;

public class ThreadExample {
   public static void main(String[] args) {
      System.out.println("Welcome to the illustration session of the threads: \n\n\n");

      System.out.println("Thread creation started ");

      try {
         for (int i = 0; i < 3; i++) {
            System.out.println("Thread cool" + (i + 1) + " creation started ");
            new Thread(new ThreadDemo("cool" + (i + 1))).start();
         }
      } catch (Exception e) {
         System.out.println("In main Exception: " + e);
      } finally {
         System.out.println("\n\n\nHurray completed!!!!");
      }
   }
}

class ThreadDemo implements Runnable {
   private static final Object lock = new Object(); // Shared lock object for synchronization
   private String name;

   ThreadDemo(String name) {
      this.name = name;
   }

   public void run() {
      try {
         for (int i = 0; i < 5; i++) {
            synchronized (lock) {
               // Synchronized block to ensure exclusive access to input operation
               Scanner sc = new Scanner(System.in);
               System.out.println("Hey I'm " + name + " enter some data please: (" + (i + 1) + "th time)");
               String str = sc.nextLine();
               System.out.println("Thank you for entering data " + str + " (Message from) " + name);
            }
         }
      } catch (Exception e) {
         System.out.println(e + " occurred");
      } finally {
         System.out.println(name + " is finished");
      }
   }
}
