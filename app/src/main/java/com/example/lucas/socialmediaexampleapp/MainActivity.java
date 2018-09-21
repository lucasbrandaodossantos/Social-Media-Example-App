package com.example.lucas.socialmediaexampleapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new Users()); // INICIANDO O FRAGMENT USERS COMO PADRÃO
    }


    /*MÉTODO PARA VERIFICAR O FRAGMENT QUE IRÁ INICIAR*/
    private boolean loadFragment (Fragment fragment){
        if (fragment!= null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout_main, fragment)
                    .commit();
            return true;
        }
        return false;
    }



    /*TODA A PARTE DO MENU INFERIOR*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
    Fragment fragment = null;

    switch (menuItem.getItemId()){
        case R.id.navigation_user:
            fragment = new Users();
            break;

        case R.id.navigation_photos:
            fragment = new Photos();
            break;
    }
        return loadFragment(fragment);
    }
}
