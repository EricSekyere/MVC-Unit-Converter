import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import java.text.DecimalFormat;


/**
 * 
 * @author Eric Sekyere
 * This is the controller for the Unit Converter application.
 *
 */

public class UnitConvController{
	

	private UnitConvModel model;
	private  UnitConvView  view;
	DecimalFormat format = new DecimalFormat();
	
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
		format.setMaximumFractionDigits(3);
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
			// when reset button is pushed ,
			getModel().clear();     // clear all numbers
			getView().setInputValue("");  // the input textfield;
			getView().setResultValue(" "); // clear the result textfield
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
			getView().setResultValue(String.valueOf(format.format(modelresultvalue)));
		}
	}

}