import java.util.HashMap;

public class HashTableProblem_2 {
	
	static boolean isSameParts(String cloth_part1, String clothes_part2) {
		
		if (cloth_part1.equals(clothes_part2)) return true;
		else return false;
		
	}
	
	static int dress_parts_hash () {
		
		return 0;
	}
	
	static int combinate (int a, int b) {
		
		return 0;
	}
	
	public static void main(String[] args) {
		
		String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
		HashMap<String, Integer> clothes_hash = new HashMap<>();
		
		String[] clothes_names = new String[clothes.length];
		String[] clothes_parts = new String[clothes.length];
		
		int number_of_parts = 0;
		
		for(int i = 0; i<clothes.length; i++) {
			
			clothes_parts[i] = clothes[i][1];
			
		}
		
		
	}

}
