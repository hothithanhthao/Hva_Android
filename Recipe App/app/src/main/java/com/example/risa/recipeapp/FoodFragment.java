package com.example.risa.recipeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



public class FoodFragment extends Fragment {
    private static final String ARG_DATA = "data";

    private Recipe mData;

    public static FoodFragment newInstance(Recipe recipe) {
        FoodFragment fragment = new FoodFragment();
        Bundle args = new Bundle();

       // args.putString("FOOD_NAME", recipeName);
        //args.putString("FOOD_DESCRIPTION", recipeInstruction);
        //args.putInt("FOOD_IMAGE", b);
        args.putParcelable(ARG_DATA, recipe);


        fragment.setArguments(args);
        return fragment;
    }

    /*
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 12, outputStream);
        return outputStream.toByteArray();
    }
    */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = getArguments().getParcelable(ARG_DATA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_layout, container, false);

        TextView textView = rootView.findViewById(R.id.recipe_name);
        ImageView imageView = rootView.findViewById(R.id.recipe_image);
        textView.setText(mData.getName());

        Log.i("dsds",mData.getImage());
       // byte[] imgByte = getArguments().getByteArray("FOOD_IMAGE");
      //  Bitmap b = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
      //  imageView.setImageBitmap( b );
       Glide.with(this)
              .load(mData.getImage())
              .into(imageView);

        return rootView;
    }
}
