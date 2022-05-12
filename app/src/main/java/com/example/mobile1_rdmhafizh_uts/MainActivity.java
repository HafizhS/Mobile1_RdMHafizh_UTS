package com.example.mobile1_rdmhafizh_uts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mobile1_rdmhafizh_uts.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<MenuItem> data;
    int mSubTotal = 0;
    int mPajak = 0;
    int mTotal = 0;
    int mTunai = 0;
    int mKembalian = 0;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        data = new ArrayList<>();
        data.add(new MenuItem("Honey Garlic Chicken", "35000", R.drawable.honey_chicken_garlic));
        data.add(new MenuItem("Beef Burger", "30000", R.drawable.beefburger));
        data.add(new MenuItem("Regular Fries", "25000", R.drawable.fries));
        data.add(new MenuItem("Ice Cream Cone", "10000", R.drawable.icecream_cone));
        data.add(new MenuItem("Flurry Oreo", "18000", R.drawable.flurryoreo_transparent));
        data.add(new MenuItem("Fanta Float", "15000", R.drawable.fantafloat));


        MenuAdapter adapter = new MenuAdapter(data, (data, pos) -> {
            this.data.set(pos, data);
            mSubTotal = 0;
            for (MenuItem item : this.data) {
                mSubTotal += item.getHargaAsInt() * item.qty;
            }
            mPajak = (int) (mSubTotal * 0.1);
            mTotal = mSubTotal + mPajak;
            binding.edtSubTotal.setText(String.valueOf(mSubTotal));
            binding.edtPajak.setText(String.valueOf(mPajak));
            binding.edtTotal.setText(String.valueOf(mTotal));
            updateKembalian();
        });

        binding.listMenu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.listMenu.setAdapter(adapter);

        binding.btnPesan.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CetakTransaksiActivity.class);
            intent.putExtra("namaPembeli", binding.edtNama.getText().toString());
            intent.putExtra("data", this.data);
            intent.putExtra("subtotal", this.mSubTotal);
            intent.putExtra("pajak", this.mPajak);
            intent.putExtra("total", this.mTotal);
            intent.putExtra("tunai", mTunai);
            intent.putExtra("kembalian", mKembalian);
            startActivity(intent);
        });


        binding.edtTunai.setOnKeyListener((view, i, keyEvent) -> {
            updateKembalian();
            return false;
        });
    }

    private void updateKembalian() {
        if (!binding.edtTunai.getText().toString().isEmpty()) {
            mTunai = Integer.parseInt(binding.edtTunai.getText().toString());
            mKembalian = mTunai - mTotal;
            binding.edtKembalian.setText(String.valueOf(mKembalian));
        }
    }
}