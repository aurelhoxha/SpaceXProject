package com.example.spacexrocketlauncher;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.spacexrocketlauncher.Api.ApiInterface;
import com.example.spacexrocketlauncher.Models.Rocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.spacexrocketlauncher.Api.ApiClient.BASE_URL;


public class MainActivity extends AppCompatActivity{

    //UI Components
    private CheckBox myCheckBox;
    private ProgressDialog myProgressDialog;
    private Button myRefreshButton;
    private RecyclerView recyclerView;

    //Variables
    private List<Rocket> rockets = new ArrayList<>();
    private Adapter adapter;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Main Activity Components
        recyclerView = findViewById(R.id.rocket_list);
        myRefreshButton = findViewById(R.id.refreshButton);
        myProgressDialog = new ProgressDialog(MainActivity.this);
        myCheckBox = findViewById(R.id.checkbox);
        myCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked();
            }
        });
        myRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRockets();
                myCheckBox.setChecked(false);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        loadRockets();

    }

    //Load JSON Data
    public void loadRockets() {

        //Setting the Progress Dialog
        myProgressDialog.setMessage("Loading Rockets.. Please wait...");
        myProgressDialog.setIndeterminate(false);
        myProgressDialog.setCancelable(false);
        myProgressDialog.show();

        //Loading the Rockets
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface request = retrofit.create(ApiInterface.class);
        Call<List<Rocket>> call = request.getRockets();
        call.enqueue(new Callback<List<Rocket>>() {
            @Override
            public void onResponse(Call<List<Rocket>> call, Response<List<Rocket>> response) {
                myProgressDialog.dismiss();
                rockets = new ArrayList<>(response.body());
                adapter = new Adapter(rockets,MainActivity.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Rocket>> call, Throwable t) {
                myProgressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Filter the Data when the Checkbox is checked
    public void onCheckboxClicked() {
        // Is the view now checked?
        boolean checked = myCheckBox.isChecked();

        if(checked){
            //Copy data of the rockets
            List<Rocket> activeRockets = new ArrayList<>(rockets);

            //Create the iterator and delete the non active rockets
            Iterator<Rocket> iterator = activeRockets.iterator();
            while (iterator.hasNext()) {
                Rocket temp = iterator.next();
                if (temp.getActive().equals("false"))
                    iterator.remove();
            }

            //Set the New Adapter and RecyclerView
            adapter.setRockets(activeRockets);
            recyclerView.setAdapter(adapter);
        }
        else{
            adapter.setRockets(rockets);
            recyclerView.setAdapter(adapter);
        }
    }
}
