import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;



class FileSearchPanel extends JFrame{
	
	private JFrame frame;
	private JList<?> list; 
	private JButton btnFileDelete;
	private JButton btnCancel;
	@SuppressWarnings("unused")
	private String searchKeyword, searchLocation;
	private DefaultListModel<String> model = new DefaultListModel<>(); 
	
	private JTextField textField;
	private final JLabel lblNewLabel = new JLabel("searching:");
	

	/**
	 * Create the application.
	 */

	public FileSearchPanel(String searchKeyword, String searchLocation) {
		
		this.searchKeyword = searchKeyword;
		this.searchLocation = searchLocation;
		
		// initialize the panel with using model already created
		initialize(model);
		
		//file Search
		Vector<String> searchedFiles = FileManager.fileSearch(searchLocation, searchKeyword);
		
		for(String fileName : searchedFiles) {
			model.addElement(fileName); 
		}
	
		// we are searching at
		textField.setText(searchLocation); 
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialize(DefaultListModel m) {
		
		frame = new JFrame();

        JPanel NewWindowContainer = new JPanel();
        frame.setContentPane(NewWindowContainer);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 523, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow][][][][][][][][][][][][]", "[][grow][][]"));
		
		frame.getContentPane().add(lblNewLabel, "cell 0 0");
		
		textField = new JTextField();
		textField.setBackground(new Color(245, 245, 220));
		textField.setEditable(false);
		textField.setColumns(10);
		frame.getContentPane().add(textField, "cell 1 0 10 1,growx");
		
		list = new JList<Object>(m);
		frame.getContentPane().add(list, "cell 0 1 13 1,grow");
		
		
		btnFileDelete = new JButton("FileDelete");
		frame.getContentPane().add(btnFileDelete, "cell 8 3");
		btnFileDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
				String selected = (String) list.getSelectedValue();
				String location = textField.getText();
				String target = location + "\\"+ selected;
				
				// synchronized function
				FileManager.fileDelete(target);
				model.removeElement(selected);
			
			}
		});
		
		btnCancel = new JButton("Cancel ");
		frame.getContentPane().add(btnCancel, "cell 11 3");
		btnFileDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				// save searched files at temp model and renew the model with the temp model
				DefaultListModel<String> temp = model;
				model = temp;
			
			}
		});
		
		frame.setVisible(true);

		
	}
	

}
