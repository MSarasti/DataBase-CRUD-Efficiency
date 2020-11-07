package ui;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * @version November 7th 2020
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
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("blankPane.fxml"));
			
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
					// TODO Auto-generated method stub
				  	try {
						//mainMenu.saveData();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
