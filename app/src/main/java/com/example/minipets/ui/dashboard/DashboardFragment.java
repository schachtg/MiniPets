package com.example.minipets.ui.dashboard;

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
import com.example.minipets.objects.Shop;
import com.example.minipets.objects.ShopItem;

public class DashboardFragment extends Fragment implements AdapterView.OnItemClickListener {

    private DashboardViewModel dashboardViewModel;
    private  ListView lvShopItems;
    private String[] items;
    private ShopItem[] shopItems;
    private Shop newShop;
    int tokens;
    

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
        tokens = 1000;
        newShop = new Shop(tokens);
        items = newShop.itemsList();
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
        lvShopItems.setAdapter(itemAdapter);
        lvShopItems.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //String item = items[position];
        shopItems = newShop.getAvailableItems();
        newShop.addBoughtItems(shopItems[position]);
        Toast.makeText(getActivity(), "Selected: "+shopItems[position].toString(), Toast.LENGTH_SHORT ).show();
        Toast.makeText(getActivity(), "Tokens left "+newShop.remTokens(), Toast.LENGTH_SHORT ).show();
    }
}