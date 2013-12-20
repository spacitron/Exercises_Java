import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

/****************************************************************************************************
 * Telephone Keypads commonly have both digits and characters on them. This is to help with 		*
 * remembering & typing phone numbers (called a Phoneword), like 1-800-PROGRAM rather than 			*
 * 1-800-776-4726. This keypad layout is also helpful with T9, a way to type texts with word 		*
 * prediction. 																						*
 * Your goal is to mimic some of the T9-features: given a series of digits from a telephone 		*
 * keypad, and a list of English words, print the word or set of words that fits the starting 		*
 * pattern. You will be given the number of button-presses and digit, narrowing down the 			*
 * search-space.																					*
 * 																									*
 * On standard console input, you will be given an array of digits (0 to 9) and spaces. All 		*
 * digits will be space-delimited, unless the digits represent multiple presses of the same button 	*
 * (for example pressing 2 twice gives you the letter 'B').											*
 * Use the modern Telephone Keypads digit-letter layout:											*
 * 0 = Not used																						*
 * 1 = Not used																						*				
 * 2 = ABC																							*
 * 3 = DEF																							*
 * 4 = GHI																							*
 * 5 = JKL																							*
 * 6 = MNO																							*	
 * 7 = PQRS																							*
 * 8 = TUV																							*
 * 9 = WXYZ																							*			
 * 																									*		
 * You may use any source for looking up English-language words.									*		
 * 																									*			
 * Sample Input:	7777 666 555 3																	*
 * 																									*
 * Sample Output:																					*
 * sold																								*
 * solder																							*	
 * soldered																							*		
 * soldering																						*			
 * solders																							*
 * soldier																							*		
 * 																									*
 * @author paolo																					*		
 *																									*
 ****************************************************************************************************/

public class TelephoneKeypads {
	String[] keys;
	
	public TelephoneKeypads(){
		keys = new String[]{"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS","TUV", "WXYZ"};
	}
	
	//First of two methods takes the number sequence and transforms it into a string
	public void makeNumber(String numSequence) {
		String[] subSeqs = numSequence.split(" ");
		String result = "";
		for (String subSeq : subSeqs) {
			int index = Integer.valueOf(subSeq.substring(0, 1));
			if (index > 1) {
				int position = subSeq.length() - 1;
				int maxLength = keys[index].length();
				char c = keys[index].charAt(position % maxLength);
				result += c;
			}
		}
		
		//Words lookup from here
		List<String> words = null;
		try {
			words = FileUtils.readLines(new File("brit-a-z.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String word : words) {
			if (word.startsWith(result.toLowerCase())) {
				System.out.println(word);
			}
		}
	}
	
}









