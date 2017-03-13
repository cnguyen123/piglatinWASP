package PigLatinTranslator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.file.*;

import java.io.File;
import java.io.FileOutputStream;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.SpeechDataEvent;
import com.voicerss.tts.SpeechDataEventListener;
import com.voicerss.tts.SpeechErrorEvent;
import com.voicerss.tts.SpeechErrorEventListener;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PigLatin {

	static final char[] VOWELS = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
	// public static boolean isAlpha(String word) {
	// 	return word.matches("[a-z,.!?A-Z ]+");
	// }

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
		
	/**
	 * translate a single Piglatin word to English word
	 * @param pigLatinWord
	 * @return list of possible English word of the input Pig latin word
	 */
	public static List<String> translatePigLatinToEnglish( String pigLatinWord){
		List<String> englishWord = new ArrayList<String>();
		//check if input is a pig latin or not
		if(pigLatinWord.length()<=2){
			return englishWord;
		}
		
		//check if input is a pig latin of an english word with first character is vowel
		String suffix = pigLatinWord.substring(pigLatinWord.length()-3, pigLatinWord.length());
		if(suffix.equals("way") && isVowel((pigLatinWord.charAt(0)))){
			englishWord.add(pigLatinWord.substring(0, pigLatinWord.length()-3));
			return englishWord;
		}
		
		//check if input is a pig latin of an english word with first character is consonant
		suffix = pigLatinWord.substring(pigLatinWord.length()-2, pigLatinWord.length());
		if(!suffix.equals("ay")){
			return englishWord;
		}else{
			String temp = pigLatinWord.substring(0, pigLatinWord.length()-2);
			
			char lastCharacter = temp.charAt(temp.length()-1);
			char secondLastCharacter = temp.charAt(temp.length() -2);
			//single consonant
			if(!isVowel(lastCharacter) && isVowel(secondLastCharacter)){
				englishWord.add(lastCharacter + temp.substring(0, temp.length() -1));
			}
			//maybe english word with consonant cluster, need to check from dictionary for sure
			else if(!isVowel(lastCharacter) && !isVowel(secondLastCharacter)){
				//get all possible words from dictionary
				String word1 = lastCharacter + temp.substring(0, temp.length() -1);
				
				String word2 = new StringBuilder().append(secondLastCharacter).append(lastCharacter).append(temp.substring(0, temp.length() -2)).toString();
				if( isEnglishWord(word1) ){
					englishWord.add(word1);
				}
				if(isEnglishWord(word2)){
					englishWord.add(word2);
				}
			}
				
			return englishWord;
		}
		
	}
	/**
	 * check whether a word is English word according to the common English dictionary
	 * @param word
	 * @return
	 */
	public static boolean isEnglishWord(String word){
		try {
            BufferedReader in = new BufferedReader(new FileReader(
                    "edict/google_10000_english.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                if (str.indexOf(word) != -1) {
                    return true;
                }
            }
            in.close();
        } catch (IOException e) {
        	System.out.println("Error reading dictionary");
        }

        return false;
	}
	/**
	 * check input character is a vowel or a consonant 
	 * @param ch
	 * @return
	 */
	public static boolean isVowel(char ch){
		for (char vowel : VOWELS) {
	        if (vowel == ch) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static String translatePigLatin(String read_word) {

		// read_word = read_word.replace(",", " ");
		// read_word = read_word.replace(".", " ");
		// read_word = read_word.replace("!", " ");
		// read_word = read_word.replace("?", " ");
		// read_word = read_word.replace("\"", " ");

		// String[] word_split = read_word.split(" +");

		String [] word_split = read_word.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");	

		String tranlated_words = "";
		for (int i = 0; i < word_split.length; i++) {
			String current_word = word_split[i].toLowerCase();
			if(startsWithVowel(current_word))
			{
				current_word = current_word + "way";
			}
			else if(current_word.startsWith("y"))
			{
				current_word = current_word.substring(1, current_word.length())  + "ay";
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

	/**
	 * presentation of program instruction which helps user to select interested function
	 */
	public static void commandPanel(){
		System.out.println("***********************************************");
		System.out.println("Please choose one of these functions:");
		System.out.print("English --> Pig Latin(1)|");
		System.out.print("Pig Latin --> English(2)|");
		System.out.println("Exit(3)");
		System.out.println("You choose:");
		
	}



    public static void main(String[] args) throws Exception {

		VoiceProvider tts = new VoiceProvider("9c64068dda3c4c7e8cd75228f721a485");
    
    	while(true){
    		commandPanel();
    		Scanner func = new Scanner(System.in);
    		String selectedFunc = func.nextLine();
    		if(selectedFunc.equals("1")){
    			Scanner sc = new Scanner(System.in);
    			System.out.println("Word to be translated:");
    			String read_word = sc.nextLine();
				// String translated_word = "";
    			if(read_word.contains(".txt"))
    			{
    				try {
    					List<String> lines = Files.readAllLines(Paths.get(read_word), Charset.forName("UTF-8"));

    					System.out.println("Found input file");
    					for (String line : lines) {

    						System.out.println("Word to be translated:");
    						System.out.println(line);

    						if (line.matches(".*\\w.*"))
    						{
    							System.out.println("Translated in Pig Latin:");
    							System.out.println(translatePigLatin(line));
    						}
    				}
    				} catch (IOException e) {
    					System.out.println(e);
    				}


    			}
    			else{
    				// if(isAlpha(read_word))
    				// {
					System.out.println("Translated in Pig Latin:");
					// System.out.println(translatePigLatin(read_word));
					String translated_word = translatePigLatin(read_word);
					System.out.println(translated_word);

					VoiceParameters params = new VoiceParameters(translated_word, Languages.English_UnitedStates);
					params.setCodec(AudioCodec.WAV);
					params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
					params.setBase64(false);
					params.setSSML(false);
					params.setRate(0);
					
					byte[] voice = tts.speech(params);
					
					FileOutputStream fos = new FileOutputStream("voice.mp3");
					fos.write(voice, 0, voice.length);
					fos.flush();
					fos.close();

					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("voice.mp3").toURL());
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
    				// }
    				// else
    				// {
    				// 	System.out.println("Please check you input, only english letters and common punctuations are allowed, ;-)");
    				// }
    			}
    			
    		}else if(selectedFunc.equals("2")){
    			Scanner sc = new Scanner(System.in);
    			System.out.println("Word/Sentence to be translated:");
    			String inputWord = sc.nextLine();
    			String []words= parseSentenceToListOfWords(inputWord.toString());
    			String output = translateToEnglish(words);
    			if(output.equals("")){
    				System.out.println("Invalid Piglatin, please try again!!");
    			}else{
    				System.out.println("Possible English word/sentence:");
    				System.out.println(output);
    				
    				
    			}
    			/*List<String> result = translatePigLatinToEnglish(inputWord);
    			if(result.size()==0){
    				System.out.println("Invalid Piglatin, please try again!!");
    			}
    			else{
    				
    			for(int i = 0; i <result.size(); i++){
    				System.out.println(result.get(i));
    			}
    			}*/
    		}else if(selectedFunc.equals("3")){
    			System.out.println("YEBAY!!!");
    			System.exit(1);
    		}else {
    			System.out.println("Type 1 or 2 or 3 please!!!");
    		}
    		
    	}
		
		
    }
    
    /**
     * translate list of Piglatin words into English
     * @param words
     * @return
     */
    public static String translateToEnglish(String []words){
    	StringBuilder sb = new StringBuilder();
    	for( int i =0; i <words.length; i++){
    	    	
    	    	if( words[i].matches("[a-zA-Z]+")){
    	    		List<String> result = PigLatin.translatePigLatinToEnglish(words[i]);
    	    		
    	    		for(int j = 0; j < result.size(); j++){
    	    			sb.append(result.get(j));
    	    			if (j == result.size() -1 ){
    	    				sb.append(" ");
    	    			}else{
    	    				sb.append('|');
    	    			}
    	    		}
    	    		
    	    	}else{
    	    		String [] filterPuncWords = words[i].replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");
    	    		for(int j =0; j<filterPuncWords.length; j++){
    	    			List<String> result = PigLatin.translatePigLatinToEnglish(filterPuncWords[j]);
    	        		
    	        		for(int k = 0; k < result.size(); k++){
    	        			sb.append(result.get(k));
    	        			if (k == result.size() -1 ){
    	        				sb.append(" ");
    	        			}else{
    	        				sb.append('|');
    	        			}
    	        		}
    	    			
    	    		
    	    		}
    	    	}
    	    }
    	return sb.toString();
    	}
    	/**
    	 * parse a pig latin sentence to list of pig latin words
    	 * @param sentence
    	 * @return
    	 */
    	public static String[] parseSentenceToListOfWords(String sentence ){
    		sentence = sentence.replaceAll("\\s+", " ");
    		return (sentence.split(" "));
    	}

}
