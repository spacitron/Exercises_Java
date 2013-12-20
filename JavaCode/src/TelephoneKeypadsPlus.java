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
	public void count(String telNumber) throws IOException {
		ArrayList<String> subWords = new ArrayList<>();
		int numLength = telNumber.length();
		int[] digits = new int[numLength];
		int[] keyIndex = new int[numLength];
		int[] keyLength = new int[numLength];
		int totPermutations = 1;

		for (int i = 0; i < numLength; i++) {
			digits[i] = Integer.valueOf(telNumber.charAt(i) + "");
			keyLength[i] = keys[digits[i]].length();
			totPermutations = totPermutations * keyLength[i];
		}

		for (int i = 0; i < totPermutations; i++) {
			String subSeq = "";
			int x = numLength - 1;
			while (keyIndex[x] == keyLength[x]) {
				keyIndex[x] = 0;
				keyIndex[x - 1] += 1;
				x--;
			}
			for (int j = 0; j < numLength; j++) {
				subSeq += keys[digits[j]].substring(keyIndex[j],keyIndex[j] + 1);
			}
			subWords.add(subSeq);
			keyIndex[numLength - 1] += 1;
		}
		List<String> words = FileUtils.readLines(new File("brit-a-z.txt"));

		for (String word : words) {
			if (word.length() >= numLength
					&& subWords.contains(word.substring(0, numLength))) {
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
