package com.example.admin.quickmaths.view.placesActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.quickmaths.BlankFragment;
import com.example.admin.quickmaths.DirectionsActivity;
import com.example.admin.quickmaths.GooglePlacesAdapter;
import com.example.admin.quickmaths.R;
import com.example.admin.quickmaths.di.component.DaggerPresenterComponent;
import com.example.admin.quickmaths.di.modules.LinearLayoutManagerModule;
import com.example.admin.quickmaths.model.google.Result;
import com.example.admin.quickmaths.model.google.Step;
import com.example.admin.quickmaths.presenter.GooglePlacesPresenter;
import com.example.admin.quickmaths.utils.MainActivityContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class GooglePlacesActivity extends AppCompatActivity implements MainActivityContract.View{

    private static final String TAG = "hey";
    @Inject
    GooglePlacesPresenter presenter;
    @Inject
    LinearLayoutManager linearLayout;
    @Inject
    RecyclerView.ItemAnimator itemAnimator;
    @Inject
    BlankFragment fragment;

    private List<Result> resultList;
    private List<Step> stepList;
    private RecyclerView recyclerView;
    private GooglePlacesAdapter adapterForNearbyPlaces;
    private GooglePlacesAdapter adapterForDirections;
    private android.support.v4.app.FragmentManager manager;
    private android.support.v4.app.FragmentTransaction transaction;
    List<String> storeNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_places);

        recyclerView = findViewById(R.id.recyclerView);
        resultList = new ArrayList<>();
        stepList = new ArrayList<>();
        DaggerPresenterComponent.builder()
                .linearLayoutManagerModule(new LinearLayoutManagerModule(this))
                .build().inject(this);
        
        if(getIntent().getStringExtra("storeName") != null)
            storeNames.add(getIntent().getStringExtra("storeName"));
        else
            storeNames = getIntent().getStringArrayListExtra("stores");

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.flFragment, fragment, "fragment").commit();
        presenter.attachView(this);
        fragment.setStoreNames(storeNames);
        fragment.setPresenter(presenter);



    }

    @Override
    public void showError(String s) {

    }

    @Override
    public void updateNearbyPlaces(List<Result> nearbyPlacesList, Map<String,Double> distanceOfClosestStores) {
        resultList.addAll(nearbyPlacesList);
        adapterForNearbyPlaces = new GooglePlacesAdapter(resultList);
        adapterForNearbyPlaces.setDistanceOfClosestStores(distanceOfClosestStores);
        recyclerView.setAdapter(adapterForNearbyPlaces);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setItemAnimator(itemAnimator);

    }

    //I chose to use setAdapter instead of swapAdapter because swapAdapter between two lists works only if the lists
    //are of the same size
    @Override
    public void updateDirections(List<Step> stepList) {
        this.stepList.addAll(stepList);
        adapterForDirections = new GooglePlacesAdapter(stepList, null);
        recyclerView.setAdapter(adapterForDirections);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setItemAnimator(itemAnimator);
    }


    public void sendSelectedLocations(View view) {
        ArrayList<String> wayPoints = (ArrayList<String>) adapterForNearbyPlaces.getWayPoints();

        if(wayPoints.isEmpty()) {
            Toast.makeText(this, "Select a result or scan another item", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, DirectionsActivity.class);
            intent.putStringArrayListExtra("wayPoints", wayPoints);
            startActivity(intent);
        }
    }

}

