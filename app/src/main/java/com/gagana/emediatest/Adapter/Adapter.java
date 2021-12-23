package com.gagana.emediatest.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gagana.emediatest.R;
import com.gagana.emediatest.View.SingleDataViewActivity;
import com.gagana.emediatest.Model.sampleDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    ArrayList<sampleDataModel> arrayList=new ArrayList<>();
    Context context;

    public Adapter(ArrayList<sampleDataModel> arrayList,Context context){
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        MyViewHolder myviewHoldernew = new MyViewHolder(view);
        return myviewHoldernew;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(arrayList.get(position).getTitle());
        holder.address.setText(arrayList.get(position).getAddress());
        Picasso.with(context).load("https://cdn.britannica.com/91/181391-050-1DA18304/cat-toes-paw-number-paws-tiger-tabby.jpg\n").into(holder.imageView);

        holder.backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sampleDataModel userItem = arrayList.get(position);
                Intent yourIntent = new Intent(context, SingleDataViewActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("Oblect", userItem);
                yourIntent.putExtras(b); //pass bundle to your intent
                context.startActivity(yourIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title,address;
        LinearLayout backView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =(ImageView) itemView.findViewById(R.id.userImage);
            title =(TextView) itemView.findViewById(R.id.txtName);
            address =(TextView) itemView.findViewById(R.id.txtAddress);
            backView=(LinearLayout)itemView.findViewById(R.id.backView);
        }
    }
}
