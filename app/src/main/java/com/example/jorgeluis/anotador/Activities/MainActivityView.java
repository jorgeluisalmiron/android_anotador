package com.example.jorgeluis.anotador.Activities;

import android.animation.Animator;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorgeluis.anotador.Adapter.NotesAdapter;
import com.example.jorgeluis.anotador.Model.Note;
import com.example.jorgeluis.anotador.R;
import com.example.jorgeluis.anotador.Util.ActivityView;
import com.example.jorgeluis.anotador.Util.DividerItemDecoration;
import com.example.jorgeluis.anotador.Util.RecyclerItemClickListener;
import com.squareup.otto.Bus;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jorge Luis on 18/05/2017.
 */
public class MainActivityView extends ActivityView  {

    @BindView(R.id.TextViewListaVacia) TextView textViewEmptyList;
    @BindView(R.id.RecyclerView) RecyclerView recyclerView;
    @BindView(R.id.buttonNewNote) FloatingActionButton buttonNewNote;
    private Bus bus;
    private NotesAdapter notesAdapter;


    public MainActivityView(Activity activity, Bus bus) {
        super(activity);
        this.bus = bus;
        ButterKnife.bind(this, activity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider));
        notesAdapter = new NotesAdapter(activity);
        recyclerView.setAdapter(notesAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        setOnItemClick(position);


                    }
                })
        );

      //  fabIconAnimate();

    }

    @OnClick(R.id.buttonNewNote)
     public void nuevaNota() {
        bus.post(new clickNewNote());
    }


    public void setOnItemClick(int position) {
        bus.post(new clickListItem(position));

    }

    public void setTextViewEmptyListVisible(boolean b) {
        if (b) {
            textViewEmptyList.setVisibility(View.VISIBLE);
        }
        else
        {
            textViewEmptyList.setVisibility(View.INVISIBLE);
        }
    }


    public void setRecyclerViewVisible(boolean b) {
        if (b) {
            recyclerView.setVisibility(View.VISIBLE);
        }
        else
        {
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }


    public void showToastMessage(String s) {
        Toast.makeText(getActivity(), s,Toast.LENGTH_SHORT).show();
    }

    public void setNoteAdapter(List<Note> listOfNotes){

        notesAdapter.setAdapterList(listOfNotes);


    }


    public static  class clickListItem {
        public int position;
        public clickListItem(int position) {
            this.position=position;
        }
    }

    public static class clickNewNote {
    }
}




   /* public void fabIconAnimate(){

        buttonNewNote.setScaleX(0);
        buttonNewNote.setScaleY(0);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            final Interpolator interpolador = AnimationUtils.loadInterpolator(getActivity().getBaseContext(),
                    android.R.interpolator.fast_out_slow_in);

            buttonNewNote.animate()
                    .scaleX(1)
                    .scaleY(1)
                    .setInterpolator(interpolador)
                    .setDuration(60)
                    .setStartDelay(50)
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
                                    .setDuration(60)
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

*/