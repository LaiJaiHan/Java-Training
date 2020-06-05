import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ToyLanguageAlgorithm {

	static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	
	 static String showResults(String exp) { 
	 
		try {
			 
			int i, len = 0;

			String sourFile = "./bin/data/base_ansi.txt";
			BufferedReader br = null;

			br = new BufferedReader(new FileReader(sourFile));

			
			Stack<String> integerStack = new Stack<>();
			Stack<String> nonTerminalStack = new Stack<>();
			
			String nonTerminal = "";
			String integerTerminal = "";
			int prev = 0;
			
			// prev = 0 : isAlphabet
			// prev = 1 : isDigit
			// prev = 3 : isMinus (to check wrong syntax '--')
			
			while ((i = br.read()) != -1) {
				
				String c = Character.toString((char) i);

				System.out.println(i);
				
				if (c.equals("(")) {
					
					nonTerminalStack.push(c);
					
				}
				
				else if (c.equals(")")) {
					
					
				}
				
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
						
						if (prev == 0) {
							
							nonTerminal ="";
							nonTerminalStack.push(nonTerminal);
						}
						
						if (prev == 1) {
							
							integerTerminal ="";
							integerStack.push(integerTerminal);
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

		} catch (IOException e) {

			System.out.println(e);

		}	 
		 
		 
	 Stack<String> integerStack = new Stack<>(); 
	 Stack<String> nonTerminalStack = new Stack<>();
	  
	 String[] array = exp.split(" ");
	  
	 for (int i = 0; i<array.length; i++) 
		 System.out.print(array[i]);
	  
	 for (int i = 0; i<array.length; i++) {
	  
		 if (isInteger(array[i])) {
			integerStack.push(array[i]);
		 	System.out.print(array[i]);
		 }
		  
		 else { 
			 
			 if (array[i].equals("("))
				 nonTerminalStack.push(array[i]); 
			  
			 else if (array[i].equals("MINUS")|| array[i].equals("IF")) 
				 nonTerminalStack.push(array[i]); 
			 
			 else if (array[i].equals(")")) { 
				 
				 String opCode = nonTerminalStack.pop(); 
				 nonTerminalStack.pop();
				  
				 int operander2 = Integer.parseInt(integerStack.pop()); 
				 int operander1 = Integer.parseInt(integerStack.pop());
				  
				 if (opCode == "MINUS") 
					 integerStack.push(Integer.toString(operander1 - operander2)); 
				 
				 if (opCode == "IF") { 
					 if (operander2 >= operander1)
						 integerStack.push(Integer.toString(operander2)); 
					 else
						 integerStack.push(Integer.toString(operander1)); 
				 	}
			 
			 	} 
	 	}
	  
	 }
	  
	 String returnValue = integerStack.peek();
	  
	 return returnValue; 
	
	}
	 

	static String calc(String exp) {

		exp = exp.replace("(", "");
		exp = exp.replace(")", "");
		
		String[] arr = exp.split(" ");
		
		// ����ó��
		if (arr[1].split("-", -1).length - 1 >= 2)
		{
			return "-2,-1";
		}
		if (arr[2].split("-", -1).length - 1 >= 2)
		{
			return "-3,-1";
		}
		if (!isInteger(arr[1]))
		{
			return "-4,-1";
		}
		if (!isInteger(arr[2])) 
		{
			return "-5,-1";
		}
		if ("MINUS".contentEquals(arr[0]))
		{
			return "0," + (Integer.parseInt(arr[1]) - Integer.parseInt(arr[2]));
		}
		else if ("IF".contentEquals(arr[0]))
		{
			if (Integer.parseInt(arr[1]) > Integer.parseInt(arr[2]))
			{
				return "0" + Integer.parseInt(arr[1]);
			}
			else return "0" + Integer.parseInt(arr[2]);
		}
		// MINUS, IF �̿��� ���
		else 
			return "-1,-1";
	}

	static void showResult(String expression) {
		
		Pattern infoPattern;
		Matcher infoMatcher;
		int oCount = 0, cCount = 0, calcCount = 0;
		expression.toUpperCase();

		// ����ó�� : ���ĺ�, ����, "-", ��ȣ, ���鹮�ڸ� ������ ������ ���ڰ� ������ ���� ó��
		{
			infoPattern = Pattern.compile("[^0-9A-Z() -]");
			infoMatcher = infoPattern.matcher(expression);
			if (infoMatcher.find()) 
			{
				System.out.println("����ó�� : ���ĺ�, ����, \"-\", ��ȣ, ���鹮�ڸ� ������ ������ ���ڰ� ������ ���� ó��");
				return;
			}
		}

		// ���� ����ȭ ����
		{
			infoPattern = Pattern.compile(" {0,}\\(");
			infoMatcher = infoPattern.matcher(expression);
			
			if (infoMatcher.find())
			{
				expression = infoMatcher.replaceAll(" (");
			}

			infoPattern = Pattern.compile("\\) {0,}");
			infoMatcher = infoPattern.matcher(expression);
			
			if (infoMatcher.find())
			{
				expression = infoMatcher.replaceAll(") ");
			}

			expression = expression.replace("( (", "((");
			expression = expression.replace(") )", "))");
			expression = expression.trim();

		}

		// ��ȣ ���� ó��
		{
			oCount = expression.split("\\(", -1).length;
			cCount = expression.split("\\)", -1).length;
			
			//����ó��
			if (oCount > cCount) 
			{
				System.out.println("\"(\"�� ������ �� �����ϴ�."); 
				return;
			} 
			else if (oCount < cCount) 
			{
				System.out.println("\"(\"�� ������ �� �����ϴ�."); 
				return;
			} 
			else 
			{
				calcCount = oCount - 1;
			}
		}

		while (calcCount > 0) {
			
			// infoPattern = Pattern.compile("\\((MINUS|IF) -{0,1}[0-9]* -{0,1}[0-9]*\\)");
			infoPattern = Pattern.compile("\\(\\S{0,} -{0,}[0-9]*\\.*[0-9]* -{0,}[0-9]*\\.*[0-9]*\\)");
			infoMatcher = infoPattern.matcher(expression);
			
			while (infoMatcher.find()) 
			{
				
				String regret = infoMatcher.group();
				String calcRet = calc(regret);
				
				//����ó�� 
				int retCode = Integer.parseInt(calcRet.split(",")[0]);
				if (retCode == -1) 
				{
					System.out.println("undefined : " + regret);
					return;
				} 
				else if (retCode == -2) 
				{
					System.out.println("op1�� -�� 2�� �̻� : " + regret);
					return;
				} 
				else if (retCode == -3) 
				{
					System.out.println("op2�� -�� 2�� �̻� : " + regret);
					return;
				} 
				else if (retCode == -4) 
				{
					System.out.println("op1�� ������ �ƴ� : " + regret);
					return;
				} 
				else if (retCode == -5) {
					System.out.println("op2�� ������ �ƴ� : " + regret);
					return;
				}

				int ret = Integer.parseInt(calcRet.split(",")[1]);

				expression = expression.replace(regret, Integer.toString(ret));
				
				calcCount--;
			}
		}
			System.out.println("��� : " + expression);
		}

	}

class InteractiveMode {

	static void interactiveModeResult(String expression) {

		ToyLanguageAlgorithm.showResult(expression);
	}

}

class FileLoadMode {

	static void fileModeResult(String dir) {

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {

			// BufferedReader ��ü ����.
			// ���� �����ϰ� �ִ� ���丮������ ������.
			fileReader = new FileReader(dir);
			bufferedReader = new BufferedReader(fileReader);

			String expression = null;

			// �� �� �� �о�鿩�� �˰����� �̿��Ѵ�.
			while ((expression = bufferedReader.readLine()) != null) {

				ToyLanguageAlgorithm.showResult(expression);

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e) {
				}
			if (fileReader != null)
				try {
					fileReader.close();
				} catch (IOException e) {
				}

		}
	}

}

public class ToyLanguage {
	
	static Scanner scn = new Scanner(System.in);
	
	static void fileLoadMode() {
		
		new FileLoadMode();
		String dir = null;
		
		System.out.println("File Name >>");
		dir = scn.nextLine();
		
		System.out.println("---- Compile Results ----");
		FileLoadMode.fileModeResult(dir);
		
		
	}
	
	static void interactiveMode() {
		
		new InteractiveMode();
		String expression = null;
		
		System.out.println("Write Command >>");
		expression = scn.nextLine();
		

		System.out.println("---- Compile Results ----");
		InteractiveMode.interactiveModeResult(expression);
		
	}
	
	public static void main(String args[]) {
	
		while (true) 
		{
			System.out.println("Choose Menu >>");
			String choose = scn.nextLine();
			
			switch (choose) {
				case "1" :
					fileLoadMode();
				case "2" :
					interactiveMode();
				case "3" :
					System.exit(0);
			}
		}
	
	}

}
