package com.example.admin.quickmaths.model.display;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 11/11/2017.
 */

public class DisplayObject implements Parcelable{
    String store, product, description;
    double price;
    boolean onLine;

    public DisplayObject(String store, String product, String description, double price, boolean onLine) {
        this.store = store;
        this.product = product;
        this.description = description;
        this.price = price;
        this.onLine = onLine;
    }

    protected DisplayObject(Parcel in) {
        store = in.readString();
        product = in.readString();
        description = in.readString();
        price = in.readDouble();
        onLine = in.readByte() != 0;
    }

    public static final Creator<DisplayObject> CREATOR = new Creator<DisplayObject>() {
        @Override
        public DisplayObject createFromParcel(Parcel in) {
            return new DisplayObject(in);
        }

        @Override
        public DisplayObject[] newArray(int size) {
            return new DisplayObject[size];
        }
    };

    public DisplayObject(String product, String walmart, String s, double v, double v1, boolean b) {

    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOnLine() {
        return onLine;
    }

    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(store);
        parcel.writeString(product);
        parcel.writeString(description);
        parcel.writeDouble(price);
        parcel.writeByte((byte) (onLine ? 1 : 0));
    }
}