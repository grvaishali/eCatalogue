package com.e.ecatalogue;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.ecatalogue.Activity.CategoriesActivity;
import com.e.ecatalogue.data.BrandData;

import java.util.ArrayList;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder> {
    private ArrayList<BrandData> brandsList;
    private Context context;

    @NonNull
    @Override
    public BrandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.brand_item, parent, false);
        BrandsViewHolder brandsViewHolder = new BrandsViewHolder(view);
        return brandsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BrandsViewHolder holder, final int listPosition) {
        final Context imageContext = holder.imageViewIcon.getContext();
        TextView textViewDescription = holder.textViewDescription;
        ImageView imageViewIcon = holder.imageViewIcon;
        textViewDescription.setText(brandsList.get(listPosition).getName());
        holder.parentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategoriesActivity.class);
                intent.putExtra(ECatalogueConstants.BRAND, brandsList.get(listPosition).getName());
                context.startActivity(intent);
            }
        });
        try {
            Glide.with(imageContext)
                    .load(String.valueOf(brandsList.get(listPosition).getImageUrl())).
                    into(imageViewIcon);
        } catch (Exception e) {
            Log.e("LoadBrandImage", e.getMessage(), e);
        }
    }

    @Override
    public int getItemCount() {
        return brandsList.size();
    }

    public static class BrandsViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDescription;
        ImageView imageViewIcon;
        CardView parentCardView;

        public BrandsViewHolder(View itemView) {
            super(itemView);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.brand_name);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
            parentCardView = (CardView) itemView.findViewById(R.id.brand_card_view);
        }
    }

    public BrandsAdapter(ArrayList<BrandData> brandsList, Context context) {
        this.brandsList = brandsList;
        this.context = context;
    }
}

