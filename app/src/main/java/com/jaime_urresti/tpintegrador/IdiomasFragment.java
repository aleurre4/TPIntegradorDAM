package com.jaime_urresti.tpintegrador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaime_urresti.tpintegrador.bdd.AppDatabase;
import com.jaime_urresti.tpintegrador.databinding.FragmentIdiomasBinding;
import com.jaime_urresti.tpintegrador.modelo.Idioma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;


public class IdiomasFragment extends Fragment {

    private FragmentIdiomasBinding binding;
    private RecyclerView recyclerIdiomas;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Idioma> idiomas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                idiomas = AppDatabase.getInstance(getActivity().getApplicationContext()).idiomaDAO().loadAll();
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(idiomas == null){
            idiomas = new ArrayList<>();

        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentIdiomasBinding.inflate(inflater,container,false);
        recyclerIdiomas = binding.recyclerIdiomas;
        recyclerIdiomas.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerIdiomas.setLayoutManager(layoutManager);


        if(idiomas!=null){
            mAdapter =  new IdiomasRecyclerAdapter(idiomas);
        }else{
            mAdapter =  new IdiomasRecyclerAdapter(new ArrayList<>());
        }



        recyclerIdiomas.setAdapter(mAdapter);



        return binding.getRoot();
    }
}