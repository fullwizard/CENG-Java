import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hangman {
	public static void main(String[] args) {
		String[] words = {"hello", "because", "hangman", "program", "certification", "phobia", "rubber", "stop", "making", "this", "array", "funny", "right", "hopeful", "random", "words", "again", "border", "keyboard", "computer", "chrome", "edge", "explorer", "terminal", "eclipse", "taskbar", "task", "manager", "file", "package", "source", "refactor", "navigate", "search", "project", "window"};
		ArrayList<String> guesses = new ArrayList<>();
		System.out.println("Welcome to Hangman!");
		System.out.println("Once you guess incorrectly 10 times, you die.");
		String secretWord = getRandomWord(words);		
		String wordWithBlanks = convertWordToBlanks(secretWord);
		int numIncorrectGuesses = 0;
		Scanner sc = new Scanner(System.in);
		while (numIncorrectGuesses < 10 && !wordWithBlanks.equals(secretWord)) {
			System.out.println(wordWithBlanks);
			boolean valid = false;
			char input = ' ';
			while (!valid) {
				try {
					System.out.print("Please guess a character: ");
					valid = true;
					input = sc.nextLine().charAt(0);
					for (String str : guesses) {
						if (str.charAt(0) == input) {
							throw new InputMismatchException();
						}
					}
				} catch (InputMismatchException e) {
					valid = false;
				} catch (IllegalArgumentException e) {
					valid = false;
				}
			}
			guesses.add(String.valueOf(input));
			
			if (secretWord.contains("" + input)) {
				wordWithBlanks = replaceBlanksWithChar(wordWithBlanks, secretWord, input);
			}
			else {
				System.out.println("The secret word does not contain " + input);
				numIncorrectGuesses++;
				System.out.println("You have made " + numIncorrectGuesses + " incorrect guesses.");
			}
		}
		if (countNumberOfBlanks(wordWithBlanks) == 0) {
			System.out.println("You win!");
			System.out.println("The secret word was " + secretWord);
		}
		else if (numIncorrectGuesses >= 10) {
			System.out.println("You lose!");
			System.out.println("The secret word was " + secretWord);
		}
	}
	
	public static String convertWordToBlanks(String word) {
		String wordBlanks = "";
		for (int i = 0; i < word.length(); i++) {
			wordBlanks += "_";
		}
		return wordBlanks;
	}
	
	public static int countNumberOfBlanks(String word) {
		int underCount = 0;
		for (int i = 0; i < word.length()-1; i++) {
			if (word.charAt(i) == '_') {
				underCount ++;
			}
		}
		return underCount;
	}

	public static String replaceBlanksWithChar(String wordWithBlanks, String secretWord, char ch) {
		String replacedString = "";
		for (int i = 0; i < wordWithBlanks.length(); i++) {
			if (secretWord.charAt(i) == ch) {
				replacedString += "" + ch;
			} else {
				replacedString += "" + wordWithBlanks.charAt(i);
			}
		}
		return replacedString;
	}
	
	public static String getRandomWord(String[] words) {
		int randInt = (int) (words.length*Math.random());
		return words[randInt];
	}

}
