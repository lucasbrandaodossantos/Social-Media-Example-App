package com.example.lucas.socialmediaexampleapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


//public class UserPosts extends Fragment {
//    public ListView list_user_posts;
//    String title, body;
//    String id;
//    String url = "https://jsonplaceholder.typicode.com/posts";
//
//    private RequestQueue queue;
//
//    ArrayList<String> lista_de_posts = new ArrayList<>();
//    ArrayAdapter <String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lista_de_posts);
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_user_posts, null); // INFLANDO O FRAGMENT RESPONSÁVEL PELOS USUÁRIOS
//        list_user_posts = view.findViewById(R.id.list_user_posts);
//        list_user_posts.setAdapter(adapter);
//        jsonParseUserPosts();
//        return view;
//
//    }
//    public void jsonParseUserPosts() {
//        JsonArrayRequest request_array = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                try {
//
//                    for (int i = 0; i < response.length(); i++) {
//                        String idcheck = "1";
//                        JSONObject users = response.getJSONObject(i);
//
//                            id = users.getString("id");
//                            title = users.getString("title");
//                            body = users.getString("body");
//
//                            if (id.equals(idcheck)) {
//                                lista_de_posts.add("USER ID: " + String.valueOf(id) +
//                                        "\n\n" + "TITLE: " + title
//                                        + "\n\n" + "BODY: " + body);
//                            }
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        queue.add(request_array);
//    }
//
//
//}
