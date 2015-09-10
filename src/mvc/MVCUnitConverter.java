package mvc;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import javax.swing.*;


public class MVCUnitConverter {
	
	public static void main(String[] args) {
		UnitConvModel model = new UnitConvModel();
		UnitConvView view = new UnitConvView(model);
		
		view.setVisible(true);
	}
	

}




   class UnitConvModel{

	/**
	 * Represents the number of centimetres in an inch.
	 * @see Constant Field Values
	 */
	
		
	/*----------- the field attributes for the class----------------------*/
	   
	public  final double CM_PER_INCH = 2.54;
	
	/**
	 * Represents the number of pounds in a kilogram.
	 * @see Constant Field Values
	 */
	
	public  final double LBS_PER_KG = 2.2; ;
	
	
	// private attributes
	
	private final double faren_const = 9.0/5.0;
	private final double faren_const2 = 32.0;
	private final double iverse_const = 5.0/9.0;
	private double inputValue;
	private double resultValue;

	private String first;

	private String second;
	
	/**
	 * Creates a model with no user values and a calculated value of zero.
	 */
	
	public UnitConvModel(){
		this.clear();
	}
	
	/**
	 * Re-initializes this model with a calculated value of zero.
	 */
	
	public void clear(){
		
		this.inputValue = 0.00;
		this.resultValue = 0.00;

	}
	
	/**
	 * Converts the passed value in centimeters to the corresponding length in inches.
	 * @param cm a length in centimeters
	 */

	public void cmToInches(double cm){
		inputValue = cm;
		resultValue = inputValue/this.CM_PER_INCH;
		
		//calling collectString to collect id's
		collectStrings("cm", "inches");

	}
	
	/**
	 * Converts the passed value in inches to the corresponding length in centimetres.
	 * @param in a length in inches
	 */

	public void inchesToCm(double in){
		inputValue = in;
		resultValue = this.CM_PER_INCH * inputValue;
		
		//calling collectString to collect id's
		collectStrings("inches", "cm");
	}
	
	/**
	 * Converts the passed value in Celcius to the corresponding temperature in Fahrenheit
	 * @param c a temperature in Celcius
	 */

	public void cToF(double c){
		inputValue =c;
		resultValue = (inputValue *this.faren_const) + this.faren_const2;
		
		//calling collectString to collect id's
		collectStrings("\u00B0C", "\u00B0F");
	}
	
	/**
	 * Converts the passed value in Fahrenheit to the corresponding temperature in Celcius
	 * @param f a temperature in Fahrenheit
	 */

	public void fToC(double f){
		inputValue = f;
		resultValue = (inputValue - this.faren_const2)/this.faren_const ;
		
		//calling collectString to collect id's
		collectStrings("\u00B0F", "\u00B0C");
	}
	
	/**
	 *Returns the input value of this model. 
	 * @return the input value as a double
	 */

	public double getInputValue(){
		return this.inputValue;

	}
	
	/**
	 * Returns the converted value calculated by this model.
	 * @return the converted value as a double
	 */

	public double getResultValue(){
		return this.resultValue;

	}
	
	/**
	 * Converts the passed value in kilograms to the corresponding weight in pounds.
	 * @param kgs a weight in kilograms
	 */

	public void kgToLbs(double kgs){
		inputValue = kgs;
		resultValue = inputValue*this.LBS_PER_KG;
		
		//calling collectString to collect id's
		collectStrings("kg", "Lbs");
	}
	
	/**
	 * Converts the passed value in pounds to the corresponding weight in kilograms.
	 * @param lbs a weight in pounds
	 */

	public void lbsToKg(double lbs){
		inputValue = lbs;
		resultValue = inputValue/this.LBS_PER_KG;
		
		//calling collectString to collect id's
		collectStrings("Lbs","Kg");
	}
	
	/**
	 * Saves the current conversion to a file in plain text.
	 * @param file the file to create for the output
	 */

	public void save(File file){
		PrintWriter tempFile;
		try{
			tempFile = new PrintWriter(file);
			if((getFirststring() == "\u00B0C" && getSecondstring()== "\u00B0F")||(getFirststring() == "\u00B0F" && getSecondstring()== "\u00B0C")){
				tempFile.println(this.getInputValue()+this.second+ " to "+this.getResultValue()+this.first);
			}
			else{
			tempFile.println(this.getInputValue()+" "+this.second+ " to "+this.getResultValue()+" "+this.first);
			}
			tempFile.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("The Specified file was not found or does not exist");
			ex.printStackTrace();
			
		}
	}
	
	
	// writing a method to collect the string id associated with each method/////
	//=====================================================================================================================================////////
	private void collectStrings(String first, String second){
		this.first = first; 
		this.second = second;
	}
	
	private String getFirststring(){
		return this.first;
	}
	private String getSecondstring(){
		return this.second;
	}
 }
//==========================================================================================================================================///////
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   class UnitConvView extends JFrame implements Serializable{
		
		// the field attributes of the converter view.

		private static final long serialVersionUID = -2264954212524054813L;
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
		private JFileChooser openFileDialogWindow;
		
		
		/**
		 * Initializes this view
		 * @param model  the model to associate with this view
		 */

		public UnitConvView(UnitConvModel model){
			super("Unit Converter Application");
			this.model = model;

			// creating the frame

			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(true);
			this.setVisible(true);
			this.setLocationRelativeTo(null);
			this.setSize(500, 500);
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
			
			p1.add(new JLabel("Input Value"));
			inputField = new JTextField(20);
			inputField.setSize(20, 20);
			p1.add(inputField);
			
			//the result value  textfield
			
			p1.add(new JLabel("Result Value"));
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
			
			// passing the controller to the view
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
   
 ///////////////////===============================================================================================//////////////////////
 //
 //
 //															The Controller Class
 //
 //
 //////////////////================================================================================================////////////////////
   
   /**
    * 
    * @author Eric Sekyere
    * This is the controller for the Unit Converter application.
    *
    */

   class UnitConvController{
   	

   	private UnitConvModel model;
   	private  UnitConvView  view;
   	
   	/**
   	 * Initialises this controller with the passed model and view.
   	 * @param model the model to associate with this controller
   	 * @param view the view to associate with this controller
   	 */
   	
   	public UnitConvController(UnitConvModel model, UnitConvView view){
   		this.model = model;
   		this.view= view;
   		
   		
   		this.view.addCmToInchesListener(new  CmToInchesListener());
   		this.view.addInchesToCmListener(new InchesToCmListener());
   		this.view.addKgToLbsListener(new KgToLbsListener());
   		this.view.addLbsToKgListener(new LbsToKgListener());
   		this.view.addCtoFListener(new CtoFListener());
   		this.view.addFtoCListener(new FtoCListener());
   		this.view.addResetListener(new ResetListener());
   		this.view.addSaveListener(new SaveListener());
   	}
   	
   	/**
   	*	Returns the model associated with this controller.
   	*	@return the model
   	*/
   	
   	public UnitConvModel getModel(){
   		return this.model;
   	}
   	
   	/**
   	*	Returns the view associated with this controller.
   	*	@return the view
   	*/

   	public UnitConvView getView(){
   		return this.view;
   	}
   	
   	
   	//////////////////////////////////////////////////////////////////////////////
   	///////// Now creating custom listeners for all buttons and menu items////////
   	//////////////////////////////////////////////////////////////////////////////
   	
   	
   	
   	private class SaveListener implements ActionListener {
   		@Override
   		public void actionPerformed(ActionEvent e) {
   			
   			// when the save button is clicked get the file and save to an instance of a class
   			
   			File tempfile = getView().getSaveFile();
   			
   			// invoke the model save method here
   			
   			getModel().save(tempfile);

   		}

   	}

   	private class ResetListener implements ActionListener {

   		@Override
   		public void actionPerformed(ActionEvent e) {
   			// when reset button is pushed,
   			
   			getModel().clear();     		// clear all numbers
   			getView().setInputValue("");  	// clear the input textfield
   			getView().setResultValue(" "); 	// clear the result textfield
   		}

   	}

   	public class FtoCListener extends UnitConvControllerListener {
   		
   		protected void doOperation(double valueInput){
   			
   			//invoke the FtoC(in model) method on the input value
   			
   			getModel().fToC(valueInput);
   		}
   	}

   	private class CtoFListener extends UnitConvControllerListener {
   		
   		protected void doOperation(double valueInput){
   			
   			//invoke the cToF(in model) method on the input value
   			
   			getModel().cToF(valueInput);
   		}
   		
   	}

   	private class LbsToKgListener extends UnitConvControllerListener {
   		protected void doOperation(double valueInput){
   			
   			//invoke the LbsToKg(in model) method on the input value
   			
   			getModel().lbsToKg(valueInput);
   			
   		}
   	}

   	private class KgToLbsListener extends UnitConvControllerListener{
   		protected void doOperation(double valueInput){
   			
   			//invoke the KgToLbs(in model) method on the input value
   			
   			getModel().kgToLbs(valueInput);	
   			
   		}
   	}

   	private class InchesToCmListener extends UnitConvControllerListener {
   		protected void doOperation(double valueInput){
   			
   			//invoke the inchesToCm(in model) method on the input value 
   			
   			getModel().inchesToCm(valueInput);
   		}
   	}

   	private class CmToInchesListener extends UnitConvControllerListener {
   		
   		protected void doOperation(double valueInput){
   			
   			//invoke the cmtoInches(in model) method on the input value
   			
   			getModel().cmToInches(valueInput);
   		}
   		
   	}
   	
   	
   	/**********************************************************************
   	 * 
   	 * @author Eric
   	 * this class is solely to handle the repetition.
   	 */
   	
   	private abstract class UnitConvControllerListener implements ActionListener
   	{
   		
   		// an abstract method to be inherited by all the action listeners for the six buttons
   		
   		protected abstract void doOperation(double valueInput);
   	
   	
   		@Override
   		public void actionPerformed(ActionEvent e)
   		{
   			
   			// creating a double reference with an initial value of 0 and a temp string ref
   			
   			Double InputFieldValue = 0.00;
   			String temp;
   			try
   			{
   				
   				// get the input value from the view(String)
   				// convert it to to a double type and and store it in InputFieldValue.
   				
   				temp = getView().getInputValue();
   				InputFieldValue = Double.parseDouble(temp);
   			}
   			
   			// catch any exception that arises and set the input field value to NaN
   			// this will in return cause the result field to be NaN
   			
   			catch(Exception ex)
   			{
   				InputFieldValue = Double.NaN;
   			}
   			
   			// now pass this InputFieldValue(Converted from string to int) doOperation
   			// set the result value in the field to be value of the conversion of the inputfield
   			
   			doOperation(InputFieldValue);
   			double modelresultvalue = getModel().getResultValue();
   			getView().setResultValue(Double.toString(modelresultvalue));
   		}
   	}

   }