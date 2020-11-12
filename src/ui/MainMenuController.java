package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import customExceptions.IncompatibleFieldsException;
import customExceptions.NotCompleteMandatoryFieldsException;
import customExceptions.WrongInformationException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import model.*;
import structures.Trie;

public class MainMenuController {
	
	private DataBase base;
	private Trie trie;
	
	@FXML
	private Pane mainPane;
	
	@FXML
	private TabPane tabPane;
	
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
	private ChoiceBox<String> genderChoiceBox;

	@FXML
	private DatePicker birthDtPick;

	@FXML
	private TextField HeightTxtField;

	@FXML
	private TextField NationTxtField;

	@FXML
	private ImageView createTabImage;

	@FXML
	private Tab searchTab;

	@FXML
	private Pane searchPane;
	
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private TextField searTxtField;
	
	@FXML
	private Label foundPerson;
	
	@FXML
	private ChoiceBox<String> searChBox;

	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private Tab personTab;
	
	@FXML
	private ImageView perPhoto;

	@FXML
	private TextField fNameSearTxtField;

	@FXML
	private TextField lNameSearTxtField;

	@FXML
	private Label codeLabel;

	@FXML
	private ChoiceBox<String> modGenderChoiceBox;

	@FXML
	private DatePicker birthSearDatPick;

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
		personTab.setDisable(true);
		loadData();
		loadPhoto();
		popList();
		searChBox.setItems(FXCollections.observableArrayList("First Name", "Last Name", "Full Name", "Code"));
		genderChoiceBox.setItems(FXCollections.observableArrayList("Male", "Female", "Other"));
		modGenderChoiceBox.setItems(FXCollections.observableArrayList("Male", "Female", "Other"));
    }
    
    @FXML
    void createPerson(ActionEvent event) {
    	try {
        	String fName = FNTxtField.getText();
        	String lName = LNTxtField.getText();
        	String gender = genderChoiceBox.getValue();
        	LocalDate birth = birthDtPick.getValue();
        	double height = Double.parseDouble(HeightTxtField.getText());
        	String nat = NationTxtField.getText();
        	if(fName.length()>=1 && lName.length()>=1 && gender.length()>=1 && height>0 && nat.length()>1) {
        		base.addPerson(fName, lName, gender, birth, height, nat);
        		FNTxtField.setText("");
        		LNTxtField.setText("");
        		genderChoiceBox.setValue(null);
        		birthDtPick.setValue(null);
        		HeightTxtField.setText("");
        		NationTxtField.setText("");
        	}else {
        		showNotCompleteMandatoryFieldsWarning();
        	}
		} catch (Exception e) {
			showNotCompleteMandatoryFieldsWarning();
		}
    }
    
    public void searchPerson(String data) {
    	Person temp = null;
    	if(searChBox.getValue().equals("First Name")) {
    		temp = base.searchByFirstName(data);
    	}else if(searChBox.getValue().equals("Last Name")) {
    		temp = base.searchByLastName(data);
    	}else if(searChBox.getValue().equals("Full Name")) {
    		temp = base.searchByFullName(data);
    	}else {
    		temp = base.searchByCode(data);
    	}
    	if(temp!=null) {
	    	codeLabel.setText(temp.getCode());
	    	fNameSearTxtField.setText(temp.getFirstName());
	    	lNameSearTxtField.setText(temp.getLastName());
	    	modGenderChoiceBox.setValue(temp.getGender());
	    	birthSearDatPick.setValue(temp.getBirthDate());
	    	heigSearTxtField.setText(temp.getHeight()+"");
	    	natSearTxtField.setText(temp.getNationality());
    	}
    }
    
    @FXML
    void deletePerson() {
    	try {
    	String code = codeLabel.getText();
    	base.deletePersonCode(code);
    	personTab.setDisable(true);
		tabPane.getSelectionModel().select(searchTab);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void updatePerson() {
    	try {
    		String code = codeLabel.getText();
        	String fName = fNameSearTxtField.getText();
        	String lName = lNameSearTxtField.getText();
        	String gender = modGenderChoiceBox.getValue();
        	LocalDate birth = birthSearDatPick.getValue();
        	double height = Double.valueOf(heigSearTxtField.getText());
        	String nat = natSearTxtField.getText();
        	if(fName.length()>=1 && lName.length()>=1 && gender.length()>=1 && height>0 && nat.length()>1) {
        		base.updatePerson(code, fName, lName, gender, birth, height, nat);
        		personTab.setDisable(true);
        		tabPane.getSelectionModel().select(searchTab);
        	}else {
        		showNotCompleteMandatoryFieldsWarning();
        	}
		} catch (Exception e) {
			showNotCompleteMandatoryFieldsWarning();
		}
    }
    
    public void popList() {
    	foundPerson.setText(null);
    	searChBox.setValue(null);
    	trie = new Trie();
    	searChBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				searTxtField.setText(null);
				scrollPane.setContent(null);
				foundPerson.setText(null);
				try {
					if((int)newValue==0) {
						trie = new Trie();
						for (Person person : base.getPersonsByFirstName()) {
							trie.insert(person.getFirstName());
						}
					}else if((int)newValue==1) {
						trie = new Trie();
						for (Person person : base.getPersonsByLastName()) {
							trie.insert(person.getLastName());
						}
					}else if((int)newValue==2) {
						trie = new Trie();
						for (Person person : base.getPersonsByFullName()) {
							trie.insert(person.getFirstName()+" "+person.getLastName());
						}
					}else if((int)newValue==3) {
						trie = new Trie();
						for (Person person : base.getPersonsByCode()) {
							trie.insert(person.getCode());
						}
					}
				}catch(Exception e) {
				}
			}
    	});
    	searTxtField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				scrollPane.setVisible(true);
				String entry = searTxtField.getText();
				GridPane gridPane = new GridPane();
				if (entry.length()==0) {
					gridPane.getChildren().clear();
					scrollPane.setContent(gridPane);
					scrollPane.setVisible(false);
				} else {
					ArrayList<String> data = trie.getEveryWord(entry);
					gridPane.getChildren().clear();
					scrollPane.setContent(gridPane);
					foundPerson.setText("("+data.size()+") results");
				
					if(data.size()<=100) {
						for (int i = 0; i < data.size(); i++) {
							Label label = new Label(data.get(i));
							gridPane.add(label, 1, i);
							if(data.size()<=20) {
								Button edit = new Button("EDIT");
								gridPane.add(edit, 2, i);
								edit.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent arg0) {
										searTxtField.setText(null);
										gridPane.getChildren().clear();
										scrollPane.setContent(gridPane);
										foundPerson.setText(null);
										tabPane.getSelectionModel().select(personTab);
										personTab.setDisable(false);
										searchPerson(label.getText());
									}
								});
							}
							scrollPane.setContent(gridPane);
						}
					}else {
						for (int i = 0; i < 99; i++) {
							Label label = new Label(data.get(i));
							gridPane.add(label, 1, i);
							if(data.size()<=20) {
								Button edit = new Button("EDIT");
								gridPane.add(edit, 2, i);
								edit.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent arg0) {
										searTxtField.setText(null);
										gridPane.getChildren().clear();
										scrollPane.setContent(gridPane);
										foundPerson.setText(null);
										tabPane.getSelectionModel().select(personTab);
										personTab.setDisable(false);
										searchPerson(label.getText());
									}
								});
							}
							scrollPane.setContent(gridPane);
						}
					}
				}
			}
		});
    }
    
    @FXML
    void loadPhoto() {
		try {
			URL url = new URL("https://thispersondoesnotexist.com/image");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("User-Agent", "Nice");
			Image image = new Image(connection.getInputStream());
			createTabImage.setImage(image);
			perPhoto.setImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void genData(ActionEvent event) {
    	timeTakenLab.setText(null);
    	double time = System.currentTimeMillis();
    	Task<Void> task = new Task<Void>() {
			@Override
			public Void call() throws IOException {
				progBarGen.setVisible(true);
				try {
					genDataFromInfo();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				progBarGen.setMaxHeight(System.currentTimeMillis()-time);
				return null;
			}
		};
		progBarGen.progressProperty().bind(task.progressProperty());
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent arg0) {
				progBarGen.setVisible(false);
				timeTakenLab.setText(progBarGen.getMaxHeight()/1000+" seconds");
			}
		});
		Thread loadingThread = new Thread(task);
		loadingThread.start();
    }
    
    public void genDataFromInfo() throws IOException{
    	File file = new File("data\\NameGender.txt");
    	BufferedReader br = new BufferedReader(new FileReader(file));
    	String[] ng = br.readLine().split(",");
    	String fname = ng[0];
    	String gender = (ng[1].equals("boy")) ? "Male" : "Female";
    	int amountGen = Integer.valueOf(genAmountTxtField.getText());
    	int total = 0;
    	while(fname!=null && total<=amountGen) {
    		File file2 = new File("data\\LastName.txt");
			BufferedReader br2 = new BufferedReader(new FileReader(file2));
			String lname = br2.readLine();
			while(lname!=null && total<=amountGen) {
				base.genPerson(fname, lname, gender, genNation());
				lname = br2.readLine();
				total++;
			}
			br2.close();
			ng = br.readLine().split(",");
			fname = ng[0];
			gender = (ng[1].equals("boy")) ? "Male" : "Female";
    	}
    	br.close();
    	genAmountTxtField.setText(null);
    }
    
    private String genNation() throws IOException {
    	File file = new File("data\\Countries.txt");
    	BufferedReader br = new BufferedReader(new FileReader(file));
    	String data = br.readLine();
    	boolean sentinel = false;
    	String nation = "";
    	double prob = Double.valueOf((Math.random() * (32.7 - 0.01)) + 0.01);
    	
    	while(data!=null && !sentinel) {
    		String[] dat = data.split(",");
    		if(prob<=Double.valueOf(dat[2])) {
    			nation = dat[0];
    			sentinel = true;
    		}
    		data = br.readLine();
    	}
    	br.close();
    	return nation;
    }
    
    public void loadData() {
    	try {
			FileInputStream file = new FileInputStream("data\\database.dat");
			ObjectInputStream ois = new ObjectInputStream(file);
			base = (DataBase) ois.readObject();
			ois.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    public void saveData() {
		try {
			FileOutputStream file = new FileOutputStream("data\\database.dat");
			ObjectOutputStream oos = new ObjectOutputStream(file);
			oos.writeObject(base);
			oos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
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