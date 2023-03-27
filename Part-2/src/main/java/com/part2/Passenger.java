package com.part2;

//For passenger details
public class Passenger {
    private final String F_name;
    private final String S_name;
    private final String V_no;
    private final int R_fuel;

    public Passenger(String F_name, String S_name, String V_no, int R_fuel) {
        this.F_name = F_name;
        this.S_name = S_name;
        this.V_no = V_no;
        this.R_fuel = R_fuel;
    }

    public String printPassenger() {
        return "\n\t\t\t First name  : " + this.F_name +
                "\n\t\t\t Second name : " + this.S_name +
                "\n\t\t\t Vehicle no  : " + this.V_no +
                "\n\t\t\t Req Fuel    : " + this.R_fuel + "L";
    }

    public String getF_name() {
        return F_name;
    }

    public int getR_fuel() {
        return R_fuel;
    }

    public String getS_name() {
        return S_name;
    }

    public String getV_no() {
        return V_no;
    }
}
