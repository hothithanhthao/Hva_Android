package com.example.risa.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;
import 	android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;
public class ImageListAdapter extends RecyclerView.Adapter<GeoObjectViewHolder>  {


    private Context context;
    public List<GeoObject> listGeoObject;


    public ImageListAdapter(Context context, List<GeoObject> listGeoObject) {
        this.context = context;
        this.listGeoObject = listGeoObject;
    }
    /*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.mylist, parent, false);
        }

        Picasso.get().load(imageUrls[position]).placeholder(R.drawable.placeholder).error(R.drawable.error).fit().centerCrop().into((ImageView)convertView);

        return convertView;
    }
    */
    @Override
    public GeoObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylist, parent, false);
        return new GeoObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GeoObjectViewHolder holder, final int position) {
        // Gets a single item in the list from its position
        final GeoObject geoObject = listGeoObject.get(position);
        // The holder argument is used to reference the views inside the viewHolder
        // Populate the views with the data from the list
        //holder.geoImage.setImageDrawable();
        //holder.geoImage.setVisibility(View.VISIBLE);
        holder.geoImage.setImageResource(geoObject.getmGeoImageName());
       // holder.geoImage.setVisibility(View.VISIBLE);

      // holder.geoName.setText(geoObject.getmGeoName());

    }


    @Override
    public int getItemCount() {
        return listGeoObject.size();
    }
}
