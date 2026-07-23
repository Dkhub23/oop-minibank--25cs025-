
import java.util.Scanner;


public class RPSLS{

    enum MOVE {ROCK, PAPER, SCISSORS, LIZARD, SPOCK}
    static int Winner(MOVE a, MOVE b) {
        if(a==b)
            return 0;

        return switch(a) {
            case ROCK -> (b == MOVE.SCISSORS || b == MOVE.LIZARD) ? 1 : -1;
            case PAPER -> (b == MOVE.ROCK || b == MOVE.SPOCK) ? 1 : -1;
            case SCISSORS -> (b == MOVE.PAPER || b == MOVE.LIZARD) ? 1 : -1;
            case LIZARD -> (b == MOVE.PAPER || b == MOVE.SPOCK) ? 1 : -1;
            case SPOCK -> (b == MOVE.SCISSORS || b == MOVE.ROCK) ? 1 : -1;
        };
    }
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        int plyrSc=0;
        int cmpSc=0;
        System.out.println("=== Rock Paper Scissors Lizard Spock ===");
        for(int i=1;i<=5;i++){
            System.out.println("\nRound " + i);
            System.out.print("Enter your move (ROCK, PAPER, SCISSORS, LIZARD, SPOCK): ");
            String inputplyr=sc.next().toUpperCase();

            MOVE player;

            try {
                player=MOVE.valueOf(inputplyr);
            } catch (IllegalArgumentException e) {

                 System.out.println("Invalid move! Round skipped.");
                continue;
            }

             System.out.print("computer's move (ROCK, PAPER, SCISSORS, LIZARD, SPOCK): ");
            String inputcmp=sc.next().toUpperCase();
            
            MOVE computer;
             try {
                computer=MOVE.valueOf(inputcmp);
            } catch (IllegalArgumentException e) {

                 System.out.println("Invalid move! Round skipped.");
                continue;
            }

            int result=Winner(player, computer);
            if(result==1){
                System.out.println("you win this round!");
                plyrSc++;
            }
            else if(result==-1)
            {
                System.out.println("Computer wins this round!");
                cmpSc++;
            }
            else {
                System.out.println("It's a tie!");
            }
        }
System.out.println("\n===== Final Score =====");
        System.out.println("You: " + plyrSc);
        System.out.println("Computer: " + cmpSc);
        if(plyrSc>cmpSc)
        System.out.println("you win! OVERALL GAME");
        else if(cmpSc>plyrSc)
        System.out.println("computer win! OVERALL GAME");
        else
        System.out.println("match Draw............");

        sc.close();

    }
}