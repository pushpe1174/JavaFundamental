package com.part2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public static CustomerQueue Q1 = new CustomerQueue();
    public static CustomerQueue Q2 = new CustomerQueue();
    public static CustomerQueue Q3 = new CustomerQueue();
    public static CustomerQueue Q4 = new CustomerQueue();
    public static CustomerQueue Q5 = new CustomerQueue();
    public static CustomerQueue Waiting = new CustomerQueue();


    @FXML
    private TableColumn<Customer, String> fname;

    @FXML
    private TableView<Customer> myTable;

    @FXML
    private TableColumn<Customer, Integer> pos;

    @FXML
    private TableColumn<Customer, Integer> rfuel;

    @FXML
    private TableColumn<Customer, String> sname;

    @FXML
    private TableColumn<Customer, String> pump;

    @FXML
    private TableColumn<Customer, String> vno;
    @FXML
    private TextField textField;

    ObservableList<Customer> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pump.setCellValueFactory(new PropertyValueFactory<>("pump"));
        pos.setCellValueFactory(new PropertyValueFactory<>("pos"));
        fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        sname.setCellValueFactory(new PropertyValueFactory<>("sname"));
        vno.setCellValueFactory(new PropertyValueFactory<>("vno"));
        rfuel.setCellValueFactory(new PropertyValueFactory<>("rfuel"));
        setCustomers();
    }


    @FXML
    void viewWaitingOnclick() {
        list.clear();
        list.addAll(Waiting.C);
        myTable.setItems(list);
    }

    @FXML
    void viewPump_1click() {
        list.clear();
        list.addAll(Q1.C);
        myTable.setItems(list);

    }

    @FXML
    void viewPump_2click() {
        list.clear();
        list.addAll(Q2.C);
        myTable.setItems(list);

    }

    @FXML
    void viewPump_3click() {
        list.clear();
        list.addAll(Q3.C);
        myTable.setItems(list);

    }

    @FXML
    void viewPump_4click() {
        list.clear();
        list.addAll(Q4.C);
        myTable.setItems(list);

    }

    @FXML
    void viewPump_5click() {
        list.clear();
        list.addAll(Q5.C);
        myTable.setItems(list);

    }

    public void setCustomers() {
        FuelQueue[] arr = FuelSystem.getData();
        CircularQueue W = FuelSystem.getWData();

        CustomerQueue[] All = {Q1, Q2, Q3, Q4, Q5, Waiting};
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 6; i++) {
                int position = i + 1;
                if (arr[j].customers[i] == null) {
                    All[j].C[i] = new Customer(Integer.toString(j+1), position, "No", "No", "No", 0);
                } else {
                    String F = arr[j].customers[i].getF_name();
                    String S = arr[j].customers[i].getS_name();
                    String V = arr[j].customers[i].getV_no();
                    int RF = arr[j].customers[i].getR_fuel();
                    All[j].C[i] = new Customer(Integer.toString(j+1), position, F, S, V, RF);
                }
            }
        }
        for(int i = 0;i<6;i++){
            int position = i + 1;
            if (W.customers[i] == null) {
                All[5].C[i] = new Customer("W", position, "No", "No", "No", 0);
            } else {
                String F = W.customers[i].getF_name();
                String S = W.customers[i].getS_name();
                String V = W.customers[i].getV_no();
                int RF = W.customers[i].getR_fuel();
                All[5].C[i] = new Customer("W", position, F, S, V, RF);
            }
        }
    }

    public void searchOnclick() {
        list.clear();
        CustomerQueue[] All = {Q1, Q2, Q3, Q4, Q5, Waiting};
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 6; i++) {

                if (!(All[j].C[i] == null)) {
                    Boolean FNameCheck = textField.getText().equalsIgnoreCase(All[j].C[i].getFname());
                    Boolean SNameCheck = textField.getText().equalsIgnoreCase(All[j].C[i].getSname());
                    Boolean VNumCheck = textField.getText().equalsIgnoreCase(All[j].C[i].getSname());
                    if (FNameCheck || SNameCheck || VNumCheck) {
                        list.add(All[j].C[i]);
                    }
                }
            }
        }
        myTable.setItems(list);
    }
}