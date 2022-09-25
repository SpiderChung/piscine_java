package rush01;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import rush01.scenes.mainConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public class Main extends Application implements mainConstants {

	public static Stage primaryStage;

	public static Socket client;
	public static BufferedReader in;
	public static BufferedWriter out;

	public static boolean right = false;
	public static boolean left = false;


	@Override
	public void start(Stage stage) {
		primaryStage = stage;

		stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
			if (KeyCode.ESCAPE == event.getCode()) {
				stage.close();
			}
		});

		stage.setTitle("rush01");
		stage.setResizable(false);
		stage.setScene(mainConnection.getScene());
		stage.show();
	}


	private void repaint() {


	}

	private void updateObjectsData() {

	}

	public static void main(String[] args) {
		launch(args);
	}
}