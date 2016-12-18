package com.example.jorgeluis.libretadealmacenero;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.List;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private FloatingActionButton buttonNewNote;
    private RecyclerView recyclerView;
    private List<Archivo> listaDeNotas;
    private ArchivosAdapter listaDeNotasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.TextViewListaVacia);
        buttonNewNote = (FloatingActionButton) findViewById(R.id.buttonNewNote);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.validarIndex();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, R.drawable.divider));

        listaDeNotasAdapter = new ArchivosAdapter(this);
        recyclerView.setAdapter(listaDeNotasAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                            abrirNota(position);

                    }
                })
        );

        fabAnimate();
        buttonNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuevaNota(v);
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        listaDeNotas = null;
        listaDeNotas = SerializableManager.readSerializable(this,"index.idx");
        listaDeNotasAdapter.setAdapterList(listaDeNotas);

        if (listaDeNotas==null){
            tv.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
        else {

            if (listaDeNotas.size() == 0) {

                tv.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);

            } else {
                tv.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }
        fabAnimate();

    }

    public void nuevaNota(View v) {
        Intent i = new Intent(this, Libreta.class);
        i.putExtra("Posicion",-1);
        i.putExtra("Lista", (Serializable) listaDeNotas);
        startActivity(i);
    }

    public void abrirNota(int position) {
        Intent i = new Intent(this, Libreta.class);
        i.putExtra("Posicion",position);
        i.putExtra("Lista", (Serializable) listaDeNotas);
        startActivity(i);
    }
    public void validarIndex() {
        String[] archivos = fileList();
        if (!existe(archivos, "index.idx")) {
            try {
                OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("index.idx", Activity.MODE_PRIVATE));

                archivo.flush();
                archivo.close();

            } catch (IOException e) {

                Toast.makeText(this, "Error al crear el índice",
                        Toast.LENGTH_SHORT).show();

            }

        }
    }

    private boolean existe(String[] archivos, String archbusca) {
        for (int f = 0; f < archivos.length; f++)
            if (archbusca.equals(archivos[f]))
                return true;
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_about) {

            Intent i = new Intent(this, About.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void fabAnimate(){

        buttonNewNote.setScaleX(0);
        buttonNewNote.setScaleY(0);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            final Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                    android.R.interpolator.fast_out_slow_in);

            buttonNewNote.animate()
                    .scaleX(1)
                    .scaleY(1)
                    .setInterpolator(interpolador)
                    .setDuration(600)
                    .setStartDelay(500)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            buttonNewNote.animate()
                                    .scaleY(1)
                                    .scaleX(1)
                                    .setInterpolator(interpolador)
                                    .setDuration(600)
                                    .start();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
        }
        else
        {
            buttonNewNote.setScaleX(1);
            buttonNewNote.setScaleY(1);
        }
    }
    

}
