package com.example.andrew_butler_c196.UI;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andrew_butler_c196.CourseActivity;
import com.example.andrew_butler_c196.Entities.CourseEntity;
import com.example.andrew_butler_c196.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{

class CourseViewHolder extends RecyclerView.ViewHolder {
    private final TextView courseTitle;
    private final TextView courseStart;
    private final TextView courseEnd;
    private final TextView courseStatus;

    private CourseViewHolder(View itemView) {
        super(itemView);
        courseTitle = itemView.findViewById(R.id.CourseTitle);
        courseStart = itemView.findViewById(R.id.CourseStart);
        courseEnd = itemView.findViewById(R.id.CourseEnd);
        courseStatus = itemView.findViewById(R.id.CourseStatus);
        itemView.setOnClickListener(v ->  {
            int position = getAdapterPosition();
            final CourseEntity current = courses.get(position);
            Intent intent = new Intent(context, CourseActivity.class);
            intent.putExtra("courseName", current.getCourseName());
            intent.putExtra("courseStart", current.getStartDate());
            intent.putExtra("courseEnd", current.getEndDate());
            intent.putExtra("courseStatus", current.getCourseStatus());
            intent.putExtra("position", position);
            intent.putExtra("termId", current.getTermId());
            intent.putExtra("courseId", current.getCourseId());
            intent.putExtra("mentorName", current.getMentorName());
            intent.putExtra("mentorPhone", current.getMentorPhone());
            intent.putExtra("mentorEmail", current.getMentorEmail());
            context.startActivity(intent);
        });

    }
}
    private final LayoutInflater inflater;
    private final Context context;
    private List<CourseEntity> courses;

    public CourseAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = inflater.inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        if (courses != null) {
            final CourseEntity current = courses.get(position);
            int backgroundColor;
            holder.courseTitle.setText(current.getCourseName());
            holder.courseStart.setText(current.getStartDate());
            holder.courseEnd.setText(current.getEndDate());
            holder.courseStatus.setText(current.getCourseStatus());
            if (current.getCourseStatus().equals("In Progress")){
              backgroundColor = ResourcesCompat.getColor(context.getResources(), R.color.listItemInProgress, null);
            } else if (current.getCourseStatus().equals("Dropped")){
                backgroundColor = ResourcesCompat.getColor(context.getResources(), R.color.listItemDropped, null);
            } else if (current.getCourseStatus().equals("Completed!")){
                backgroundColor = ResourcesCompat.getColor(context.getResources(), R.color.listItemComplete, null);
            } else {
                backgroundColor = ResourcesCompat.getColor(context.getResources(), R.color.listItem, null);
            }
            holder.courseTitle.setBackgroundColor(backgroundColor);
            holder.courseStart.setBackgroundColor(backgroundColor);
            holder.courseEnd.setBackgroundColor(backgroundColor);
            holder.courseStatus.setBackgroundColor(backgroundColor);

        } else {
            holder.courseTitle.setText("No data");
            holder.courseStart.setText("No data");
            holder.courseEnd.setText("No data");
            holder.courseStatus.setText("No data");
        }
    }

    public void setWords(List<CourseEntity> words) {
        courses = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(courses != null) {
            return courses.size();
        } else {
            return 0;
        }
    }
}
