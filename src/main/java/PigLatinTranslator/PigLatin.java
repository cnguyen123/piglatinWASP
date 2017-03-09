package PigLatinTranslator;
import java.util.Scanner;

public class PigLatin {

	public static boolean isAlpha(String word) {
		return word.matches("[a-z A-Z]+");
	}

	public static boolean startsWithVowel(String word) {
		// boolean  b_low = word.startsWith("a") || word.startsWith("e") || word.startsWith("i") || word.startsWith("o") || word.startsWith("u");
		boolean  b_low = word.matches("(a|e|i|o|u).*");
		return b_low;
	}

	public static int findFirstVowel(String word) {
		for (int i = 0 ; i<word.length() ; i++)
				if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u' || word.charAt(i) == 'y')
					return i;

		return 1;			
	}
				
	public static String translatePigLatin(String read_word) {

		String[] word_split = read_word.split(" ");
		String tranlated_words = "";
		for (int i = 0; i < word_split.length; i++) {
			String current_word = word_split[i].toLowerCase();
			if(startsWithVowel(current_word))
			{
				current_word = current_word + "way";
			}
			else
			{
				// current_word = current_word.substring(1, current_word.length()) + current_word.charAt(0);
				current_word = current_word.substring(findFirstVowel(current_word), current_word.length()) + current_word.substring(0, findFirstVowel(current_word));
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
			System.out.println("Please check you input, only english letters and space are allowed, ;-)");
		}

    }
}
