package com.example.anilturan.sozlukapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anilturan.sozlukapp.model.GlosbeResponse;

public class MeanAdapter extends RecyclerView.Adapter<MeanAdapter.MyViewHolder> {
    LayoutInflater inflater;
    GlosbeResponse gResponse = new GlosbeResponse();


    public MeanAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (gResponse.getTuc().get(position).getPhrase() != null) {
            holder.definition1.setText(gResponse.getTuc().get(position).getPhrase().getText());
        }
    }

    @Override
    public int getItemCount() {
        return gResponse.getTuc().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView definition1;


        public MyViewHolder(View itemView) {
            super(itemView);
            definition1 = itemView.findViewById(R.id.definition1);
        }
    }


    public void setList(GlosbeResponse data) {
        this.gResponse.getTuc().clear();
        this.gResponse.getTuc().addAll(data.getTuc());
        notifyDataSetChanged();
    }
}
