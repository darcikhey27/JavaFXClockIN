package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField; // use this do not use awt shit
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import student.Student;
import student.StudentLinkedList;
import java.util.*;

public class MainController implements Initializable
{

	// fields for StudentLoad scene
	@FXML
	private Pane studentScenePane;
	@FXML
	private TextField txtUserName;
	@FXML
	private TextField txtID;
	@FXML
	private Button closeButton;
	@FXML
	private Button loadAnotherButton;
	@FXML
	private Button loadSceneEnterButton;
	@FXML
	private Label loadScenelbl;

	// student linkedlist
	private static StudentLinkedList list = new StudentLinkedList();

	//////////////////////////////////////

	@FXML
	private TextField textDisplay; // main keypad TextField
	@FXML
	private Button checkinButton;
	@FXML
	private Button btnClear;

	/* main method for getting user input using mouse click */
	@FXML
	public void buttonClick(ActionEvent event)
	{
		String digit = ((Button) event.getSource()).getText();
		String old = textDisplay.getText();
		String newText = old + digit;
		if (newText.length() <= 9)
		{
			textDisplay.setText(newText);
		} else
		{
			showMsgBox("id must be less then 9 digits");
		}

	}

	/* method writing input to the textDisplay textFiled */
	private void writeInput(int number)
	{
		if (textDisplay.getLength() < 9)
		{
			String oldText = textDisplay.getText();
			textDisplay.setText(oldText + number);
		}
		// btnEnter.Focus();
	}// End of wrtieNumber method.

	/* getting user input from the keyboard */
	@FXML
	public void getKeyboardbInput(KeyEvent key)
	{
		KeyCode input = key.getCode();
		// testing for DIGITS from 0 to 10
		switch (input)
		{
		case DIGIT0:
			writeInput(0);
			break;
		case DIGIT1:
			writeInput(1);
			break;
		case DIGIT2:
			writeInput(2);
			break;
		case DIGIT3:
			writeInput(3);
			break;
		case DIGIT4:
			writeInput(4);
			break;
		case DIGIT5:
			writeInput(5);
			break;
		case DIGIT6:
			writeInput(6);
			break;
		case DIGIT7:
			writeInput(7);
			break;
		case DIGIT8:
			writeInput(8);
			break;
		case DIGIT9:
			writeInput(9);
			break;

		default:
			break;
		}
	}

	// check in students
	public void handleCheckinButton(ActionEvent event)
	{
		// validate that the input is valid
		String num = textDisplay.getText();
		if(num.length() <= 9)
		{
			showMsgBox(num);
			// good input, 
		}
		else
		{
			showMsgBox("Bad input");
		}
		

		// check that students exist in the linkedList

		// create another linkedlist of checked in students

		// display you are checked in

		// if student is not in the student database print message
	}

	// calc scene

	@FXML
	public void handleMainCloseButton(ActionEvent event)
	{
		//
	}

	public void showMsgBox(String output)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText(null);
		alert.setContentText(output);

		alert.showAndWait();

	}

	@FXML
	public void showAboutMsgbox()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText(null);
		alert.setContentText("App built by Darci K Saucedo\n"
				+ "Computer Science Student at Eastern Washington University\n" + "Mind Wide Open!");

		alert.showAndWait();

	}

	// load a new student into the program
	@FXML
	public String menuFileAddnew_btnClick(ActionEvent event) throws IOException
	{
		// crate nodes for the new stage

		Stage newStage = new Stage();
		// create the Root Node: Pane
		/* this Pane element has to math in the FXML file */
		Pane root = FXMLLoader.load(getClass().getResource("StudentLoadScene.fxml"));

		// create a new scene with root and set the stage
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// create a new stage

		newStage.setScene(scene);
		newStage.show();

		// if(Studnet form is good, return that student

		return "return something";
	}

	@FXML
	public void printList(ActionEvent event)
	{
		// System.out.println("in printlist");
		showMsgBox(list.toString());
		// System.out.println();
	}

	@FXML
	public Optional<String> menuFileAddFromText()
	{
		TextInputDialog dialog = new TextInputDialog("filepath");
		dialog.setTitle("File path");
		dialog.setHeaderText("Enter file name or path");
		dialog.setContentText("Enter the file name:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent())
		{
			return result;
		}
		return null;
	}

	// close add new student stage
	@FXML
	public void handleCloseButtonAction(ActionEvent event) // StudentLoadScene
	{
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void loadSceneHandleEnterButton(ActionEvent event) // StudentLoadScene
	{
		String name = txtUserName.getText();
		int id = 0;

		try
		{ // cast
			id = Integer.parseInt(txtID.getText());

		} catch (Exception e)
		{
			showMsgBox("Enter a valid integer for ID");
		}

		Student student = new Student(name, id);
		list.addNode(student);

		resetLabels();

	}

	@FXML
	public void resetLabels() // StudentLoadScene
	{
		// reset labels
		txtUserName.setText("");
		txtID.setText("");
	}

	@FXML
	public void handleAddAnotherButtonAction() // StudentLoadScene
	{
		txtUserName.setText("");
		txtID.setText("");
	}

	@FXML
	public void clearDisplayBox() // clear display box
	{
		textDisplay.setText("");
	}

	public java.util.Scanner readFile(String filename)
	{
		java.util.Scanner inf = null;
		try
		{
			inf = new java.util.Scanner(new File(filename));
		} catch (FileNotFoundException e)
		{
			System.out.println("file not found");
		}
		return inf;

	}

	public void addFromText()
	{
		Optional<String> filename = menuFileAddFromText();

		String fname = filename.get();

		java.util.Scanner inf = readFile(fname);

		while (inf.hasNextLine())
		{
			// output file here
			// System.out.println(inf.nextLine());
			// crate students object here and add to linkedList
			String[] splittedStr = inf.nextLine().split(",");
			String name = splittedStr[0].trim();
			int id = Integer.parseInt(splittedStr[1].trim());

			Student s = new Student(name, id);
			list.addNode(s);

		}
		// finished reading file
		printListCount();
		// System.out.println(filename.get());
	}

	public void printListCount()
	{
		int count = list.getSize();
		showMsgBox(count + " have been added to the list");
	}

	public void initialize(URL arg0, ResourceBundle arg1)
	{
		// TODO Auto-generated method stub

	}

}
