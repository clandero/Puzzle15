package com.curso.tarea2_1;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.Dialog;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int hayquecargarpartida=0;
    Button[] celdas_juego = new Button[16];
    Button guardarJuego;
    TextView marcador;
    int[] backgroundIDs = new int[16];
    int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
    int numero_jugadas;

    boolean checkIfPuzzleIsSolved(int[] array){
        int sum=0;
        for(int i=0;i<16;i++){
            if(i+1 == array[i]){
                sum=sum+1;
            }
            else if(i==15 && array[i]==0){
                sum=sum+1;
            }
        }
        if(sum==16){
            return true;
        }
        else{
            return false;
        }
    }

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

    private void saveGame(String filename, int[] content, int actualScore){
        String file = filename+".txt";
        try{
            FileOutputStream fos = getApplicationContext().openFileOutput(file, MODE_PRIVATE);
            fos.write((actualScore +" ").getBytes());
            for(int i=0;i<16;i++) {
                fos.write(Integer.toString(content[i]).getBytes());
                fos.write(" ".getBytes());
            }
            fos.close();
            Toast.makeText(this,"La partida ha sido guardada",Toast.LENGTH_SHORT).show();
        }catch(FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this,"Archivo no disponible",Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this,"Error al guardar partida",Toast.LENGTH_SHORT).show();
        }
    }

    public static void LoadGameListener(){
        hayquecargarpartida = 1;
    }

    public static void NewGameListener(){
        hayquecargarpartida = 0;
    }

    private void loadGame(String file) {
        int bytesLeidos;
        byte[] buffer = new byte[1024];
        try {
            FileInputStream fis = getApplicationContext().openFileInput(file+".txt");
            while((bytesLeidos = fis.read(buffer))>0){
                String s = new String(buffer);
                int[] numbers = new int[17];
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(s);
                int i=0;
                while(m.find()) {
                    numbers[i] = Integer.parseInt(m.group());
                    i++;
                }
                numero_jugadas = numbers[0];
                for(i=0;i<16;i++){
                    values[i] = numbers[i+1];
                }
            }
            fis.close();
        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al cargar partida", Toast.LENGTH_SHORT).show();
        }

    }

    public void MyCustomAlertDialog(){
        final Dialog MyDialog = new Dialog(MainActivity.this);
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.popup_image);
        Button close = MyDialog.findViewById(R.id.close);
        close.setEnabled(true);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
            }
        });
        MyDialog.show();
    }

    void moveImageinGrid(Button x, int i, Button y, int j){
        swapButtonBackgrounds(x,i,y,j);
        swap(backgroundIDs,i,j);
        swap(values,i,j);
        numero_jugadas++;
        marcador.setText(Integer.toString(numero_jugadas));
        if(checkIfPuzzleIsSolved(values)){
            Toast.makeText(getApplicationContext(),"Has resuelto el Puzzle15, felicitaciones!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(hayquecargarpartida==1){
            loadGame("respaldo_partida");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Button show = findViewById(R.id.show_image);
            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyCustomAlertDialog();
                }
            });
        }
        if(hayquecargarpartida==0){
            numero_jugadas=0;
        }
        marcador = findViewById(R.id.score);
        marcador.setText(Integer.toString(numero_jugadas));
        guardarJuego = findViewById(R.id.save_game);
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
        if(hayquecargarpartida==0){
            Fihser_Yates_Algorithm(values);
            checkIsValid(values);
        }
        arrangeImagesOnGrid(values,celdas_juego,backgroundIDs);
        guardarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = "respaldo_partida";
                saveGame(filename,values,numero_jugadas);
            }
        });

        celdas_juego[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==1){
                    moveImageinGrid(celdas_juego[0],0,celdas_juego[1],1);
                }
                if(findPositionX(values)==4){
                    moveImageinGrid(celdas_juego[0],0,celdas_juego[4],4);
                }
            }
        });
        celdas_juego[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==0){
                    moveImageinGrid(celdas_juego[1],1,celdas_juego[0],0);
                }
                if(findPositionX(values)==2){
                    moveImageinGrid(celdas_juego[1],1,celdas_juego[2],2);
                }
                if(findPositionX(values)==5){
                    moveImageinGrid(celdas_juego[1],1,celdas_juego[5],5);
                }
            }
        });
        celdas_juego[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==1){
                    moveImageinGrid(celdas_juego[2],2,celdas_juego[1],1);
                }
                if(findPositionX(values)==3){
                    moveImageinGrid(celdas_juego[2],2,celdas_juego[3],3);
                }
                if(findPositionX(values)==6){
                    moveImageinGrid(celdas_juego[2],2,celdas_juego[6],6);
                }
            }
        });
        celdas_juego[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==2){
                    moveImageinGrid(celdas_juego[3],3,celdas_juego[2],2);
                }
                if(findPositionX(values)==7){
                    moveImageinGrid(celdas_juego[3],3,celdas_juego[7],7);
                }
            }
        });
        celdas_juego[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==0){
                    moveImageinGrid(celdas_juego[4],4,celdas_juego[0],0);
                }
                if(findPositionX(values)==5){
                    moveImageinGrid(celdas_juego[4],4,celdas_juego[5],5);
                }
                if(findPositionX(values)==8){
                    moveImageinGrid(celdas_juego[4],4,celdas_juego[8],8);
                }
            }
        });
        celdas_juego[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==1){
                    moveImageinGrid(celdas_juego[5],5,celdas_juego[1],1);
                }
                if(findPositionX(values)==4){
                    moveImageinGrid(celdas_juego[5],5,celdas_juego[4],4);
                }
                if(findPositionX(values)==6){
                    moveImageinGrid(celdas_juego[5],5,celdas_juego[6],6);
                }
                if(findPositionX(values)==9){
                    moveImageinGrid(celdas_juego[5],5,celdas_juego[9],9);
                }
            }
        });
        celdas_juego[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==2){
                    moveImageinGrid(celdas_juego[6],6,celdas_juego[2],2);
                }
                if(findPositionX(values)==5){
                    moveImageinGrid(celdas_juego[6],6,celdas_juego[5],5);
                }
                if(findPositionX(values)==7){
                    moveImageinGrid(celdas_juego[6],6,celdas_juego[7],7);
                }
                if(findPositionX(values)==10){
                    moveImageinGrid(celdas_juego[6],6,celdas_juego[10],10);
                }
            }
        });
        celdas_juego[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==3){
                    moveImageinGrid(celdas_juego[7],7,celdas_juego[3],3);
                }
                if(findPositionX(values)==6){
                    moveImageinGrid(celdas_juego[7],7,celdas_juego[6],6);
                }
                if(findPositionX(values)==11){
                    moveImageinGrid(celdas_juego[7],7,celdas_juego[11],11);
                }
            }
        });
        celdas_juego[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==4){
                    moveImageinGrid(celdas_juego[8],8,celdas_juego[4],4);
                }
                if(findPositionX(values)==9){
                    moveImageinGrid(celdas_juego[8],8,celdas_juego[9],9);
                }
                if(findPositionX(values)==12){
                    moveImageinGrid(celdas_juego[8],8,celdas_juego[12],12);
                }
            }
        });
        celdas_juego[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==8){
                    moveImageinGrid(celdas_juego[9],9,celdas_juego[8],8);
                }
                if(findPositionX(values)==5){
                    moveImageinGrid(celdas_juego[9],9,celdas_juego[5],5);
                }
                if(findPositionX(values)==10){
                    moveImageinGrid(celdas_juego[9],9,celdas_juego[10],10);
                }
                if(findPositionX(values)==13){
                    moveImageinGrid(celdas_juego[9],9,celdas_juego[13],13);
                }
            }
        });
        celdas_juego[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==6){
                    moveImageinGrid(celdas_juego[10],10,celdas_juego[6],6);
                }
                if(findPositionX(values)==9){
                    moveImageinGrid(celdas_juego[10],10,celdas_juego[9],9);
                }
                if(findPositionX(values)==11){
                    moveImageinGrid(celdas_juego[10],10,celdas_juego[11],11);
                }
                if(findPositionX(values)==14){
                    moveImageinGrid(celdas_juego[10],10,celdas_juego[14],14);
                }
            }
        });
        celdas_juego[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==7){
                    moveImageinGrid(celdas_juego[11],11,celdas_juego[7],7);
                }
                if(findPositionX(values)==10){
                    moveImageinGrid(celdas_juego[11],11,celdas_juego[10],10);
                }
                if(findPositionX(values)==15){
                    moveImageinGrid(celdas_juego[11],11,celdas_juego[15],15);
                }
            }
        });
        celdas_juego[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==8){
                    moveImageinGrid(celdas_juego[12],12,celdas_juego[8],8);
                }
                if(findPositionX(values)==13){
                    moveImageinGrid(celdas_juego[12],12,celdas_juego[13],13);
                }
            }
        });
        celdas_juego[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==12){
                    moveImageinGrid(celdas_juego[13],13,celdas_juego[12],12);
                }
                if(findPositionX(values)==9){
                    moveImageinGrid(celdas_juego[13],13,celdas_juego[9],9);
                }
                if(findPositionX(values)==14){
                    moveImageinGrid(celdas_juego[13],13,celdas_juego[14],14);
                }
            }
        });
        celdas_juego[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==10){
                    moveImageinGrid(celdas_juego[14],14,celdas_juego[10],10);
                }
                if(findPositionX(values)==13){
                    moveImageinGrid(celdas_juego[14],14,celdas_juego[13],13);
                }
                if(findPositionX(values)==15){
                    moveImageinGrid(celdas_juego[14],14,celdas_juego[15],15);
                }
            }
        });
        celdas_juego[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findPositionX(values)==11){
                    moveImageinGrid(celdas_juego[15],15,celdas_juego[11],11);
                }
                if(findPositionX(values)==14){
                    moveImageinGrid(celdas_juego[15],15,celdas_juego[14],14);
                }
            }
        });
    }

    void swapButtonBackgrounds(Button x, int i, Button y, int j){
        int aux = backgroundIDs[i];
        y.setBackgroundResource(aux);
        x.setBackgroundResource(backgroundIDs[j]);

    }
    void arrangeImagesOnGrid(int[] array, Button[] buttons, int[] backgrounds){
        for(int i=0;i<16;i++){
            if(array[i]==1) {
                buttons[i].setBackgroundResource(R.drawable.uno);
                backgrounds[i] = R.drawable.uno;
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
                buttons[i].setBackgroundResource(R.drawable.trece);
                backgrounds[i] = R.drawable.trece;
            }else if(array[i]==14){
                buttons[i].setBackgroundResource(R.drawable.catorce);
                backgrounds[i] = R.drawable.catorce;
            }else if(array[i]==15){
                buttons[i].setBackgroundResource(R.drawable.quince);
                backgrounds[i] = R.drawable.quince;
            }else if(array[i]==0){
                buttons[i].setBackgroundResource(R.drawable.dieciseis);
                backgrounds[i]=R.drawable.dieciseis;
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
            if(findRowX(array)==2){
                swap(array,14,15);
            }
            else if(findRowX(array)==3){
                swap(array,13,15);
            }
            else{
                swap(array,13,14);
            }

        }
    }
    int findRowX(int[] array){
        int x=0;
        for(int i=0;i<16;i++){
            if(array[i]==0){
                if(i>=0 && i<=3){
                    x=1;
                }else if(i>=4 && i<=7){
                    x=2;
                }else if(i>=8 && i<=11){
                    x=3;
                }else if(i>=12 && i<=15){
                    x=4;
                }
            }
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.instructions){
            Intent myintent = new Intent(this,Instructions.class);
            startActivity(myintent);
            return false;
        }
        else if(id == R.id.credits){
            Intent myintent = new Intent(this,Creditos.class);
            startActivity(myintent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    //Método que guarda el estado actual de la aplicación, para ser restaurado posteriormente
    @Override
    protected void onSaveInstanceState(Bundle outState){
        //outState.putString("numero_telefono",input.getText().toString());
        outState.putIntArray("posiciones",values);
        outState.putInt("marcador",numero_jugadas);
        //outState.putIntArray("fondos_botonoes",backgroundIDs);
        super.onSaveInstanceState(outState);
    }
    //Método que restaura el estado de la aplicación guardado previamente
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        values = savedInstanceState.getIntArray("posiciones");
        numero_jugadas = savedInstanceState.getInt("marcador");
        marcador.setText(Integer.toString(numero_jugadas));
        //backgroundIDs = savedInstanceState.getIntArray("fondos_botones");
        arrangeImagesOnGrid(values,celdas_juego,backgroundIDs);

    }
}
