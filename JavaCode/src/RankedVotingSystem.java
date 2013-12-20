/********************************************************************************************
 * @author paolo																			*
 *																							*
 * A Ranked Voting System is a system that chooses a result based on a ranked-preference	*
 * rather than a simple majority. A standard ranked ballot generally has multiple choices, 	*
 * only one of which one can be picked. A ranked ballot allows you to choose the order in 	*
 * which you prefer candidates. An example could be that you prefer choice B first, then 	*
 * choice C, and finally choice A.															*
 * Your goal is to take a list of candidates and voter's ballots, implement this voting 	*
 * system and print the results of the fictional election.									*
 * 																							*
 * Input Description:																		*
 * An input string on multiple lines will be given. On the first line two space-delimited 	*
 * integers, N and M. N is the number of votes, while M is the number of candidates. After 	*
 * this line, you will be given the candidates line, which is a space-delimited set of 		*
 * M-number of candidate names. These names are one-word lower-case letters only. This is 	*
 * followed by N-lines of ballots, where each ballot is a list of M-integers, from 0 to M-1,*
 * representing the order of preference.													*
 * Note that the order of preference for ballots goes from left-to-right. The integers are	*
 * the index into the candidate list. For the example below, you can map 0: Knuth, 1: 		*
 * Turing, 2: Church. This means that if the ballot row is "1 0 2", that means the voter 	*
 * prefers Turing over Knuth over Church.													*
 * 																							*
 * Output Description:																		*
 * Given the candidates and ballots, compute the first-round of successful candidates (e.g.	*
 * rank them based on all ballot's first choice). If the percentage of votes for any one	*
 * candidate is more than 50%, print the candidate name as the winner. Else, take all the	*
 * votes of the least-successful candidate, and use their ballot's 2nd choice, summing again*
 * the total votes. If needed (e.g. there is no candidate that has more than 50% of the		*
 * votes), repeat this process for the 3rd, 4th, etc. choice, and print the winner of the	*
 * election.
 * 
 * Sample input:
 * "5 3
 * Knuth Turing Church
 * 1 0 2
 * 0 1 2
 * 2 1 0
 * 2 1 0
 * 1 2 0"
 * 
 * Sample output:
 * Turing is the winner!
 * 
 */
public class RankedVotingSystem {

	
//	public static void main(String[] args) {
//		RankedVotingSystem rvs = new RankedVotingSystem();
//	/*Sample input:*/ 
//	String input = "6 3 \nKnuth Turing Church " + "\n1 0 2 "
//			+ "\n0 1 2 " + "\n0 1 2 " + "\n2 1 0 " + "\n2 1 0 " + "\n1 2 0";
//	rvs.countVotes(input);
//	/*Expected output: "All remaining candidates each received the same number of votes. There was no winner."*/
//
//	/*Sample input:*/
//	String input2 = "5 3 \nKnuth Turing Church " + "\n1 0 2 "
//			+ "\n0 1 2 " + "\n0 1 2 " + "\n2 1 0 " + "\n2 1 0 ";
//	rvs.countVotes(input2);
// 	/*Expected output: "Knuth is the winner!"*/
//	}
	
	
	//First method processes the input and feeds it to the vote calculating method
	public void countVotes(String input) {
		String[] inputArr = input.split("\n");
		String[] infoStr = inputArr[0].split(" ");
		int[] info = {Integer.valueOf(infoStr[0]), Integer.valueOf(infoStr[1])};
		String[] names = inputArr[1].split(" ");
		String[] votes = new String[inputArr.length - 2];
		for (int i = 2; i < inputArr.length; i++) {
			votes[i - 2] = inputArr[i];
		}
		count(info, names, votes);
	}

	//Second method recursively calculates remaining votes and declares the winner - or a draw.
	private void count(int[] info, String[] names, String... votes) {
		int candidatePool = info[1];
		int ballotCount = info[0];
		float winningPercentage = ballotCount * 0.5f;
		int[] voteCounter = new int[names.length];
		int lowestVotes = ballotCount;
		int lowestVoteIndex = 0;
		boolean tie = true;

		for (String v : votes) {
			int vote = Integer.valueOf(v.substring(0, 1));
			voteCounter[vote]++;
		}

		for (int i = 0; i < voteCounter.length; i++) {
			if (voteCounter[i] > (winningPercentage)) {
				System.out.println(names[i] + " is the winner!");
				return;
			}
			if (voteCounter[i] < lowestVotes) {
				lowestVoteIndex = i;
				lowestVotes = voteCounter[i];
			}
		}

		for (int v : voteCounter) {
			if (voteCounter[0] != v) {
				tie = false;
			}
		}
		
		if (tie) {
			System.out.println("All remaining candidates each received the same number of votes. There was no winner.");
			return;
		} else {
			for (int i = 0; i < votes.length; i++) {
				votes[i] = votes[i].replace(lowestVoteIndex + " ", "");
			}
			info[1] = candidatePool - 1;
			count(info, names, votes);
		}
	}

}
