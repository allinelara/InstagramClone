package com.parse.starter.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private ListView listaImagens;
    private ArrayList<ParseObject> postagens = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private ParseQuery<ParseObject> query;
    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_home, container, false);
        listaImagens = (ListView) view.findViewById(R.id.listaImagem);
        homeAdapter = new HomeAdapter(getActivity(), postagens);
        listaImagens.setAdapter(homeAdapter);
        getPostagens();
        return view;
    }
    public void getPostagens(){
        query = ParseQuery.getQuery("Imagem");
        String nome = ParseUser.getCurrentUser().getUsername();
        query.whereEqualTo("username", nome);
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        postagens.clear();
                        for(ParseObject parseObject: objects){
                            postagens.add(parseObject);
                        }
                        homeAdapter.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(getActivity(), "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
