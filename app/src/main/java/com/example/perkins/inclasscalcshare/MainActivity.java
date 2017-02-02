package com.example.perkins.inclasscalcshare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText etNumb1;
    private EditText etNumb2;

    private int numb1;
    private int numb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumb1= (EditText)findViewById(R.id.numb1);
        etNumb2= (EditText)findViewById(R.id.numb2);

        loadprefs();
    }

    private void loadprefs() {
        SharedPreferences myPrefs = getSharedPreferences(Constants.FILENAME,MODE_PRIVATE);

        getVals(myPrefs);

        etNumb1.setText(Integer.toString(numb1));
        etNumb2.setText(Integer.toString(numb2));
    }

    private void getVals(SharedPreferences myPrefs) {
        numb1 = myPrefs.getInt(Constants.NUMB1, -1);
        numb2 = myPrefs.getInt(Constants.NUMB2,-1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void doAdd(View view) {

        //get the values of numb1 and numb2
        numb1   = Integer.parseInt(etNumb1.getText().toString().trim());
        numb2   = Integer.parseInt(etNumb2.getText().toString().trim());

        saveprefs(numb1, numb2);

        //create an intent that points to next activity
        Intent myIntent = new Intent(this,AddActivity.class);

        //add numbers to myIntent
        myIntent.putExtra(Constants.NUMB1,numb1);
        myIntent.putExtra(Constants.NUMB2,numb2);

        startActivity(myIntent);
    }


    private void saveprefs(int numb1, int numb2) {
        SharedPreferences myPrefs = getSharedPreferences(Constants.FILENAME,MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myPrefs.edit();

        //save values
        myEditor.putInt(Constants.NUMB1,numb1 );
        myEditor.putInt(Constants.NUMB2,numb2 );

        //commit values
        myEditor.commit();
    }
}
