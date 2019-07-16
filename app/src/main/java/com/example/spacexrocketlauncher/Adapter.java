package com.example.spacexrocketlauncher;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spacexrocketlauncher.Models.Rocket;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    //CONSTANTS
    public static final String ROCKET_NAME = "RocketName";
    public static final String ROCKET_DESC = "RocketDesc";

    //Variables
    private List <Rocket> rockets;
    private Context context;

    //Constructor
    public Adapter(List<Rocket> rockets, Context context) {
        this.rockets = rockets;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rocket_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {

        viewHolder.rocketName.setText(rockets.get(i).getRocketName());
        viewHolder.rocketCountry.setText(rockets.get(i).getCountry());
        viewHolder.rocketEngineNo.setText(rockets.get(i).getEngine().getNumber());
        viewHolder.rocketStatus.setText(rockets.get(i).getActive());
        viewHolder.rocketDesc.setText(rockets.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return rockets.size();
    }


    public void setRockets(List<Rocket> rockets) {
        this.rockets = rockets;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rocketName, rocketCountry, rocketEngineNo, rocketStatus, rocketDesc;

        public MyViewHolder(View itemView){
            super(itemView);
            rocketName = itemView.findViewById(R.id.rocket_name_textview);
            rocketDesc = itemView.findViewById(R.id.rocket_desc__value);
            rocketCountry = itemView.findViewById(R.id.rocket_country_value);
            rocketEngineNo = itemView.findViewById(R.id.rocket_engines__value);
            rocketStatus = itemView.findViewById(R.id.rocket_status_value);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,Launches.class);
                    intent.putExtra(ROCKET_NAME,rocketName.getText());
                    intent.putExtra(ROCKET_DESC,rocketDesc.getText());
                    context.startActivity(intent);
                }
            });
        }
    }

}
