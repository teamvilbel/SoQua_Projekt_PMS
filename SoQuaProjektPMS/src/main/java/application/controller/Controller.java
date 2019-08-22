/**
 *
 */
package application.controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

/**
 * @author Noah Ruben
 * @see Original on <a href="https://github.com/SirMoM/BirthdayManager">Github</a>
 *
 */
public abstract class Controller implements Initializable{

	private final MainController mainController;

	/**
	 * @param mainController The "MainController" for this application
	 */
	public Controller(final MainController mainController){
		this.mainController = mainController;
	}

	/**
	 * @return the MainController
	 */
	public MainController getMainController(){
		return this.mainController;
	}
	
	/**
	 * All assertions for the controller. Checks if all FXML-Components have been
	 * loaded properly.
	 */
	protected abstract void assertions();
	
	
	/**
	 * Initializing everything needed by the Controller.
	 * <p>
	 * 
	 * Has to contain atleast {@link Controller#assertions()} and {@link Controller#bindComponents()} 
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(final URL location, final ResourceBundle resources){
		this.assertions();
		this.bindComponents();
	}

	protected abstract void bindComponents();
}
