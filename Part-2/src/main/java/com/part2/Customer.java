package com.part2;

public class Customer {

    private String pump;
    private int pos;
    private String fname;
    private String sname;
    private String vno;
    private int rfuel;

    public Customer(String pump, int pos, String fname, String sname, String vno, int rfuel) {
        this.pump = pump;
        this.pos = pos;
        this.fname = fname;
        this.sname = sname;
        this.vno = vno;
        this.rfuel = rfuel;
    }

    public String getPump() {
        return pump;
    }

    public void setPump(String pump) {
        this.pump = pump;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getVno() {
        return vno;
    }

    public void setVno(String vno) {
        this.vno = vno;
    }

    public int getRfuel() {
        return rfuel;
    }

    public void setRfuel(int rfuel) {
        this.rfuel = rfuel;
    }
}
