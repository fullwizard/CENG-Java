import java.util.InputMismatchException;
import java.util.Scanner;

public class RockPaperScissors {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char input = '1';
		int i = 0;
		int playerScore = 0;
		int computerScore = 0;
		char computerMove;
		System.out.println("Welcome to Rock, Paper, Scissors!");
		boolean playAgain = false;
		boolean valid = false;
		do {
			while (i<3) {
				while (!valid) {
					try {
						System.out.print("Choose a move (r)ock / (p)aper / (s)cissors: ");
						valid = true;
						String input1 = sc.nextLine();
						input =  input1.length() > 0  ? input1.charAt(0) : '*';
						if (!isValid(input)) throw new InputMismatchException();
					} catch (InputMismatchException e) {
						valid = false;
					}
				}
				valid = false;
				computerMove = getMove();
				System.out.println("The computer plays: " + computerMove);
				if (getWin(computerMove, input) == 0) {
					System.out.println("Tie. Redo.");
					continue;
				}
				if (getWin(computerMove, input) == 1) {
					System.out.println("Computer wins this one. The score is now: ");
					computerScore++;
					System.out.println("Player: " + playerScore + "\n" + "Computer: " + computerScore);
				}
				if (getWin(computerMove, input) == 2) {
					System.out.println("Player wins this one. The score is now: ");
					playerScore++;
					System.out.println("Player: " + playerScore + "\n" + "Computer: " + computerScore);
				}
				if ((computerScore == 2 && playerScore == 0) || (computerScore == 0 && playerScore == 2 )) {
					break;
				}
				i++;
			}
			if (computerScore > playerScore) {
				System.out.println("Computer Wins!");
			}
			else {
				System.out.println("Player Wins!");
			}
			System.out.println("Play Again? y/n ");
			String again = sc.nextLine();
			playAgain = again.equals("y");
			 i = 0;
			 playerScore = 0;
			 computerScore = 0;
		} while (playAgain);
	}
	public static boolean isValid(char c) {
		if (c == 'r') return true;
		if (c == 'p') return true;
		if (c == 's') return true;
		return false;
	}
	public static char getMove(){
		switch ((int)(3*Math.random())){
				case 0: return 'r';
				case 1: return 'p';
				default: return 's';
		}
	}
	public static int getWin(char compMove, char playerMove) {
		if (compMove == playerMove) {
			return 0;
		}
		if ((compMove == 'r' && playerMove == 's') || (compMove == 'p' && playerMove == 'r') || (compMove == 's' && playerMove == 'p')) {
			return 1;
		}
		return 2;
	}
}
