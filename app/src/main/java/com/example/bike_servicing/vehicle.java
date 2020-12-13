package com.example.bike_servicing;

public class vehicle {
    String model_name, Reg_no, vehicle_type;

    public vehicle(String model_name, String Reg_no, String vehicle_type) {
        this.model_name = model_name;
        this.Reg_no = Reg_no;
        this.vehicle_type = vehicle_type;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getReg_no() {
        return Reg_no;
    }

    public void setReg_no(String reg_no) {
        this.Reg_no = reg_no;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }
}