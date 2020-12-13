package com.example.bike_servicing;

import android.app.Application;

import java.util.ArrayList;

public class Application_class extends Application {

    public static ArrayList<vehicle> vehicles;
    public static ArrayList<orders> orders_list;

    @Override
    public void onCreate() {
        super.onCreate();

        vehicles =new ArrayList<vehicle>();
        orders_list = new ArrayList<orders>();

        vehicles.add(new vehicle("Honda Unicorn","MH28 ML 1829","bike"));
        vehicles.add(new vehicle("Activa 3g","RJ14 BM 4714","scooter"));

        orders_list.add(new orders("64326432","Bajaj pulsar","26 November 2020","Rs 1200","bike"));
        orders_list.add(new orders("34543534","Hero Maestro","11 December 20202","Rs 600","scooter"));
    }
}
