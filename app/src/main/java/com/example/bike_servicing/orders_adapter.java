package com.example.bike_servicing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class orders_adapter extends RecyclerView.Adapter<orders_adapter.ViewHolder>{

    private ArrayList<orders> orders_list;
    vehicle_adapter.onitemclick activity;

    public orders_adapter(){}

    public orders_adapter(ArrayList list){
        orders_list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView orderlogo;
        TextView order_num, model_name, date,amount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderlogo = itemView.findViewById(R.id.iv_orderlogo);
            order_num = itemView.findViewById(R.id.tv_order_no);
            model_name = itemView.findViewById(R.id.tv_model_name);
            date = itemView.findViewById(R.id.tv_date);
            amount = itemView.findViewById(R.id.tv_amountpaid);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_cardview,parent,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setTag(orders_list.get(position));
        holder.model_name.setText(orders_list.get(position).getModel_name());
        holder.date.setText(orders_list.get(position).getService_date());
        holder.order_num.setText(orders_list.get(position).getOrder_num());
        holder.amount.setText(orders_list.get(position).getAmount_paid());



        if(orders_list.get(position).getVehicle_type().toLowerCase().equals("bike")){
            holder.orderlogo.setImageResource(R.drawable.bike_logo);
        }else if(orders_list.get(position).getVehicle_type().toLowerCase().equals("scooter")){
            holder.orderlogo.setImageResource(R.drawable.scooter_logo);
        }else{
            holder.orderlogo.setImageResource(R.drawable.bike_logo);
        }

    }

    @Override
    public int getItemCount() {
        return orders_list.size();
    }





}
