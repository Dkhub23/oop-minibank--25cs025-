
import java.util.Scanner;
import java.util.Random;

enum Move {
    ROCK, PAPER, SCISSORS, LIZARD, SPOCK
}

public class Main {

    static int winner(Move a, Move b) {

        if (a == b) {
            return 0;
        }

        return switch (a) {
            case ROCK -> (b == Move.SCISSORS || b == Move.LIZARD) ? 1 : -1;

            case PAPER -> (b == Move.ROCK || b == Move.SPOCK) ? 1 : -1;

            case SCISSORS -> (b == Move.PAPER || b == Move.LIZARD) ? 1 : -1;

            case LIZARD -> (b == Move.SPOCK || b == Move.PAPER) ? 1 : -1;

            case SPOCK -> (b == Move.SCISSORS || b == Move.ROCK) ? 1 : -1;
        };
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        int playerScore = 0;
        int computerScore = 0;

        Move[] moves = Move.values();

        for (int i = 1; i <= 5; i++) {

            System.out.println("\nRound " + i);
            System.out.println("Enter your move (ROCK, PAPER, SCISSORS, LIZARD, SPOCK):");

            Move player = Move.valueOf(sc.next().toUpperCase());

            Move computer = moves[r.nextInt(moves.length)];

            System.out.println("Player: " + player);
            System.out.println("Computer: " + computer);

            int result = winner(player, computer);

            if (result == 1) {
                System.out.println("You win this round!");
                playerScore++;
            }
            else if (result == -1) {
                System.out.println("Computer wins this round!");
                computerScore++;
            }
            else {
                System.out.println("Round is a tie!");
            }

            System.out.println("Player Score: " + playerScore);
            System.out.println("Computer Score: " + computerScore);
        }

        System.out.println("\n----- FINAL RESULT -----");

        if (playerScore > computerScore) {
            System.out.println("Player is the Overall Winner!");
        }
        else if (computerScore > playerScore) {
            System.out.println("Computer is the Overall Winner!");
        }
        else {
            System.out.println("Match is Draw!");
        }

        sc.close();
    }
}
