package application;

import java.io.FileNotFoundException;

import application.controller.MainController;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Jean-Paul Edoh
 * Einstiegspunkt der Applikation. Hier wird die Applikation gestartet und der MainController Initialisiert und gestartet.
 */
public class AppStart extends Application {

	
	/**
	 * @param args
	 * Main-Methode der Appstart-Klasse. 
	 */
	public static void main(final String[] args) {
		launch(args);
	}

	/**
	 * Die Init-Methode bildet das Exceptionhandling f�r schwerwiegende Fehler ab und stellt das Fehler-Dialogfenster dar. 
	 */
	@Override
	public void init() throws Exception {
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(final Thread thread, final Throwable throwable) {

				final Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("ERROR");
				alert.setHeaderText("Someting went wrong! \n Consider sending me the log.");

				final StringBuilder stringBuilder = new StringBuilder("Stacktrace: \n");
				for (int i = 0; i < throwable.getStackTrace().length; i++) {
					stringBuilder.append(throwable.getStackTrace()[i]);
					stringBuilder.append(System.getProperty("line.separator"));
				}

				final TextArea textArea = new TextArea(stringBuilder.toString());
				textArea.setEditable(false);
				textArea.setWrapText(true);
				final Button copyButton = new Button("Copy");
				copyButton.setOnAction((event) -> {
					final Clipboard clipboard = Clipboard.getSystemClipboard();
					final ClipboardContent content = new ClipboardContent();
					content.putString(stringBuilder.toString());
					clipboard.setContent(content);
				});

				final GridPane gridPane = new GridPane();
				gridPane.setMaxWidth(Double.MAX_VALUE);
				gridPane.add(textArea, 0, 0);
				gridPane.add(copyButton, 0, 1);
				alert.getDialogPane().setContent(gridPane);
				alert.showAndWait();
			}
		});
		super.init();
	}

	/**
	 * Setzen des Main-Controllers.
	 */
	@Override
	public void start(final Stage stage){
		final MainController mainController = new MainController(stage);
		mainController.start();
	}

	/**
	 * Schlie�en der Applikation.
	 */
	@Override
	public void stop() {
		System.out.println("Stage is closing");
//		System.exit(0);
	}
}
