package PigLatinTranslator;
import java.util.Scanner;

public class PigLatin {

	public static boolean isAlpha(String word) {
		return word.matches("[a-z A-Z]+");
	}

	public static boolean starts_with_vowel(String word) {
		boolean  b_low = word.startsWith("a") || word.startsWith("e") || word.startsWith("i") || word.startsWith("o") || word.startsWith("u");
		boolean  b_up = word.startsWith("A") || word.startsWith("E") || word.startsWith("I") || word.startsWith("O") || word.startsWith("U");
		return b_low || b_up;
	}

	public static String translatePigLatin(String read_word) {

		String[] word_split = read_word.split(" ");
		String tranlated_words = "";
		for (int i = 0; i < word_split.length; i++) {
			String current_word = word_split[i];
			if(starts_with_vowel(current_word))
			{
				current_word = current_word + "way";
			}
			else
			{
				current_word = current_word.substring(1, current_word.length()) + current_word.toLowerCase().charAt(0);
				current_word = current_word + "ay";
			}
			tranlated_words =  tranlated_words + current_word + " ";
		}

		tranlated_words = tranlated_words.substring(0, tranlated_words.length()-1);
		return tranlated_words;
	}

    public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Word to be translated:");
		String read_word = sc.nextLine();

		if(isAlpha(read_word))
		{
			System.out.println("Translated in Pig Latin:");
			System.out.println(translatePigLatin(read_word));
		}
		else
		{
			System.out.println("Please check you input, no number or special character is allowed,;-)");
		}

    }
}
