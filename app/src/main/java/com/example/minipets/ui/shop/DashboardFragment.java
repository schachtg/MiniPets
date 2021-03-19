package com.example.minipets.ui.shop;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.minipets.R;
import com.example.minipets.data_layer.SQLdb;
import com.example.minipets.data_layer.SQLiteHelper;
import com.example.minipets.objects.Shop;
import com.example.minipets.objects.ShopItem;
import com.example.minipets.data_layer.ShopFakeDatabase;

import java.sql.SQLException;

public class DashboardFragment extends Fragment implements AdapterView.OnItemClickListener {

    private DashboardViewModel dashboardViewModel;
    private  ListView lvShopItems;
    private String[] items;
    private ShopItem[] shopItems;
    private Shop newShop;
    int tokens;
    private ShopFakeDatabase DB;
    SQLdb db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        lvShopItems = (ListView) getView().findViewById(R.id.lvShopItems);
        newShop = new Shop();
        DB = new ShopFakeDatabase(newShop);
        tokens = DB.getCurrentTokens();
        items = newShop.itemsList();
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
        lvShopItems.setAdapter(itemAdapter);
        lvShopItems.setOnItemClickListener(this);
        db = new SQLdb(getActivity());
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(db.get().getCount()>0){
            Cursor cursor = db.get();
            cursor.moveToFirst();
            tokens = cursor.getInt(1);
            newShop.set_tokens(tokens);
            Toast.makeText(getActivity(), "We already have this!", Toast.LENGTH_SHORT).show();
        }
        else {
            long tester = db.insert_shop(tokens);
            System.out.println(tester);
            newShop.set_tokens(1000);
            Toast.makeText(getActivity(), "Inserted new token value!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //String item = items[position];
        shopItems = newShop.getAvailableItems();
        newShop.addBoughtItems(shopItems[position]);
        newShop.itemsListBought();
        DB.updateTokens();
        int test = db.update(1, DB.getCurrentTokens());
        System.out.println(test);
        Toast.makeText(getActivity(), "Selected: "+shopItems[position].toString(), Toast.LENGTH_SHORT ).show();
        Toast.makeText(getActivity(), "Tokens left "+DB.getCurrentTokens(), Toast.LENGTH_SHORT ).show();
    }
}