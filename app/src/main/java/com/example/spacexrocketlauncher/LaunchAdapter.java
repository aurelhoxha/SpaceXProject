package com.example.spacexrocketlauncher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.spacexrocketlauncher.Models.Launch;

import java.util.List;

public class LaunchAdapter extends RecyclerView.Adapter<LaunchAdapter.MyViewHolder> {

    //Variables
    private List<Launch> launches;
    private Context context;

    RequestOptions options;
    //Constructor
    public LaunchAdapter(List<Launch> launches, Context context) {
        this.launches = launches;
        this.context = context;

        //Glide Options
        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public LaunchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.launch_item,viewGroup,false);
        return new LaunchAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaunchAdapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.missionName.setText(launches.get(i).getMission_name());
        myViewHolder.launchDate.setText(launches.get(i).getLaunch_date_local());
        myViewHolder.successful.setText(launches.get(i).getLaunch_success());


        //Load the Image with Glide
        Glide.with(context).load(launches.get(i).getLinks().getMission_patch_small()).apply(options).into(myViewHolder.missionImage);

    }

    public List<Launch> getLaunches() {
        return launches;
    }

    public void setLaunches(List<Launch> launches) {
        this.launches = launches;
    }

    @Override
    public int getItemCount() {
        return launches.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView missionName;
        TextView launchDate;
        TextView successful;
        ImageView missionImage;

        public MyViewHolder(View itemView){
            super(itemView);
            missionName = itemView.findViewById(R.id.mision_name_value);
            launchDate = itemView.findViewById(R.id.launch_name_value);
            successful = itemView.findViewById(R.id.successful_name_value);
            missionImage = itemView.findViewById(R.id.launch_icon);

        }
    }
}
