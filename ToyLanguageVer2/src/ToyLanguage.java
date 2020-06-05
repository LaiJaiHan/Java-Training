import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class ToyLanguage {
	
	static Function[] functions = new Function[1000];

	static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	static boolean isFunction(String term) {
		
		for(int i = 0; i<functions.length; i++) {
			
			if (term.equals(functions[i].functionTerm))  return true;
		}
		
		return false;
		
	}
	
	static void doFunction(String term) {
		
		Function func = null;
		
		for(int i = 0; i<functions.length; i++) {
			
			if (term.equals(functions[i].functionTerm)) 
				func = functions[i];
		}
		
		int num = func.paramNum;
		
		
	}
	
	static void defineFunctions() throws IOException {
		
		try {
			
			String sourFile = "defun.txt";
			BufferedReader br = null;
			br = new BufferedReader(new FileReader(sourFile));
			
			String s;
			int i = 0;
			while ((s = br.readLine()) != null) {
				
				Function func = new Function(s);
				functions[i] = func;
				i++;
			}
			
			
			br.close();
			
			functions = Arrays.copyOf(functions, i);
			
			for (int a = 0 ; a<functions.length; a++)
				functions[a].printFunction();
			
			
			System.out.println ("Generated fucntions :" + functions.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	static void lexical(String exp) {
		
		try {
			 
			int i, len = 0;

			char[] charExp = exp.toCharArray();
			BufferedReader br = new BufferedReader(new CharArrayReader(charExp));

			
			Stack<String> integerStack = new Stack<>();
			Stack<String> nonTerminalStack = new Stack<>();
			
			String nonTerminal = "";
			String integerTerminal = "";
			int prev = 0;
			
			// prev = 0 : isAlphabet
			// prev = 1 : isDigit
			// prev = 3 : isMinus (to check wrong syntax '--')
			// prev = 2 : is'(' OR ')'
			
			while ((i = br.read()) != -1) {
				
				String c = Character.toString((char) i);
				
				if (c.equals("(")) {
					
					nonTerminalStack.push(c);
					prev = 2;
				}
				
				else if (c.equals(")")) {
					
					if (prev == 1) {
						
						integerStack.push(integerTerminal);
						
					}
					
					   String opCode = nonTerminalStack.pop();
		               nonTerminalStack.pop();

		               if (!isFunction(opCode)) {
		            	   System.out.println("not a function");
		            	   if (opCode.equals( "MINUS")) {
			            	   
			            	   int operander2 = Integer.parseInt(integerStack.pop());
				               int operander1 = Integer.parseInt(integerStack.pop());
				               
				               integerStack.push(Integer.toString(operander1 - operander2));
				      
			               }
		            	   
		               } 
		               else if (isFunction(opCode)) {
		            	   
		            	   
		            	   
		               }
		               
		               prev = 2;
					
				}
				
				// to check not supported syntax 'float'
				else if (c.equals(".")) {
					
					if (prev == 0) 
						System.out.println("Doesn't support float");
					
				}
				
				else {
					
					if(c.equals("-")) {
						
						if (prev == 3) System.out.println("err");
						
						integerTerminal = integerTerminal+c;
						prev = 3;
						
					}
					
					if(c.equals(" ")) {
						
						if (prev == 2) ;
						
						if (prev == 0) {
							
							nonTerminalStack.push(nonTerminal);
							nonTerminal ="";
						}
						
						if (prev == 1) {
							
							integerStack.push(integerTerminal);
							integerTerminal ="";
							// then push
						}
						
					}
					
					if(Character.isAlphabetic(i)) {
						
						nonTerminal = nonTerminal+c;
						prev = 0;
						
					}
					
					if(Character.isDigit(i)) {
						
						integerTerminal = integerTerminal+c;
						prev = 1;
					}
				};

					

			}

			br.close();
			
			System.out.println("finised");
			
			String returnValue = integerStack.peek();

		    System.out.println(returnValue);

		} catch (IOException e) {

			System.out.println(e);

		}	 
		
	}

	public static void main(String[] args) throws IOException {
		
		ToyLanguage.defineFunctions();
		ToyLanguage.lexical("(MINUS 3 (MINUS 4 3))");
		
		System.out.println(isFunction("ADD"));
		

	}

}
