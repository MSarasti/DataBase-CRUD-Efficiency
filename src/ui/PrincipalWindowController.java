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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.*;

public class PrincipalWindowController {
	
	private DataBase base;
	private ClientController clientController;
	private CurrentClientsController currentClientsController;
	private CanceledClientsController canceledClientsController;
	private QueueController queueController;

	@FXML
	private ToggleGroup options;

	@FXML
	private RadioButton queue;

	@FXML
	private RadioButton information;
	
	@FXML
	private Button btDatabase;
	
	@FXML
	private Button btCanceledClients;
	
	@FXML
	private Button btStart;
	
	@FXML
	public void initialize() {
		base = new DataBase();
		loadData();
		queue.setToggleGroup(options);
		information.setToggleGroup(options);
	}

	@FXML
	public void start(ActionEvent event) throws IOException {
		if(queue.isSelected()) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientQueue.fxml"));
			Pane root= fxmlLoader.load();
			queueController = fxmlLoader.getController();
			queueController.setPrincipal(this);
			Scene scene= new Scene(root);
			Stage stage= new Stage();
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
			stage.setTitle("Waiting line");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
			stage = (Stage) information.getScene().getWindow();
			stage.hide();
		}else if(information.isSelected()) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientInformation.fxml"));
			Pane root= fxmlLoader.load();
			clientController = fxmlLoader.getController();
			clientController.setPrincipal(this);
			Scene scene= new Scene(root);
			Stage stage= new Stage();
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
			stage.setTitle("Client Information");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
			stage = (Stage) information.getScene().getWindow();
			stage.hide();
		}
	}
	
    @FXML
    public void canceledClients(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CancelledClients.fxml"));
    	Pane root = fxmlLoader.load();
    	canceledClientsController = fxmlLoader.getController();
    	canceledClientsController.setPrincipal(this);
		Scene scene= new Scene(root);
		Stage stage= new Stage();
		stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
		stage.setTitle("Client Information");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		btDatabase.setDisable(true);
		btCanceledClients.setDisable(true);
		btStart.setDisable(true);
		stage.setOnHidden(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				btDatabase.setDisable(false);
				btCanceledClients.setDisable(false);
				btStart.setDisable(false);
			}
		});
    }

	@FXML
    public void clientDatabase(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CurrentClients.fxml"));
    	Pane root= fxmlLoader.load();
		currentClientsController= fxmlLoader.getController();
		currentClientsController.setPrincipal(this);
		Scene scene= new Scene(root);
		Stage stage= new Stage();
		stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
		stage.setTitle("Client Information");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		btDatabase.setDisable(true);
		btCanceledClients.setDisable(true);
		btStart.setDisable(true);
		stage.setOnHidden(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				btDatabase.setDisable(false);
				btCanceledClients.setDisable(false);
				btStart.setDisable(false);
			}
		});
    }
    
    public Client searchClient(String id) {
    	return bank.searchClient(id);
    }
    
    public List<Client> getClients(){
    	return bank.getClientList();
    }

    public Bank getBank() {
		return bank;
	}
    
    public Stage getStage() {
    	return (Stage) information.getScene().getWindow();
    }
    
    public void loadData() {
    	
		BufferedReader br;
		BufferedReader br2;
		try {
			br = new BufferedReader(new FileReader(new File("resources\\database.txt")));
			br2 = new BufferedReader(new FileReader(new File("resources\\canceledAccounts.txt")));

			String data = br.readLine();
			String data2 = br2.readLine();

			while(data!=null) {

				String[] dataArray = data.split(", ");
				Account a = new Account(Double.parseDouble(dataArray[6]), dataArray[5]);
				bank.fillClientData(dataArray[0], dataArray[1], dataArray[2], LocalDate.parse(dataArray[3]), 
						LocalDate.parse(dataArray[4]), a, dataArray[7], Double.parseDouble(dataArray[8]));
				data = br.readLine();
			}

			while(data2!=null) {
				String[] dataArray = data2.split(", ");
				Account a = new Account(Double.parseDouble(dataArray[6]), dataArray[5]);
				bank.fillCanceledClientData(dataArray[0], dataArray[1], dataArray[2], LocalDate.parse(dataArray[3]), 
						LocalDate.parse(dataArray[4]), a, dataArray[7], Double.parseDouble(dataArray[8]), LocalDate.parse(dataArray[9]),
						dataArray[10]);
				data2 = br2.readLine();
			}
			br.close();
			br2.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void saveData() throws IOException {
		
    	File tempCanceledAccounts = new File("resources\\tempFile.txt");
		File tempDataBase = new File("resources\\tempFile.txt");
		File canceledAccounts = new File("resources\\canceledAccounts.txt");
		File database = new File("resources\\database.txt");
		BufferedWriter dataBaseBW = new BufferedWriter(new FileWriter(database));
		BufferedWriter canceledAccountsBW = new BufferedWriter(new FileWriter(canceledAccounts));
		
		for (Client client : bank.getClientList()) {
			dataBaseBW.write(client.getClientData());
			dataBaseBW.newLine();
		}
		
		for (Client client : bank.getClientStack()) {
			canceledAccountsBW.write(client.getClientData());
			canceledAccountsBW.newLine();
		}
		dataBaseBW.close();
		canceledAccountsBW.close();
		try {
			Files.move(tempCanceledAccounts.toPath(), canceledAccounts.toPath(), StandardCopyOption.REPLACE_EXISTING);
			Files.move(tempDataBase.toPath(), database.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}catch (IOException E) {
		}
	}
}