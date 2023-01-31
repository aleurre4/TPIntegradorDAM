package com.jaime_urresti.tpintegrador;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaime_urresti.tpintegrador.databinding.FragmentPimerBinding;


public class PrimerFragment extends Fragment {

    private FragmentPimerBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPimerBinding.inflate(inflater,container,false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, Bundle saveInstanceState){

        binding.buttonMisTarjetas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PrimerFragment.this)
                        .navigate(R.id.action_pimerFragment_to_idiomasFragment);
            }

        });
        binding.buttonNuevaTarjeta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PrimerFragment.this)
                        .navigate(R.id.action_pimerFragment_to_nuevaTarjetaFragment);
            }

        });

        binding.buttonNuevoIdioma.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PrimerFragment.this)
                        .navigate(R.id.action_pimerFragment_to_nuevoIdiomaFragment);
            }

        });



    }






}