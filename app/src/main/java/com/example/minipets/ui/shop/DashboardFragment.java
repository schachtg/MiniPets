package com.example.minipets.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.minipets.R;
import com.example.minipets.logic.ShopDBLogic;
import com.example.minipets.objects.Shop;
import com.example.minipets.objects.ShopItem;

public class DashboardFragment extends Fragment implements AdapterView.OnItemClickListener {

    private DashboardViewModel dashboardViewModel;
    private  ListView lvShopItems;
    private TextView currTokens;
    private String[] items;
    private ShopItem[] shopItems;
    private Shop newShop;
    int tokens;
    ShopDBLogic dbLogic;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return root;
    }

    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        lvShopItems = (ListView) getView().findViewById(R.id.lvShopItems);
        currTokens = (TextView) getView().findViewById(R.id.tokens);
        newShop = new Shop();
        shopItems = newShop.getAvailableItems();
        dbLogic = new ShopDBLogic(getActivity());
        dbLogic.init_shop(newShop, tokens);
        dbLogic.update_shop(newShop.remTokens());
        ItemsListAdapter itemAdapter = new ItemsListAdapter(getActivity(), R.layout.adapter_view_layout, shopItems);
        lvShopItems.setAdapter(itemAdapter);
        lvShopItems.setOnItemClickListener(this);


        currTokens.setText("Tokens: "+newShop.remTokens());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        shopItems = newShop.getAvailableItems();
        newShop.addBoughtItems(shopItems[position], getActivity());
        newShop.itemsListBought();
        dbLogic.update_shop(newShop.remTokens());
        Toast.makeText(getActivity(), "Selected: "+shopItems[position].toString(), Toast.LENGTH_SHORT ).show();
        Toast.makeText(getActivity(), "Tokens left "+newShop.remTokens(), Toast.LENGTH_SHORT ).show();
        Toast toast = new Toast(getActivity());
        //toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
        toast.setText("Bought: "+shopItems[position].getName());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
        currTokens.setText("Tokens: "+newShop.remTokens());
    }
}