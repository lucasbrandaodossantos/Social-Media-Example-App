package com.example.lucas.socialmediaexampleapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Photos extends Fragment {
    private Context context;
    View view;
    private RequestQueue queue;
    private ImageView galleryimgview;
    private GridView gridView;
    String url_json = "https://jsonplaceholder.typicode.com/photos";
    String img_url = "";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos, null); // INFLANDO O FRAGMENT RESPONSÁVEL PELOS USUÁRIOS
        queue = Volley.newRequestQueue(getActivity());
        jsonParseUsers();

        galleryimgview= view.findViewById(R.id.galleryImageView);

        gridView = view.findViewById(R.id.gridView);

        return view;
    }

    private void loadImageFromURL(String u){
        Picasso.get().load(img_url).into(galleryimgview);
   }

    public void jsonParseUsers() {
        JsonArrayRequest request_array = new JsonArrayRequest(Request.Method.GET, url_json, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject users = response.getJSONObject(i);
                        img_url = users.getString("url");
                        loadImageFromURL(img_url);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request_array);
    }

}
