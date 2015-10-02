import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Eric Owusu Sekyere
 * This is the view for the Unit Converter application.
 *
 */
public class UnitConvView extends JFrame implements Serializable{
	
	// the field attributes of the converter view.

	private static final long serialVersionUID = -2264954212524054813L;
	
	@SuppressWarnings("unused")
	private  UnitConvModel model;
	private  JButton cm_to_inches;
	private  JButton kg_to_lb;
	private  JButton F_to_C;
	private  JButton inches_to_cm;
	private  JButton lb_to_kg;
	private  JButton C_to_F;
	private  JPanel p2;
	private  JPanel p1;
	private  JMenuBar menubar;
	private  JMenu file;
	private  JMenuItem save;
	private  JMenuItem reset;
	private  JTextField inputField;
	private  JTextField resultfield;
	private  JFileChooser openFileDialogWindow;
	private JLabel input;
	private JLabel output;
	
	
	/**
	 * Initializes this view
	 * @param model  the model to associate with this view
	 */

	public UnitConvView(UnitConvModel model){
		super("Unit Converter Application");
		
		this.model = model;
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String []fontFamilies = ge.getAvailableFontFamilyNames();
		// creating the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setVisible(true);
		this.setPreferredSize(new Dimension(800,400));
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(2,1));

		// now creating JMenu menus and items
		
		menubar  = new JMenuBar();
		setJMenuBar(menubar);
		file  = new JMenu("File");
		menubar.add(file);
		save = new JMenuItem("Save");
		file.add(save);
		reset = new JMenuItem("Reset");
		file.add(reset);
		
		// now creating panel to add textfield and inputs
		
		p1 = new JPanel();
		add(p1);
		p1.setLayout(new GridLayout(2, 2, 5, 0));
		
		// the input value textfield
		input = new JLabel("Input Value");
		input.setFont(new Font(fontFamilies[70], Font.PLAIN, 30));
		p1.add(input);
		inputField = new JTextField(20);
		inputField.setSize(20, 20);
		p1.add(inputField);
		
		//the result value  textfield
		output = new JLabel("Result Value");
		output.setFont(new Font(fontFamilies[70], Font.PLAIN, 30));
		p1.add(output);
		resultfield = new JTextField(20);
		resultfield.setEditable(false);
		p1.add(resultfield);
		
		
		// now creating the second panel to hold the buttons
		
		p2 = new JPanel();
		add(p2);
		p2.setLayout(new GridLayout(3,2));
		
		
		C_to_F = new JButton("\u00B0C to F");
		p2.add(C_to_F);
		
		F_to_C  = new JButton("F to \u00B0C ");
		p2.add(F_to_C);
		
		cm_to_inches  = new JButton("cm to inches");
		p2.add(cm_to_inches);
		
		inches_to_cm = new JButton("inches to cm");
		p2.add(inches_to_cm);
		
		
		kg_to_lb  = new JButton("kg to lb");
		p2.add(kg_to_lb);
		
		lb_to_kg  = new JButton("lb to kg");
		p2.add(lb_to_kg);
		
		// pack all elements in the frame
		pack();
		 
		Object[] nodes = {	this.C_to_F, this.F_to_C,
							this.inputField,this.resultfield,
							this.kg_to_lb,this.lb_to_kg,
							this.input, this.output,
							this.inches_to_cm,this.cm_to_inches,
							this.save, this.reset,
							this.file, this
						};
		int i =0;
		while(i < nodes.length){
			if(nodes[i] instanceof JButton)			{((JButton)nodes[i]).setFont(new Font(fontFamilies[70], Font.PLAIN, 25));}
			else if(nodes[i] instanceof JMenuItem)	{((JMenuItem)nodes[i]).setFont(new Font(fontFamilies[70], Font.CENTER_BASELINE, 25));}
			else if(nodes[i] instanceof JLabel)		{((JLabel)nodes[i]).setFont(new Font(fontFamilies[70], Font.PLAIN, 25));}
			else if(nodes[i] instanceof JTextField){
				JTextField b = (JTextField)nodes[i];
				b.setHorizontalAlignment(JTextField.CENTER);
				b.setFont(new Font(fontFamilies[70], Font.PLAIN, 35));
			}
			i++;
		}
		// passing the controller to the view
		@SuppressWarnings("unused")
		UnitConvController control = new UnitConvController(model, this);
		
		this.openFileDialogWindow = new JFileChooser();


	}
	
	/**
	 * Add a listener for the "cm to inches" button.
	 * @param t the action listener
	 */

	public void addCmToInchesListener(ActionListener t){
		this.cm_to_inches.addActionListener(t);
		
	}
	
	/**
	 * Add a listener for the "C to F" button.
	 * @param t the action listener
	 */

	public void addCtoFListener(ActionListener t){
		this.C_to_F.addActionListener(t);
	}
	
	/**
	 * Add a listener for the "F to C" button.
	 * @param t the action listener
	 */

	public void addFtoCListener(ActionListener t){
		this.F_to_C.addActionListener(t);
	}
	
	/**
	 * Add a listener for the "inches to cm" button.
	 * @param t the action listener
	 */

	public void addInchesToCmListener(ActionListener t){
		this.inches_to_cm.addActionListener(t);
	}
	
	/**
	 * Add a listener for the "kg to lbs" button.
	 * @param t the action listener
	 */

	public void addKgToLbsListener(ActionListener t){
		this.kg_to_lb.addActionListener(t);
	}
	
	/**
	 * Add a listener for the "lbs to kg" button.
	 * @param t the action listener
	 */
	
	public void addLbsToKgListener(ActionListener t){
		this.lb_to_kg.addActionListener(t);
	}
	
	/**
	 * Add a listener for the reset menu item.
	 * @param t the action listener
	 */

	public void addResetListener(ActionListener t){
		this.reset.addActionListener(t);
	}
	
	/**
	 * Add a listener for the save menu item.
	 * @param t the action listener
	 */

	public void addSaveListener(ActionListener t){
		this.save.addActionListener(t);
	}
	
	/**
	 * Get the string value of the user input text field.
	 * @return The string in the user input text field.
	 */

	public String getInputValue(){
		return inputField.getText();
	}
	
	/**
	 * Get the string value of the result text field.
	 * @return The string in the result text field.
	 */

	public String getResultValue(){
		return resultfield.getText();


	}
	
	/**
	 * Shows a file chooser for the user to select the file to save.
	 * @return The file chosen by the user, or null if no file was chosen.
	 */

	public File getSaveFile(){
		
		// open a pop up window for options
		// if the the users choice is yes or ok then return the selected file.
		// else show nothing
		
		int saveResultValue = openFileDialogWindow.showOpenDialog(this);
		
		if(saveResultValue == JFileChooser.APPROVE_OPTION){
			return openFileDialogWindow.getSelectedFile();
		}
		else{
			return null;
		}

	}
	
	/**
	 * Set the string for the user input text field.
	 * @param value The new value for the user input text field.
	 * @pre value is not null
	 */

	public void setInputValue(String value){
		inputField.setText(value);

	}
	
	/**
	 * Set the string for the calculated value text field.
	 * @param value The new value for the calculated value text field.
	 * @pre value is not null
	 */

	public void setResultValue(String value){
		resultfield.setText(value);
	}

}