package com.e.spectra.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.e.spectra.constants.ECatalogueConstants;
import com.e.spectra.R;
import com.e.spectra.presentation.ItemsActivity;
import com.e.spectra.presentation.data.CategoryData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>  implements Filterable {
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

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category_card_view)
        CardView parentCardView;
        @BindView(R.id.category_name)
        TextView textViewDescription;

        public CategoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public CategoriesAdapter(ArrayList<CategoryData> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    public void filterList(ArrayList<CategoryData> filteredCategoryList) {
        categoryList = filteredCategoryList;
        notifyDataSetChanged();
    }

}

