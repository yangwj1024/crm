package com.ywj.crm.bean;

public class CustomerConstitute {
    private String name;
    private Float y;

    public  CustomerConstitute(){}
    public CustomerConstitute(String name, Float y) {
        this.name = name;
        this.y = y;
    }

    @Override
    public String toString() {
        return "CustomerConstitute{" +
                "name='" + name + '\'' +
                ", y=" + y +
                " }'";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

}
