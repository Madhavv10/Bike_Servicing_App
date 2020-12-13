package com.example.bike_servicing;

public class orders {

    String order_num,model_name,service_date,amount_paid,vehicle_type;

    public orders(String order_num,String model_name,String service_date,String amount_paid,String vehicle_type){
        this.order_num = order_num;
        this.model_name = model_name;
        this.service_date = service_date;
        this.amount_paid = amount_paid;
        this.vehicle_type = vehicle_type;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getService_date() {
        return service_date;
    }

    public void setService_date(String service_date) {
        this.service_date = service_date;
    }

    public String getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(String amount_paid) {
        this.amount_paid = amount_paid;
    }
}
