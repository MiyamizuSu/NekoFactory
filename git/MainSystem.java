package Application;

import DreamFatcory.Control.AdminstratorController;
import DreamFatcory.View.GUI;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainSystem extends Application{
	private GUI UI;
	@Override
	public void start(Stage arg0) throws Exception {
		UI=new GUI();
		UI.StartView();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
