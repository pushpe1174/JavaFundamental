package com.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FuelSystem extends Application {
    public static FuelQueue Q1 = new FuelQueue(1);
    public static FuelQueue Q2 = new FuelQueue(2);
    public static FuelQueue Q3 = new FuelQueue(3);
    public static FuelQueue Q4 = new FuelQueue(4);
    public static FuelQueue Q5 = new FuelQueue(5);

    public static CircularQueue WaitingList = new CircularQueue();

    public static int RemainingFuel = 6600;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FuelSystem.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 325);
        stage.setTitle("Fuel System 2022");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args){
        mainProgram();
    }

    private static void mainProgram() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                         Fuel Center                        |");
        System.out.println("\t--------------------------------------------------------------");

        System.out.println("\t Remaining Fuel Amount : " + RemainingFuel + "L");

        System.out.println("\t 100 or VFQ: View all Fuel Queues");
        System.out.println("\t 101 or VEQ: View all Empty Queues");
        System.out.println("\t 102 or ACQ: Add customer to a Queue");
        System.out.println("\t 103 or RCQ: Remove a customer from a Queue.");
        System.out.println("\t 104 or PCQ: Remove a served customer");
        System.out.println("\t 105 or VCS: View Customers Sorted in alphabetical order");
        System.out.println("\t 106 or SPD: Store Program Data into file");
        System.out.println("\t 107 or LPD: Load Program Data from file");
        System.out.println("\t 108 or STK: View Remaining Fuel Stock");
        System.out.println("\t 109 or AFS: Add Fuel Stock.");
        System.out.println("\t 110 or IFQ: Income of each Fuel queue");
        System.out.println("\t 111 ----- : Task - 2 GUI");
        System.out.println("\t 999 or EXT: Exit the Program");

        boolean flag = false;

        do {
            Scanner input = new Scanner(System.in);
            System.out.print("\n\t Enter your option : ");
            String choice = input.next();
            choice = choice.toUpperCase();

            switch (choice) {
                case "100", "VFQ" -> viewFuelQ();
                case "101", "VEQ" -> viewEmptyQ();
                case "102", "ACQ" -> addCustomerQ();
                case "103", "RCQ" -> removeCustomerQ();
                case "104", "PCQ" -> removeSCustomerQ();
                case "105", "VCS" -> sortCustomer();
                case "106", "SPD" -> storeFile();
                case "107", "LPD" -> loadFile();
                case "108", "STK" -> viewRFuelS();
                case "109", "AFS" -> addFuelS();
                case "110", "IFQ" -> incomeF();
                case "111"        -> launch();
                case "999", "EXT" -> System.exit(0);
                default -> {
                    System.out.println("\n\t Select correct option!!");
                    flag = true;
                }
            }
        }while(flag);
    }

    private static void incomeF() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                         Income Fuel                        |");
        System.out.println("\t--------------------------------------------------------------");

        FuelQueue[] arr = {Q1,Q2,Q3,Q4,Q5};
        do{
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("\n\t Select Pump Num : ");
                int pumpNum = input.nextInt();

                if((0<pumpNum & pumpNum<6)){
                    arr[pumpNum-1].incomeFQ();
                }
                else{
                    System.out.println("\t Pump range is upto 1-5");
                }

            }catch (InputMismatchException e){
                System.out.println("\t\t Input is Invalid ");
            }
        }while (proceedCheck(5));

        mainProgram();
    }

    private static void loadFile() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                          Load file                         |");
        System.out.println("\t--------------------------------------------------------------");

        FuelQueue tempQ1 = new FuelQueue(1);
        FuelQueue tempQ2 = new FuelQueue(2);
        FuelQueue tempQ3 = new FuelQueue(3);
        FuelQueue tempQ4 = new FuelQueue(4);
        FuelQueue tempQ5 = new FuelQueue(5);
        CircularQueue tempWait = new CircularQueue();

        FuelQueue[] temparr = {tempQ1, tempQ2, tempQ3, tempQ4, tempQ5};
        String F_name, S_name, V_no;
        int R_fuel, posNum, pumpNum;
        int loadRemain;
        int lineCount = 1;

        File inputFile = new File("Fuel_CW2022_Part2_Load.txt");
        try {
            Scanner read = new Scanner(inputFile);
            while (read.hasNext()){
                String LineR = read.nextLine();
                Scanner R = new Scanner(LineR);
                R.useDelimiter("#");
                if(lineCount==1){
                    if(R.next().equals("RemainingFuel")){
                        loadRemain = R.nextInt();
                        RemainingFuel = loadRemain;
                    }
                }else if(1<lineCount & lineCount<32){
                    pumpNum = R.nextInt();
                    posNum = R.nextInt();
                    F_name = R.next();
                    if(F_name.equals("No")){
                        continue;
                    }else{
                        S_name = R.next();
                        V_no = R.next();
                        R_fuel = R.nextInt();
                        Passenger customer = new Passenger(F_name,S_name,V_no,R_fuel);
                        temparr[pumpNum-1].tempAddCustomer(customer,posNum-1);
                    }
                }else {
                    if(R.next().equals("W")){
                        F_name = R.next();
                        S_name = R.next();
                        V_no = R.next();
                        R_fuel = R.nextInt();
                        Passenger customer = new Passenger(F_name,S_name,V_no,R_fuel);
                        tempWait.enqueue(customer);
                    }
                }
                lineCount++;
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Q1 = tempQ1;
        Q2 = tempQ2;
        Q3 = tempQ3;
        Q4 = tempQ4;
        Q5 = tempQ5;
        WaitingList = tempWait;

        FuelQueue[] arr = {Q1,Q2,Q3,Q4,Q5};
        for (FuelQueue Q:arr) {
            Q.setNextIndex();
        }

        System.out.println("\t\t Successfully Loaded form txt File!!");

        if(proceedCheck(1)){
            mainProgram();
        }else{
            System.out.println("Have a nice Day!!");
        }

    }


    private static void storeFile() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                        Store in file                       |");
        System.out.println("\t--------------------------------------------------------------");
        FuelQueue[] arr = {Q1,Q2,Q3,Q4,Q5};
        try {
            FileWriter fileWrite = new FileWriter("Fuel_CW2022_Part2.txt");
            System.out.println("\n\t\t File created!!!");
            fileWrite.write("Remaining Fuel Stock is " + RemainingFuel + " L");
            for (FuelQueue Q : arr){
                fileWrite.write(Q.storeFQ());
            }
            fileWrite.write(WaitingList.storeCQ());

            fileWrite.close();
            System.out.println("\t\tSuccessfully written.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            FileWriter fileWrite = new FileWriter("Fuel_CW2022_Part2_Load.txt");
            fileWrite.write("RemainingFuel#" + RemainingFuel);
            for (FuelQueue Q : arr){
                fileWrite.write(Q.storeforLoadFQ());
            }
            fileWrite.write(WaitingList.storeforLoadCQ());
            fileWrite.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        if(proceedCheck(1)){
            mainProgram();
        }else{
            System.out.println("Have a nice Day!!");
        }
    }
    private static void addFuelS() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                        Add Fuel Stock                      |");
        System.out.println("\t--------------------------------------------------------------");

        System.out.print("\n\t Remaining Fuel amount : " + RemainingFuel);
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("\n\t Add Fuel new amount : ");
            int newFuel = input.nextInt();
            RemainingFuel += newFuel;
            System.out.println("\t New Fuel amount : " + RemainingFuel);
            System.out.println("\t\t Successfully added fuel ");
        }catch (InputMismatchException e){
            System.out.println("\t\t Input is Invalid ");
        }
        if(proceedCheck(1)){
            mainProgram();
        }else{
            System.out.println("Have a nice Day!!");
        }
    }

    private static void viewRFuelS() {
        System.out.println("\n\t Remaining Fuel Stock is " + RemainingFuel + "L");
        if(proceedCheck(1)){
            mainProgram();
        }else{
            System.out.println("\t Have a nice Day!!");
        }
    }

    private static void removeSCustomerQ() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                   Remove Served Customer                   |");
        System.out.println("\t--------------------------------------------------------------");
        FuelQueue[] arr = {Q1,Q2,Q3,Q4,Q5};

        do{
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("\n\t Select Pump Num : ");
                int pumpNum = input.nextInt();

                if((0<pumpNum & pumpNum<6)){
                    RemainingFuel -= arr[pumpNum-1].removeCustomerSFQ();
                    if(WaitingList.isCQEmpty()){
                        arr[pumpNum-1].addCustomerFQ(WaitingList.getFWaiting());
                        WaitingList.dequeue();
                        System.out.println("\t\t ( from WaitingList )");
                    }
                }
                else{
                    System.out.println("\t Pump range is upto 1-5");
                }

            }catch (InputMismatchException e){
                System.out.println("\t\t Input is Invalid ");
            }
        }while (proceedCheck(4));

        mainProgram();
    }

    private static void sortCustomer() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                        Sort Customer                       |");
        System.out.println("\t--------------------------------------------------------------");

        Q1.sortCustomerFQ();
        Q2.sortCustomerFQ();
        Q3.sortCustomerFQ();
        Q4.sortCustomerFQ();
        Q5.sortCustomerFQ();

        if(proceedCheck(1)){
            mainProgram();
        }else{
            System.out.println("Have a nice Day!!");
        }
    }

    private static void removeCustomerQ() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                       Remove Customer                      |");
        System.out.println("\t--------------------------------------------------------------");

        FuelQueue[] arr = {Q1,Q2,Q3,Q4,Q5};

        do{
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("\n\t Select Pump Num : ");
                int pumpNum = input.nextInt();
                System.out.print("\t Select Position : ");
                int posNum = input.nextInt();
                if((0<pumpNum & pumpNum<6) & (0<posNum & posNum<7)){
                    arr[pumpNum-1].removeCustomerFQ(posNum-1);
                    if(WaitingList.isCQEmpty()){
                        arr[pumpNum-1].addCustomerFQ(WaitingList.getFWaiting());
                        WaitingList.dequeue();
                        System.out.println("\t\t ( from WaitingList )");
                    }
                }else{
                    System.out.println("\t Pump range is upto 1-5 and Position range is upto 1-6");
                }
            }catch (InputMismatchException e){
                System.out.println("\t\t Input is Invalid ");
            }

        }while(proceedCheck(3));

        mainProgram();
    }

    private static void addCustomerQ() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                         Add Customer                       |");
        System.out.println("\t--------------------------------------------------------------");

        do {
            Scanner input = new Scanner(System.in);
            System.out.print("\n\t Enter First Name  : ");
            String F_name = input.next();
            System.out.print("\t Enter Second Name : ");
            String S_name = input.next();
            System.out.print("\t Enter Vehicle No. : ");
            String V_no = input.next();
            int R_fuel;
            try {
                System.out.print("\t Enter Required Fuel : ");
                R_fuel = input.nextInt();
                Passenger Customer = new Passenger(F_name,S_name,V_no,R_fuel);
                addCustomer(Customer);
            }catch (InputMismatchException e){
                System.out.println("\t\t Input is invalid");
            }
        }while (proceedCheck(2));
        mainProgram();
    }

    private static void addCustomer(Passenger Customer) {
        FuelQueue[] arr = {Q1,Q2,Q3,Q4,Q5};

        int min = arr[0].getNextIndex();
        for(int i = 1;i< arr.length;i++){
            if(arr[i].getNextIndex()<min){
                min = arr[i].getNextIndex();
            }
        }

        for (FuelQueue fuelQueue : arr) {
            if (fuelQueue.getNextIndex() == min) {
                if(fuelQueue.isFQFull()){
                    WaitingList.enqueue(Customer);
                }else{
                    fuelQueue.addCustomerFQ(Customer);
                }
                break;
            }
        }
    }

    private static boolean proceedCheck(int i) {
        switch (i){
            case 1 -> System.out.print("\n\t Back to menu (y/any)? ");

            case 2 -> System.out.print("\n\t Would you like to add another customer (y/any)? ");

            case 3 -> System.out.print("\n\t Would you like to remove another customer (y/any)? ");

            case 4 -> System.out.print("\n\t Would you like to remove another served customer (y/any)? ");

            case 5 -> System.out.print("\n\t Would you like to find income for another pump (y/any)? ");

        }
        Scanner input = new Scanner(System.in);
        String choice = input.next();
        choice = choice.toLowerCase();

        return choice.equals("y");


    }

    private static void viewEmptyQ() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                    View All Empty Queues                   |");
        System.out.println("\t--------------------------------------------------------------");

        Q1.printEmptyQ();
        Q2.printEmptyQ();
        Q3.printEmptyQ();
        Q4.printEmptyQ();
        Q5.printEmptyQ();

        if(proceedCheck(1)){
            mainProgram();
        }else{
            System.out.println("Have a nice Day!!");
        }
    }

    private static void viewFuelQ() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                     View All Fuel Queues                   |");
        System.out.println("\t--------------------------------------------------------------");

        Q1.printFQ();
        Q2.printFQ();
        Q3.printFQ();
        Q4.printFQ();
        Q5.printFQ();
        WaitingList.print();

        if(proceedCheck(1)){
            mainProgram();
        }else{
            System.out.println("Have a nice Day!!");
        }
    }

    public static FuelQueue[] getData(){
        FuelQueue[] arr = {Q1,Q2,Q3,Q4,Q5};
        return arr;
    }

    public static CircularQueue getWData(){
        return WaitingList.getCQData();
    }
}