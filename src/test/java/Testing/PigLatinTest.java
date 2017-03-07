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
@Test
public void translatePigLatin(){
	String inputString1 = "pig eat";
	String inputString2 = "Pig EAT";
	PigLatin pigLatin = new PigLatin();
	assertEquals("igpay eatway", pigLatin.translatePigLatin(inputString1));
	assertEquals("igpay Eatway", pigLatin.translatePigLatin(inputString2));
}

}
