import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class serverTest{
   public static void main(String[] args) throws IOException{

      ServerSocket serverSocket = null;
      List<String> messageBuffer = new ArrayList<>();
      List<String> senderName = new ArrayList<>();
      List<ClientHandler> clients = new ArrayList<>();

      try{
         serverSocket = new ServerSocket(5000);
      }
      catch(Exception e){
         System.out.println("Exception while creating ServerSocket");
         System.out.println(e);
      }

      System.out.println("Server started: \n\n");

      while (true) {
         Socket clienStocket = serverSocket.accept();
         ClientHandler new_client = new ClientHandler(clienStocket, clients);
         clients.add(new_client);
         new Thread(new_client).start();
      }
      
   }
}

class ClientHandler implements Runnable{
   Socket clientSocket; 
   BufferedReader clientIn;
   PrintWriter out;
   String client_name;
   List<ClientHandler> clients_reference;


   ClientHandler(Socket clientSocket, List<ClientHandler> clients_reference) throws IOException{
      // this.client_name = client_name;
      this.clientSocket = clientSocket;
      this.clients_reference = clients_reference;
      clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      out = new PrintWriter(clientSocket.getOutputStream(), true);
   }

   @Override
   public void run(){
      try {
         client_name = clientIn.readLine();
         System.out.println("[ " + client_name + " ]: " + "Connected ");
      } 
      catch (IOException e) {
         System.out.println("Exception while reading name of client");
         e.printStackTrace();
      }

      while(true){
         String msg = "";

         //recieving a message from the respective client
         try {
             msg = clientIn.readLine();
         } 
         catch (IOException e) {
            System.out.println("Exception while reading message from client");
            e.printStackTrace();
         }

         System.out.println("[" + client_name + " sent ]: " + msg);

         for(ClientHandler client: clients_reference){
            // client.out.println(client_name);
            // client.out.println(msg);
            if(!client.client_name.equals(client_name)){
               System.out.println(client.client_name);
               client.out.println(client_name);
               client.out.println(msg);
            }
         }
         
         
      }
   }
}