package com.jaime_urresti.tpintegrador;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.jaime_urresti.tpintegrador.databinding.ActivityMainBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MaterialToolbar toolbar;

    public final static String CHANEL_ID = "canal";
    private PendingIntent pendingIntent;
//    private  NavController navController;
    ActivityResultLauncher<String []> permisionResultLauncher;
    public static boolean permissionToRecordAccepted = false;
    public static boolean permissionToManageExternalAccepted = false;

    private BroadcastReceiver receiver;



    private boolean isRecordAudioPermissionGranted()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) ==
                    PackageManager.PERMISSION_GRANTED) {
                permissionToRecordAccepted = true;
                permissionToManageExternalAccepted = true;
                return true;
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                    Toast.makeText(this,
                            "App required access to audio", Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO
                        },1);
                return false;
            }

        } else {
            permissionToRecordAccepted = true;
            permissionToManageExternalAccepted = true;
            return true;
        }
    }

    private void requestPermission(){


        permissionToRecordAccepted = ContextCompat.checkSelfPermission(
                this, Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED;

        permissionToManageExternalAccepted = ContextCompat.checkSelfPermission(
                this,Manifest.permission.MANAGE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED;



        List<String> permisionRequest = new ArrayList<>();

        if(!permissionToManageExternalAccepted){

            permisionRequest.add(Manifest.permission.MANAGE_EXTERNAL_STORAGE);


        }
        if(!permissionToRecordAccepted){

            permisionRequest.add(Manifest.permission.RECORD_AUDIO);


        }

        if(!permisionRequest.isEmpty()){


            System.out.println(permisionRequest);

            permisionResultLauncher.launch(permisionRequest.toArray(new String[0]));

        }




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createNotificationChannel();
//        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        permisionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                result -> {

                    if(result.get(Manifest.permission.RECORD_AUDIO)!=null){

                        permissionToRecordAccepted = result.get(Manifest.permission.RECORD_AUDIO);
                    }

                    if(result.get(Manifest.permission.MANAGE_EXTERNAL_STORAGE)!=null){

                        permissionToManageExternalAccepted = result.get(Manifest.permission.MANAGE_EXTERNAL_STORAGE);
                    }

                });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        toolbar = binding.materialToolbar;



        setSupportActionBar(toolbar);


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation);
        NavController navCo = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration
                .Builder(navCo.getGraph())
                .build();
        NavigationUI.setupWithNavController(toolbar, navCo, appBarConfiguration);





        isRecordAudioPermissionGranted();

        // your oncreate code should be

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);


        receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                setPendingIntent(MainActivity.class);
                System.out.println("HOLU");
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MainActivity.this,CHANEL_ID)
                                .setSmallIcon(android.R.drawable.sym_action_chat)
                                .setContentTitle("Modo avion?")
                                .setContentText("Es una buena oportunidad para repasar tus tarjetas!")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent);;


                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
                managerCompat.notify(0, mBuilder.build());
                System.out.println("HOLU2");



            }


        };


        registerReceiver(receiver, filter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
//    @Override
//    protected void onDestroy() {
//        if (receiver != null) {
//            unregisterReceiver(receiver);
//            receiver = null;
//        }
//        super.onDestroy();
//    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "nuevo";
            String description = "Canal para notificacion)";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel =
                    new NotificationChannel(CHANEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showNotifiacion(){
        NotificationChannel channel = null;

            channel = new NotificationChannel(CHANEL_ID,"NEW", NotificationManager.IMPORTANCE_DEFAULT);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            manager.createNotificationChannel(channel);

        showNewNotificacion();
    }

    private void showNewNotificacion(){

        setPendingIntent(MainActivity.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANEL_ID)
                .setSmallIcon(R.drawable.baseline_star_rate_white_24dp)
                .setContentTitle("Viajando?")
                .setContentText("Es una buena oportunidad para repasar tus tarjetas pa")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).
                setContentIntent(pendingIntent);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());

    }


    private void setPendingIntent(Class<?> classAct){

        Intent intent =  new Intent(this, classAct);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(classAct);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1,PendingIntent.FLAG_UPDATE_CURRENT);



    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

//        NavHostFragment.findNavController(PrimerFragment.this)

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
