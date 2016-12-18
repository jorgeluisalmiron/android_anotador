package com.example.jorgeluis.libretadealmacenero;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Libreta extends AppCompatActivity implements View.OnClickListener {

    private EditText nombre, contenido;
    private AppCompatImageButton buttonSave, buttonDelete, buttonShare, buttonPaint;
    private Context context = this;
    private boolean notaModificada;
    private boolean nuevaNota;
    private List<Archivo> notaList = new ArrayList<>();
    private int posicion;
    private int cantidadNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libreta);
        InitializeUI();

    }

    public void InitializeUI()
    {
        contenido = (EditText) findViewById(R.id.editText);
        nombre = (EditText) findViewById(R.id.editText2);
        buttonDelete = (AppCompatImageButton) findViewById(R.id.buttonDelete);
        buttonSave = (AppCompatImageButton) findViewById(R.id.buttonSave);
        buttonShare = (AppCompatImageButton) findViewById(R.id.buttonShare);
        buttonPaint = (AppCompatImageButton) findViewById(R.id.buttonPaint);

        buttonSave.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonShare.setOnClickListener(this);
        buttonPaint.setOnClickListener(this);

        Intent i = getIntent();
        posicion = i.getIntExtra("Posicion", -1);
        notaList = (List<Archivo>) i.getSerializableExtra("Lista");



        if (notaList==null)
        {
            cantidadNotas=0;
            notaList  = new ArrayList<>();
        }
        else
        {
            cantidadNotas=notaList.size();
        }


        if (posicion == -1) {
            nuevaNota = true;

        } else {
            nuevaNota = false;
            nombre.setText(notaList.get(posicion).getNombre());
            contenido.setBackgroundColor(notaList.get(posicion).getFondoDeNota());
            recuperar();
        }

        notaModificada = false;
        contenido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                notaModificada = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        contenido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
            }

        });


    }



    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_libreta);



        InitializeUI();


    }

    public boolean validarNombreArchivo(String titulo) {

        if (cantidadNotas > 0) {
            for (int i = 0; i < cantidadNotas; i++) {
                if (titulo.toString().equals(notaList.get(i).getNombre())) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public void grabar() {

        String nomarchivo = nombre.getText().toString();
        String nota = contenido.getText().toString();
        try {
            if (nomarchivo.equals(null) || nomarchivo.equals("")) {
                Toast.makeText(this, "Debe ingresar el título de la nota",
                        Toast.LENGTH_SHORT).show();
            } else if (!validarNombreArchivo(nomarchivo) && nuevaNota) {
                Toast.makeText(this, "Ya existe una nota con el mismo nombre.",
                        Toast.LENGTH_SHORT).show();
            } else {
                agregarAlIndice();
                OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                        nomarchivo, Activity.MODE_PRIVATE));
                archivo.write(nota);
                archivo.flush();
                archivo.close();

                finish();
            }

        } catch (IOException ioe) {
            Toast.makeText(this, "No se pudo grabar",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void recuperar() {
        String nomarchivo = nombre.getText().toString();
        try {
            nombre.setEnabled(false);
            InputStreamReader archivo = new InputStreamReader(
                    openFileInput(nomarchivo));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();

            String todo = "";
            while (linea != null) {
                todo = todo + linea + "\n";
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            contenido.setText(todo);

        } catch (IOException e) {
            Toast.makeText(this, "No se pudo leer",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void agregarAlIndice() throws IOException {

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        if (nuevaNota) {
            notaList.add(new Archivo(nombre.getText().toString(), ft.format(dNow).toString(),((ColorDrawable) contenido.getBackground()).getColor()));
        }
        else
        {
            notaList.get(posicion).setFondoDeNota(((ColorDrawable) contenido.getBackground()).getColor());
        }
        SerializableManager.saveSerializable(this, (Serializable) notaList, "index.idx");
    }


    public void volver(View v) {
        finish();
    }

    public void eliminar() {
        String nomarchivo = nombre.getText().toString();
        notaList.remove(posicion);
        SerializableManager.saveSerializable(this, (Serializable) notaList, "index.idx");
        String dir = getFilesDir().getAbsolutePath();
        File file = new File(dir, nomarchivo);
        boolean del = file.delete();
        if (!del)
        {
            Toast.makeText(this, "Error al eliminar la nota",
                   Toast.LENGTH_SHORT).show();
        }
        finish();
    }


    public void enviar() {

        try {
            if ((nombre.getText().toString() != null) && (contenido.getText().toString() != null)) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = contenido.getText().toString();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, nombre.getText().toString());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Enviar por"));
            } else {
                Toast.makeText(getApplicationContext(), "Se necesita un título y el texto de la nota para compartir", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error al compartir", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonSave:
                grabar();
                break;
            case R.id.buttonDelete:
                if (!nuevaNota) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setMessage("¿Desea eliminar la nota?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    eliminar();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                break;

            case R.id.buttonShare:
                enviar();
                break;

            case R.id.buttonPaint:

                CustomDialogClass cdd=new CustomDialogClass(this);
                cdd.closeOptionsMenu();
                cdd.show();

                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {

        if (notaModificada) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setMessage("¿Desea guardar la nota?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            grabar();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else {
            finish();
        }

    }
}
     /* public void agregarAlIndice()
    {
        String idxLinea = nombre.getText().toString();
        String file = "index.txt";
        String data = "";
        boolean found=false;
        try {

                   Date dNow = new Date( );
                    SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy hh:mm");
                    idxLinea.concat("|");
                    idxLinea.concat(ft.format(dNow));

                    InputStreamReader archivo = new InputStreamReader(
                            openFileInput(file));
                    BufferedReader br = new BufferedReader(archivo);
                    String linea = br.readLine();
                    while (linea != null) {

                         if (linea.equals(idxLinea)) {
                            found = true;
                            break;
                        }
                        linea=linea+"\n";
                        data=data+linea;

                    linea = br.readLine();
                }
                br.close();
                archivo.close();


                if (!found) {

                    OutputStreamWriter archivoOut = new OutputStreamWriter(openFileOutput(
                            "index.txt", Activity.MODE_PRIVATE));
                    data = data +idxLinea + "\n";
                    archivoOut.append(data);
                    archivoOut.flush();
                    archivoOut.close();
                }


            } catch (IOException e) {
                Toast.makeText(this, "No se pudo leer indice",
                        Toast.LENGTH_SHORT).show();
            }

    }*/


   /* public void quitarDelIndice()
        {
            String idxLinea = nombre.getText().toString();
            String file = "index.txt";
            String data="";
            boolean found=false;
            try {
                    InputStreamReader archivo = new InputStreamReader(
                            openFileInput(file));
                    BufferedReader br = new BufferedReader(archivo);
                    String linea = br.readLine();

                while (linea != null) {
                    found=true;
                    if (!linea.equals(idxLinea)) {
                        data=data+linea+"\n";
                    }

                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                OutputStreamWriter archivoOut = new OutputStreamWriter(openFileOutput(
                            "index.txt", Activity.MODE_PRIVATE));
                archivoOut.append(data);
                archivoOut.flush();
                archivoOut.close();


            } catch (IOException e) {
                Toast.makeText(this, "No se pudo leer indice",
                        Toast.LENGTH_SHORT).show();
            }
    }*/

