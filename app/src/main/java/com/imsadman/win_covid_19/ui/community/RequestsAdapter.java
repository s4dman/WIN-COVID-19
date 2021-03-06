package com.imsadman.win_covid_19.ui.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.models.RequestedEntity;

import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ViewHolder> {

    private Context mContext;
    private List<RequestedEntity> mRequestedEntityList;

    public RequestsAdapter(Context mContext, List<RequestedEntity> mRequestedEntityList) {
        this.mContext = mContext;
        this.mRequestedEntityList = mRequestedEntityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequestedEntity index = mRequestedEntityList.get(position);

        holder.name.setText(index.getRequested_name());
        if (index.getRequested_quantity() > 1) {
            holder.quantity.setText(index.getRequested_quantity() + " Pcs");
        } else holder.quantity.setText(index.getRequested_quantity() + " Pc");

        holder.category.setText(index.getRequested_category());
        holder.phone.setText(index.getRequested_phone_number());
    }

    @Override
    public int getItemCount() {
        return mRequestedEntityList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity, category, phone, available;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            quantity = itemView.findViewById(R.id.product_quantity);
            category = itemView.findViewById(R.id.product_category);
            phone = itemView.findViewById(R.id.phone_number);
            available = itemView.findViewById(R.id.text_available);
            available.setText("requested");

        }
    }
}
