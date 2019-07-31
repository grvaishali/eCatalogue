package com.e.ecatalogue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.e.ecatalogue.models.GlideApp;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<ListDataModel> dataSet;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);

        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        Context context = holder.imageViewIcon.getContext();
        TextView textViewDescription = holder.textViewDescription;
        TextView textViewCode = holder.textViewCode;
        TextView textViewPrice = holder.textViewPrice;
        ImageView imageViewIcon = holder.imageViewIcon;

        textViewDescription.setText(dataSet.get(listPosition).getDescription());
        textViewCode.setText(dataSet.get(listPosition).getCode());
        textViewPrice.setText(String.valueOf(dataSet.get(listPosition).getPrice()));
        try {

            GlideApp.with(context)
                    .load(String.valueOf(dataSet.get(listPosition).getImage())).
                    into(imageViewIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView textViewDescription;
        TextView textViewCode;
        TextView textViewPrice;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.description);
            this.textViewCode = (TextView) itemView.findViewById(R.id.code);
            this.textViewPrice = (TextView) itemView.findViewById(R.id.price);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public CustomAdapter(ArrayList<ListDataModel> data) {
        this.dataSet = data;
    }


}
