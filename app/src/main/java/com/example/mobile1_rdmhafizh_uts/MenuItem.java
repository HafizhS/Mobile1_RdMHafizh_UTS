package com.example.mobile1_rdmhafizh_uts;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.NumberFormat;
import java.util.Locale;

public class MenuItem implements Parcelable {
    public String nama;
    public String harga;
    public int img;
    public int qty;


    public MenuItem(String nama, String harga, int img) {
        this.nama = nama;
        this.harga = harga;
        this.img = img;
    }


    protected MenuItem(Parcel in) {
        nama = in.readString();
        harga = in.readString();
        img = in.readInt();
        qty = in.readInt();
    }

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };

    public String getHargaToIdrFormat() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return formatter.format(Integer.valueOf(harga));
    }

    public int getHargaAsInt() {
        return Integer.parseInt(harga);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(harga);
        parcel.writeInt(img);
        parcel.writeInt(qty);
    }
}
