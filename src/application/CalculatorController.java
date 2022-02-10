package application;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.EvaluateString;

public class CalculatorController {
	
	@FXML
	private Label resultLabel, operacionLabel;
	
	private ArrayList<String> opHistory = new ArrayList<>();
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	public Label getOperacionLabel() {
		return operacionLabel;
	}
	
	public Label getResultLabel() {
		return resultLabel;
	}
	
	public void insertOperator(String operator) {
		operacionLabel.setText(operacionLabel.getText() + " " + operator + " ");
	}
	
	public void insertNumber(String digit) {
		operacionLabel.setText(operacionLabel.getText() + digit);
	}
	
	public void clear() {
		operacionLabel.setText("");
		resultLabel.setText("");
	}
	
	public void deleteLast() {
		if (!getOperacionLabel().getText().isEmpty()) {
			StringBuilder text = new StringBuilder(getOperacionLabel().getText());
			text.deleteCharAt(text.length() - 1);
			getOperacionLabel().setText(text.toString());
		}
	}
	
	public void insertTotal(String result) {
		resultLabel.setText(result);
	}
	
	public void ans(ActionEvent event) {
		String ans = this.getResultLabel().getText();
		clear();
		insertNumber(ans);
	}
	
	public void openHistory(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
			Parent root = loader.load();
			Main.getHistoryStage().setScene(new Scene(root));
			
			HistoryController historycontroller = loader.getController();
			historycontroller.initializeCalculations(opHistory);
			
			Main.getHistoryStage().show();
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void addOpHistory(String operation, String result) {
		this.opHistory.add(operation + " = " + result);
	}
	
	public void onMouseClick(ActionEvent event) {
		Button button = (Button) event.getSource();
		String digit = button.getText();
		
		switch(digit) {
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "0":
		case ".":
			insertNumber(digit);
			break;
		case "+":
		case "-":
		case "/":
		case "*":
			insertOperator(digit);
			break;
		case "=":
			double result = EvaluateString.evaluate(this.getOperacionLabel().getText());
			String resultStr = String.valueOf(df.format(result));
			insertTotal(resultStr);
			addOpHistory(this.getOperacionLabel().getText(), resultStr);
		}
	}
}
