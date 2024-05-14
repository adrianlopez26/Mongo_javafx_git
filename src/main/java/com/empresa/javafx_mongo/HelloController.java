package com.empresa.javafx_mongo;

import com.mongodb.client.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.bson.Document;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private ListView lv_datos;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        MongoClient cliente = MongoClients.create("mongodb+srv://adri:adri@cluster0.uay2677.mongodb.net/");
        System.out.println(cliente);
        MongoDatabase db = cliente.getDatabase("test");
        MongoCollection tabla = db.getCollection("usuario");
        System.out.println(tabla);

        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        Document newUser = new Document("name", name)
                .append("email", email)
                .append("password", password);

        tabla.insertOne(newUser);

        ObservableList<String> documentos = FXCollections.observableArrayList();
        MongoCursor<Document> cursor = tabla.find().iterator();
        while(cursor.hasNext()){
            Document doc = cursor.next();
            documentos.add(doc.toJson());
            System.out.println(doc+"-----------------");
        }
        System.out.println(documentos);
        lv_datos.setItems(documentos);
    }
}