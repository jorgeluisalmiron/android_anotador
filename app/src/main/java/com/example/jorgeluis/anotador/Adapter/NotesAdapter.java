package com.example.jorgeluis.anotador.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jorgeluis.anotador.Model.Note;
import com.example.jorgeluis.anotador.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jorge Luis on 14/09/2016.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    private Context context;
    private List<Note> listOfNotes =null;

    public NotesAdapter(Context context, List<Note> listOfNotes)
    {
        this.context = context;
        this.listOfNotes = listOfNotes;

    }

    public NotesAdapter(Context context)
    {
        this.context = context;
    }

    public void setAdapterList(List<Note> noteList)
    {
        this.listOfNotes = noteList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.getTextViewNoteTitle().setText(listOfNotes.get(position).getName());
        holder.getTextViewDateTime().setText(listOfNotes.get(position).getDateTime());
    }

    @Override
    public int getItemCount() {
        if (listOfNotes == null) {
            return 0;
        } else {
            return listOfNotes.size();

        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.TextViewDateTime) TextView textViewDateTime;
        @BindView(R.id.TextViewNoteTitle) TextView textViewNoteTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public TextView getTextViewDateTime() {
            return textViewDateTime;
        }

        public void setTextViewDateTime(TextView textViewDateTime) {
            this.textViewDateTime = textViewDateTime;
        }

        public TextView getTextViewNoteTitle() {
            return textViewNoteTitle;
        }

        public void setTextViewNoteTitle(TextView textViewNoteTitle) {
            this.textViewNoteTitle = textViewNoteTitle;
        }
    }
}
