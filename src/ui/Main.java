package ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * @version November 14th 2020
 */
public class Main extends Application {

	private MainMenuController mainMenu;
	
	public Main() throws IOException {
		mainMenu = new MainMenuController();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainMenuPane.fxml"));
			
			fxmlLoader.setController(mainMenu);
			
			Parent root = fxmlLoader.load();
	    	
			Scene scene= new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Database");
			primaryStage.setResizable(false);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent arg0) {
					mainMenu.saveData();
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
