package com.example.andrew_butler_c196.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andrew_butler_c196.Entities.NoteEntity;
import com.example.andrew_butler_c196.NoteActivity;
import com.example.andrew_butler_c196.R;
import com.example.andrew_butler_c196.ViewModel.NoteViewModel;

import java.io.Serializable;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{

class NoteViewHolder extends RecyclerView.ViewHolder {
    private final TextView noteContent;
    private final Button shareButton;
    public NoteViewModel noteViewModel;

    private NoteViewHolder(View itemView) {
        super(itemView);
        noteContent = itemView.findViewById(R.id.NoteContent);
        shareButton = itemView.findViewById(R.id.ShareButton);
        shareButton.setOnClickListener(v ->  {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, noteContent.getText());

            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            context.startActivity(shareIntent);
        });

    }
}
    private final LayoutInflater inflater;
    private final Context context;
    private List<NoteEntity> notes;

    public NoteAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = inflater.inflate(R.layout.note_list_item, parent, false);
        return new NoteViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        if (notes != null) {
            final NoteEntity current = notes.get(position);
            holder.noteContent.setText(current.getNoteContent().toString());

        } else {
            holder.noteContent.setText("No data");
        }
    }

    public void setWords(List<NoteEntity> words) {
        notes = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(notes != null) {
            return notes.size();
        } else {
            return 0;
        }
    }
}
