package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.*;

public class MainMenuController {
	
	private DataBase base;

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
    private TextField BirthTxtField;

    @FXML
    private TextField HeightTxtField;

    @FXML
    private TextField NationTxtField;

    @FXML
    private Tab searchTab;

    @FXML
    private Pane searchPane;

    @FXML
    private Tab modifyTab;

    @FXML
    private Pane modifyPane;
    
    @FXML
	public void start(ActionEvent event) {
    	
    }
    
    @FXML
    void createPerson(ActionEvent event) {

    }

    @FXML
    void genData(ActionEvent event) {

    }
    
    public void loadData() {
		
	}
    
    public void saveData() throws IOException {
    	
	}
}