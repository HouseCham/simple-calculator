package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class HistoryController {

	@FXML
	private ListView historyListView;
	
	public void initializeCalculations(ArrayList<String> calculationHistory) {
		calculationHistory.forEach((calculation)->{
			historyListView.getItems().add(calculation);
		});
	}
}
