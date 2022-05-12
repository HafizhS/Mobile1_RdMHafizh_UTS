package com.example.mobile1_rdmhafizh_uts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile1_rdmhafizh_uts.databinding.MenuItemBinding;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    ArrayList<MenuItem> data;
    MenuAdapterInterface adapterInterface;

    public MenuAdapter(ArrayList<MenuItem> data, MenuAdapterInterface adapterInterface) {
        this.data = data;
        this.adapterInterface = adapterInterface;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuViewHolder(MenuItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), adapterInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.bind(data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    interface MenuAdapterInterface {
        void OnDataChanged(MenuItem item, int position);
    }

    protected static class MenuViewHolder extends RecyclerView.ViewHolder {

        MenuItemBinding binding;
        MenuAdapterInterface adapterInterface;
        MenuItem item;
        int position;
        int currentQty = 0;

        public MenuViewHolder(@NonNull MenuItemBinding binding, MenuAdapterInterface adapterInterface) {
            super(binding.getRoot());
            this.binding = binding;
            this.adapterInterface = adapterInterface;
        }

        public void bind(MenuItem item, int position) {
            this.position = position;
            this.item = item;

            binding.tvHarga.setText(item.getHargaToIdrFormat());
            binding.tvNama.setText(item.nama);
            binding.imgMenu.setImageDrawable(AppCompatResources.getDrawable(itemView.getContext(), item.img));

            binding.btnTambah.setOnClickListener(view -> {
                toggleQtyView();
                setQty(1);
            });

            binding.btnAdd.setOnClickListener(view -> {
                setQty(1);
            });

            binding.btnRemove.setOnClickListener(view -> {
                setQty(-1);
            });

        }

        public void setQty(int value) {
            currentQty = MathUtils.clamp(currentQty + value, 0, Integer.MAX_VALUE);
            if (currentQty == 0) {
                toggleQtyView();
            }
            binding.tvQty.setText(String.valueOf(currentQty));
            item.qty = currentQty;
            adapterInterface.OnDataChanged(this.item, position);
        }


        public void toggleQtyView() {
            boolean toggle = !(binding.viewQuantity.getVisibility() == View.GONE);
            binding.viewQuantity.setVisibility(toggle ? View.GONE : View.VISIBLE);
            binding.btnTambah.setVisibility(!toggle ? View.GONE : View.VISIBLE);
        }
    }
}
