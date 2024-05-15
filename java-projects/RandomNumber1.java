import java.util.Random;
import java.util.Scanner;

class RandomNumber1{
   static Scanner sc = new Scanner(System.in);
   public static void main(String args[]){
      
      System.out.println("*******************************************Welcome to RandomNumber*******************************************");

      char ch = '\0';

      while(true){
         System.out.println("\n\n");
         while(ch != 'f' && ch != 'c' && ch != '0'){
            System.out.print("\nEnter 'f/F' to play with friend, 'c/C' to play with bot, '0' to exit: " );
            ch = Character.toLowerCase(sc.next().charAt(0));
            if(ch != 'f' && ch != 'c' && ch != '0'){
               System.out.println("Invalid input, choose correct option");
            }
         }
   
         if(ch == 'f'){
            playWithFriend();
         }
         else if(ch == 'c'){
            System.out.println("\n\n*******************************************You choose to play with Bot*******************************************\n\n");
            playWithBot();
         }
         else{
            System.out.println("\n\nThnakyou :) Have a nice day\n\n");
         }

         System.out.print("\n\n*******************************************Press 'y/Y' to continue, else any alphanumeric key to end game*******************************************\n\n");
         
         try{
            ch = Character.toLowerCase(sc.next().charAt(0));
         }
         catch(Exception e){
            System.out.println("\n\nThnakyou for playing  :) Have a nice day\n\n");
            break;
         }
         
         if(ch != 'y'){
            System.out.println("\n\nThnakyou for playing  :) Have a nice day\n\n");
            break;
         }
      }

      
   }

   public static void playWithFriend(){
      clearScreen();
      System.out.println("\n\n*******************************************You choose to play with friend*******************************************\n\n");

      String player1, player2;

      System.out.print("Enter the first player name: ");
      player1 = sc.next();
      System.out.print("Enter the second player name: ");
      player2 = sc.next();

      System.out.println("\n\nWelcome " + player1 + " and " + player2);

      System.out.println("\n\n*******************************************Rules and regulations*******************************************");
      System.out.println("1. In each turn of player, each player must guess an number between 0 to 10");
      System.out.println("2. If guessed number and generated number is same, player will gain point");
      System.out.println("3. Player with maximum score will win the game");
      System.out.println("4. If any number choosen outside, modulus of 10 is applied");

      System.out.println("\n\n**************************************************************************************\n\n");

      int no_rounds;
      System.out.println("Enter number of rounds: ");

      //to enter valid int for no_rounds
      no_rounds = getNumber();

      System.out.println("\n\nGame started all the best!!!\n\n");

      int player1_score = 0, player2_score = 0;
      Random random = new Random();

      for(int i=1; i<=no_rounds; i++){
         System.out.println("\nRound "+i);

         int player1_num, player2_num, rand_num;

         //Player1's turn
         System.out.printf("%s guess a number between 0 to 10\n",player1);
         player1_num = getNumber()%10;

         rand_num = random.nextInt(10);

         System.out.printf("%s has guessed number %d and generated num is %d\n", player1, player1_num, rand_num);

         if(rand_num == player1_num){
            System.out.printf("%s won an point!!!!\n", player1);
            player1_score++;
         }
         else{
            System.out.println("Oops guess was wrong");
         }

         //Player2's turn
         System.out.printf("\n%s guess a number between 0 to 10\n",player2);
         player2_num = getNumber()%10;

         rand_num = random.nextInt(10);

         System.out.printf("%s has guessed number %d and generated num is %d\n", player2, player2_num, rand_num);

         if(rand_num == player2_num){
            System.out.printf("%s won an point!!!!\n", player2);
            player2_score++;
         }
         else{
            System.out.println("Oops guess was wrong");
         }
         
         System.out.printf("\n\nScore board:\n%s: %d\t\t%s: %d \n",player1, player1_score, player2, player2_score);
      }

      clearScreen();


      //result
      System.out.println("\n\n*******************************************Results*******************************************\n\n");

      if(player1_score == player2_score){
         System.out.println("Ooops it an draw!! better luck next time");
      }
      
      else{
         if(player1_score > player2_score){
            System.out.println("Congratulations "+ player1 + " you won!!! by "+ (player1_score-player2_score) + " points");
         }
         else{
            System.out.println("Congratulations "+ player2 + " you won!!! by "+ (player2_score-player1_score) + " points");
         }
      }
      System.out.printf("\n\nScore board:\n%s: %d\t\t%s: %d \n",player1, player1_score, player2, player2_score);

   }

   public static void playWithBot(){
      clearScreen();
      System.out.println("\n\n*******************************************You choose to play with Bot*******************************************\n\n");
      // System.out.println("Happiest single person");

      String player;

      System.out.print("Enter the player name: ");
      player = sc.next();

      System.out.println("\n\nWelcome " + player);
      
      System.out.println("\n\n*******************************************Rules and regulations*******************************************");
      System.out.println("1. In each turn of player, each player must guess an number between 0 to 10");
      System.out.println("2. If guessed number and generated number is same, player will gain point");
      System.out.println("3. Else the bot will gain the point");
      System.out.println("4. Player wins if his score is greater tha bot score");
      System.out.println("4. If any number choosen outside, modulus of 10 is applied");

      int player_score=0, bot_score=0;

      System.out.println("\n\nGame started all the best!!!\n\n");

      int no_rounds;
      Random random = new Random();
      System.out.println("Enter number of rounds: ");
      no_rounds = getNumber();

      for(int i=0; i<no_rounds; i++){
         System.out.println("\nRound "+i);

         System.out.printf("%s guess a number between 0 to 10\n",player);
         int player_num = getNumber()%10;

         int rand_num = random.nextInt(10);

         System.out.printf("%s has guessed number %d and generated num is %d\n", player, player_num, rand_num);

         if(player_num == rand_num){
            System.out.println("Hurray " + player + " won");
            player_score++;
         }
         else{
            System.out.println("Bot won");
            bot_score++;
         }

         System.out.printf("Score board\n%s: %d\nBot: %d\n", player, player_score, bot_score);
         
      }

      System.out.println("\n\n*******************************************Results*******************************************\n\n");

      if(player_score == bot_score){
         System.out.println("Ooops its an draw!!!, better luck next time");
      }
      else if(player_score > bot_score){
         System.out.println("Hurray " + player +" won the game by " + (player_score-bot_score));
      }
      else{
         System.err.println("Bot won and " + player + " loose the game, better luck next time\n");
      }




   }

   public static void clearScreen() {
      System.out.print("\033[H\033[2J");
      System.out.flush();
  }

  public static int getNumber(){
      int num=-1;
      boolean validInput = false;

      while (!validInput) {
         try {
            num = sc.nextInt();
            validInput = true; // Set to true if input is valid to break out of the loop
         } catch (Exception e) {
            System.out.println("Number must be an integer, try again");
            sc.next(); // Consume invalid input
         }
      }
      
      return num;
  }
}