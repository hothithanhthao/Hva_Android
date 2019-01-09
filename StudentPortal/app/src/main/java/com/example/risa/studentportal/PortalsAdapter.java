package com.example.risa.studentportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
public class PortalsAdapter extends RecyclerView.Adapter<PortalsAdapter.ViewHolder> {
    private List<Portals> mPortals;
    final private PortalsClickListener mPortalsClickListener;

    public interface PortalsClickListener{
        void reminderOnClick (int i);
    }

    public PortalsAdapter(List<Portals> mPortals, PortalsClickListener mPortalsClickListener) {
        this.mPortals = mPortals;
        this.mPortalsClickListener = mPortalsClickListener;
    }

    @NonNull
    @Override
    public PortalsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);
// Return a new holder instance
        PortalsAdapter.ViewHolder viewHolder = new PortalsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PortalsAdapter.ViewHolder holder, int position) {
        Portals portals =  mPortals.get(position);
        holder.textView.setText(portals.getmPortalsText());
    }

    @Override
    public int getItemCount() {
        return mPortals.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;

        public ViewHolder(View itemView) {

            super(itemView);
            textView=  itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mPortalsClickListener.reminderOnClick(clickedPosition);
        }

    }

}
