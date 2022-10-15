package com.majorproject.supplychain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    Button loginButton;
    private static final int width=700,height=600,upperLine=50;

    private Pane headerBar(){
        Pane topPane= new Pane();
        topPane.setPrefSize(width,upperLine-10);

        // addlogo
        Image image= new Image("C:\\Users\\Koushal_K\\Desktop\\SupplyChain\\logo.png");
        ImageView logo=new ImageView(image);
        logo.setFitHeight(50);
        logo.setFitWidth(50);
        logo.setTranslateX(10);

        // textField: for search
        TextField searchText= new TextField();
        searchText.setPromptText("Search here");
        searchText.setTranslateX((80));
        searchText.setPrefWidth((double)300);
        searchText.setTranslateY(10);

        //create Buttons
        Button searchButton=new Button("Search");
        Button loginButton= new Button("Login");
        searchButton.setTranslateX(385);
        loginButton.setTranslateX(480);
        searchButton.setTranslateY(10);
        loginButton.setTranslateY(10);

        topPane.getChildren().addAll(searchText,searchButton,loginButton,logo);

        return topPane;
    }
    private Pane CreateContent(){
        Pane root= new Pane();
        root.setPrefSize(width,height+upperLine);
        root.getChildren().add(headerBar());
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(CreateContent());
        stage.setTitle("SupplyChainApplication");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}