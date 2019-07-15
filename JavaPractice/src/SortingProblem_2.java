

public class SortingProblem_2 {
	
	static int digit_compare(String number_1, String number_2) {
		
		// 전자가 후자보다 자릿수가 크면 0을, 작으면 1을, 같으면 2를 리턴하는 함수
		
		if (number_1.length() > number_2.length()) return 0;
		else if (number_1.length() < number_2.length()) return 1;
		else return 2;
		
	} 
	
	
	
	public static void main(String[] args) {
		
		int[] numbers = {6,10,2};
		
		String[] numbers_string = new String[numbers.length];
		String answer = "";
		
		for ( int i = 0; i<numbers.length; i++) {
			
			numbers_string[i] = Integer.toString(numbers[i]);
			
		}
		
		// dictionary sorting  //
		
		// Bubble Sort 
		for (int i = 0; i < numbers_string.length; i++) {
			for (int j = 0; j < numbers_string.length-1 ; j++) {
				
				switch (digit_compare(numbers_string[j], numbers_string[j+1])) {
					// 자릿수가 다른 경우1: 앞 수가 뒷 수 보다 자릿수가 큰 경우
					case 0: 
						
						for(int a = 0; a < numbers_string[j+1].length(); a++) {
							
							int x = numbers_string[j].charAt(a) - '0'; int y = numbers_string[j+1].charAt(a) - '0';
							
							if(x <= y) {
						
								String temp = numbers_string[j+1];
								numbers_string[j+1] = numbers_string[j];
								numbers_string[j] = temp;
								
								break;
	
							}
							 
						}
						
						break;
					
					// 자릿수가 다른 경우1: 앞 수가 뒷 수 보다 자릿수가 작은 경우
					case 1:
						
						for(int a = 0; a < numbers_string[j].length(); a++) {
						
							int x = numbers_string[j].charAt(a) - '0'; int y = numbers_string[j+1].charAt(a) - '0';
							
							if(x <= y) {
						
								String temp = numbers_string[j+1];
								numbers_string[j+1] = numbers_string[j];
								numbers_string[j] = temp;
								
								break;
	
							}
						 
						}
						
						break;
						
					// 자릿수가 같은 경우 	
					case 2:
						
						if(Integer.parseInt(numbers_string[j]) < Integer.parseInt(numbers_string[j+1])) {
							
							String temp = numbers_string[j+1];
							numbers_string[j+1] = numbers_string[j];
							numbers_string[j] = temp;
							
						}
						
						break;
				}
				
			}
		}

		
		
		
		for ( int i = 0; i<numbers_string.length; i++) {
			
			answer = answer + numbers_string[i];
			
		}
		
	}
	

}
