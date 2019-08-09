package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Student;
import model.services.StudentService;

public class StudentListControler implements Initializable{
	
	private StudentService service;
	
	@FXML
	private TableView<Student> tableViewStudent;
	
	@FXML
	private TableColumn<Student, Integer> tableColumnRa;
	
	@FXML
	private TableColumn<Student, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	private ObservableList<Student> obsList;
	
	public void setStudentService(StudentService service) {
		this.service = service;
	}
	
	@FXML
	public void onBtNewAction() {
		System.out.println("ok");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnRa.setCellValueFactory(new PropertyValueFactory<>("ra"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewStudent.prefHeightProperty().bind(stage.heightProperty());
	}
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Student> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewStudent.setItems(obsList);
	}
	
}
