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
import android.widget.Toast;

import com.jaime_urresti.tpintegrador.bdd.AppDatabase;
import com.jaime_urresti.tpintegrador.databinding.FragmentNuevoIdiomaBinding;
import com.jaime_urresti.tpintegrador.modelo.Idioma;
import com.jaime_urresti.tpintegrador.util.ClaseUtil;

import java.util.concurrent.Executors;


public class NuevoIdiomaFragment extends Fragment {


    private FragmentNuevoIdiomaBinding binding;
    private Integer maxId;
    private View buscar;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        setHasOptionsMenu(true);



        super.onCreate(savedInstanceState);
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

        binding = FragmentNuevoIdiomaBinding.inflate(inflater,container,false);



        // Inflate the layout for this fragment
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle saveInstanceState){



        binding.buttonAgregarIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                maxId = 0;
                Idioma nuevoIdioma = new Idioma();


                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        maxId = AppDatabase.getInstance(getActivity().getApplicationContext()).idiomaDAO().getMaxID();
                    }
                };

                 Thread hilo = new Thread(runnable);

                 hilo.start();
                try {
                    hilo.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                if(maxId==null){
                    maxId=1;
                }else{
                    maxId++;
                }

                System.out.println(maxId);
                nuevoIdioma.setIdIdioma(maxId);
                nuevoIdioma.setNombreIdioma(binding.textInputNuevoIdioma.getText().toString());
                nuevoIdioma.setFechaCreacion(00000000);

                        Executors.newSingleThreadExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                AppDatabase.getInstance(getActivity().getApplicationContext()).idiomaDAO().insert(nuevoIdioma);

                            }
                        });

                ClaseUtil.alerta("idioma creada flama flama tigraso", "Idioma CREADo",getActivity());
                 getActivity().onBackPressed();



            }

        });



    }




}