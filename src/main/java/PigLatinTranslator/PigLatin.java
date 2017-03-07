// package PigLatinTranslator;

import java.util.Scanner;

public class PigLatin {

	public static boolean isConsonant(String word) {
		boolean  b = word.startsWith("a") || word.startsWith("e") || word.startsWith("i") || word.startsWith("o") || word.startsWith("u");
		return b;
	}

	public static void translatePigLatin(String read_word) {
		

		if isConsonant(read_word)
		{

			
		}

		System.out.println(isConsonant(read_word));

		System.out.println(read_word);


	}

    public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String read_word = sc.nextLine();
		translatePigLatin(read_word);

    }

	//TODO implement piglatin translator following rule from https://en.wikipedia.org/wiki/Pig_Latin
}
