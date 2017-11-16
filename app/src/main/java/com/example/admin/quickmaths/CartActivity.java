package com.example.admin.quickmaths;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.quickmaths.model.display.DisplayObject;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends Fragment {

    private static final String TAG = "CartActivity";
    private List<DisplayObject> cartList;
    private CartListAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_cart, container, false);
        init();
        return myView;
    }

    private void init() {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cart);

//        Toolbar toolbar = myView.findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        cartList = new ArrayList<>();
        prepareCart();

        mAdapter = new CartListAdapter(cartList);

        RecyclerView recyclerView = myView.findViewById(R.id.rvCart);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

//        NavigationView navigationView = myView.findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

    }

    private void prepareCart() {

        DisplayObject item1 = new DisplayObject("Walmart", 1.50, 34.00, R.drawable.walmart);
        DisplayObject item2 = new DisplayObject("Best Buy", 1.30, 34.00, R.drawable.walmart);
        DisplayObject item3 = new DisplayObject("Target", 1.10, 34.00, R.drawable.walmart);
        DisplayObject item4 = new DisplayObject("Big Lots", 1.40, 34.00, R.drawable.walmart);
        DisplayObject item5 = new DisplayObject("Kmart", 1.20, 34.00, R.drawable.walmart);

        cartList.add(item1);
        cartList.add(item2);
        cartList.add(item3);
        cartList.add(item4);
        cartList.add(item5);

    }

}