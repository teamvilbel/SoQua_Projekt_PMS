/**
 *
 */
package application.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.application.Platform;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * @author Noah Ruben
 * @see <a href="https://github.com/SirMoM/BirthdayManager">Github</a>
 */
public class MainController {
	private final Stage stage;
	private Controller activeController = null;

	/**
	 * Dieser "Handler" ist f�r das ordnungsgem��e Schlie�en der Anwendung zust�ndig. 
	 */
	final EventHandler closeAppHandler = new EventHandler<Event>() {
		@Override
		public void handle(final Event event) {
			Platform.exit();
			System.exit(0);
		}

	};

	/**
	 * @param stage the mainstage for the application
	 */
	public MainController(final Stage stage) {
		this.stage = stage;
		stage.setOnCloseRequest(this.closeAppHandler);
	}

	public Controller getActiveController() {
		return this.activeController;
	}

	/**
	 * @return the main stage of this app
	 */
	public Stage getStage() {
		return this.stage;
	}

	/**
	 * Swiches scenes to the SettingsView. Generates a new Controller.
	 *
	 * @see BirthdaysOverviewController
	 */
	public void goToSettingdiew() {
//		this.setActiveController(new BirthdaysOverviewController(this));
		try {
			this.replaceSceneContent("/application/view/Settings.fxml", this.getActiveController());

		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}



	/**
	 * @param fxmlPath   the path of the FXML-File representing the view
	 * @param controller the associated Controller
	 */
	public void gotoNextScene(final String fxmlPath, final Initializable controller) {
		try {
			this.replaceSceneContent(fxmlPath, controller);
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * @param fxmlPath   the path of the FXML-File representing the view
	 * @param controller the associated Controller
	 * @throws IOException if the FXML-File could not be loaded
	 */
	private void replaceSceneContent(final String fxmlPath, final Initializable controller) throws IOException {
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource(fxmlPath));
		loader.setController(controller);
		final Parent root = loader.load();
		final Scene scene = new Scene(root);
		scene.getStylesheets().add("test.css");
		this.stage.setScene(scene);

		// Show the GUI
		this.stage.show();
	}

	private void setActiveController(final Controller activeController) {
		this.activeController = activeController;
	}

	public void settingsChanged() {
		this.getActiveController().updateLocalisation();
	}

	/**
	 * Starts the application with the BirthdaysOverview and possibly loaded file
	 * @throws Exception 
	 */
	public void start() {
		this.setActiveController(new SettingsViewController(this));
		try {
			this.replaceSceneContent("/application/view/Settings.fxml", this.getActiveController());
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}

}
