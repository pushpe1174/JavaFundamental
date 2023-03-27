package com.part2;

// For Waiting Que
public class CircularQueue {
    Passenger[] customers = new Passenger[6];
    private int front = -1;
    private int rear = -1;

    private final int n = customers.length;

    public void enqueue(Passenger customer) {
        if (front == -1 & rear == -1) {
            front = rear = 0;
            customers[rear] = customer;
            System.out.println("\t\t SuccessFully added to the waiting list");
        } else if (((rear + 1) % n) == front) {
            System.out.println("\t\t Waiting list is Full");
        } else {
            rear = (rear + 1) % n;
            customers[rear] = customer;
            System.out.println("\t\t SuccessFully added to the waiting list");
        }
    }

    public boolean isCQEmpty() {
        return !(front == -1 & rear == -1);
    }

    public void dequeue() {
        if (isCQEmpty()) {
            if (front == rear) {
                customers[front] = null;
                front = rear = -1;
            } else {
                customers[front] = null;
                front = (front + 1) % n;
            }
        }
    }

    public void print() {
        if (front == -1 & rear == -1) {
            System.out.println("\n\t Waiting List -> \n\t\t\tEmpty Queue");
        } else {
            System.out.println("\n\t Waiting List ->");
            int i = front;
            while (i != rear) {
                System.out.println("\n\t\t -> " + customers[i].printPassenger());
                i = (i + 1) % n;
            }
            System.out.println("\n\t\t -> " + customers[rear].printPassenger());
        }
    }

    public String storeCQ() {
        StringBuilder data = new StringBuilder("\nWaiting List -> ");
        if (front == -1 & rear == -1) return data.append("\n\t\t\tEmpty Queue").toString();
        int i = front;
        while (i != rear) {
            data.append("\n\t\t -> ").append(customers[i].printPassenger());
            i = (i + 1) % n;
        }
        data.append("\n\t\t -> ").append(customers[rear].printPassenger());
        return data.toString();
    }

    public String storeforLoadCQ() {
        StringBuilder data = new StringBuilder();
        if (!(front == -1 & rear == -1)) {
            int i = front;
            while (i != rear) {
                data.append("\nW").append("#");
                data.append(customers[i].getF_name()).append("#");
                data.append(customers[i].getS_name()).append("#");
                data.append(customers[i].getV_no()).append("#");
                data.append(customers[i].getR_fuel());
                i = (i + 1) % n;
            }
            data.append("\nW").append("#");
            data.append(customers[rear].getF_name()).append("#");
            data.append(customers[rear].getS_name()).append("#");
            data.append(customers[rear].getV_no()).append("#");
            data.append(customers[rear].getR_fuel());
        }
        return data.toString();
    }

    public Passenger getFWaiting() {
        return customers[front];
    }

    public CircularQueue getCQData() {
        CircularQueue temp = new CircularQueue();
        if (!(front == -1 & rear == -1)) {
            int i = front;
            int j =0;
            while (i != rear) {
                temp.customers[j] = customers[i];
                j++;
                i = (i + 1) % n;
            }
            temp.customers[j] = customers[rear];
        }
        return temp;
    }
}
