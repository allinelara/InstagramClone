package com.parse.starter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.activity.FeedPostagensUserActivity;
import com.parse.starter.activity.FeedUsuarioActivity;
import com.parse.starter.adapter.UsuarioAdapter;

import java.util.ArrayList;
import java.util.List;


public class UsuariosFragment extends Fragment {
    private ListView lista;
    private ArrayAdapter<ParseUser> adapter;
    private ArrayList<ParseUser> usuarios = new ArrayList<>();
    private ParseQuery<ParseUser> query;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_usuarios, container, false);
        lista = (ListView) view.findViewById(R.id.lista);
        adapter = new UsuarioAdapter(getActivity(), usuarios);
        lista.setAdapter(adapter);

        getUsuarios();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ParseUser parseUser = usuarios.get(i);
                Intent intent = new Intent(getActivity(), FeedUsuarioActivity.class);
                intent.putExtra("nome", parseUser.getUsername());
                startActivity(intent);
            }
        });
        return view;
    }

    private void getUsuarios(){
        query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByAscending("username");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        usuarios.clear();
                        for(ParseUser parseUser : objects){
                            usuarios.add(parseUser);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
