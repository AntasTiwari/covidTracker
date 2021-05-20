package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class detailActivity extends AppCompatActivity {
    private int positioncountry;
    TextView tvcountry,tvcases,tvtodaycases,tvdeaths,tvtodaydeaths,tvrecovered,tvcritical,tvactive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent=getIntent();
        positioncountry=intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of " + affectedcountries.countrymodelList.get(positioncountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvcountry=findViewById(R.id.cases7);
        tvcases=findViewById(R.id.cases);
        tvtodaycases=findViewById(R.id.cases1);
        tvdeaths=findViewById(R.id.cases2);
        tvtodaydeaths=findViewById(R.id.cases3);
        tvrecovered=findViewById(R.id.cases4);
        tvcritical=findViewById(R.id.cases5);
        tvactive=findViewById(R.id.cases6);
         //setting the data on the textview
        tvcountry.setText(affectedcountries.countrymodelList.get(positioncountry).getCountry());
        tvcases.setText(affectedcountries.countrymodelList.get(positioncountry).getCases());
        tvtodaycases.setText(affectedcountries.countrymodelList.get(positioncountry).getTodaycases());
        tvdeaths.setText(affectedcountries.countrymodelList.get(positioncountry).getDeaths());
        tvtodaydeaths.setText(affectedcountries.countrymodelList.get(positioncountry).getTodaydeaths());
        tvrecovered.setText(affectedcountries.countrymodelList.get(positioncountry).getRecovered());
        tvcritical.setText(affectedcountries.countrymodelList.get(positioncountry).getCritical());
        tvactive.setText(affectedcountries.countrymodelList.get(positioncountry).getActive());



    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}