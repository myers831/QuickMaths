package com.example.admin.quickmaths.view.apiActivity;

import com.example.admin.quickmaths.BasePresenter;
import com.example.admin.quickmaths.BaseView;
import com.example.admin.quickmaths.model.display.DisplayObject;

import java.util.List;

/**
 * Created by Admin on 11/13/2017.
 */

public interface ApiActivityContract {
    interface View extends BaseView {
        //activity methods called by presenter
        void initRecyclerView(List<DisplayObject> itemList);
        void showProgress();
    }

    interface Presenter extends BasePresenter<View> {
        //presenter methods called by activity
        void makeCall(int pageCallUpdate, String upc);
    }
}
