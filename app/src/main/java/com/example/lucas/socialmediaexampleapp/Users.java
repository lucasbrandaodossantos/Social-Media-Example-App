package com.example.lucas.socialmediaexampleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
* CLASSE USERS, UTILIZADA PARA BUSCAR A LISTA DE USUÁRIOS ATRAVÉS DO DOCUMENTO JSON.
* AO CLICAR EM QUALQUER ITEM DA LISTVIEW, TODAS AS POSTAGENS DO USUÁRIO SELECIONADO SERÃO OBTIDAS
* FOI UTILIZADA A BIBLIOTECA VOLLEY PARA REALIZAR AS CHAMADAS
*
*
*/


public class Users extends Fragment {
    private RequestQueue queue;
    private ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String>arrayAdapter;
    String user_name, user_email, user_city, user_company_name;

    ArrayList<String> lista_de_posts;
    ArrayAdapter<String> adapter;

    String title, body;
    int user_post_id;

    String url = "https://jsonplaceholder.typicode.com/users";
    int user_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, null);

        lv = view.findViewById(R.id.list_users);

        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(arrayAdapter);
        queue = Volley.newRequestQueue(getActivity());
        jsonParseUsers();
        return view;
    }



    public void jsonParseUsers() {
        //CHAMADA PARA JARRAYREQUEST PARA BUSCAR AS INFORMAÇÕES NO JSON
        JsonArrayRequest request_array = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {

                try {

                    for (int i = 0; i < response.length(); i++) {
                        /*ORDENANDO OS JSONOBJECTS PARA BUSCAR KEYS DENTRO DE JSONOBJECTS*/
                        JSONObject users = response.getJSONObject(i);
                        String JsonData = new String(response.getString(i));
                        JSONObject j1 = new JSONObject(JsonData);
                        JSONObject j2 = j1.getJSONObject("address");
                        JSONObject j3 = j1.getJSONObject("company");

                        /*OBTENDO OS DADOS ATRAVES DAS RESPECTIVAS KEYS*/
                        user_id = users.getInt("id");
                        user_name = users.getString("name");
                        user_email = users.getString("email");
                        user_city = j2.getString("city");
                        user_company_name = j3.getString("name");

                        /*EXIBIÇÃO DO ARRAY*/
                        arrayList.add("ID: " + String.valueOf(user_id) +
                                "\n\n" + "NAME: " + user_name
                                + "\n\n" + "EMAIL: " + user_email
                                + "\n\n" + "CITY: " + user_city
                                + "\n\n" + "COMPANY: " + user_company_name);
                    }
                    /*AÇÃO A SER TOMADA CASO UM ITEM DA LISTA SEJA SELECIONADO*/
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override

                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            final String content = (String) lv.getItemAtPosition(i);
                            String url_posts = "https://jsonplaceholder.typicode.com/posts?";

                            JsonArrayRequest request_array = new JsonArrayRequest(Request.Method.GET, url_posts, null, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(final JSONArray response) {
                                    String id = String.valueOf(content.charAt(4)); // PARA ENCONTRAR A POSICAO EXATA DO ID ONDE O LAÇO FOR BUSCARÁ AS POSTAGENS DO USUÁRIO ESPECIFICO
                                    try {
                                        arrayList.clear();
                                        arrayAdapter.notifyDataSetChanged();
                                        for (int i = 0; i < response.length(); i++) {
                                            JSONObject userposts = response.getJSONObject(i);
                                            user_post_id = userposts.getInt("userId");

                                            if (Integer.parseInt(id) == user_post_id){
                                                title = userposts.getString("title");
                                                body = userposts.getString("body");
                                                arrayList.add("USER ID: " + String.valueOf(user_post_id)
                                                        +"\n\n" + "TITLE: " + title
                                                        + "\n\n" + "BODY: " + body);

                                            }

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
                    });

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

