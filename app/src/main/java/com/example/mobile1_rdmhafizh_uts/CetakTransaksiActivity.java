package com.example.mobile1_rdmhafizh_uts;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile1_rdmhafizh_uts.databinding.ActivityCetakTransaksiBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CetakTransaksiActivity extends AppCompatActivity {

    ActivityCetakTransaksiBinding binding;

    int mSubTotal, mPajak, mTotal, mTunai, mKembalian;
    String mNamaPembeli = "";
    ArrayList<MenuItem> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCetakTransaksiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mData = (ArrayList<MenuItem>) getIntent().getSerializableExtra("data");
        mNamaPembeli = getIntent().getStringExtra("namaPembeli");
        mSubTotal = getIntent().getIntExtra("subtotal", 0);
        mPajak = getIntent().getIntExtra("pajak", 0);
        mTotal = getIntent().getIntExtra("total", 0);
        mTunai = getIntent().getIntExtra("tunai", 0);
        mKembalian = getIntent().getIntExtra("kembalian", 0);

        init();
    }

    private void init() {
        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()).toString();

        binding.btnKembali.setOnClickListener(view -> finish());
        binding.btnCetak.setOnClickListener(view -> Toast.makeText(this, "CETAK!!", Toast.LENGTH_LONG).show());

        StringBuilder builder = new StringBuilder();
        for (MenuItem item : mData) {
            if (item.qty == 0) continue;
            String text = item.nama + " Rp." + item.harga + " x" + item.qty + " = Rp." + Integer.parseInt(item.harga) * item.qty;
            builder.append(text).append("\n");
        }

        binding.tvNamaPembeli.setText("Nama Pembeli: " + mNamaPembeli);
        binding.tvKeranjang.setText(builder.toString());
        binding.tvSubtotal.setText("SubTotal: Rp" + mSubTotal);
        binding.tvPajak.setText("Pajak: Rp." + mPajak);
        binding.tvTotal.setText("Total: Rp." + mTotal);
        binding.tvTunai.setText("Tunai: Rp." + mTunai);
        binding.tvKembalian.setText("Kembalian: Rp." + mKembalian);
        binding.tvTanggal.setText("Tanggal: "+currentDate);
    }
}