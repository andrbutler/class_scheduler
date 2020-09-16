package com.example.andrew_butler_c196;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.andrew_butler_c196.Entities.AssessmentEntity;
import com.example.andrew_butler_c196.Entities.CourseEntity;
import com.example.andrew_butler_c196.UI.AssessmentAdapter;
import com.example.andrew_butler_c196.ViewModel.AssessmentViewModel;
import com.example.andrew_butler_c196.ViewModel.CourseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CourseActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private CourseViewModel courseViewModel;
    private AssessmentViewModel assessmentViewModel;
    private EditText inputName;
    private EditText inputStart;
    private EditText inputEnd;
    private EditText inputMentorName;
    private EditText inputPhone;
    private EditText inputEmail;
    private TextView status;
    private int position;
    private int termId;
    final Calendar myCalendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        termId = getIntent().getIntExtra("termId", 0);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        inputName = findViewById(R.id.InputCourseName);
        inputStart = findViewById(R.id.InputCourseStart);
        inputEnd = findViewById(R.id.InputCourseEnd);
        inputMentorName = findViewById(R.id.InputMentorName);
        inputPhone = findViewById(R.id.InputPhone);
        inputEmail = findViewById(R.id.InputEmail);
        status = findViewById(R.id.Status);
        inputStart.setText(dateFormat.format(myCalendar.getTime()));
        inputEnd.setText(dateFormat.format(myCalendar.getTime()));

        final Button notesButton = findViewById(R.id.NoteButton);
        notesButton.setEnabled(false);
        if (getIntent().getIntExtra("courseId", 0) != 0) {
            inputName.setText(getIntent().getStringExtra("courseName"));
            inputStart.setText(getIntent().getStringExtra("courseStart"));
            inputEnd.setText(getIntent().getStringExtra("courseEnd"));
            inputMentorName.setText(getIntent().getStringExtra("mentorName"));
            inputPhone.setText(getIntent().getStringExtra("mentorPhone"));
            inputEmail.setText(getIntent().getStringExtra("mentorEmail"));
            status.setText(getIntent().getStringExtra("courseStatus"));
            int courseId = getIntent().getIntExtra("courseId", 0);


            notesButton.setEnabled(true);
            notesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View noteView) {
                    Intent noteIntent = new Intent(CourseActivity.this, NoteActivity.class);
                    noteIntent.putExtra("courseId", courseId);
                    startActivityForResult(noteIntent, NEW_WORD_ACTIVITY_REQUEST_CODE);
                }
            });

            FloatingActionButton assessmentFab = new FloatingActionButton(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(32, 32, 32, 32);
            assessmentFab.setLayoutParams(layoutParams);
            assessmentFab.setImageResource(R.drawable.ic_add_black_24dp);
            LinearLayout linearLayout = findViewById(R.id.CourseButtonContainer);
            if (linearLayout != null) {
                linearLayout.addView(assessmentFab);
                assessmentFab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View assessmentView) {
                        Intent assessmentIntent = new Intent(CourseActivity.this, AssessmentActivity.class);
                        assessmentIntent.putExtra("courseId", courseId);
                        startActivityForResult(assessmentIntent, NEW_WORD_ACTIVITY_REQUEST_CODE);
                    }
                });
            }


        }


        RecyclerView recyclerView = findViewById(R.id.AssessmentList);
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        assessmentViewModel.getAllAssessments().observe(this, new Observer<List<AssessmentEntity>>() {
            @Override
            public void onChanged(@Nullable final List<AssessmentEntity> words) {
                List<AssessmentEntity> filteredWords = new ArrayList<>();
                for (AssessmentEntity a : words) {
                    if (a.getCourseId() == getIntent().getIntExtra("courseId", 0)) {
                        filteredWords.add(a);
                    }
                    adapter.setWords(filteredWords);
                }
            }
        });

        final Button button = findViewById(R.id.SaveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                String name = inputName.getText().toString();
                String start = inputStart.getText().toString();
                String end = inputEnd.getText().toString();
                String mentorName = inputMentorName.getText().toString();
                String phone = inputPhone.getText().toString();
                String email = inputEmail.getText().toString();
                String courseStatus = status.getText().toString();
                int id = getIntent().getIntExtra("courseId", 0);
                replyIntent.putExtra("courseName", name);
                replyIntent.putExtra("courseStart", start);
                replyIntent.putExtra("courseEnd", end);
                CourseEntity course = new CourseEntity(termId, name, start, end, mentorName, email,
                        phone, courseStatus);

                if (getIntent().getIntExtra("courseId", 0) != 0) {
                    course.setCourseId(id);
                    course.setCourseStatus(status.getText().toString());
                } else {
                    course.setCourseStatus("Plan to Enroll");
                }
                courseViewModel.insert(course);

                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });


        DatePickerDialog.OnDateSetListener startDateDialog = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                inputStart.setText(dateFormat.format(myCalendar.getTime()));

            }

        };


        inputStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CourseActivity.this, startDateDialog, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener endDateDialog = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                inputEnd.setText(dateFormat.format(myCalendar.getTime()));

            }

        };


        inputEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CourseActivity.this, endDateDialog, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        if (status.getText().toString().equals("Plan to Enroll")) {
            getMenuInflater().inflate(R.menu.menu_with_notification_and_status_update, menu);
        } else if (status.getText().toString().equals("In Progress")) {
            getMenuInflater().inflate(R.menu.menu_with_notification_and_status_update_after_enroll, menu);
        }/* else {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }*/
        return true;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Delete) {
            courseViewModel.getAllCourses().observe(this, new Observer<List<CourseEntity>>() {
                @Override
                public void onChanged(@Nullable final List<CourseEntity> words) {
                    // Update the cached copy of the words in the adapter.
                    for (CourseEntity c : words) {
                        if (c.getCourseId() == getIntent().getIntExtra("courseId", 0)) {
                            courseViewModel.delete(c);
                            Intent intent = new Intent(CourseActivity.this, MainActivity.class);
                            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
                        }
                    }
                }
            });

        }
        if (id == R.id.UpdateStatus){
            if (status.getText().toString().equals("Plan to Enroll")) {
                status.setText("In Progress");
            }
        }

        if (id == R.id.DropCourse) {
            if (status.getText().toString().equals("In Progress")) {
                status.setText("Dropped");
            }
        }

        if (id == R.id.CompleteCourse) {
            if (status.getText().toString().equals("In Progress")) {
                status.setText("Completed!");
            }
        }

        if (id == R.id.NotifyStart) {

            Date startDateForAlert = null;
            long startDateTimeInMilli;
            try {
                startDateForAlert = dateFormat.parse(getIntent().getStringExtra("courseStart"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDateForAlert);
            startDateTimeInMilli = cal.getTimeInMillis();



            Intent intent = new Intent(CourseActivity.this, MyReceiver.class);
            intent.putExtra("notificationContent","Course: " +
                    getIntent().getStringExtra("courseName") + " begins on: " +
                    getIntent().getStringExtra("courseStart") + "!");
            intent.putExtra("notificationTitle","You have a course beginning Today!");
            int uniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
            PendingIntent sender= PendingIntent.getBroadcast(CourseActivity.this, uniqueId ,intent,0);
            AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, startDateTimeInMilli, sender);
            return true;
        }

        if (id == R.id.NotifyEnd) {

            Date endDateForAlert = null;
            long startDateTimeInMilli;
            long endDateTimeInMilli;
            try {
                endDateForAlert = dateFormat.parse(getIntent().getStringExtra("courseEnd"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDateForAlert);
            endDateTimeInMilli = cal.getTimeInMillis();


            Intent intent = new Intent(CourseActivity.this, MyReceiver.class);
            intent.putExtra("notificationContent","Course: " +
                    getIntent().getStringExtra("courseName") + " ends on: " +
                    getIntent().getStringExtra("courseEnd") + "!");
            intent.putExtra("notificationTitle","You have a course ending Today!");
            int uniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
            PendingIntent sender= PendingIntent.getBroadcast(CourseActivity.this, uniqueId ,intent,0);
            AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, endDateTimeInMilli, sender);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            AssessmentEntity assessment = new AssessmentEntity(
                    data.getIntExtra("courseId", 0),
                    data.getStringExtra("assessmentName"),
                    data.getStringExtra("assessmentDueDate"),
                    data.getStringExtra("assessmentType"),
            data.getStringExtra("expectedCompletionDate"),
                    data.getStringExtra("assessmentDueDate"));
        }
    }
}