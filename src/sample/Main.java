package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Label labelEnvironment, labelUser, labelPassword;
    ComboBox comboBox;
    ChoiceBox choiceBox;
    PasswordField passwordField;
    ObservableList<String> prodList,testList,devList;

    @Override
    public void start(Stage primaryStage) {
        labelEnvironment = new Label("Srodowisko: ");
        labelPassword = new Label("Haslo: ");
        labelUser = new Label("Uzytkownik: ");

        ObservableList<String> environment = FXCollections.observableArrayList();
        environment.add("Produkcyjne");
        environment.add("Testowe");
        environment.add("Deweloperskie");

        choiceBox = new ChoiceBox(environment);
        choiceBox.setValue("Produkcyjne");
        choiceBox.setPrefWidth(200);

        ObservableList<String> prodList = FXCollections.observableArrayList();
        prodList.add("Jarek");
        prodList.add("Marek");
        prodList.add("Zbychu");

        ObservableList<String> testList = FXCollections.observableArrayList();
        testList.add("Marcin");
        testList.add("Wojtek");

        ObservableList<String> devList = FXCollections.observableArrayList();
        devList.add("Stefan");
        devList.add("Michal");

        comboBox = new ComboBox(prodList);
        comboBox.setValue("Jarek");
        comboBox.setPrefWidth(200);
        comboBox.setEditable(true);

        choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ChoiceBox>() {
            @Override
            public void changed(ObservableValue<? extends ChoiceBox> observableValue, ChoiceBox oldValue, ChoiceBox newValue) {
                if(newValue.equals("Produkcyjne")) {
                    ChoiceBox box = (ChoiceBox) choiceBox.getSelectionModel().getSelectedItem();
                    comboBox.setValue(prodList);
                }
                else if(newValue.equals("Testowe")) {
                    ChoiceBox box = (ChoiceBox) choiceBox.getSelectionModel().getSelectedItem();
                    comboBox.setValue(testList);
                }
                else if(newValue.equals("Deweloperskie")){
                    ChoiceBox box = (ChoiceBox) choiceBox.getSelectionModel().getSelectedItem();
                    comboBox.setValue(devList);
                }
            }
        });

        passwordField = new PasswordField();
        passwordField.setPromptText("Podaj swoje haslo");
        passwordField.setPrefWidth(200);

        choiceBox.valueProperty().addListener(
                ((observableValue, oldValue, newValue) -> choiceBox_ChangedValue(newValue))
        );

        passwordField.textProperty().addListener(
                (observable, oldValue, newValue) -> passwordField_Changed(newValue));

        GridPane gridPane = new GridPane();
        gridPane.add(labelEnvironment, 0, 0);
        gridPane.add(choiceBox,1,0);
        gridPane.add(labelUser, 0, 1);
        gridPane.add(comboBox,1,1);
        gridPane.add(labelPassword, 0, 2);
        gridPane.add(passwordField,1,2);

        gridPane.setVgap(10);
        ColumnConstraints column1 = new ColumnConstraints(100);
        ColumnConstraints column2 = new ColumnConstraints(200);
        gridPane.getColumnConstraints().addAll(column1,column2);

        TextInputDialog textInputDialog = new TextInputDialog("Okno Logowania");
        textInputDialog.setTitle("Okno Logowania");
        textInputDialog.setHeaderText("Logowanie do systemu");
        textInputDialog.setContentText("Srodowisko:");
        textInputDialog.getDialogPane().setPrefHeight(300);
        textInputDialog.getDialogPane().setPrefWidth(400);
        textInputDialog.getDialogPane().setContent(gridPane);
        ((Button) textInputDialog.getDialogPane().lookupButton(ButtonType.OK)).setText("Login");
        ((Button) textInputDialog.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Anuluj");

        Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/3/38/Svengraph_Lock.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(70);
        textInputDialog.setGraphic(imageView);

        Optional<String> result = textInputDialog.showAndWait();
    }

    private void choiceBox_ChangedValue(Object newValue) {
        if(newValue.equals("Produkcjne")){
            comboBox = new ComboBox(prodList);
        }else if(newValue.equals("testowe")){
            comboBox = new ComboBox(testList);
        }else if(newValue.equals("Deweloperskie")){
            comboBox = new ComboBox(devList);
        }
    }

    private void passwordField_Changed(String newValue) {
        if(newValue.equalsIgnoreCase("inu")){
            Stage newStage= new Stage();
            newStage.setWidth(300);
            newStage.setHeight(200);
            newStage.setTitle("Congrats Window");
            newStage.setAlwaysOnTop(true);
            newStage.centerOnScreen();
            newStage.setResizable(false);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.showAndWait();
        }
    }


}
