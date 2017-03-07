package PigLatinTranslator;
import java.util.Scanner;

public class PigLatin {
	
	public static boolean starts_with_vowel(String word) {
		boolean  b_low = word.startsWith("a") || word.startsWith("e") || word.startsWith("i") || word.startsWith("o") || word.startsWith("u");
		boolean  b_up = word.startsWith("A") || word.startsWith("E") || word.startsWith("I") || word.startsWith("O") || word.startsWith("U");
		return b_low || b_up;
	}

	public static String translatePigLatin(String read_word) {
		if(starts_with_vowel(read_word))
		{
			read_word = read_word + "way";
		}
		else
		{
			read_word = read_word.substring(1, read_word.length()) + read_word.toLowerCase().charAt(0);
			read_word = read_word + "ay";
		}
		return read_word;
	}

    public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Word to be translated:");
		String read_word = sc.nextLine();

		System.out.println("Translated in Pig Latin:");
		System.out.println(translatePigLatin(read_word));
    }
}
