package com.example.risa.golfapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.view.View.OnClickListener;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private List<Place> mPlaces;
    private Context mContext;
    private OnItemClickListener mListener;
   // final private PortalsClickListener mPortalsClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public PlaceAdapter(Context mContext,List<Place> mPlaces) {
        this.mPlaces = mPlaces;
      //  this.mPortalsClickListener = mPortalsClickListener;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_places, null);
// Return a new holder instance
        PlaceAdapter.ViewHolder viewHolder = new PlaceAdapter.ViewHolder(view,mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceAdapter.ViewHolder holder,final int position) {

        final Place place =  mPlaces.get(position);
        holder.placeName.setText(place.getTitle());
        holder.placeImage.setImageResource(place.getImageResource());



    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout placeNameHolder;
        public TextView placeName;
        public ImageView placeImage;;

        public ViewHolder(View itemView, final OnItemClickListener listener) {

            super(itemView);
            placeName = (TextView) itemView.findViewById(R.id.placeName);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            placeImage = (ImageView) itemView.findViewById(R.id.placeImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }

    }

    public void swapList (List<Place> newList) {


        mPlaces = newList;

        if (newList != null) {

            // Force the RecyclerView to refresh

            this.notifyDataSetChanged();

        }

    }



}
