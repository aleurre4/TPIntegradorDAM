package com.jaime_urresti.tpintegrador;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jaime_urresti.tpintegrador.bdd.AppDatabase;
import com.jaime_urresti.tpintegrador.databinding.FragmentDetalleTarjetaBinding;
import com.jaime_urresti.tpintegrador.modelo.Tarjeta;
import com.jaime_urresti.tpintegrador.util.ClaseUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DetalleTarjetaFragment extends Fragment implements MediaPlayer.OnCompletionListener {


    private FragmentDetalleTarjetaBinding binding;
    private Integer idTarjeta;
    private Tarjeta tarjeta;
    private boolean editar;
    private MediaPlayer player;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);

        idTarjeta = getArguments().getInt("id");
        editar = false;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tarjeta = AppDatabase.getInstance(getActivity().getApplicationContext()).tarjetaDAO().loadByIdTarjeta(idTarjeta);
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(tarjeta == null){
            tarjeta = new Tarjeta();
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetalleTarjetaBinding.inflate(inflater,container,false);


        binding.editarTarjetaOriginal.setText(tarjeta.getTextoOrignal());
        binding.editarTarjetaTraduccion.setText(tarjeta.getTextoTraduccion());


        binding.buttonEditarTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             if(editar){
                 editar=false;

                 binding.editarTarjetaTraduccion.setEnabled(false);
                 binding.editarTarjetaOriginal.setEnabled(false);


                 tarjeta.setTextoOrignal(binding.editarTarjetaOriginal.getText().toString());
                 tarjeta.setTextoTraduccion(binding.editarTarjetaTraduccion.getText().toString());

                 Runnable runnable = new Runnable() {
                     @Override
                     public void run() {
                   AppDatabase.getInstance(getActivity().getApplicationContext()).tarjetaDAO().update(tarjeta);
                     }
                 };
                 Thread hilo = new Thread(runnable);
                 hilo.start();
                 try {
                     hilo.join();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }

                 ClaseUtil.alerta("Tarjeta updateada perro", "renovada tarjeta",getActivity());


                 binding.buttonEditarTarjeta.setText("Editar tarjeta");
//                 Bundle bundle = new Bundle();
//                 bundle.putInt("id",tarjeta.getIdIdioma());
//                 Navigation.findNavController((View)view.getParent()).navigate(
//                         R.id.action_detalleTarjetaFragment_to_tarjetasFragment,bundle);
                     getActivity().onBackPressed();
             }

             else{
                 editar=true;
                 binding.editarTarjetaTraduccion.setEnabled(true);
                 binding.editarTarjetaOriginal.setEnabled(true);
                 binding.buttonEditarTarjeta.setText("Confirmar");
             }




            }
        });

        binding.floatingEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                      AppDatabase.getInstance(getActivity().getApplicationContext()).tarjetaDAO().delete(tarjeta);
                    }
                };
                Thread hilo = new Thread(runnable);
                hilo.start();
                try {
                    hilo.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ClaseUtil.alerta("Tarjeta eliminada tigreee", "rip tarjeta",getActivity());



//                Bundle bundle = new Bundle();
//                bundle.putInt("id",tarjeta.getIdIdioma());
//                Navigation.findNavController((View)view.getParent()).navigate(
//                        R.id.action_detalleTarjetaFragment_to_tarjetasFragment,bundle);

                getActivity().onBackPressed();


            }
        });


        binding.floatingDetallePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir();

            }})



        ;



        return binding.getRoot();
    }

    public void reproducir() {

        player = new MediaPlayer();
        player.setOnCompletionListener(this);
//        File path = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_MUSIC).getPath());
        try {
            player.setDataSource(getActivity().getExternalFilesDir(Environment.DIRECTORY_MUSIC).getPath() + "/" + tarjeta.getAudio());
            player.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
        binding.buttonEditarTarjeta.setEnabled(false);
        binding.floatingEliminar.setEnabled(false);
        binding.floatingDetallePlay.setEnabled(false);

    }

    public void onCompletion(MediaPlayer mp) {
        binding.buttonEditarTarjeta.setEnabled(true);
        binding.floatingEliminar.setEnabled(true);
        binding.floatingDetallePlay.setEnabled(true);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.itemBuscarMenu);
        if(item!=null)
            item.setVisible(false);
    }
}