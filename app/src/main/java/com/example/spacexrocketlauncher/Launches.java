package com.example.spacexrocketlauncher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacexrocketlauncher.Api.ApiInterface;
import com.example.spacexrocketlauncher.Models.Launch;
import com.example.spacexrocketlauncher.Models.Rocket;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.spacexrocketlauncher.Adapter.ROCKET_DESC;
import static com.example.spacexrocketlauncher.Adapter.ROCKET_NAME;
import static com.example.spacexrocketlauncher.Api.ApiClient.BASE_URL;

public class Launches extends AppCompatActivity {

    //UI Components
    private TextView rocketName;
    private TextView rocketDesc;
    private ProgressDialog myProgressDialog;
    private GraphView myGrapView;
    private RecyclerView recyclerView;

    //Variables
    private List<Launch> launches = new ArrayList<>();
    private PointsGraphSeries<DataPoint> series;
    private LaunchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launches);

        //Connect The Components With UI Element
        rocketName = findViewById(R.id.rocket_name_textview);
        rocketDesc = findViewById(R.id.rocket_desc__value);
        myGrapView = findViewById(R.id.launches_graph);
        recyclerView = findViewById(R.id.launch_list_recycle);
        myProgressDialog = new ProgressDialog(Launches.this);

        //Get The Required Info Of the Rocket
        Intent intent = getIntent();
        String rocketNameText = intent.getStringExtra(ROCKET_NAME);
        String rocketDescText = intent.getStringExtra(ROCKET_DESC);

        //Set the Rocket Name and Description
        rocketName.setText(rocketNameText);
        rocketDesc.setText(rocketDescText);

        recyclerView.setLayoutManager(new LinearLayoutManager(Launches.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        loadLaunches(rocketNameText);
    }

    //Fetch all the Launches
    public void loadLaunches(String rocketNameText){

        //Setting the Progress Dialog
        myProgressDialog.setMessage("Loading Launches.. Please wait...");
        myProgressDialog.setIndeterminate(false);
        myProgressDialog.setCancelable(false);
        myProgressDialog.show();

        //Loading the Launches
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface request = retrofit.create(ApiInterface.class);
        Call<List<Launch>> call = request.getLaunches(rocketNameText);
        call.enqueue(new Callback<List<Launch>>() {
            @Override
            public void onResponse(Call<List<Launch>> call, Response<List<Launch>> response) {
                myProgressDialog.dismiss();
                launches = new ArrayList<>(response.body());
                adapter = new LaunchAdapter(launches,Launches.this);
                recyclerView.setAdapter(adapter);

                //Group All Launches By Year
                Map<String, List<Launch>> launchByYear = new HashMap<>();
                for (Launch l : launches) {
                    if (!launchByYear.containsKey(l.getLaunch_year())) {
                        launchByYear.put(l.getLaunch_year(), new ArrayList<Launch>());
                    }
                    launchByYear.get(l.getLaunch_year()).add(l);
                }

                String result = "";
                launchByYear = new TreeMap<>(launchByYear);

                DataPoint[] myData = new DataPoint[launchByYear.size()];
                int location = 0;
                for (Map.Entry<String, List<Launch>> entry : launchByYear.entrySet()) {
                    myData[location] = new DataPoint(Integer.parseInt(entry.getKey()),entry.getValue().size());
                    location++;
                    result = result + "Year: " + entry.getKey() + "-> Rocket Names: ";
                    for (int i = 0; i < entry.getValue().size(); i++){
                        result = result + entry.getValue().get(i).getMission_name() + ",";
                    }
                }
                series = new PointsGraphSeries<DataPoint>(myData);
                series.setColor(Color.parseColor("#ea474a"));
                Log.d("FULL RESULT", result);
                myGrapView.addSeries(series);
            }

            @Override
            public void onFailure(Call<List<Launch>> call, Throwable t) {
                myProgressDialog.dismiss();
                Toast.makeText(Launches.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
