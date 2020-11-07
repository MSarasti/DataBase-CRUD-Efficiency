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
 * @version November 6th 2020
 */
public class Main extends Application {

	private MainMenuController mainMenu;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
	    	Parent root = fxmlLoader.load();
	    	mainMenu = fxmlLoader.getController();
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
						mainMenu.saveData();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
