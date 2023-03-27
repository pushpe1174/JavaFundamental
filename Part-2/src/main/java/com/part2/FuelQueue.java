package com.part2;

//For Fuel Queues
public class FuelQueue {
    Passenger[] customers = new Passenger[6];
    private int nextIndex = -1;
    private final int pumpNum;

    private int SoldFuel;

    public FuelQueue(int pumpNum) {
        this.pumpNum = pumpNum;
    }

    public void printFQ() {
        System.out.println("\n\t Fuel Pump " + pumpNum + " :");

        for (int i = 0; i < customers.length; i++) {
            if (customers[i] == null) {
                System.out.println("\t\t " + (i + 1) + " -> \n\t\t\tNo Customer");
            } else {
                System.out.println("\t\t " + (i + 1) + " -> " + customers[i].printPassenger());
            }
        }
    }

    public void printEmptyQ() {
        if(customers[0] == null){
            System.out.print("\n\t Fuel Pump " + pumpNum + " -> ");
            System.out.println("Que is Empty");
        } else{
            printFQ();
        }
    }

    public int getNextIndex() {
        return this.nextIndex;
    }

    public boolean isFQFull() {
        return nextIndex == customers.length - 1;
    }

    public void addCustomerFQ(Passenger customer) {
        if (nextIndex == customers.length - 1) {
            System.out.println("\n\t Fuel Pump " + pumpNum + " :");
            System.out.println("\n\t\t pump is full ");// need to implement
        } else {
            nextIndex++;
            customers[nextIndex] = customer;
            System.out.println("\n\t\t Successfully added in Fuel Pump " + pumpNum);
        }
    }

    public void removeCustomerFQ(int posNum) {
        if (nextIndex == -1 | this.customers[posNum] == null) {
            System.out.println("\t\t No customer to remove");
        }else {
            int i;
            for (i = posNum; i < nextIndex; i++) {
                this.customers[i] = this.customers[i + 1];
            }
            this.customers[i] = null;
            nextIndex--;
            System.out.println("\n\t\t Successfully removed in Fuel Pump " + pumpNum);
        }

    }

    public void sortCustomerFQ() {
        System.out.println("\n\t Fuel Pump " + pumpNum + " :");
        if (nextIndex == -1) {
            System.out.println("\t\t No customer to Sort");
        } else {
            String[] arr = new String[6];
            for (int i = 0; i <= nextIndex; i++) {
                arr[i] = customers[i].getF_name();
            }
            l1:
            for (int j = 0; j <= nextIndex; j++) {
                for (int i = j + 1; i <= nextIndex; i++) {
                    if (arr[i].compareTo(arr[j]) < 0) {
                        String temp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = temp;
                    }
                }
                for (int i = 0; i <= nextIndex; i++) {
                    if (customers[i].getF_name().equals(arr[j])) {
                        System.out.println(customers[i].printPassenger());
                        continue l1;
                    }
                }
            }
        }
    }

    public int removeCustomerSFQ() {
        if (customers[0] == null) {
            System.out.println("\t\t No Customer to Serve Fuel!!");
            return 0;
        }
        int ServeFuel = customers[0].getR_fuel();
        SoldFuel += ServeFuel;
        removeCustomerFQ(0);
        return ServeFuel;
    }

    public void setNextIndex() {
        int index = -1;
        for (Passenger customer : customers) {
            if (customer == null) break;
            index++;
        }
        nextIndex = index;
    }

    public String storeFQ() {
        StringBuilder data = new StringBuilder("\nFuel Pump " + pumpNum + " -> ");
        int i = 0;
        for (Passenger customer : customers) {
            if (customer == null) {
                data.append("\n\t\t").append(i + 1).append(" -> \n\t\t\tNo Customer");
            } else {
                data.append("\n\t\t").append(i + 1).append(" -> ").append(customer.printPassenger());
            }
            i++;
        }
        return data.toString();
    }

    public String storeforLoadFQ() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] == null) {
                data.append("\n").append(pumpNum).append("#");
                data.append(i + 1).append("#");
                data.append("No");
            } else {
                data.append("\n").append(pumpNum).append("#");
                data.append(i + 1).append("#");
                data.append(customers[i].getF_name()).append("#");
                data.append(customers[i].getS_name()).append("#");
                data.append(customers[i].getV_no()).append("#");
                data.append(customers[i].getR_fuel());
            }
        }
        return data.toString();
    }

    public void tempAddCustomer(Passenger tempCus, int posNum) {
        customers[posNum] = tempCus;
    }

    public void incomeFQ() {
        System.out.println("\t\t Income is Rs." + (430 * SoldFuel));
    }

}
