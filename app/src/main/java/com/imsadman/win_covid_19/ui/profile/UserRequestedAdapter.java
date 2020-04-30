package com.imsadman.win_covid_19.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.models.RequestedEntity;
import com.imsadman.win_covid_19.utils.Generics;

import java.util.List;

public class UserRequestedAdapter extends RecyclerView.Adapter<UserRequestedAdapter.ViewHolder> {

    private static final String TAG = "UserRequestedAdapter";
    private Context mContext;
    private List<RequestedEntity> mRequestedEntityList;

    public UserRequestedAdapter(Context mContext, List<RequestedEntity> mRequestedEntityList) {
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        RequestedEntity index = mRequestedEntityList.get(position);

        holder.name.setText(index.getRequested_name());
        if (index.getRequested_quantity() > 1) {
            holder.quantity.setText(index.getRequested_quantity() + " Pcs");
        } else holder.quantity.setText(index.getRequested_quantity() + " Pc");

        holder.category.setText(index.getRequested_category());
        holder.phone.setText(index.getRequested_phone_number());

        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(mContext, holder.option);
                popup.inflate(R.menu.product_menu);
                MenuItem editItem = popup.getMenu().findItem(R.id.product_edit);
                editItem.setVisible(false);
                MenuItem deleteItem = popup.getMenu().findItem(R.id.product_edit);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.product_delete:
                                deleteProduct(mRequestedEntityList.get(position).getRequested_products_id(), mRequestedEntityList.indexOf(mRequestedEntityList.get(position)));
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRequestedEntityList.size();
    }

    private void deleteProduct(String id, final int adapterPosition) {
        DocumentReference productIdRef = Generics.initFirestore(mContext).collection("requested_products").document(id);

        productIdRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                mRequestedEntityList.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
                notifyDataSetChanged();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity, category, phone, available, option;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            quantity = itemView.findViewById(R.id.product_quantity);
            category = itemView.findViewById(R.id.product_category);
            phone = itemView.findViewById(R.id.phone_number);
            available = itemView.findViewById(R.id.text_available);
            option = itemView.findViewById(R.id.mylist_option);
            option.setVisibility(View.VISIBLE);
            available.setText("requested");
        }
    }
}
