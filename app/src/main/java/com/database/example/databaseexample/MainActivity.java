package com.database.example.databaseexample;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText efname, elname, epoints;
    DBHelper helper;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);
        res = helper.selectRecords();
        efname = findViewById(R.id.etfname);
        elname = findViewById(R.id.etlname);
        epoints = findViewById(R.id.etpoints);
    }

    public void insertRecord(View v){
        String firstname = efname.getText().toString().trim();
        String lastname = elname.getText().toString().trim();
        int points = Integer.parseInt(epoints.getText().toString());
        boolean isInserted = helper.insert(firstname,lastname,points);
        if(isInserted == true){
            Toast.makeText(this,"Record Saved...", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"Failed Saving Record...", Toast.LENGTH_LONG).show();
        }
    }

    public void moveFirst(View v){
        res.moveToFirst();
        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);
        efname.setText(fname);
        elname.setText(lname);
        epoints.setText(point);
    }

    public void moveLast(View v){
        res.moveToLast();
        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);
        efname.setText(fname);
        elname.setText(lname);
        epoints.setText(point);
    }

    public void movePrevious(View v){
        res.moveToPrevious();
        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);
        efname.setText(fname);
        elname.setText(lname);
        epoints.setText(point);
    }

    public void moveNext(View v){
        res.moveToNext();
        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);
        efname.setText(fname);
        elname.setText(lname);
        epoints.setText(point);
    }

    public void editRecord(View v){
        String fname = efname.getText().toString();
        String lname = elname.getText().toString();
        int points = Integer.parseInt(epoints.getText().toString());
        boolean isUpdated = helper.update(res.getString(0), fname, lname, points);
        if(isUpdated == true){
            Toast.makeText(this,"Updated Successfully...", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"Failed to Update...", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteRecord(View v){
        helper.delete(res.getString(0));
    }
}
