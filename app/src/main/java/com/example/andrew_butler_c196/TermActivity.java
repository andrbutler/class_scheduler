package com.example.andrew_butler_c196;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.andrew_butler_c196.Entities.CourseEntity;
import com.example.andrew_butler_c196.Entities.TermEntity;
import com.example.andrew_butler_c196.UI.CourseAdapter;
import com.example.andrew_butler_c196.ViewModel.CourseViewModel;
import com.example.andrew_butler_c196.ViewModel.TermViewModel;
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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TermActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private TermViewModel termViewModel;
    private CourseViewModel courseViewModel;
    private EditText inputName;
    private EditText inputStart;
    private EditText inputEnd;
    private int position;
    private int numCourses;
    final Calendar myCalendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        inputName = findViewById(R.id.InputTermName);
        inputStart = findViewById(R.id.InputCourseStart);
        inputEnd = findViewById(R.id.InputCourseEnd);
        inputStart.setText(dateFormat.format(myCalendar.getTime()));
        inputEnd.setText(dateFormat.format(myCalendar.getTime()));

        if (getIntent().getIntExtra("termId", 0) != 0) {
            inputName.setText(getIntent().getStringExtra("termName"));
            inputStart.setText(getIntent().getStringExtra("termStart"));
            inputEnd.setText(getIntent().getStringExtra("termEnd"));

            int termId = getIntent().getIntExtra("termId", 0);

            FloatingActionButton fab = new FloatingActionButton(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(32, 32, 32, 32);
            fab.setLayoutParams(layoutParams);
            fab.setImageResource(R.drawable.ic_add_black_24dp);
            LinearLayout linearLayout = findViewById(R.id.ButtonContainer);
            if (linearLayout != null) {
                linearLayout.addView(fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(TermActivity.this, CourseActivity.class);
                        intent.putExtra("termId", termId);
                        startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
                    }
                });
            }

        }

     /*   FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermActivity.this, CourseActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });*/

        RecyclerView recyclerView = findViewById(R.id.CourseList);
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        courseViewModel.getAllCourses().observe(this, new Observer<List<CourseEntity>>() {
            @Override
            public void onChanged(@Nullable final List<CourseEntity> words) {
                List<CourseEntity> filteredWords = new ArrayList<>();
                for (CourseEntity c : words) {
                    if (c.getTermId() == getIntent().getIntExtra("termId", 0)) {
                        filteredWords.add(c);
                    }
                    numCourses = filteredWords.size();
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
                int id = getIntent().getIntExtra("termId", 0);
                replyIntent.putExtra("termName", name);
                replyIntent.putExtra("termStart", start);
                replyIntent.putExtra("termEnd", end);
                TermEntity term = new TermEntity(name, start, end);
                if (getIntent().getIntExtra("termId", 0) != 0) {
                    term.setTermId(id);
                }
                termViewModel.insert(term);

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
                new DatePickerDialog(TermActivity.this, startDateDialog, myCalendar
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
                new DatePickerDialog(TermActivity.this, endDateDialog, myCalendar
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
        if (getIntent().getIntExtra("termId", 0) != 0) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Delete) {
            if (numCourses == 0) {
                termViewModel.getAllTerms().observe(this, new Observer<List<TermEntity>>() {
                    @Override
                    public void onChanged(List<TermEntity> words) {


                        // Update the cached copy of the words in the adapter.

                        for (TermEntity t : words) {
                            if (t.getTermId() == getIntent().getIntExtra("termId", 0)) {
                                termViewModel.delete(t);

                            }
                        }
                        Intent intent = new Intent(TermActivity.this, MainActivity.class);
                        startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Terms containing courses cannot be deleted", Toast.LENGTH_LONG).show();// make another toast
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            CourseEntity course = new CourseEntity(
                    data.getIntExtra("termId", 0),
                    data.getStringExtra("courseName"),
                    data.getStringExtra("courseStart"),
                    data.getStringExtra("courseEnd"),
                    data.getStringExtra("mentorName"),
                    data.getStringExtra("mentorEmail"),
                    data.getStringExtra("mentorPhone"),
                    data.getStringExtra("courseStatus"));
        }
    }
}
