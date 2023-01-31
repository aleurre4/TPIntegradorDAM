package com.jaime_urresti.tpintegrador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaime_urresti.tpintegrador.databinding.FragmentIdiomasBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class IdiomasFragment extends Fragment {

    private FragmentIdiomasBinding binding;
    private RecyclerView recyclerIdiomas;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> idiomas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        idiomas = Arrays.asList("INGLES","FRANCES","PORTUGUES","ITALIANO");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentIdiomasBinding.inflate(inflater,container,false);
        recyclerIdiomas = binding.recyclerIdiomas;
        recyclerIdiomas.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerIdiomas.setLayoutManager(layoutManager);


       mAdapter =  new IdiomasRecyclerAdapter(idiomas);


        recyclerIdiomas.setAdapter(mAdapter);



        return binding.getRoot();
    }
}