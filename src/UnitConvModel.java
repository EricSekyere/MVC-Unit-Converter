import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class UnitConvModel{
	
	// the field attributes for the class

	/**
	 * Represents the number of centimetres in an inch.
	 * @see Constant Field Values
	 */
	
	public  final double CM_PER_INCH = 2.54;
	
	/**
	 * Represents the number of pounds in a kilogram.
	 * @see Constant Field Values
	 */
	
	public  final double LBS_PER_KG = 2.2; ;
	
	
	// private attributes
	
	private final double faren_const = 9.0/5.0;
	private final double faren_const2 = 32.0;
	private final double inverse_const = 5.0/9.0;
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
		
		//calling collectString to collect ids
		collectStrings("cm", "inches");

	}
	
	/**
	 * Converts the passed value in inches to the corresponding length in centimetres.
	 * @param in a length in inches
	 */

	public void inchesToCm(double in){
		inputValue = in;
		resultValue = this.CM_PER_INCH * inputValue;
		
		//calling collectString to collect ids
		collectStrings("inches", "cm");
	}
	
	/**
	 * Converts the passed value in Celcius to the corresponding temperature in Fahrenheit
	 * @param c a temperature in Celcius
	 */

	public void cToF(double c){
		inputValue =c;
		resultValue = (inputValue *this.faren_const) + this.faren_const2;
		
		//calling collectString to collect ids
		collectStrings("\u00B0C", "\u00B0F");
	}
	
	/**
	 * Converts the passed value in Fahrenheit to the corresponding temperature in Celcius
	 * @param f a temperature in Fahrenheit
	 */

	public void fToC(double f){
		inputValue = f;
		resultValue = (inputValue - this.faren_const2)/this.inverse_const ;
		
		//calling collectString to collect ids
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
		
		//calling collectString to collect ids
		collectStrings("kg", "Lbs");
	}
	
	/**
	 * Converts the passed value in pounds to the corresponding weight in kilograms.
	 * @param lbs a weight in pounds
	 */

	public void lbsToKg(double lbs){
		inputValue = lbs;
		resultValue = inputValue/this.LBS_PER_KG;
		
		//calling collectString to collect ids
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
			final boolean checkstringvalue =(getFirststring() == "\u00B0C" && getSecondstring()== "\u00B0F")||
											(getFirststring() == "\u00B0F" && getSecondstring()== "\u00B0C"); 
			if(checkstringvalue){
				tempFile.println(this.getInputValue()+this.second+ " to "+this.getResultValue()+this.first);
			}
			else{
			tempFile.println(this.getInputValue()+" "+this.second+ " to "+
								 this.getResultValue()+" "+this.first);
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
//==========================================================================================================================================///////
}