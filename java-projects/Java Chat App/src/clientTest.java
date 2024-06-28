import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class clientTest{
   public static void main(String[] args) throws IOException{
      Socket  clientSocket = null;

      try{
         clientSocket = new Socket("localhost", 5000);
      }
      catch(Exception e){
         System.out.println("Exception occurred while creating client socket: ");
         System.out.println(e);
      }
      
      BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

      //Sending the user name to the server
      System.out.print("Enter the user name: ");
      String user_name = userIn.readLine();
      out.println(user_name);

      while(true){
         //Sending message to the server
         System.out.print("[Enter your message]: " );
         String msg = userIn.readLine();
         out.println(msg);

         //Recieving from server
         String name = serverIn.readLine();
         msg = serverIn.readLine();

         System.out.println("[" + name + " sent]: " + msg);

         if(msg.equals("exit")){
            System.out.println("Thankyou for using service");
            break;
         }
      }

      System.out.println("Client is offline now");
      clientSocket.close();
   }
}