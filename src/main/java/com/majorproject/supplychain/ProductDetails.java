package com.majorproject.supplychain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


public class ProductDetails {
    //table view;
    public Pane getAllProducts(){
        TableView<Product> table= new TableView<>();

        //create columns:
        TableColumn idCol= new TableColumn("ID");
        idCol.setCellFactory(new PropertyValueFactory<>("id"));
        TableColumn nameCol= new TableColumn("Product");
        nameCol.setCellFactory(new PropertyValueFactory<>("name"));
        TableColumn priceCol= new TableColumn("Price");
        priceCol.setCellFactory(new PropertyValueFactory<>("price"));

        ObservableList<Product> data= FXCollections.observableArrayList(
                new Product(2,"abc",889.0),
                new Product(3,"def",990.0)
        );
        table.setItems(data);
        table.getColumns().addAll(idCol,nameCol,priceCol);
        Pane tablePane= new Pane();
        tablePane.getChildren().add(table);

        return tablePane;
    }
}
