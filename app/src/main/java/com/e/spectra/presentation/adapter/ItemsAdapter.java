package com.e.spectra.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.spectra.R;
import com.e.spectra.constants.ECatalogueConstants;
import com.e.spectra.presentation.ItemDetailsActivity;
import com.e.spectra.presentation.data.ItemData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private ArrayList<ItemData> itemsList;
    private Context context;

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }


    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int listPosition) {
        Context imageContext = holder.imageViewIcon.getContext();
        TextView textViewDescription = holder.textViewDescription;
        TextView textViewCode = holder.textViewCode;
        TextView textViewPrice = holder.textViewPrice;
        TextView textViewName = holder.textViewName;
        ImageView imageViewIcon = holder.imageViewIcon;
        textViewDescription.setText(itemsList.get(listPosition).getDescription());
        textViewName.setText(itemsList.get(listPosition).getName());
        textViewCode.setText(itemsList.get(listPosition).getCode());
        textViewPrice.setText(String.valueOf(itemsList.get(listPosition).getPrice()));
        try {

            Glide.with(imageContext)
                    .load(String.valueOf(itemsList.get(listPosition).getImageUrl())).
                    into(imageViewIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.parentCardView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ItemDetailsActivity.class);
                        intent.putExtra(ECatalogueConstants.ITEM, itemsList.get(listPosition));
                        context.startActivity(intent);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_card_view)
        CardView parentCardView;
        @BindView(R.id.description)
        TextView textViewDescription;
        @BindView(R.id.code)
        TextView textViewCode;
        @BindView(R.id.price)
        TextView textViewPrice;
        @BindView(R.id.name)
        TextView textViewName;
        @BindView(R.id.imageView)
        ImageView imageViewIcon;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ItemsAdapter(ArrayList<ItemData> data, Context context) {
        this.itemsList = data;
        this.context = context;
    }

    public void filterList(ArrayList<ItemData> filteredItemsList) {
        itemsList = filteredItemsList;
        notifyDataSetChanged();
    }


}
