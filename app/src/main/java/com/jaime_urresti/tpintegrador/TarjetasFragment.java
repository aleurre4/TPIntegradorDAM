package com.jaime_urresti.tpintegrador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaime_urresti.tpintegrador.databinding.FragmentIdiomasBinding;
import com.jaime_urresti.tpintegrador.databinding.FragmentTarjetasBinding;

import java.util.Arrays;
import java.util.List;

public class TarjetasFragment extends Fragment {

    private FragmentTarjetasBinding binding;
    private RecyclerView recyclerTarjetas;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> tarjetas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tarjetas = Arrays.asList("Tarjeta1","Tarjeta2","Tarjeta3","Tarjeta4");

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