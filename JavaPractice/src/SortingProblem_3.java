import java.util.Arrays;
import java.util.HashMap;

public class SortingProblem_3 {
	
	public static void main(String[] args) {
		
		String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
		String[] completion = {"josipa", "filipa", "marina", "nikola"};
		
		Arrays.sort(participant);
		Arrays.sort(completion);
		
		int break_point = 0;

		int index = 0;
		
		while (true) {
			
			if (index == (completion.length)) {
				break_point = index;
				break;
			}
			
			
			if (!participant[index].equals(completion[index])) {
				break_point = index;
				break;
			}
			
			index++;
				
			
		}
		
		System.out.println(participant[break_point]);
		
		
		
	}

}
