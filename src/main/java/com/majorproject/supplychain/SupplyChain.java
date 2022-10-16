package com.majorproject.supplychain;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChain extends Application {
    Button loginButton;
    private int searchBarX=100;
    private static final int width=700,height=550,upperLine=50;

    ProductDetails productDetails= new ProductDetails();

    private Pane headerBar(){
        Pane topPane= new Pane();
        topPane.setPrefSize(width,upperLine-10);

        // addlogo
        Image image= new Image("C:\\Users\\Koushal_K\\Desktop\\SupplyChain\\src\\logo.png");
        ImageView logo=new ImageView(image);
        logo.setFitHeight(70);
        logo.setFitWidth(70);
        logo.setTranslateX(10);
        logo.setTranslateY(5);

        // textField: for search
        TextField searchText= new TextField();
        searchText.setPromptText("Search here");
        searchText.setTranslateX((searchBarX));
        searchText.setTranslateY(30);
        searchText.setPrefWidth((double)300);

        //create Buttons
        Button searchButton=new Button("Search");
        Button loginButton= new Button("Login");
        searchButton.setTranslateX(searchBarX+305);
        loginButton.setTranslateX(480);
        searchButton.setTranslateY(30);
        loginButton.setTranslateY(30);

        topPane.getChildren().addAll(searchText,searchButton,loginButton,logo);

        return topPane;
    }
    private Pane CreateContent(){
        Pane root= new Pane();
        root.setPrefSize(width,height+upperLine);
        root.setStyle("-fx-background-color: #AEBDCA");
        root.getChildren().addAll(headerBar());
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