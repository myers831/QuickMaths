package com.example.admin.quickmaths.view.apiActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.quickmaths.R;
import com.example.admin.quickmaths.model.display.DisplayObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApiActivity extends Fragment implements ApiActivityContract.View{

    private static final String TAG = "ApiActivity";

//    @BindView(R.id.tvUPC)
    TextView upcTextView;
//    @BindView(R.id.rvItems)
    RecyclerView rvItems;

    int pageCall = 1;
    int threadCheck = 0;
    RecycleViewAdapter recycleViewAdapter;
    RecyclerView.LayoutManager layoutManager;

    ApiActivityPresenter presenter = new ApiActivityPresenter();

    List<DisplayObject> newItemList = new ArrayList<>();

    View myView;

    String upc;

    boolean oncreatecalled = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        oncreatecalled = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        myView = inflater.inflate(R.layout.activity_api, container, false);
        init();
        return myView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
        oncreatecalled = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        oncreatecalled = false;
    }

    private void init() {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_api);
//        ButterKnife.bind(this);

        upcTextView = myView.findViewById(R.id.tvUPC);
        rvItems = myView.findViewById(R.id.rvItems);

        //set up dagger

//        String upc = getIntent().getStringExtra("query");

        if(oncreatecalled) {
            Log.d(TAG, "init: here");
            upc = getArguments().getString("query");
            presenter.attachView(this);
            presenter.makeCall(pageCall, upc);
        }


        upcTextView.setText("Results for: " + upc);

//        newItemList.add(new DisplayObject("food", "Mac's", "http://freelogophoto.b-cdn.net/wp-content/uploads/2012/04/best_buy-logo.jpg", 2.00, 0, true));
//        newItemList = mergeSort(newItemList);
//        recycleViewAdapter.notifyDataSetChanged();
    }

    public void initRecyclerView(List<DisplayObject> itemList) {
        newItemList = itemList;
        threadCheck++;

        layoutManager = new LinearLayoutManager(getActivity());
        recycleViewAdapter = new RecycleViewAdapter(getActivity(), newItemList, getActivity());
        rvItems.setLayoutManager(layoutManager);
        rvItems.setAdapter(recycleViewAdapter);

        Log.d(TAG, "initRecyclerView: threadCheck: "+threadCheck);
        if(threadCheck == 4){
            newItemList = presenter.mergeSort(newItemList);
            initRecyclerView(newItemList);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showError(String error) {

    }

//    @Override
//    public void onItemClicked(DisplayObject displayObject) {
//        Log.d(TAG, "onItemClicked: ");
//    }
}
