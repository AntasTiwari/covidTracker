package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.GetChars;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;

public class MainActivity extends AppCompatActivity {

    TextView tvcases,tvcases1,tvcases2,tvcases3,tvcases4,tvcases5,tvcases6,tvcases7;
    ScrollView scrollview;
    SimpleArcLoader simpleArcLoader;
    PieChart piechart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvcases=findViewById(R.id.tvcases);
        tvcases1=findViewById(R.id.tvcases1);
        tvcases2=findViewById(R.id.tvcases2);
        tvcases3=findViewById(R.id.tvcases3);
        tvcases4=findViewById(R.id.tvcases4);
        tvcases5=findViewById(R.id.tvcases5);
        tvcases6=findViewById(R.id.tvcases6);
        tvcases7=findViewById(R.id.tvcases7);

        scrollview =findViewById(R.id.scrollstats);
        piechart=findViewById(R.id.piechart);
        simpleArcLoader=findViewById(R.id.loader);

        fetchData();

    }

    private void fetchData() {
        String url="https://corona.lmao.ninja/v2/all/";
        simpleArcLoader.start();

        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //json format data will come here

                        try {
                            JSONObject jsonobject=new JSONObject(response.toString());
                            //setting the value in the textbox using json object which will use getstring method.
                            tvcases.setText(jsonobject.getString("active"));
                            tvcases1.setText(jsonobject.getString("recovered"));
                            tvcases2.setText(jsonobject.getString("deaths"));
                            tvcases3.setText(jsonobject.getString("critical"));
                            tvcases4.setText(jsonobject.getString("cases"));
                            tvcases5.setText(jsonobject.getString("deathsPerOneMillion"));
                            tvcases6.setText(jsonobject.getString("affectedCountries"));
                            tvcases7.setText(jsonobject.getString("todayRecovered"));
                            //adding the data in the piechart
                            piechart.addPieSlice(new PieModel("cases",Integer.parseInt(tvcases4.getText().toString()), Color.parseColor("#FFC107")));
                            piechart.addPieSlice(new PieModel("recovered",Integer.parseInt(tvcases1.getText().toString()), Color.parseColor("#FFEB3B")));
                            piechart.addPieSlice(new PieModel("deaths",Integer.parseInt(tvcases2.getText().toString()), Color.parseColor("#E91E8E")));
                            piechart.addPieSlice(new PieModel("active",Integer.parseInt(tvcases.getText().toString()), Color.parseColor("#2196F3")));
                            piechart.startAnimation();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollview.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollview.setVisibility(View.VISIBLE);
                            scrollview.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.setVisibility(View.GONE);
                scrollview.setVisibility(View.VISIBLE);
                scrollview.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                Log.d("My.tag", "goTrackCountries: ");
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this); // this queue will handle the request
        requestQueue.add(request);//add request in the queues.
    }

    public void goTrackCountries(View view) {
        startActivity(new Intent(getApplicationContext(),affectedcountries.class));
        Log.d("My.tag", "goTrackCountries:send ");
    }
}