package com.example.andrew_butler_c196;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.andrew_butler_c196.Entities.AssessmentEntity;
import com.example.andrew_butler_c196.ViewModel.AssessmentViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AssessmentActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private AssessmentViewModel assessmentViewModel;
    private EditText inputName;
    private EditText inputType;
    private EditText inputDueDate;
    private int position;
    private int courseId;
    private EditText inputExpectedCompletionDate;
    private EditText inputAssessmentStartDate;
    final Calendar myCalendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);

        courseId = getIntent().getIntExtra("courseId", 0);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        inputName = findViewById(R.id.InputAssessmentName);
        inputType = findViewById(R.id.InputAssessmentType);
        inputDueDate = findViewById(R.id.InputDueDate);
        inputExpectedCompletionDate = findViewById(R.id.InputExpectedCompletionDate);
        inputAssessmentStartDate = findViewById(R.id.InputAssessmentStartDate);
        inputDueDate.setText(dateFormat.format(myCalendar.getTime()));
        inputExpectedCompletionDate.setText(dateFormat.format(myCalendar.getTime()));
        inputAssessmentStartDate.setText(dateFormat.format(myCalendar.getTime()));

        if (getIntent().getIntExtra("assessmentId", 0) != 0) {
            inputName.setText(getIntent().getStringExtra("assessmentName"));
            inputType.setText(getIntent().getStringExtra("assessmentType"));
            inputDueDate.setText(getIntent().getStringExtra("assessmentDueDate"));
            inputExpectedCompletionDate.setText(getIntent().getStringExtra("expectedCompletionDate"));
            inputAssessmentStartDate.setText(getIntent().getStringExtra("assessmentStartDate"));
            int courseId = getIntent().getIntExtra("courseId", 0);

        }


        final Button button = findViewById(R.id.SaveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                String name = inputName.getText().toString();
                String type = inputType.getText().toString();
                String dueDate = inputDueDate.getText().toString();
                String expectedCompletionDate = inputExpectedCompletionDate.getText().toString();
                String startDate = inputAssessmentStartDate.getText().toString();

                int courseId = getIntent().getIntExtra("courseId", 0);
                int id = getIntent().getIntExtra("assessmentId", 0);
                replyIntent.putExtra("assessmentName", name);
                replyIntent.putExtra("assessmentType", type);
                replyIntent.putExtra("assessmentDueDate", dueDate);
                AssessmentEntity assessment = new AssessmentEntity(courseId, type, name, dueDate, expectedCompletionDate, startDate);
                if (getIntent().getIntExtra("assessmentId", 0) != 0) {
                    assessment.setAssessmentId(id);
                }
                assessmentViewModel.insert(assessment);

                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });


        DatePickerDialog.OnDateSetListener dueDateDialog = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                    inputDueDate.setText(dateFormat.format(myCalendar.getTime()));


            }

        };


        inputDueDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AssessmentActivity.this, dueDateDialog, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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


                inputAssessmentStartDate.setText(dateFormat.format(myCalendar.getTime()));


            }

        };


        inputAssessmentStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AssessmentActivity.this, startDateDialog, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener expectedCompletionDateDialog = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                inputExpectedCompletionDate.setText(dateFormat.format(myCalendar.getTime()));


            }

        };


        inputExpectedCompletionDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AssessmentActivity.this, expectedCompletionDateDialog, myCalendar
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
        if (getIntent().getIntExtra("assessmentId", 0) != 0) {
            getMenuInflater().inflate(R.menu.menu_with_notification_assessment, menu);
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
            assessmentViewModel.getAllAssessments().observe(this, new Observer<List<AssessmentEntity>>() {
                @Override
                public void onChanged(@Nullable final List<AssessmentEntity> words) {
                    // Update the cached copy of the words in the adapter.
                    for (AssessmentEntity a : words) {
                        if (a.getAssessmentId() == getIntent().getIntExtra("assessmentId", 0)) {
                            assessmentViewModel.delete(a);

                        }
                    }
                    Intent intent = new Intent(AssessmentActivity.this, MainActivity.class);
                    startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
                }
            });

        }
        if (id == R.id.NotifyDueDate) {

            Date dueDateForAlert = null;
            long dueDateTimeInMilli;
            try {
                dueDateForAlert = dateFormat.parse(getIntent().getStringExtra("assessmentDueDate"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(dueDateForAlert);
            dueDateTimeInMilli = cal.getTimeInMillis();


            Intent intent = new Intent(AssessmentActivity.this, MyReceiver.class);
            intent.putExtra("notificationContent","Assessment: " +
                    getIntent().getStringExtra("assessmentName") + " is Due on: " +
                    getIntent().getStringExtra("assessmentDueDate") + "!");
            intent.putExtra("notificationTitle","Assessment: " +
                    getIntent().getStringExtra("assessmentName") + " is Due!");
            int uniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
            PendingIntent sender= PendingIntent.getBroadcast(AssessmentActivity.this, uniqueId ,intent,0);
            AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, dueDateTimeInMilli, sender);
            return true;
        }
        if (id == R.id.NotifyExpectedCompletionDate) {

            Date completionDateForAlert = null;
            long completionDateTimeInMilli;
            try {
                completionDateForAlert = dateFormat.parse(getIntent().getStringExtra("expectedCompletionDate"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(completionDateForAlert);
            completionDateTimeInMilli = cal.getTimeInMillis();


            Intent intent = new Intent(AssessmentActivity.this, MyReceiver.class);
            intent.putExtra("notificationContent","Assessment: " +
                    getIntent().getStringExtra("assessmentName") + " is expected to be completed on: " +
                    getIntent().getStringExtra("expectedCompletionDate") + "!");
            intent.putExtra("notificationTitle","Assessment: " +
                    getIntent().getStringExtra("assessmentName") + " is expected to be Completed!");
            int uniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
            PendingIntent sender= PendingIntent.getBroadcast(AssessmentActivity.this, uniqueId ,intent,0);
            AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, completionDateTimeInMilli, sender);
            return true;
        }

        if (id == R.id.NotifyAssessmentStartDate) {

            Date startDateForAlert = null;
            long startDateTimeInMilli;
            try {
                startDateForAlert = dateFormat.parse(getIntent().getStringExtra("assessmentStartDate"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDateForAlert);
            startDateTimeInMilli = cal.getTimeInMillis();


            Intent intent = new Intent(AssessmentActivity.this, MyReceiver.class);
            intent.putExtra("notificationContent","Assessment: " +
                    getIntent().getStringExtra("assessmentName") + " starts on: " +
                    getIntent().getStringExtra("assessmentStartDate") + "!");
            intent.putExtra("notificationTitle","Assessment: " +
                    getIntent().getStringExtra("assessmentName") + " starts today!");
            int uniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
            PendingIntent sender= PendingIntent.getBroadcast(AssessmentActivity.this, uniqueId ,intent,0);
            AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, startDateTimeInMilli, sender);
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
                    data.getStringExtra("assessmentStartDate"));
        }
    }
}