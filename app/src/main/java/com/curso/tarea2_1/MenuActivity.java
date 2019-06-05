package com.curso.tarea2_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        MainActivity.LoadGameListener();
    }
}
