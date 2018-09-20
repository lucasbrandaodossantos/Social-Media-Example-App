package com.example.lucas.socialmediaexampleapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Photos extends Fragment {

    private RequestQueue queue;
    private ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String image;
    String url = "https://jsonplaceholder.typicode.com/photos";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos, null); // INFLANDO O FRAGMENT RESPONSÁVEL PELOS USUÁRIOS

        lv = view.findViewById(R.id.list_users);

        arrayList = new ArrayList<>();
//        arrayAdapter  = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrayList);
//        lv.setAdapter(arrayAdapter);
        queue = Volley.newRequestQueue(getActivity());
        jsonParseUsers();
        return view;
    }
    public void jsonParseUsers() {
        JsonArrayRequest request_array = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject users = response.getJSONObject(i);
                        image = users.getString("url");
                        arrayList.add(image);


                    }
//                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
////                            UserPosts userPosts= new UserPosts();
////                            getActivity().getSupportFragmentManager().beginTransaction()
////                                    .replace(R.id.frame_layout_main, userPosts,userPosts.getTag())
////                                    .addToBackStack(null)
////                                    .commit();
//                        }
//                    });

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
