package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.StudentService;

public class MainViewControler implements Initializable {

	@FXML
	private MenuItem menuItemReportCard;

	@FXML
	private MenuItem menuItemReportCard2;

	@FXML
	public void onMenuItemReportCardAction() {
		System.out.println("onMenuItemReportCardAction()");
	}

	@FXML
	public void onMenuItemReportCard2Action() {
		loadView("/gui/StudentList.fxml", (StudentListControler controler) -> {
			controler.setStudentService(new StudentService());
			controler.updateTableView();
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	private <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controler = loader.getController();
			initializingAction.accept(controler);
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
