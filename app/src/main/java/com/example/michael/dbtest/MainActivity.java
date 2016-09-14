package com.example.michael.dbtest;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.GregorianCalendar;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private CommentsDbHelper dbHelper;
    private Chronometer chronometer;
    private boolean mStarted;


    private Button startStopButton;
    private EditText commentEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mStarted = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        commentEntry = (EditText)findViewById(R.id.comment_entry_id);

        final Button commentButton = (Button) findViewById(R.id.comment_button_id);
        commentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // get timestamp
                long base = chronometer.getBase();
//                GregorianCalendar.getInstance(base);
                Date d = new Date(base * 1000L);
                commentEntry.append(d.getTime() / 1000 / 60 + "\n");

                // enable comment entry field
            }
        });




        // instantiate db helper
        dbHelper = new CommentsDbHelper(getApplicationContext());

        chronometer = (Chronometer) findViewById(R.id.chronometer);
//        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.Base(0);

        startStopButton = ((Button)findViewById(R.id.startstop_button_id));
        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChronometer();
            }
        });
    }

    private void startChronometer() {
        mStarted = !mStarted;

        startStopButton.setText(mStarted ? "Stop" : "Start");
        if(mStarted == true) {
            chronometer.start();
        } else {
            chronometer.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    void addComment(String comment, int timestamp) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        /*
        values.put(CommentsDbHelper.COLUMN_NAME_COMMENT, comment);
        values.put(CommentsDbHelper.COLUMN_NAME_TIMESTAMP, timestamp);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedEntry.TABLE_NAME, null, values);
        */
    }
}
