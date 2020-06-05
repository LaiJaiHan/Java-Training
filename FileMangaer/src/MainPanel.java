import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JLabel;


import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;


class EmptyExceptionDialog extends JDialog {
	
	
}

public class MainPanel {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPanel window = new MainPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	boolean isNotSupported(String searchWord){

		if (searchWord.contains("*")) return false;
		else if (searchWord.contains("/")) return false;
		else if (searchWord.contains(":")) return false;
		else if (searchWord.contains("\\")) return false;
		else if (searchWord.contains(">")) return false;
		else if (searchWord.contains("<")) return false;
		else if (searchWord.contains("|")) return false;
		else if (searchWord.contains("?")) return false;
		else return true;
	}

	/**
	 * Create the application.
	 */
	public MainPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 435, 243);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][][][grow]", "[][][][][][][][][][]"));
		
		JLabel lblSearchLocation = new JLabel("Search Location");
		lblSearchLocation.setForeground(Color.DARK_GRAY);
		lblSearchLocation.setBackground(Color.DARK_GRAY);
		lblSearchLocation.setFont(new Font("a����������4", Font.PLAIN, 15));
		frame.getContentPane().add(lblSearchLocation, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		textField.setBackground(new Color(245, 245, 245));
		frame.getContentPane().add(textField, "cell 0 1 7 1,growx");
		textField.setColumns(10);
		

		JLabel lblSerarchKeyword = new JLabel("Serarch Keyword");
		lblSerarchKeyword.setForeground(Color.DARK_GRAY);
		lblSerarchKeyword.setFont(new Font("a����������4", Font.PLAIN, 15));
		frame.getContentPane().add(lblSerarchKeyword, "cell 0 2,alignx trailing");
 		
		textField_1 = new JTextField();
		textField_1.setForeground(new Color(0, 0, 0));
		textField_1.setBackground(new Color(245, 245, 245));
		frame.getContentPane().add(textField_1, "cell 0 3 7 1,growx");
		textField_1.setColumns(10);
		
		JButton btnSearhIt = new JButton("Searh It");
		btnSearhIt.setFont(new Font("Arial", Font.PLAIN, 10));
		btnSearhIt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					// if fileLocation is empty, throws exception 
					System.out.println(textField.getText());
					System.out.println(textField_1.getText());
					if(textField.getText().isEmpty()) throw new Exception();
					// if fileLocation contains unsupported char, throws exception
					if(!isNotSupported(textField_1.getText())) throw new Exception();

					FileSearchThread fsThread = new FileSearchThread(textField_1.getText(), textField.getText());
					fsThread.run();
					
				} catch(Exception excpetion) {
					System.out.println("This is not correct textbox");
				} 
				
			}
		});
		
		frame.getContentPane().add(btnSearhIt, "cell 6 5,alignx center");
		
	
		

	}

}
