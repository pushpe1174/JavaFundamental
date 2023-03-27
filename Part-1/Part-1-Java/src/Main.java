import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static int remainingFuel = 6600;
    public static String[] Q1 = new String[6];
    public static String[] Q2 = new String[6];
    public static String[] Q3 = new String[6];

    public static int Q1el,Q2el,Q3el;

    public static void main(String[] args) {
        mainProgram();
    }

    private static void mainProgram() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                         Fuel Center                        |");
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t Remaining Fuel Amount : " + remainingFuel + "L\n");
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
        System.out.println("\t 999 or EXT: Exit the Program");

        boolean flag = false;
        do{
            Scanner input = new Scanner(System.in);
            System.out.print("\n\t Enter your option : ");
            String choice = input.next();
            choice = choice.toUpperCase();

            switch (choice) {
                case "999", "EXT" -> System.exit(0);
                case "100", "VFQ" -> viewFuelQ();
                case "101", "VEQ" -> viewEmptyQ();
                case "102", "ACQ" -> addCustomerQ();
                case "103", "RCQ" -> removeCustomerQ(); 
                case "105", "VCS" -> sortCustomer();
                case "104", "PCQ" -> removeSCustomerQ();
                case "106", "SPD" -> storeFile();
                case "107", "LPD" -> loadFile();
                case "108", "STK" -> viewRFuelS();
                case "109", "AFS" -> addFuelS();
                default -> flag = true;
            }
        }while(flag);
    }

    private static void sortCustomer() {
        sortPump(Q1,1);
        sortPump(Q2,2);
        sortPump(Q3,3);
        if(!(flagCheck())){
            System.out.println("\t\t Have a nice day");
        }else{
            mainProgram();
        }
    }

    private static void sortPump(String[] Q,int num) {
        String[] ar = Q.clone();
        System.out.println("\t\t Fuel Pump " + num  + " Queue :");
        if(Q[0] == null){
            System.out.println("\t\t\t Empty Que\n");
        }else{
            for (int j = 0; j < ar.length; j++) {
                if(ar[j] == null)continue;

                for (int i = j + 1; i < ar.length; i++) {
                    if(ar[i] == null)continue;

                    if (ar[i].compareTo(ar[j]) < 0) {
                        String temp = ar[j];
                        ar[j] = ar[i];
                        ar[i] = temp;
                    }
                }
                System.out.println("\t\t\t " + ar[j]);
            }

        }
    }

    private static void loadFile(){
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                          Load file                         |");
        System.out.println("\t--------------------------------------------------------------");

        try {
            File inputFile = new File("Fuel_CW2022.txt");
            Scanner read = new Scanner(inputFile);
            int lineCount =-1;
            while (read.hasNext()){
                String line = read.nextLine();
                String[] l = line.split(" ");
                try {
                    if(lineCount == -1){
                        try{
                            remainingFuel =  Integer.parseInt(l[5]);
                        }catch (Exception e){
                            System.out.println("Invalid format remaining fuel");
                        }
                    }
                    if(l[1].equals("Pos") & Integer.parseInt(l[2])<7){
                        if(-1<lineCount & lineCount<7) {
                            System.out.println(Arrays.toString(l));
                            try{
                                int pos = Integer.parseInt(l[2]);
                                Q1[pos-1] = l[4];
                            }catch (Exception e){
                                System.out.println("Invalid format pump 1");
                            }
                        }
                        if(7<lineCount & lineCount<15){
                            System.out.println(Arrays.toString(l));
                            try{
                                int pos = Integer.parseInt(l[2]);
                                Q2[pos-1] = l[4];

                            }catch (Exception e){
                                System.out.println("Invalid format pump 2");

                            }
                        }
                        if(15<lineCount & lineCount<23){
                            System.out.println(Arrays.toString(l));
                            try{
                                int pos = Integer.parseInt(l[2]);
                                Q3[pos-1] = l[4];

                            }catch (Exception e){
                                System.out.println("Invalid format pump 3");

                            }
                        }
                    }
                }catch (Exception e){
                    continue;
                }finally {
                    lineCount++;
                }
            }
            read.close();
        }catch (IOException e){
            System.out.println("Error IOException is: " + e);
        }
        if(!(flagCheck())){
            System.out.println("\t\t Have a nice day");
        }else{
            mainProgram();
        }
    }

    private static void storeFile() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                        Store in file                       |");
        System.out.println("\t--------------------------------------------------------------");
        try {
            FileWriter fileWrite = new FileWriter("Fuel_CW2022.txt");
            System.out.println("\n\t\t File created!!!");
            fileWrite.write( "\t Remaining Fuel stock is " + remainingFuel + " L\n");
            fileWrite.write("\t Fuel Pump 1 Queue : \n");
            for (int i=0;i<6;i++){
                if (Q1[i] == null){
                    fileWrite.write("\t\t Pos " + (i+1) + " : No-one\n");
                }else{
                    fileWrite.write("\t\t Pos " + (i+1) + " : " + Q1[i] +"\n");
                }

            }
            fileWrite.write("\n\t Fuel Pump 2 Queue : \n");
            for (int i=0;i<6;i++){
                if (Q2[i] == null){
                    fileWrite.write("\t\t Pos " + (i+1) + " : No-one\n");
                }else{
                    fileWrite.write("\t\t Pos " + (i+1) + " : " + Q2[i] +"\n");
                }

            }

            fileWrite.write("\n\t Fuel Pump 3 Queue : \n");
            for (int i=0;i<6;i++){
                if (Q3[i] == null){
                    fileWrite.write("\t\t Pos " + (i+1) + " : No-one\n");
                }else{
                    fileWrite.write("\t\t Pos " + (i+1) + " : " + Q3[i] +"\n");
                }

            }
            fileWrite.close();
            System.out.println("\t\tSuccessfully written.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if(!(flagCheck())){
            System.out.println("\t\t Have a nice day");
        }else{
            mainProgram();
        }
    }

    private static void addFuelS() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                           Add Fuel                         |");
        System.out.println("\t--------------------------------------------------------------");
        do{
            Scanner input = new Scanner(System.in);
            System.out.println("\t\t Remaining Fuel  : " + remainingFuel +"L");
            System.out.print("\t\t Add Fuel amount : ");
            try{
                int addFuel = input.nextInt();
                remainingFuel += addFuel;
            }catch (InputMismatchException e){
                System.out.println("\t\t Input is invalid.");
            }
            System.out.println("\t\t New Fuel Amount : " + remainingFuel +"L");
        }while(!(flagCheck()));
        mainProgram();

    }

    private static void viewRFuelS() {
        System.out.println("\n\t Remaining Fuel stock is " + remainingFuel + "L");
    }

    private static void removeSCustomerQ() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                    Remove Serve Customer                   |");
        System.out.println("\t--------------------------------------------------------------");
        do{
            Scanner input = new Scanner(System.in);
            System.out.print("\t\t Select a Fuel Pump : ");
            int pump = input.nextInt();

            if(0<pump & pump<4){
                switch (pump){
                    case 1 -> System.out.println(removeServedCustomerQSelect(Q1));
                    case 2 -> System.out.println(removeServedCustomerQSelect(Q2));
                    case 3 -> System.out.println(removeServedCustomerQSelect(Q3));
                }
            }else{
                System.out.println("\t\t Valid pumps are 1,2,3");
            }


        }while(!(flagCheck()));
        mainProgram();
    }

    private static String removeServedCustomerQSelect(String[] Q) {
        if(Q[0] == null){
            return "\t\t Que is Empty!!";
        }
        int i;
        for( i = 0;i<5;i++){
            if(Q[i] == null) break;
            Q[i] = Q[i+1];
        }
        Q[i] = null;
        remainingFuel -= 10;
        return "\t\t Successfully served fuel";
    }

    private static void removeCustomerQ() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                       Remove Customer                      |");
        System.out.println("\t--------------------------------------------------------------");

        do{
            int pump;
            Scanner input = new Scanner(System.in);
            try {
                System.out.print("\t\t Select a Fuel Pump : ");
                pump = input.nextInt();
            }catch (InputMismatchException e){
                System.out.println("\t\t Valid pumps are 1,2,3");
                continue;
            }
            int pos;
            try {
                System.out.print("\t\t Enter the position : ");
                pos = input.nextInt();
            }catch (InputMismatchException e){
                System.out.println("\t\t Valid positions are 1 to 6");
                continue;
            }
            if(0<pump & pump<4 & 0<pos & pos<7){
                switch (pump){
                    case 1 -> System.out.println(removeCustomerQSelect(Q1,pos));
                    case 2 -> System.out.println(removeCustomerQSelect(Q2,pos));
                    case 3 -> System.out.println(removeCustomerQSelect(Q3,pos));
                }
            }else{
                System.out.print("\t\t Valid pumps are 1,2,3 1");
                System.out.println("and Valid positions are 1 to 6");
            }

        }while(!(flagCheck()));
        mainProgram();
    }

    private static String removeCustomerQSelect(String[]Q , int pos) {
        if(0<pos && pos<7){
            if(Q[pos] == null){
                return "\t\t No one to remove";
            }
            int i;
            for(i = pos - 1; i<Q.length -1 ; i++){
                if(Q[i + 1] == null) break;
                Q[i] = Q[i +1];
            }
            Q[i] = null;
            return "\t\t Successfully removed";
        }
        return "\t\t Invalid position";
    }
    private static void addCustomerQ(){
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                         Add Customer                       |");
        System.out.println("\t--------------------------------------------------------------");

        do{
            Scanner input = new Scanner(System.in);
            int pump;
            try {
                System.out.print("\t\t Select a Fuel Pump : ");
                 pump = input.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("\t\t Valid pumps are 1,2,3");
                continue;
            }
            System.out.print("\t\t Enter the Name     : ");
            String name = input.next();
            name = name.toLowerCase();

            switch (pump){
                case 1 -> addCustomerQSelect(Q1,name,Q1el);
                case 2 -> addCustomerQSelect(Q2,name,Q2el);
                case 3 -> addCustomerQSelect(Q3,name,Q3el);
                default -> System.out.println("Valid pumps are 1,2,3");
            }

        }while (!(flagCheck()));
        mainProgram();
    }

    private static boolean flagCheck() {
        Scanner input = new Scanner(System.in);
        System.out.print("\t\tWould you like to continue (y/n) ? ");
        String option = input.next();
        option = option.toLowerCase();

        return option.equals("n");
    }

    private static void addCustomerQSelect(String[] Q , String name,int el) {
        if(el<6 && Q[el] == null){
            Q[el] = name;
            System.out.println("\t\tSuccessfully added position " + (el + 1) + "\n");
            if(Q == Q1){
                Q1el++;
            } else if (Q == Q2) {
                Q2el ++;
            }else{
                Q3el ++;
            }
        }
        if(5 < el){
            System.out.println("\t\tThis que is full!!!");
        }

    }

    private static void viewEmptyQ() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                    View All Empty Queues                   |");
        System.out.println("\t--------------------------------------------------------------");

        System.out.println(viewEmptyQChk(Q1,1));
        System.out.println(viewEmptyQChk(Q2,2));
        System.out.println(viewEmptyQChk(Q3,3));
        if(flagCheck()){
            mainProgram();
        }else{
            System.out.println("\t\t Have a nice day");
        }

    }

    private static String viewEmptyQChk(String[] Q, int j) {
        for (int i =0; i<6;i++){
            if(Q[i] == null){
                return "\t\t Fuel Pump " + j + " : Que is Empty \n";
            }
        }
        return "";
    }

    private static void viewFuelQ() {
        System.out.println("\t--------------------------------------------------------------");
        System.out.println("\t|                     View All Fuel Queues                   |");
        System.out.println("\t--------------------------------------------------------------");

        viewFuelQPos(Q1,1);
        viewFuelQPos(Q2,2);
        viewFuelQPos(Q3,3);
        if(flagCheck()){
            mainProgram();
        }else{
            System.out.println("\t\t Have a nice day");
        }

    }

    private static void viewFuelQPos(String[] Q ,int j) {
        int i = 1;
        System.out.println("\n\t Fuel Pump " + j +" Queue :");
        for (String person:Q) {
            if(person == null) {
                System.out.println("\t\tPos " + i + " : No one");
            }else {
                System.out.println("\t\tPos " + i + " : " + person);
            }
            i++;
        }
    }
}