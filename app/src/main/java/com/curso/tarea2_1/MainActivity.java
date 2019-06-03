package com.curso.tarea2_1;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button[] celdas_juego = new Button[16];
    int[] backgroundIDs = new int[16];
    int[] values = {13,0,11,6,5,7,4,8,1,12,14,9,3,15,2,10};

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
        //int[] values = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //Fihser_Yates_Algorithm(values);
        checkIsValid(values);
        arrangeImagesOnGrid(values,celdas_juego,backgroundIDs);

        celdas_juego[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==1){
                    swapButtonBackgrounds(celdas_juego[0],0,celdas_juego[1],1);
                    swap(backgroundIDs,0,1);
                    swap(values,0,1);
                }
                if(findPositionX(values)==4){
                    swapButtonBackgrounds(celdas_juego[0],0,celdas_juego[4],4);
                    swap(backgroundIDs,0,4);
                    swap(values,0,4);
                }
            }
        });
        celdas_juego[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==0){
                    swapButtonBackgrounds(celdas_juego[1],1,celdas_juego[0],0);
                    swap(backgroundIDs,1,0);
                    swap(values,1,0);
                }
                if(findPositionX(values)==2){
                    swapButtonBackgrounds(celdas_juego[1],1,celdas_juego[2],2);
                    swap(backgroundIDs,1,2);
                    swap(values,1,2);
                }
                if(findPositionX(values)==5){
                    swapButtonBackgrounds(celdas_juego[1],1,celdas_juego[5],5);
                    swap(backgroundIDs,1,5);
                    swap(values,1,5);
                }

            }
        });

    }

    void swapButtonBackgrounds(Button x, int i, Button y, int j){
        int aux = backgroundIDs[i];
        Log.d("SWAP","x.ResourceBack:"+aux);
        y.setBackgroundResource(aux);
        x.setBackgroundResource(backgroundIDs[j]);
    }
    void arrangeImagesOnGrid(int[] array, Button[] buttons, int[] backgrounds){
        for(int i=0;i<16;i++){
            if(array[i]==1) {
                buttons[i].setBackgroundResource(R.drawable.uno);
                backgrounds[i] = R.drawable.uno;
                Log.d("RESOURCES","R.res.uno:"+R.drawable.uno);
            }else if(array[i]==2){
                buttons[i].setBackgroundResource(R.drawable.dos);
                backgrounds[i] = R.drawable.dos;
            }else if(array[i]==3){
                buttons[i].setBackgroundResource(R.drawable.tres);
                backgrounds[i] = R.drawable.tres;
            }else if(array[i]==4){
                buttons[i].setBackgroundResource(R.drawable.cuatro);
                backgrounds[i] = R.drawable.cuatro;
            }else if(array[i]==5){
                buttons[i].setBackgroundResource(R.drawable.cinco);
                backgrounds[i] = R.drawable.cinco;
            }else if(array[i]==6){
                buttons[i].setBackgroundResource(R.drawable.seis);
                backgrounds[i] = R.drawable.seis;
            }else if(array[i]==7){
                buttons[i].setBackgroundResource(R.drawable.siete);
                backgrounds[i] = R.drawable.siete;
            }else if(array[i]==8){
                buttons[i].setBackgroundResource(R.drawable.ocho);
                backgrounds[i] = R.drawable.ocho;
            }else if(array[i]==9){
                buttons[i].setBackgroundResource(R.drawable.nueve);
                backgrounds[i] = R.drawable.nueve;
            }else if(array[i]==10){
                buttons[i].setBackgroundResource(R.drawable.diez);
                backgrounds[i] = R.drawable.diez;
            }else if(array[i]==11){
                buttons[i].setBackgroundResource(R.drawable.once);
                backgrounds[i] = R.drawable.once;
            }else if(array[i]==12){
                buttons[i].setBackgroundResource(R.drawable.doce);
                backgrounds[i] = R.drawable.doce;
            }else if(array[i]==13){
                Log.d("RESOURCES","R.res.trece:"+R.drawable.trece);
                buttons[i].setBackgroundResource(R.drawable.trece);
                backgrounds[i] = R.drawable.trece;
            }else if(array[i]==14){
                buttons[i].setBackgroundResource(R.drawable.catorce);
                backgrounds[i] = R.drawable.catorce;
            }else if(array[i]==15){
                buttons[i].setBackgroundResource(R.drawable.quince);
                backgrounds[i] = R.drawable.quince;
            }else if(array[i]==0){
                backgrounds[i]=0;
            }
        }
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
        indice+=findRowX(array);
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
            if(findRowX(array)==2){
                swap(array,14,15);
            }
            else if(findRowX(array)==3){
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
    int findRowX(int[] array){
        int x=0;
        for(int i=0;i<16;i++){
            if(array[i]==0){
                x = (i%4)+1;
            }
        }
        //Log.d("MY_INT","posicion: "+x);
        return x;
    }

    int findPositionX(int values[]){
        int x=0;
        for(int i=0;i<16;i++) {
            if (values[i] == 0) {
                x = i;
            }
        }
        return x;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

}
