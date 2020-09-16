package com.example.andrew_butler_c196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import com.example.andrew_butler_c196.Entities.TermEntity;
import com.example.andrew_butler_c196.R;
import com.example.andrew_butler_c196.TermActivity;



import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder>{

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termTitle;
        private final TextView termStart;
        private final TextView termEnd;


        private TermViewHolder(View itemView) {
            super(itemView);
            termTitle = itemView.findViewById(R.id.TermTitle);
            termStart = itemView.findViewById(R.id.TermStart);
            termEnd = itemView.findViewById(R.id.TermEnd);

            itemView.setOnClickListener(v ->  {
                int position = getAdapterPosition();
                final TermEntity current = terms.get(position);
                Intent intent = new Intent(context, TermActivity.class);
                intent.putExtra("termId", current.getTermId());
                intent.putExtra("termName", current.getTermName());
                intent.putExtra("termStart", current.getStartDate());
                intent.putExtra("termEnd", current.getEndDate());
                intent.putExtra("position", position);
                context.startActivity(intent);
            });

        }
    }
    private final LayoutInflater inflater;
    private final Context context;
    private List<TermEntity> terms;

    public TermAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public TermViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = inflater.inflate(R.layout.term_list_item, parent, false);
        return new TermViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(TermViewHolder holder, int position) {
        if (terms != null) {
            final TermEntity current = terms.get(position);
            holder.termTitle.setText(current.getTermName());
            holder.termStart.setText(current.getStartDate());
            holder.termEnd.setText(current.getEndDate());
        } else {
            holder.termTitle.setText("No data");
            holder.termStart.setText("No data");
            holder.termEnd.setText("No data");
        }
    }

    public void setWords(List<TermEntity> words) {
        terms = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(terms != null) {
            return terms.size();
        } else {
            return 0;
        }
    }
}
