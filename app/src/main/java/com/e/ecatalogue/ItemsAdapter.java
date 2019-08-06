package com.e.ecatalogue;

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
import com.e.ecatalogue.Activity.ItemDetailsActivity;
import com.e.ecatalogue.data.ItemData;

import java.util.ArrayList;

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
        ImageView imageViewIcon = holder.imageViewIcon;
        textViewDescription.setText(itemsList.get(listPosition).getDescription());
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
        CardView parentCardView;
        TextView textViewDescription;
        TextView textViewCode;
        TextView textViewPrice;
        ImageView imageViewIcon;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.description);
            this.textViewCode = (TextView) itemView.findViewById(R.id.code);
            this.textViewPrice = (TextView) itemView.findViewById(R.id.price);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
            parentCardView=  itemView.findViewById(R.id.item_card_view);
        }
    }

    public ItemsAdapter(ArrayList<ItemData> data, Context context) {
        this.itemsList = data;
        this.context = context;
    }


}
