package Testing;
import org.junit.*;
import static org.junit.Assert.*;
import PigLatinTranslator.*;
public class PigLatinTest {
//TODO implement testing for all methods from PigLatin
@Test
public void starts_with_vowel(){
	String inputString1 = "aGJKAgjaknbbagayoh";
	String inputString2 = "helloabcd";
	PigLatin pigLatin= new PigLatin();
	assertTrue(pigLatin.starts_with_vowel(inputString1));
	assertFalse(pigLatin.starts_with_vowel(inputString2));

}
}
