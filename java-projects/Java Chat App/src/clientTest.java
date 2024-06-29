import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class clientTest{
   public static void main(String[] args) throws IOException{
      Socket  clientSocket = null;
      // PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

      try{
         clientSocket = new Socket("localhost", 5000);

         new ReadThread(clientSocket).start();
         new WriteThread(clientSocket).start();
      }
      catch(Exception e){
         System.out.println("Exception occurred while creating client socket: ");
         System.out.println(e);
      }

   }
}

class ReadThread extends Thread{
   Socket clientSocket;
   BufferedReader serverIn;

   ReadThread(Socket socket) throws IOException{
      clientSocket = socket;
      serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
   }

   //Recieving from server
         

   public void run(){
      while(true){
         try{
            String name = serverIn.readLine();
            String msg = serverIn.readLine();

            System.out.println("[" + name + " sent]: " + msg);
            System.out.print("[Enter your message]: " );
         }
         catch(Exception e){
            System.out.println("Exception while recieving message from server");
         }
      }
   }
}

class WriteThread extends Thread{
   Socket clientSocket;
   PrintWriter out;
   BufferedReader userIn;
   
   WriteThread(Socket socket) throws IOException{
      clientSocket = socket;
      out = new PrintWriter(clientSocket.getOutputStream(), true);
      userIn = new BufferedReader(new InputStreamReader(System.in));
   }

   public void run(){
      //Sending the user name to the server
      
      System.out.print("Enter the user name: ");
      String user_name = null;
      try {
         user_name = userIn.readLine();
      } 
      catch (IOException e) {
         System.out.println("Error while reading the student name");
         e.printStackTrace();
      }

      if(user_name != null){
         out.println(user_name);
         
         while(true){
         //Sending message to the server
         System.out.print("[Enter your message]: " );
         String msg;
         try {
            msg = userIn.readLine();
            out.println(msg);
            if(msg.equals("exit")){
               System.out.println("Thankyou for using service");
               break;
            }
         } 
         catch (IOException e) {
            System.out.println("Error while reading from the user");
            e.printStackTrace();
         }
         }

      }

      System.out.println("Client is offline now");

      try {
         clientSocket.close();
      } 
      catch (IOException e) {
         System.out.println("Error while closing the socket ");
         e.printStackTrace();
      }

   }


}