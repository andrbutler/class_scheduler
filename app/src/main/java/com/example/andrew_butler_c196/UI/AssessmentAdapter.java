package com.example.andrew_butler_c196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.andrew_butler_c196.AssessmentActivity;
import com.example.andrew_butler_c196.Entities.AssessmentEntity;
import com.example.andrew_butler_c196.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder>{

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentTitle;
        private final TextView assessmentDueDate;

        private AssessmentViewHolder(View itemView) {
            super(itemView);
            assessmentTitle = itemView.findViewById(R.id.AssessmentTitle);
            assessmentDueDate = itemView.findViewById(R.id.AssessmentDueDate);

            itemView.setOnClickListener(v ->  {
                int position = getAdapterPosition();
                final AssessmentEntity current = assessments.get(position);
                Intent intent = new Intent(context, AssessmentActivity.class);
                intent.putExtra("assessmentName", current.getAssessmentTitle());
                intent.putExtra("assessmentDueDate", current.getDueDate());
                intent.putExtra("assessmentType", current.getAssessmentType());
                intent.putExtra("position", position);
                intent.putExtra("courseId", current.getCourseId());
                intent.putExtra("assessmentId", current.getAssessmentId());
                intent.putExtra("expectedCompletionDate", current.getExpectedCompletionDate());
                intent.putExtra("assessmentStartDate", current.getAssessmentStartDate());
                context.startActivity(intent);
            });

        }
    }
    private final LayoutInflater inflater;
    private final Context context;
    private List<AssessmentEntity> assessments;

    public AssessmentAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public AssessmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = inflater.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AssessmentViewHolder holder, int position) {
        if (assessments != null) {
            final AssessmentEntity current = assessments.get(position);
            holder.assessmentTitle.setText(current.getAssessmentTitle());
            holder.assessmentDueDate.setText(current.getDueDate());

        } else {
            holder.assessmentTitle.setText("No data");
            holder.assessmentDueDate.setText("No data");
        }
    }

    public void setWords(List<AssessmentEntity> words) {
        assessments = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(assessments != null) {
            return assessments.size();
        } else {
            return 0;
        }
    }
}

