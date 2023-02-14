package com.jaime_urresti.tpintegrador;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.jaime_urresti.tpintegrador.bdd.AppDatabase;
import com.jaime_urresti.tpintegrador.databinding.ActivityMainBinding;
import com.jaime_urresti.tpintegrador.databinding.FragmentPimerBinding;


public class PrimerFragment extends Fragment {

    private FragmentPimerBinding binding;
    private MenuItem buscar;
    private Menu menu;
    private Integer cantTarjetas;
    private Integer cantIdiomas;
    private Integer nivel;

    @Override
    public void onCreate(Bundle savedInstanceState) {



        setHasOptionsMenu(true);


        super.onCreate(savedInstanceState);

    }


    public static Integer calcularNivel(Integer cantIdiomas,Integer cantTarjetas){

        if(cantIdiomas == 0 || cantTarjetas == 0){
            return 0;
        }
        else if(cantIdiomas >= 5 && cantTarjetas > 20){
            return 4;
        }
        else if(cantIdiomas >= 2 && cantTarjetas > 8){
            return 3;
        }
        else if(cantIdiomas == 1 && cantTarjetas > 10){
            return 2;
        }

        else if(cantIdiomas == 1 && cantTarjetas > 1){
            return 1;
        }



return 0;



    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.itemBuscarMenu);
        if(item!=null)
            item.setVisible(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPimerBinding.inflate(inflater,container,false);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                cantTarjetas = AppDatabase.getInstance(getActivity().getApplicationContext()).tarjetaDAO().getCantTarjetas();
                cantIdiomas = AppDatabase.getInstance(getActivity().getApplicationContext()).idiomaDAO().getCantIdiomas();
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        binding.textNumIdiomas.setText(cantIdiomas + "");

        binding.textNumTarjetas.setText(cantTarjetas + "");


        nivel = calcularNivel(cantTarjetas,cantIdiomas);


        switch (nivel){

            case 0:
                binding.textNivel.setText("Que esperas para comenzar?");
            case 1:
                binding.textNivel.setText("Nivel principiante");
                break;
            case 2:
                binding.textNivel.setText("Nivel intermedio");
                break;
            case 3:
                binding.textNivel.setText("Nivel pro");
                break;
            case 4:
                binding.textNivel.setText("Nivel MESSI DE IDIOMAS");
                break;


        };

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