package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class affectedcountries extends AppCompatActivity {

    EditText edtSearch;
    ListView listview;
    SimpleArcLoader simpleArcLoader;
    //stroing the data coming from the api

    public static List<countrymodel> countrymodelList=new ArrayList<>();
    countrymodel countrymodel;
    myCustomAdapter myCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("My.tag", "goTrackCountries:accept ");
        setContentView(R.layout.activity_affectedcountries);
        Log.d("My.tag", "goTrackCountries:accepted ");
        edtSearch=findViewById(R.id.edtSearch);
        listview=findViewById(R.id.listView);
        simpleArcLoader=findViewById(R.id.loader);
        getSupportActionBar().setTitle("Affected countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);  //back icon will be available
        fetchData();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(),detailActivity.class).putExtra("position",i)); //we are also taking positon of each country so that data can be extracted easily
            }
        });
        //text watcher will watch all the characters/sequence
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                myCustomAdapter.getFilter().filter(s);  //for flitering data, a list is also created for this in adapter class
                myCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    //method for back icon on which some operation will be performed
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {
        String url="https://corona.lmao.ninja/v2/countries/";
        simpleArcLoader.start();

        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //json format data will come here
                        try {
                            JSONArray jsonArray=new JSONArray(response); //for stroing multiples values
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String countryname= jsonObject.getString("country");
                                String cases= jsonObject.getString("cases");
                                String todaycases= jsonObject.getString("todayCases");
                                String deaths= jsonObject.getString("deaths");
                                String todaydeaths= jsonObject.getString("todayDeaths");
                                String recovered= jsonObject.getString("recovered");
                                String active= jsonObject.getString("active");
                                String critical= jsonObject.getString("critical");

                                //now creating one more object to store the country info which also includes flag
                                JSONObject object=jsonObject.getJSONObject("countryInfo");
                                String flagurl=object.getString("flag");// for getting flag
                                //sending data to countrymodel
                                countrymodel=new countrymodel(flagurl,countryname,cases,todaycases,deaths,todaydeaths,recovered,critical,active);
                                countrymodelList.add(countrymodel); //putting data into list.
                            }

                            myCustomAdapter =new myCustomAdapter(affectedcountries.this,countrymodelList); //intializing customadapter
                            listview.setAdapter(myCustomAdapter); //setting adapter on the listview it is very important thing
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                Toast.makeText(affectedcountries.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this); // this queue will handle the request
        requestQueue.add(request);//add request in the queues.
    }
}