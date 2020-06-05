import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Function {
	
	String functionTerm = "";
	String functionExp = "";
	
	String[] parameters;
	int paramNum = 0;
	
	Stack<String> arguments = new Stack<String>();
	
	public Function(String exp) {
		
		char[] charExp = exp.toCharArray();
		int i = 0;
		
		String argument ="";
		
		while(true) {

			String c = Character.toString((char) charExp[i]);
			
			if (c.equals("(")) ;
				
			else if (c.equals(")")) {
				i = i+2;
				break;
			}
			
			else if (c.equals(" ") && !argument.equals("")) {
				arguments.push(argument);
				this.paramNum ++;
				argument ="";
			}
			
			else {
				if (Character.isAlphabetic(charExp[i]) || Character.isDigit(charExp[i]))
					argument = argument + c;
			}			
			
			i++;
			
		}
				
		for(int z = i; z<charExp.length; z++) {
			
			String c = Character.toString((char) charExp[z]);
			this.functionExp = this.functionExp + c;
					
		}
		
		
		// set parmeter 
		this.parameters = new String[this.paramNum-1];
		
		for(int k = 0; k<this.paramNum-1; k++) {
			this.parameters[k] = arguments.get(k+1);
		}
		
		// set function's term
		this.functionTerm = arguments.firstElement();
	}
	

	void printFunction() {
		
		System.out.println("parameters:" + paramNum);
		for(int i = 0; i<parameters.length; i++) {
			System.out.println(this.parameters[i]);
		}
		System.out.println("Function name: " + functionTerm);
		System.out.println("Expression : " + functionExp);
		
	}
	
	
}


