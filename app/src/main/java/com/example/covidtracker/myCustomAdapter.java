package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class myCustomAdapter extends ArrayAdapter<countrymodel>{

    private Context context;
    private List<countrymodel> countrymodelList;
    private List<countrymodel> countrymodelListFiltered;

    public myCustomAdapter(@NonNull Context context, List<countrymodel> countrymodelList) {
        super(context, R.layout.listcustomitem,countrymodelList);
        this.context=context;
        this.countrymodelList=countrymodelList;
        this.countrymodelListFiltered=countrymodelList; //passing original data of list into filtered list
    }
    //data coming from listview will be set on listcustomitem
    @NonNull
    @Override
    //layout inflator takes xml files and convert into view objects which is presented on screen by the os
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listcustomitem,null,true);
        TextView tvcountryName=view.findViewById(R.id.countryName); //intialization
        ImageView imageView=view.findViewById(R.id.imageFlag);
        //sending data in countrymodellist and will set incoming name on the tvcountryname.
        tvcountryName.setText(countrymodelListFiltered.get(position).getCountry());
        Glide.with(context).load(countrymodelListFiltered.get(position).getFlag()).into(imageView); //glide library for setting image from the url

        return view;
    }
        //return size of the list
    @Override
    public int getCount() {
        return countrymodelListFiltered.size();

    }

    @Nullable
    @Override
    public countrymodel getItem(int position) {
        return countrymodelListFiltered.get(position);  //items will get from this list only
    }

    @Override
    public long getItemId(int position) {
        return position;  //get poisiton of ids
    }
    //It is the main function in which all working will be done.
    @NonNull
    @Override
    public Filter getFilter() {
        //object
        Filter filter=new Filter()
     {
         @Override
         protected FilterResults performFiltering(CharSequence constraints) {
             FilterResults filterResults=new FilterResults();
             if(constraints==null|| constraints.length()==0)
             {
                 filterResults.count=countrymodelList.size();
                 filterResults.values=countrymodelList; //data which will be matched  that value will be stored in filterresults value.
             }else
             {
                 List<countrymodel> resultsmodel=new ArrayList<>();
                 String searchStr =constraints.toString().toLowerCase();
                 for(countrymodel itemsmodel:countrymodelList){
                     //if suppose value enter is 'a' and if it is matched then its all data will be stored in resultsmodel
                     if(itemsmodel.getCountry().toLowerCase().contains(searchStr))
                     {
                         resultsmodel.add(itemsmodel);
                     }
                        filterResults.count=resultsmodel.size();
                        filterResults.values=resultsmodel;
                 }
             }
             return filterResults;
         }

         @Override
         protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                countrymodelListFiltered = (List<countrymodel>) filterResults.values;
                affectedcountries.countrymodelList=(List<countrymodel> ) filterResults.values;
                notifyDataSetChanged();
          }
     };
        return filter;
    }
}
