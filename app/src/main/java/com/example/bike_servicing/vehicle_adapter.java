package com.example.bike_servicing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class vehicle_adapter extends RecyclerView.Adapter<vehicle_adapter.ViewHolder> {

    private ArrayList<vehicle> vehicles;
    onitemclick activity;

    public vehicle_adapter(){
    }

    public vehicle_adapter(ArrayList list){
        vehicles = list;
    }

    public interface onitemclick{
        void onitemclick(int index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView vehicle_logo;
        TextView model_name, reg_no;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            vehicle_logo = itemView.findViewById(R.id.iv_orderlogo);
            reg_no = itemView.findViewById(R.id.tv_regno);
            model_name = itemView.findViewById(R.id.tv_modelname);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }


    @NonNull
    @Override
    public vehicle_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vehicle_adapter.ViewHolder holder, int position) {

        holder.itemView.setTag(vehicles.get(position));
        holder.model_name.setText(vehicles.get(position).getModel_name());
        holder.reg_no.setText(vehicles.get(position).getReg_no());


        if(vehicles.get(position).getVehicle_type().toLowerCase().equals("bike")){
            holder.vehicle_logo.setImageResource(R.drawable.bike_logo);
        }else if(vehicles.get(position).getVehicle_type().toLowerCase().equals("scooter")){
            holder.vehicle_logo.setImageResource(R.drawable.scooter_logo);
        }else{
            holder.vehicle_logo.setImageResource(R.drawable.bike_logo);
        }
    }


    @Override
    public int getItemCount() {
        return vehicles.size();
    }
}