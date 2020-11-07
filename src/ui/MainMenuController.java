package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import customExceptions.IncompatibleFieldsException;
import customExceptions.NotCompleteMandatoryFieldsException;
import customExceptions.WrongInformationException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.*;

public class MainMenuController {
	
	private DataBase base;
	
	@FXML
	private Pane mainPane;
	
	@FXML
    private Tab startTab;

    @FXML
    private Pane startPane;

    @FXML
    private Button genDataButton;

    @FXML
    private ProgressBar progBarGen;

    @FXML
    private TextField genAmountTxtField;

    @FXML
    private Button saveDataButton;

    @FXML
    private Label timeTakenLab;

    @FXML
    private Tab createTab;

    @FXML
    private Pane createPane;

    @FXML
    private Button createPersonButton;

    @FXML
    private TextField FNTxtField;

    @FXML
    private TextField LNTxtField;

    @FXML
    private TextField GenderTxtField;

    @FXML
    private DatePicker birthDtPick;

    @FXML
    private TextField HeightTxtField;

    @FXML
    private TextField NationTxtField;

    @FXML
    private Tab searchTab;

    @FXML
    private Pane searchPane;

    @FXML
    private TextField searTxtField;

    @FXML
    private ChoiceBox<?> searChBox;

    @FXML
    private ListView<?> searListView;

    @FXML
    private Tab modifyTab;

    @FXML
    private Pane modifyPane;

    @FXML
    private TextField modTxtField;

    @FXML
    private ChoiceBox<?> modChBox;

    @FXML
    private ListView<?> modListView;

    @FXML
    private TextField delTxtField;

    @FXML
    private ChoiceBox<?> delChBox;

    @FXML
    private ListView<?> delListView;
    
    @FXML
    private ImageView picSearImgView;

    @FXML
    private TextField fNameSearTxtField;

    @FXML
    private TextField lNameSearTxtField;

    @FXML
    private TextField codeSearTxtField;

    @FXML
    private TextField genSearTxtField;

    @FXML
    private DatePicker birthSearTxtField;

    @FXML
    private TextField heigSearTxtField;

    @FXML
    private TextField natSearTxtField;
    
    @FXML
    private Button updateButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
	public void initialize() throws Exception {
		base = new DataBase();
		//loadData();
	}
    
    @FXML
	public void start(ActionEvent event) throws Exception {
    	loadMainMenu(null);
    }
    
    @FXML
    void createPerson(ActionEvent event) {
    	try {
        	String fName = FNTxtField.getText();
        	String lName = LNTxtField.getText();
        	String gender = GenderTxtField.getText();
        	LocalDate birth = birthDtPick.getValue();
        	double height = Double.valueOf(HeightTxtField.getText());
        	String nat = NationTxtField.getText();
        	if(fName.length()>=1 && lName.length()>=1 && gender.length()>=1 && height>0 && nat.length()>1) {
        		base.addPerson(fName, lName, gender, birth, height, nat);
        		LocalDate temp = LocalDate.now();
        		FNTxtField.setText("");
        		LNTxtField.setText("");
        		GenderTxtField.setText("");
        		birthDtPick.setValue(temp);
        		HeightTxtField.setText("");
        		NationTxtField.setText("");
        	}else {
        		showNotCompleteMandatoryFieldsWarning();
        	}
		} catch (Exception e) {
			showNotCompleteMandatoryFieldsWarning();
		}
    }
    
    @FXML
    void foundPerson(ActionEvent event) throws Exception{
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("foundPerson.fxml"));
		
		fxmlLoader.setController(this);    	
		Pane found = fxmlLoader.load();
    	picSearImgView.setImage(new Image("https://thispersondoesnotexist.com/"));
		
		mainPane.getChildren().clear();
    	mainPane.getChildren().add(found);
    }
    
    @FXML
    void genData(ActionEvent event) {

    }
    
    @FXML
    void loadMainMenu(ActionEvent event) throws Exception{
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
		
		fxmlLoader.setController(this);    	
		TabPane mainMenu = fxmlLoader.load();
    	
		mainPane.getChildren().clear();
    	mainPane.getChildren().add(mainMenu);
    }
    
    public void loadData() {
		
	}
    
    public void saveData() throws IOException {
    	
	}
    
    public void showNotCompleteMandatoryFieldsWarning() {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Warning");
	    alert.setHeaderText("Mandatory fields are empty");
	    alert.setContentText("Please fill all the mandatory fields");
	    
	    Exception e = new NotCompleteMandatoryFieldsException();
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    pw.write(e.toString());;
	    String exceptionText = sw.toString();

	    Label label = new Label("The caught exception was:");

	    TextArea textArea = new TextArea(exceptionText);
	    textArea.setEditable(false);
	    textArea.setWrapText(true);

	    textArea.setMaxWidth(Double.MAX_VALUE);
	    textArea.setMaxHeight(Double.MAX_VALUE);
	    GridPane.setVgrow(textArea, Priority.ALWAYS);
	    GridPane.setHgrow(textArea, Priority.ALWAYS);

	    GridPane expContent = new GridPane();
	    expContent.setMaxWidth(Double.MAX_VALUE);
	    expContent.add(label, 0, 0);
	    expContent.add(textArea, 0, 1);

	    alert.getDialogPane().setExpandableContent(expContent);
	    
	    alert.showAndWait();
    }
    
    public void showWrongInformationWarning(String id,String pass) {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText("Wrong information");
	    alert.setContentText("Please check the information entered in the fields");
	    
	    Exception e = new WrongInformationException(id,pass);
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    pw.write(e.toString());;
	    String exceptionText = sw.toString();

	    Label label = new Label("The caught exception was:");

	    TextArea textArea = new TextArea(exceptionText);
	    textArea.setEditable(false);
	    textArea.setWrapText(true);

	    textArea.setMaxWidth(Double.MAX_VALUE);
	    textArea.setMaxHeight(Double.MAX_VALUE);
	    GridPane.setVgrow(textArea, Priority.ALWAYS);
	    GridPane.setHgrow(textArea, Priority.ALWAYS);

	    GridPane expContent = new GridPane();
	    expContent.setMaxWidth(Double.MAX_VALUE);
	    expContent.add(label, 0, 0);
	    expContent.add(textArea, 0, 1);

	    alert.getDialogPane().setExpandableContent(expContent);
	    
	    alert.showAndWait();
    }
    
    public void showNumberFormatError() {
    	Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText("Number Format Exception");
	    alert.setContentText("You should enter an integer number in the Product Price or Product Calories field"); 

	    Exception e = new NumberFormatException("You should enter an integer number in the Product Price or Product Calories field");
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    pw.write(e.toString());;
	    String exceptionText = sw.toString();

	    Label label = new Label("The caught exception was:");

	    TextArea textArea = new TextArea(exceptionText);
	    textArea.setEditable(false);
	    textArea.setWrapText(true);

	    textArea.setMaxWidth(Double.MAX_VALUE);
	    textArea.setMaxHeight(Double.MAX_VALUE);
	    GridPane.setVgrow(textArea, Priority.ALWAYS);
	    GridPane.setHgrow(textArea, Priority.ALWAYS);

	    GridPane expContent = new GridPane();
	    expContent.setMaxWidth(Double.MAX_VALUE);
	    expContent.add(label, 0, 0);
	    expContent.add(textArea, 0, 1);

	    alert.getDialogPane().setExpandableContent(expContent);
	    	    
	    alert.showAndWait();
    }
    
    public void showIncompatibleFieldsError() {
    	Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText("Incompatible field Error");
	    alert.setContentText("You should only fill one or none of the non-mandatory fields");
	    
	    Exception e = new IncompatibleFieldsException();
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    pw.write(e.toString());;
	    String exceptionText = sw.toString();

	    Label label = new Label("The caught exception was:");

	    TextArea textArea = new TextArea(exceptionText);
	    textArea.setEditable(false);
	    textArea.setWrapText(true);

	    textArea.setMaxWidth(Double.MAX_VALUE);
	    textArea.setMaxHeight(Double.MAX_VALUE);
	    GridPane.setVgrow(textArea, Priority.ALWAYS);
	    GridPane.setHgrow(textArea, Priority.ALWAYS);

	    GridPane expContent = new GridPane();
	    expContent.setMaxWidth(Double.MAX_VALUE);
	    expContent.add(label, 0, 0);
	    expContent.add(textArea, 0, 1);

	    alert.getDialogPane().setExpandableContent(expContent);
	    	    
	    alert.showAndWait();
    }
}