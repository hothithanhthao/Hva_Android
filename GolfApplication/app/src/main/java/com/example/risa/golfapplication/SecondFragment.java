package com.example.risa.golfapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class SecondFragment extends Fragment {

    private JSONArray highscores;
    private  StringBuffer text;
    private GoogleMap mMap;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FetchDataTask task = new FetchDataTask();
        task.execute("https://api.myjson.com/bins/12u9tq");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                mMap = googleMap;

                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.setMinZoomPreference(5);
                mMap.setInfoWindowAdapter(new CustomInfoWindowGoogleMap(getContext()));
                // CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(mMap.setInfoWindowAdapter(MapFragment.class));


                // mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                /*
                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(37.4219999,-122.0862462))
                        .zoom(10)
                        .bearing(0)
                        .tilt(45)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);
                */

            }
        });

        return rootView;
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    class FetchDataTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            JSONObject json = null;
            try {
                URL url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                json = new JSONObject(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) urlConnection.disconnect();
            }
            return json;
        }

        protected void onPostExecute(JSONObject json) {
            text = new StringBuffer("");
            double longi;
            double lati;
            float markerColor;
            try {
                // store highscores
                highscores = json.getJSONArray("courses");
                for (int i=0;i < highscores.length();i++) {
                    JSONObject hs = highscores.getJSONObject(i);
                    //text.append(hs.getString("name")+":"+hs.getString("score")+"\n");
                    longi = Double.parseDouble(hs.getString("Lat"));
                    lati = Double.parseDouble(hs.getString("Lng"));
                    LatLng Places = new LatLng(longi,lati);

                    //color
                    if(hs.getString("Type").equals("Gold")){
                        markerColor = BitmapDescriptorFactory.HUE_YELLOW;
                    }
                    else if(hs.getString("Type").equals("Front")){
                        markerColor = BitmapDescriptorFactory.HUE_AZURE;
                    }
                    else{
                        markerColor = BitmapDescriptorFactory.HUE_GREEN;
                    }

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(Places)
                            .title(hs.getString("course"))
                            .snippet(hs.getString("Web"))
                            .icon(BitmapDescriptorFactory.defaultMarker(markerColor));

                    InfoWindowData info = new InfoWindowData();
                    info.setHotel(hs.getString("address"));
                    info.setFood(hs.getString("phone"));
                    info.setTransport(hs.getString("Email"));

                    final Marker m = mMap.addMarker(markerOptions);
                    m.setTag(info);

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(Places));


                    /*
                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(final Marker marker) {
                            if (marker.equals(m)) {
                                Toast.makeText(getApplicationContext(), "Marker = " + marker.getTitle(), Toast.LENGTH_SHORT).show();
                                return true;
                            }
                            return false;
                        }
                    });
                    */

                }
            } catch (JSONException e) {
                Log.e("JSON", "Error getting data.");
            }

        }
    }

}