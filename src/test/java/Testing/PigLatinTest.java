package Testing;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.*;

import static org.junit.Assert.*;
import PigLatinTranslator.*;
public class PigLatinTest {
//TODO implement testing for all methods from PigLatin
@Test
public void startWithVowel(){
	String inputString1 = "aGJKAgjaknbbagayoh";
	String inputString2 = "helloabcd";
	
	assertTrue(PigLatin.startsWithVowel(inputString1));
	assertFalse(PigLatin.startsWithVowel(inputString2));

}
@Test
public void translatePigLatin(){
	Map<String, String> testCase = new HashMap<String, String>();
	testCase.put("hello world", "ellohay orldway");
	testCase.put("ant eat pig", "antway eatway igpay");
	testCase.put("pig eat while smile!!! cheers,   i know, thanks", "igpay eatway ilewhay ilesmay!!! eerschay,   iway owknay, anksthay");
	
	Iterator it = testCase.entrySet().iterator();
	while(it.hasNext()){
		Map.Entry pair = (Map.Entry)it.next();
        String actualValue = (String)pair.getValue();
        assertEquals(actualValue, PigLatin.translatePigLatin((String)pair.getKey()));
        
	}
	
}

}
