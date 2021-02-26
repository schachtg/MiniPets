package com.example.minipets.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.minipets.R;
import com.example.minipets.Shop;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    ListView lvShopItems;
    String[] items;
    

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
        ListView lvShopItems = (ListView) getView().findViewById(R.id.lvShopItems);
        int newToken = 1000;
        Shop newShop = new Shop(newToken);
        String[] items = newShop.getAvailableItems();
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
        lvShopItems.setAdapter(itemAdapter);


        
        
    }
}