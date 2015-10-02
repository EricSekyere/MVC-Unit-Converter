import javax.swing.UIManager;


/**
 * This class is the main class for the Unit Converter application.
 */
public class UnitConvApp
{
	/**
	 * Initializes and starts the Unit Converter application.
	 */
	public static void main(String[] args)
	{
			// use look and feel for my system (Win32)
		try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} 
		catch (Exception e) {}

		UnitConvModel model = new UnitConvModel();
		UnitConvView view = new UnitConvView(model);
		
		view.setVisible(true);		
	}
}