package com.curso.tarea2_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuActivity extends AppCompatActivity {
    Button new_game;
    Button load_game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        MainActivity.NewGameListener();

    }

    public void CargarPartida(View view){
        try{
            FileInputStream fis = getApplicationContext().openFileInput("respaldo_partida.txt");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            MainActivity.LoadGameListener();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "No existe partida creada previamente.", Toast.LENGTH_SHORT).show();
        }
    }
}
