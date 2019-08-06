package com.e.ecatalogue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.e.ecatalogue.Activity.CategoriesActivity;
import com.e.ecatalogue.Activity.ItemsActivity;
import com.e.ecatalogue.data.BrandData;
import com.e.ecatalogue.data.CategoryData;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    private ArrayList<CategoryData> categoryList;
    private Context context;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int listPosition) {
        TextView textViewDescription = holder.textViewDescription;
        textViewDescription.setText(categoryList.get(listPosition).getName());
        holder.parentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ItemsActivity.class);
                intent.putExtra(ECatalogueConstants.BRAND, categoryList.get(listPosition).getBrand());
                intent.putExtra(ECatalogueConstants.CATEGORY, categoryList.get(listPosition).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        CardView parentCardView;
        TextView textViewDescription;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            this.parentCardView = itemView.findViewById(R.id.category_card_view);
            this.textViewDescription = itemView.findViewById(R.id.category_name);
        }
    }

    public CategoriesAdapter(ArrayList<CategoryData> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context=context;
    }
}

