import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/****************************************************************************************************
 * This exercise is the advanced version of TelephoneKeypads. Here each number refers to a whole	*
 * array of possible results and the program needs to fetch the words matching all possible 		*
 * sequences. 																						*
 * 																									*
 * Sample input: 	272434																			*
 * 																									*
 * Sample output:																					*
 * archdiocesan																						*
 * archdiocese																						*		
 * archdioceses																						*
 * archfiend																						*
 * braiding																							*		
 * 																									*
 * @author paolo																					*		
 *																									*
 ****************************************************************************************************/

public class TelephoneKeypadsPlus {
	String[] keys;

	public TelephoneKeypadsPlus() {
		keys = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno",
				"pqrs", "tuv", "wxyz" };
	}

	//Efficient solution
	public void efficientMakeNumbersPlus(String telNumber) throws IOException {
		ArrayList<String> subWords = new ArrayList<>();
		ArrayList<Integer> digits = new ArrayList<>();
		ArrayList<Integer> keyIndex = new ArrayList<>();
		ArrayList<Integer> keyLength = new ArrayList<>();
		int permutations = 1;
		int digit=0;
		
		for (int i = 0; i < telNumber.length(); i++) {
			if((digit =Integer.valueOf(telNumber.charAt(i) + ""))>1){
				digits.add(digit);
				keyLength.add(keys[digit].length());
				keyIndex.add(0);
				permutations = permutations*keys[digit].length();
			}
		}

		for (int i = 0; i < permutations; i++) {
			String subSeq = "";
			int x = digits.size()- 1;
			while (keyIndex.get(x) == keyLength.get(x)) {
				keyIndex.set(x,0);
				keyIndex.set(x-1, keyIndex.get(x-1)+1);
				x--;
			}
			for (int j = 0; j < digits.size(); j++) {
				int charIndex =keyIndex.get(j);
				int currDigit = digits.get(j); 
				String ch = keys[currDigit].substring(charIndex, charIndex+1);
				subSeq += ch;
			}
			subWords.add(subSeq);
			keyIndex.set(keyIndex.size()-1, keyIndex.get(keyIndex.size()-1)+1);
		}
		List<String> words = FileUtils.readLines(new File("brit-a-z.txt"));

		for (String word : words) {
			if (word.length() >= digits.size()&& subWords.contains(word.substring(0, digits.size()))) {
				System.out.println(word);
			}
		}

	}
	

	//Inefficient solution
	public void makeNumberPlus(String numSequence) throws IOException {
		int numLength = numSequence.length();
		ArrayList<String> subWords = new ArrayList<>();
		subWords.add("");
		char[] numbs = numSequence.toCharArray();
		ArrayList<String> tempWords = null;
		for (char numb : numbs) {
			tempWords = new ArrayList<>();
			int index = Integer.valueOf(numb + "");
			char[] characters = keys[index].toCharArray();
			for (String subWord : subWords) {
				for (char character : characters) {
					tempWords.add(subWord + character);
				}
			}
			subWords.removeAll(subWords);
			subWords.addAll(tempWords);
		}
		List<String> words = FileUtils.readLines(new File("brit-a-z.txt"));
		for (String word : words) {
			if (word.length() >= numLength && subWords.contains(word.substring(0, numLength))) {
				System.out.println(word);
			}
		}
	}

}
