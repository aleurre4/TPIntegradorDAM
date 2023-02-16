package com.jaime_urresti.tpintegrador;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.jaime_urresti.tpintegrador.bdd.AppDatabase;
import com.jaime_urresti.tpintegrador.databinding.FragmentNuevaTarjetaBinding;
import com.jaime_urresti.tpintegrador.modelo.Idioma;
import com.jaime_urresti.tpintegrador.modelo.Tarjeta;
import com.jaime_urresti.tpintegrador.util.ClaseUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;


public class NuevaTarjetaFragment extends Fragment implements MediaPlayer.OnCompletionListener {

    private FragmentNuevaTarjetaBinding binding;
    private List<Idioma> idiomas;
    private Integer maxId;
    MediaRecorder recorder;
    MediaPlayer player;
    File archivo;
    private boolean grabando = false;




    // Requesting permission to RECORD_AUDIO


//    private boolean permissionToReadExternalAccepted = false;
//    private boolean permissionToAccesFineExternalAccepted = false;
//    private boolean permissionToAccesCoarseExternalAccepted = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                maxId = AppDatabase.getInstance(getActivity().getApplicationContext()).tarjetaDAO().getMaxID();
            }
        };

        Thread hilo = new Thread(runnable);

        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            maxId++;
        }catch (Exception e){

            maxId=1;
        }


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

        binding = FragmentNuevaTarjetaBinding.inflate(inflater,container,false);
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






        ArrayAdapter<CharSequence> adapterIdiomas = new ArrayAdapter(getActivity().getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,idiomas);


        binding.spinnerIdiomas.setAdapter(adapterIdiomas);



        // Inflate the layout for this fragment
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, Bundle saveInstanceState){



        binding.buttonCrearTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tarjeta nuevaTarjeta = new Tarjeta();

                Integer idIdioma = ((Idioma)binding.spinnerIdiomas.getSelectedItem()).getIdIdioma();

                System.out.println("ID DE NUEVA TARJETA: " + maxId);
                nuevaTarjeta.setIdTarjeta(maxId);
                nuevaTarjeta.setTextoOrignal(binding.editTextOriginal.getText().toString());
                nuevaTarjeta.setTextoTraduccion(binding.editTextTraduccion.getText().toString());
                nuevaTarjeta.setIdIdioma(idIdioma);
                nuevaTarjeta.setAudio("audio" + maxId + ".3gp");
                nuevaTarjeta.setFechaCreacion(00000000);

                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase.getInstance(getActivity().getApplicationContext()).tarjetaDAO().insert(nuevaTarjeta);

                    }
                });

                ClaseUtil.alerta("Tarjeta creada con exito", "TARJETA CREADA",getActivity());
                getActivity().onBackPressed();



            }

        });


        binding.floatingGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!grabando){
                    if(MainActivity.permissionToManageExternalAccepted && MainActivity.permissionToRecordAccepted){
                        grabar();
                        grabando= true;
                    }else {
                        System.out.println("NO HAY PERMISOS");
                    }
                }else{
                    detener();
                    grabando= false;
                }

            }

        });

        binding.floatingPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reproducir();

            }


        });


    }

    public void grabar() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        File path = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_MUSIC).getPath());
        System.out.println(path);



        File file = new File(path,("audio" + maxId + ".3gp"));

        try {
            path.mkdir();
        }catch (Exception e){
        }


        try {
            archivo = file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        recorder.setOutputFile(archivo.getAbsolutePath());
        recorder.setAudioEncodingBitRate(16);
        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.start();
        binding.comoSuenaText.setText("Grabando");
        binding.buttonCrearTarjeta.setEnabled(false);
        binding.editTextTraduccion.setEnabled(false);
        binding.editTextOriginal.setEnabled(false);



    }

    public void detener() {
        recorder.stop();
        recorder.release();
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        try {
            player.setDataSource(archivo.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        binding.editTextOriginal.setEnabled(true);
        binding.editTextTraduccion.setEnabled(true);
        binding.buttonCrearTarjeta.setEnabled(true);
        binding.comoSuenaText.setText("Volver a intentarlo");
    }

    public void reproducir() {
        player.start();
        binding.editTextTraduccion.setEnabled(false);
        binding.editTextOriginal.setEnabled(false);
        binding.buttonCrearTarjeta.setEnabled(false);
        binding.floatingGrabar.setEnabled(false);
        binding.floatingPlay.setEnabled(false);
        binding.comoSuenaText.setText("Reproduciendo");
    }

    public void onCompletion(MediaPlayer mp) {
        binding.editTextTraduccion.setEnabled(true);
        binding.editTextOriginal.setEnabled(true);
        binding.floatingGrabar.setEnabled(true);
        binding.buttonCrearTarjeta.setEnabled(true);
        binding.floatingPlay.setEnabled(true);
        binding.comoSuenaText.setText("Volver a grabar?");
    }




}