package com.curso.tarea2_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static void Fihser_Yates_Algorithm(int[] array) {
        int n = array.length;
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomValue = i + random.nextInt(n - i);
            int randomElement = array[randomValue];
            array[randomValue] = array[i];
            array[i] = randomElement;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button celdas_juego[] = new Button[16];
        celdas_juego[0] = findViewById(R.id.pos1_1);
        celdas_juego[1] = findViewById(R.id.pos1_2);
        celdas_juego[2] = findViewById(R.id.pos1_3);
        celdas_juego[3] = findViewById(R.id.pos1_4);
        celdas_juego[4] = findViewById(R.id.pos2_1);
        celdas_juego[5] = findViewById(R.id.pos2_2);
        celdas_juego[6] = findViewById(R.id.pos2_3);
        celdas_juego[7] = findViewById(R.id.pos2_4);
        celdas_juego[8] = findViewById(R.id.pos3_1);
        celdas_juego[9] = findViewById(R.id.pos3_2);
        celdas_juego[10] = findViewById(R.id.pos3_3);
        celdas_juego[11] = findViewById(R.id.pos3_4);
        celdas_juego[12] = findViewById(R.id.pos4_1);
        celdas_juego[13] = findViewById(R.id.pos4_2);
        celdas_juego[14] = findViewById(R.id.pos4_3);
        celdas_juego[15] = findViewById(R.id.pos4_4);
        int[] values = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //int[] values = {13,10,11,6,5,7,4,8,1,12,14,9,3,15,2,0};
        Fihser_Yates_Algorithm(values);
        checkIsValid(values);
    }
    int computeIndex(int[] array){
        int indice=0;
        for(int i=0;i<16;i++){
            for(int j=i+1;j<16;j++) {
                if (array[i] > array[j] && array[j] != 0) {
                    indice++;
                }
            }
        }
        indice+=findPositionX(array);
        //Log.d("MY_INT","indice: "+indice);
        return indice;
    }
    void swap(int[] array, int i, int j){
        int aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }
    void checkIsValid(int[] array){
        int x = computeIndex(array);
        if(x%2 == 1){
            //Log.d("MY_INT","Es impar, recalculando");
            if(findPositionX(array)==2){
                swap(array,14,15);
            }
            else if(findPositionX(array)==3){
                swap(array,13,15);
            }
            else{
                swap(array,13,14);
            }
            /*for(int i=0;i<16;i++){
                //Log.d("MY_INT","element is: "+array[i]);
            }*/
            //x = computeIndex(array);
            //Log.d("MY_INT","indice: "+x);
        }
    }
    int findPositionX(int[] array){
        int x=0;
        for(int i=0;i<16;i++){
            if(array[i]==0){
                x = (i%4)+1;
            }
        }
        //Log.d("MY_INT","posicion: "+x);
        return x;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

}
