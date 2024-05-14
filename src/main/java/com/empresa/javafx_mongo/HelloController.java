package com.empresa.javafx_mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TableView<TableData> dataTableView;

    @FXML
    private TableColumn<TableData, String> column1;

    @FXML
    private TableColumn<TableData, String> column2;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        MongoClient cliente = MongoClients.create("mongodb+srv://admin:admin@javaadri.bydxigr.mongodb.net/");
        System.out.println(cliente);
        MongoDatabase db = cliente.getDatabase("javaadri");
        System.out.println(db);

        // Define cómo se deben llenar las columnas
        column1.setCellValueFactory(new PropertyValueFactory<>("data1"));
        column2.setCellValueFactory(new PropertyValueFactory<>("data2"));

        // Crea y añade datos a la tabla
        ObservableList<TableData> data = FXCollections.observableArrayList(
                new TableData("Data 1", "Data 2"),
                new TableData("Data 3", "Data 4")
        );
        dataTableView.setItems(data);
    }

    // Clase para representar los datos en la tabla
    public static class TableData {
        private String data1;
        private String data2;

        public TableData(String data1, String data2) {
            this.data1 = data1;
            this.data2 = data2;
        }

        public String getData1() {
            return data1;
        }

        public String getData2() {
            return data2;
        }
    }
}