package com.example.minipets.ui.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.minipets.R;
import com.example.minipets.objects.Stock;

public class ItemsListAdapter extends ArrayAdapter<Stock> {

    private Context newContext;
    int newResource;

    public ItemsListAdapter(Context context, int resource, Stock[] items){
        super(context, resource, items);
        newContext = context;
        newResource = resource;
    }

    @NonNull
    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        //populating the shop items info
        String name = getItem(pos).getName();
        int cost = getItem(pos).getCost();
        int happiness = 5;

        //creating a new shop item for the text view
        Stock newItem = new Stock(name, cost);

        LayoutInflater inflater = LayoutInflater.from(newContext);
        convertView = inflater.inflate(newResource, parent, false);

        TextView textViewName = (TextView) convertView.findViewById(R.id.textView1);
        TextView textViewCost = (TextView) convertView.findViewById(R.id.textView2);
        TextView textViewHappiness = (TextView) convertView.findViewById(R.id.textView3);

        textViewName.setText(name);
        textViewCost.setText("Cost: "+String.valueOf(cost));
        textViewHappiness.setText("Happiness: "+String.valueOf(happiness));

        return convertView;
    }
}
