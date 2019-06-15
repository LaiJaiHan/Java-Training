	
	import java.util.Scanner;

	class DecimalCalculation{
			
		boolean isDecimal(int N){
			    
			    int count = 0;
			    
			    for (int i = 1; i<=N; i++){
			        if (N%i == 0) count ++;
			    }
			    
			    if (count > 2) return false;
			    else return true;
			    
			}
			
			int solution(int N) {
			    
			    int answer = 0;
			    
			    for (int i = 2; i<=N; i++){
			        if (isDecimal(i)) answer = answer + i;
			    }
			    
			    return answer;
			}
		
	}
	
	public class Calculator {
		
		static DecimalCalculation dca = new DecimalCalculation();
		
		public static void main(String[] args) {
			
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("수를 입력하세요 >>");
			int Number = scanner.nextInt();
			
			System.out.println("정답은" + dca.solution(Number));
			
		}
	
	}
