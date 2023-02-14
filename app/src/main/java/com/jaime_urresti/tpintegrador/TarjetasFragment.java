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
import com.jaime_urresti.tpintegrador.databinding.FragmentTarjetasBinding;
import com.jaime_urresti.tpintegrador.modelo.Tarjeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class TarjetasFragment extends Fragment {

    private FragmentTarjetasBinding binding;
    private RecyclerView recyclerTarjetas;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Tarjeta> tarjetas;
    private Integer idIdioma;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        idIdioma = getArguments().getInt("id");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tarjetas = AppDatabase.getInstance(getActivity().getApplicationContext()).tarjetaDAO().loadByIdioma(idIdioma);
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(tarjetas == null){
            tarjetas = new ArrayList<>();

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("RESUMEEE");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tarjetas = AppDatabase.getInstance(getActivity().getApplicationContext()).tarjetaDAO().loadByIdioma(idIdioma);
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(tarjetas == null){
            tarjetas = new ArrayList<>();

        }

        mAdapter =  new TarjetasRecyclerAdapter(tarjetas);


        recyclerTarjetas.setAdapter(mAdapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentTarjetasBinding.inflate(inflater,container,false);
        recyclerTarjetas = binding.recyclerTarjetas;
        recyclerTarjetas.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerTarjetas.setLayoutManager(layoutManager);


        mAdapter =  new TarjetasRecyclerAdapter(tarjetas);


        recyclerTarjetas.setAdapter(mAdapter);



        return binding.getRoot();
    }
}