
/**
 * This class is the main class for the Unit Converter application.
 */
public class UnitConvApp
{
	/**
	 * Initialises and starts the Unit Converter application.
	 */
	public static void main(String[] args)
	{
		UnitConvModel model = new UnitConvModel();
		UnitConvView view = new UnitConvView(model);
		
		view.setVisible(true);		
	}
}